const WebSocketServer = require('ws').Server;
const { REGISTER, NEW_MESSAGE, REGISTER_SUCCESS } = require('./types');

const wss = new WebSocketServer({port: 8080, path: '/chat'});

const groups = {};
// const clients = [];
let _groupId = 0;

wss.on('connection', (socket) => {
    console.log('New connection!');
    socket.on('message', (data) => {
        const { type, ...rest } = JSON.parse(data);
        switch(type) {
            case REGISTER:
                handleRegister(socket, rest);
                break;
            case NEW_MESSAGE:
                handleNewMessage(socket, rest);
            default:
                console.log('Invalid message type!')
                return;
        }        
    });
})
console.log('Server started, waiting for connections');

const handleRegister = (socket, data) => {
    const { groupName, groupId } = data;
    if (!!groups[groupId]) {
        groups[groupId].clients.push(socket);
        console.log('Registering new client to group ', groupId);
    } else {
        groups[groupId] = {
            name: groupName,
            messages: [],
            clients: [socket]
        };
        console.log('Created new group ', groupId)
    }
    socket.send(JSON.stringify({
        type: REGISTER_SUCCESS,
        groups: Object.keys(groups)
    }));
}

const handleNewMessage = (socket, data) => {
    const { groupId, text } = data;
    if (!!groups[groupId]) {
        groups[groupId].messages.push(text);
        _brodcastMessages(groupId);
        console.log('New message received, broadcasting to clients');
    }
}

const _brodcastMessages = (groupId) => {
    if (!!groups[groupId]) {
        const { clients, messages } = groups[groupId];
        clients.forEach(socket => {
            socket.send(JSON.stringify({
                type: NEW_MESSAGE,
                groupId,
                messages
            }))
        });
    }
}