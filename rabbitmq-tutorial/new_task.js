#!/usr/bin/env node

const amqp = require('amqplib/callback_api');
const msg = process.argv.slice(2).join('') || 'Hello world';


amqp.connect('amqp://localhost', (err, conn) => {
    conn.createChannel((err, ch) => {
        const q = 'durable_task_queue';
        
        ch.assertQueue(q, {durable: false});

        ch.sendToQueue(q,Buffer.from(msg, 'utf8'), {persistent: true});
        console.log('New message was sent!');
    });
    setTimeout(() => { conn.close(); process.exit(0) }, 500);
});
