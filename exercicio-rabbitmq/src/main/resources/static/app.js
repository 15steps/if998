var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#orders").show();
    }
    else {
        $("#orders").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/websocket-orders-exercise');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.send("/orders", {}, {});
        stompClient.subscribe('/topic/orders', function (message) {
            console.log('new order received!')
            showOrder(JSON.parse(message.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendOrder() {
    var customerName = $('#name').val();
    var restaurant = $('#restaurant').val();
    var items = $('#items').val()
    stompClient.send("/order", {}, JSON.stringify({
        customer: customerName,
        restaurant: restaurant,
        items: items.split(',')
    }));
}

function showOrder(orders) {
    console.log("orders ", orders)
    $('.orderRow').remove();
    orders.forEach(function (order) {
        $("#orders").append(
            "<tr class='orderRow'><td>" + order.customer + "</td>" +
            "<td>" + order.restaurant || '' + "</td>" +
            "<td>" + JSON.stringify(order.items) + "</td></tr>");
    })
}

function processOrder() {
    console.log("processing next order")
    stompClient.send("/process", {}, {})
}

$(document).ready(function() {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendOrder(); });
    $( "#processOrder" ).click(function(e) {
        e.preventDefault();
        processOrder();
    })
});