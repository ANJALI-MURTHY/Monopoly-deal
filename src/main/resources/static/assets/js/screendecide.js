async function checkGame() {
    console.log("checkGame");
    let response = await fetch('https://localhost:8443/existingGame/');
    const myJson = await response.json(); //extract JSON from the http response
    console.log(myJson);
}

var gameId = checkGame();

if(gameId > 0) {
    window.location.replace("https://localhost:8443/gamescreen/"+gameId);
}
else {
        window.location.replace("https://localhost:8443/welcome");
}