//compares the submitted information to the user database.
//Checks if user is valid, if valid creates a session cookie.
function loginSubmit() {
  var userId = document.getElementById("user").value;
  var pwd = document.getElementById("pwd").value;

  var url =
    "http://serenity.ist.rit.edu:22222/footballapi/User/login?userName=" +
    userId +
    "&password=" +
    pwd;
  console.log(url);
  $.getJSON(url).done(function(json) {
    console.log("JSON Data: " + json.successfullLogin);

    //checks JSON if successfulLogin == true
    if (json.successfullLogin) {
      //makes session cookie and bind it to user
      document.cookie = "session = " + json.userid;
      //redirect to main page
      window.location.href = "index.php";
    } else {
      alert("You entered the wrong username or password. Please try again.");
    }
  });
}

//compares the submitted information to the user database.
//Checks if user is valid, if valid creates a session cookie.
function createSubmit() {
  var userId = document.getElementById("user-reg").value;
  var pwd = document.getElementById("pwd-reg").value;

  var access = $("input[name=accessType]:checked").val();

  console.log(access);

  var url_reg =
    "http://serenity.ist.rit.edu:22222/footballapi/User/create?userName=" +
    userId +
    "&password=" +
    pwd +
    "&accessLevel=" +
    access;
  //console.log(url);
  $.getJSON(url_reg).done(function(json) {
    console.log("JSON Data: " + json.successfullUserCreation);

    //checks JSON if successfulLogin == true
    if (json.successfullUserCreation) {
      alert(
        "You've successfully created an account, go back to the login page and login."
      );
      //redirect to main page
      window.location.href = "index.php";
    } else {
      alert("Your account creation failed. Please try again.");
    }
  });
}

//checkSession() checks for current session running in the
//user's browser.  If session is available, user can go through,
//otherwise redirected to login page.

function checkSession() {
  //check if there is cookie
  var currSession = GetCookie("session");
  if (currSession != null) {
    alert("Welcome again " + currSession);

    var loginBtn = document.getElementById("loginBtn");

    if (loginBtn.classList.contains("invisible")) {
      //don't do anything
    } else {
      hideElem("loginBtn");
      showElem("logoutBtn");
    }
  } else {
    alert("No session running, you can login");

    var logoutDD = document.getElementById("logoutBtn");

    if (logoutDD.classList.contains("invisible")) {
      //don't do anything
    } else {
      hideElem("logoutBtn");
      showElem("loginBtn");
    }
  }
}

//removes the cookie
function logout() {
  DeleteCookie("session");
  location.reload();
}
