#!/usr/bin/env node

const amqp = require('amqplib/callback_api');

amqp.connect('amqp://localhost', (err, conn) => {
    conn.createChannel((err, ch) => {
        const ex = '_logs';
        
        ch.assertExchange(ex, 'fanout', {durable: false});

        ch.assertQueue('', {exclusive: true}, (err, q) => {
            console.log('Waiting for messages.');
            ch.bindQueue(q.queue, ex, '');

            ch.consume(q.queue, (msg) => {
                if (msg.content) {
                    console.log('New message: ', msg.content.toString());
                }
            }, {noAck: true});
        });
        
    });
});