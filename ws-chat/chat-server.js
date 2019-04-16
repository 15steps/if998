const WebSocket = require('ws');
const WebSocketServer = WebSocket.Server;
const { REGISTER, NEW_MESSAGE, REGISTER_SUCCESS } = require('./types');

const wss = new WebSocketServer({port: 8080, path: '/chat'});

const groups = {
    'general': {
        clients: new Set(),
        messages: []
    }
};
let _groupId = 0;

wss.on('connection', (socket) => {
    broadcast({
        type: REGISTER_SUCCESS,
        groups: Object.keys(groups)
    });
    socket.on('message', (data) => {
        console.log(data)
        const { type, ...rest } = JSON.parse(data);
        switch(type) {
            case REGISTER:
                handleRegister(socket, rest);
                break;
            case NEW_MESSAGE:
                handleNewMessage(socket, rest);
                break;
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
        groups[groupId].clients.add(socket);
        console.log('Registering new client to group ', groupId);
    } else {
        groups[groupId] = {
            name: groupName,
            messages: [],
            clients: new Set().add(socket)
        };
        console.log('Created new group ', groupId)
    }
    broadcast({
        type: REGISTER_SUCCESS,
        groups: Object.keys(groups),
    });
    _brodcastMessages(groupId);
}

const broadcast = (data) => {
    wss.clients.forEach(client => {
        if (client.readyState === WebSocket.OPEN) {
            client.send(JSON.stringify(data))
        }
    })
}

const handleNewMessage = (socket, data) => {
    const { groupId, text, username } = data;
    if (!!groups[groupId]) {
        groups[groupId].messages.push({text, username});
        _brodcastMessages(groupId);
        console.log('New message received, broadcasting to clients');
    }
}

const _brodcastMessages = (groupId) => {
    if (!!groups[groupId]) {
        const { clients, messages } = groups[groupId];
        clients.forEach(socket => {
            if (socket.readyState === WebSocket.OPEN) {
                socket.send(JSON.stringify({
                    type: NEW_MESSAGE,
                    groupId,
                    messages
                }))
            }
        });
    }
}