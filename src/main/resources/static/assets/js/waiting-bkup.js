var stompClient = null;

function connect() {

    console.log("Room num: " + roomnum);
    var socket = new SockJS('/joingame');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/joinroom/' + roomnum, function (greeting) {
            const obj = JSON.parse(greeting.body);
            console.log(obj);

        });
          console.log("before sendJoinRequest");
            sendJoinRequest();
    });

}

function sendJoinRequest() {
    console.log("send joinrequest");
    stompClient.send("/app/join/" + roomnum, {}, JSON.stringify({'messageType': 'joinrequest'}));
}

//function showGreeting(message) {
//console.log("Check this" + message);
//    $("#greetings").append("<tr><td>" + "card " + message.playedCardName + " id " + message.playedCardId + " turns " + message.numTurnsPlayed +"</td></tr>");
//}

