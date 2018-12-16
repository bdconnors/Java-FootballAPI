function authenticate(userId, leagueId, status) {
  var promptWindow = window.open("", "", "width=400,height=250,resizable=0");
  var theHTML = "";

  theHTML +=
    "<p>The server http://localhost:8888 requires a username and password. The server says: These are restricted files, please authenticate yourself.</p>";
  theHTML += "<br/>";
  theHTML +=
    "Password: <input type='password' id='thePass' placeholder='Enter Password...'/>";
  theHTML += "<br />";
  theHTML += "<input type='button' value='OK' id='authOK'/>";
  promptWindow.document.body.innerHTML = theHTML;

  var thePass = promptWindow.document.getElementById("thePass").value;
  promptWindow.document.getElementById("authOK").onclick = function() {
    respondRequest(thePass, userId, leagueId, status);
    promptWindow.close();
  };
}

//function respondRequest(pwd, userId, leagueId, status) {
function respondRequest() {
  console.log("luke respondRequest method started");
  var uName = GetCookie("session");
  //var url_response ="http://serenity.ist.rit.edu:22222/footballapi/User/respondToReq?userName="+uName +"&pass=" +pwd +"&userid=" +userId +"&leagueid=" +leagueId +"&accept=" +status;
  var url_response =
    "http://serenity.ist.rit.edu:22222/footballapi/User/respondToReq?userName=bconnors&pass=Emory2015&userid=2&leagueid=1&accept=true";

  console.log(url_response);
  $.getJSON(
    "http://serenity.ist.rit.edu:22222/footballapi/User/respondToReq?userName=bconnors&pass=Emory2015&userid=2&leagueid=1&accept=true"
  ).done(function(json) {
    console.log("JSON Data: " + json.successfullRequestResponse);

    //checks JSON if successfulLogin == true
    if (json.successfullRequestResponse) {
      //makes session cookie and bind it to user
      alert("You've accepted the trade");
    } else {
      alert("You've denied the trade");
    }
  });
}
