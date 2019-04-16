#!/usr/bin/env node

const amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', (err, conn) => {
    conn.createChannel((err, ch) => {
        const q = 'durable_task_queue';
        
        ch.assertQueue(q, {durable: false});

        ch.prefetch(1);

        ch.consume(q, msg => {
            const complexity = msg.content.toString().split('.').length - 1;
            console.log('Message received: ', msg.content.toString());
            setTimeout(() => {
                console.log('Done.')
                ch.ack(msg);
            }, complexity * 1000);
        });
    });
});