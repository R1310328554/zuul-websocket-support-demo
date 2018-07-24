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

function getSessionId () {
    return "fffffff"
}
var headers = {
    login: 'mylogin',
    passcode: 'mypasscode',
    token: 'accountIdddddd',
    // additional header
    'client-id': 'my-client-id'
};

var uu = {token: username}

function connect() {
    var socket = new SockJS('/epld-websocket?token=tttttttttttttt', null, {
        'protocols_whitelist': ['websockettt'],
        'sessionId': getSessionId
    });
    stompClient = Stomp.over(socket);
    stompClient.connect(headers, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings?token=tttttttttttttt', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });

        stompClient.subscribe('/topic/time?token=tttttttttttttt', function (greeting) {
            showTime(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function showGreeting(message) {
    console.log(message);
    $("#greetings").html("<tr><td>" + message + "</td></tr>");
}

function showTime(time) {
    console.log(time);
    $("#time").text(time);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $("#connect").click(function () {
        connect();
    });
    $("#disconnect").click(function () {
        disconnect();
    });
    $("#send").click(function () {
        sendName();
    });

    //connect();
});

