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
    <header class="txt-header">
         <h1>Auction Status</h1>
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

    </div>
    </main>

    <c:if test="${message!= null}">
                    <div id="snackbar">${message}</div>
                    <script>
                    myFunction();
                    </script>
          </c:if>
           <script>
                setInterval(function(){
                  location.reload();
                }, 5000);
              </script>

  </body>
</html>
