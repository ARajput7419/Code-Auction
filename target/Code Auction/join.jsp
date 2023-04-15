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
  <link rel="shortcut icon" href="logo.png" type="image/x-icon">
  <link rel="stylesheet" href="style.css">
    <script src="toast.js"></script>
        <link rel="stylesheet" href="toast.css">
  <title>Join A Team</title>
</head>
<body>
<div class="login-wrapper">
  <form action="join" class="form" method="post">
    <img src="av.png" alt="avatar">
    <h2>Registration</h2>
    <div class="input-group">
      <input type="text" name="username" id="userName" required>
      <label for="userName">Name</label>
    </div>
    <div class="input-group">
      <input type="text" name="team" id="loginUser" required>
      <label for="loginUser">Team Name</label>
    </div>
    <div class="input-group">
      <input type="password" name="secret" id="loginPassword" required>
      <label for="loginPassword">Team Code</label>
    </div>
    <input type="submit" value="Join" class="submit-btn">
  </form>

</div>
<c:if test="${message!= null}">
                <div id="snackbar">${message}</div>
                <script>
                myFunction();
                </script>
      </c:if>
</body>
</html>