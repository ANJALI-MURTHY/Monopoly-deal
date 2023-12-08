var current_url = window.location.href
var id = /[^/]*$/.exec(current_url)[0];

console.log("id " + id)


async function getCards() {

    console.log("getCards");
    let response = await fetch('https://localhost:8443/getcards/'+id);
    const myJson = await response.json(); //extract JSON from the http response
    console.log(myJson);
}


$(document).ready(function(){
    getCards();
});