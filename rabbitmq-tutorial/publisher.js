#!/usr/bin/env node

const amqp = require('amqplib/callback_api');
const msg = process.argv.slice(2).join('') || 'Hello world';


amqp.connect('amqp://localhost', (err, conn) => {
    conn.createChannel((err, ch) => {
        const ex = '_logs';
        ch.assertExchange(ex, 'fanout', {durable: false});
        
        // ch.assertQueue('', {exclusive: true});   

        // ch.sendToQueue(q,Buffer.from(msg, 'utf8'), {persistent: true});
        ch.publish(ex, '', Buffer.from(msg));
        console.log('New message was sent!');
    });
    setTimeout(() => { conn.close(); process.exit(0) }, 500);
});