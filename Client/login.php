<!DOCTYPE html>
<html>
  <head>
    <link rel="stylesheet" href="css/form.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="js/login.js"></script>
    <meta charset="utf-8">
    <title>Login Form</title>

  </head>
  <body>
    <div class="container">
      <h1>Login</h1>
      <p>Log-in or if you haven't registered yet, create an account down below</p>
      <form onsubmit="loginSubmit()" action="#">
        <input type="text" placeholder="Username" class="field" id="user">
        <input type="password" placeholder="Password" class="field" id="pwd">
        <input type="submit" value="Log in" class="btn">
      </form>

      <h2>OR</h2>

      <h1>Create an Account</h1>
      <form onsubmit="createSubmit()" action="#">
        <input type="text" placeholder="Username" class="field" id="user-reg">
        <input type="password" placeholder="Password" class="field" id="pwd-reg">

        <input type="radio" name="accessType" value="STD" checked> Standard<br>
        <input type="radio" name="accessType" value="MNGR"> League Manager<br>
        
        <input type="submit" value="Create Account" class="btn">
      </form>
    </div>
  </body>
</html>
