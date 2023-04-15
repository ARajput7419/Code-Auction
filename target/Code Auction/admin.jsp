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
    <link rel="stylesheet" href="admin_style.css">
    <link rel="shortcut icon" href="logo.png" type="image/x-icon">
      <script src="toast.js"></script>

          <link rel="stylesheet" href="toast.css">
  </head>
  <body>
    <header>
      <h1>Admin Page</h1>
      <nav>
        <ul>
          <li><a href="dashboard">Dashboard</a></li>
          <li><a href="admin_logout">Logout</a></li>
        </ul>
      </nav>
    </header>

  <div class="login-wrapper">     
       <form action="upload" method="post" class ="form" enctype="multipart/form-data">
        <h2>Information</h2>
       
       
       
        <div class="input-group">
          <input type="file" id="file" name="question" accept=".txt">
          <label for="file">Select a file to upload:</label>
         
       
        </div>
       
       
       <div class="input-group">            
          <input type="number" id="qno" name="number" >
          <label for="qno">Question number:</label>
        </div>
         
        
       
        <label>Tag:</label>
          <input type="radio" id="easy" name="level" value="Easy">
          <label for="easy">Easy</label>
          
          <input type="radio" id="medium" name="level" value="Medium">
          <label for="medium">Medium</label>
          
          <input type="radio" id="hard" name="level" value="Hard">
          <label for="medium">Hard</label>

          <br><br>
          <button type="submit" class="submit-btn">Upload</button>
        </form>
      </section>
    </div>

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
