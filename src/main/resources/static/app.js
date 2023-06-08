var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/broadcast', function (greeting) {
            console.log("Greeting: " + greeting);
            const bodyParsing = JSON.parse(greeting.body);
            showGreeting(
                "Sender: " + bodyParsing.sender + " - \n" +
                "Type: " + bodyParsing.type + " - \n" +
                "Message: " + bodyParsing.message );
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

function sendMsg() {
    const objectToSend = {
        'clientName' : $("#name").val(),
        'clientAlert' : '\nSimple chat msg',
        'clientMsg' : '\nThis is what I wanted to tell you'
    }
    stompClient.send("/app/client-message", {}, JSON.stringify(objectToSend));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendMsg(); });
});