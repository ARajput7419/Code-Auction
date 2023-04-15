<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%
String temp = request.getParameter("message");
if( temp != null) request.setAttribute("message",temp);
%>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Page</title>
    <link rel="stylesheet" href="admin_styles.css" />
    <link rel="stylesheet" href="neon2.css" />
    <link rel="shortcut icon" href="logo.png" type="image/x-icon">
      <script src="toast.js"></script>

          <link rel="stylesheet" href="toast.css">
  </head>
  <body>
    <header>
      <h1>Admin Page</h1>
      <nav>
        <ul>
          <li><a href="admin.jsp">Admin</a></li>
          <li><a href="admin_logout">Logout</a></li>
        </ul>
      </nav>
    </header>
    <div class="login-wrapper">
  
        <h2>Dashboard</h2>

         
          <br><br>

          <table border="1" width="100%" align="center" valign="middle">
            <caption><h1>Teams Data:<h1></caption>
            <thead>
              <tr>
                <th>Team Name</th>
                <th>Question purchased</th>
                <th>Remaining coins</th>
              </tr>
            </thead>
            <tbody>


              <c:forEach var="team" items="${requestScope.results}">

              <tr>
                <td>${team.name}</td>
                <td>${team.done}</td>
                <td>${team.points}</td>
              </tr>

              </c:forEach>

            </tbody>
          </table>
          

          <br><br>

        <form action="bid" method="post">
       <div class="input-group">
        <label for="qno">Question number:</label>
        <input type="number" id="qno" name="id" >
        
        </div>
          <br><br>
 
          <br><br>
          <label for="tname">Team name:</label>

          <select name="team" id="teamName">

            <c:forEach var="team" items="${requestScope.results}">
            <option value="${team.name}">${team.name}</option>
            </c:forEach>

          </select>

          <br><br>
 
          <br><br>
          <label for="purchasedAmount">Purchased Amount</label>
                   
          <input type="number" id="purchasedAmount" name="amount" >
          <br><br>
 
          <input type="submit" value="Refresh">
        </form>
   
    </div>
    </main>
    <footer>
      <p>&copy; 2023 - Admin Page</p>
    </footer>

    <c:if test="${message!= null}">
                    <div id="snackbar">${message}</div>
                    <script>
                    myFunction();
                    </script>
          </c:if>

  </body>
</html>
