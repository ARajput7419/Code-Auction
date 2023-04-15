<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%
String temp = request.getParameter("message");
if( temp != null) request.setAttribute("message",temp);
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <link rel="stylesheet" href="style.css">
  <link rel="shortcut icon" href="logo.png" type="image/x-icon">
    <script src="toast.js"></script>

        <link rel="stylesheet" href="toast.css">
  <title>Login</title>
</head>
<body>
  <div class="login-wrapper">
    <form action="problems" class="form" method="post">
      <img src="av.png" alt="avatar">
      <h2>Login</h2>
      <div class="input-group">
        <input type="text" name="team" id="loginUser" required>
        <label for="loginUser">Team Name</label>
      </div>
      <div class="input-group">
        <input type="password" name="secret" id="loginPassword" required>
        <label for="loginPassword">Team Code</label>
      </div>
      <input type="submit" value="Login" class="submit-btn">
      <a href="#forgot-pw" class="forgot-pw">Forgot Code?</a>
    </form>


    <div id="forgot-pw">
      <form action="reset" class="form" method="post">
        <a href="#" class="close">&times;</a>
        <h2>Reset Code</h2>
        <div class="input-group">
          <input type="email" name="email" id="email" required>
          <label for="email">Email</label>
        </div>
        <input type="submit" value="Submit" class="submit-btn">
      </form>
    </div>
  </div>

  <c:if test="${message!= null}">
                  <div id="snackbar">${message}</div>
                  <script>
                  myFunction();
                  </script>
        </c:if>

</body>
</html>