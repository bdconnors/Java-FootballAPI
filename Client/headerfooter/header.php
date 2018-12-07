<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/master.js"></script>
    <script src="js/login.js"></script>
    <script src="js/cookies.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="css/master.css">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
    <title></title>
  </head>
  <body onload="checkSession()">
    <nav class="navbar sticky-top navbar-expand-lg navbar-dark bg-dark">

      <a class="navbar-brand" href="index.php">
        <img src="img/logo.png" height="45" alt="Fantasy Football Logo">
      </a>

      <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
          <li class="nav-item active">
            <a class="nav-link" href="index.php">Statistics <span class="sr-only">(current)</span></a>
          </li>
          <li class="nav-item">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                My League
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="leagueRoster.php">League Rosters</a>
                <a class="dropdown-item" href="myLeague.php">League Schedule</a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="league.php">If not in league</a>
              </div>
            </li>
          </li>
          <li class="nav-item">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                My Team
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="myTeam-Roster.php">Team Rosters</a>
                <a class="dropdown-item" href="myTeam-Schedule.php">Team Schedule</a>
                <a class="dropdown-item" href="myTeam-Trade.php">Trade</a>
              </div>
            </li>
          </li>
          <li class="nav-item">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                League Manager
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <a class="dropdown-item" href="pendingJoins.php">Pending Joins</a>
                <a class="dropdown-item" href="managerTrade.php">Trades</a>
                <a class="dropdown-item" href="createTeams.php">Create Teams</a>
              </div>
            </li>
          </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
          <button class="btn btn-outline-success my-2 my-sm-0 invisible" id="loginBtn" type="submit"><a id="name-space" href="login.php">Login</a></button>
          <div class="dropdown" id="logoutDropdown">
            <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Dropdown button
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <div id="logoutBtn">
                <a class="dropdown-item" href="#">Logout</a>
              </div>
            </div>
          </div>
        </form>
      </div>
    </nav>

    <div class="banner"></div>
