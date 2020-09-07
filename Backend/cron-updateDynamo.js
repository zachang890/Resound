var XMLHttpRequest = require("xmlhttprequest").XMLHttpRequest;
let request = new XMLHttpRequest();
request.open("PUT", "http://dynamo-access.eba-24mqhk9g.us-west-2.elasticbeanstalk.com/dynamo/revisit-details/global warming")
request.send();
request.onload = () => {
    console.log(request.response);
    // if (request.status == 200) {
    //     console.log(JSON.parse(request.response));
    // } else {
    //     console.log('rip')
    // }
}