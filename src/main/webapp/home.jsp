<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%
String temp = request.getParameter("message");
if( temp != null) request.setAttribute("message",temp);
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Code-Auction</title>
     <script src="toast.js"></script>
     <link rel="stylesheet" href="toast.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Bruno+Ace&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <link rel="stylesheet" href="ab_style.css">
</head>
<body>
    <section id="header" class="back">

        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="#" class="d-flex align-items-center m-2 mb-lg-0 text-white text-decoration-none">
                <img src="unilogo.png" width="60" height="52" />
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto m-2 justify-content-center mb-md-0">
              <li><a href="#" class="nav-link px-2 text-white">Home</a></li>
              <li><a href="admin.jsp" class="nav-link px-2 text-white">Admin</a></li>
              <li><a href="status.jsp" class="nav-link px-2 text-white">Status</a></li>
            </ul>

            <div class="text-end m-2">
              <a href="problems" style="text-decoration: none;color:black;"><button class="btn btn-warning">Log in</button></a>
            </div>
        </div>

    </section>

    <section id="content" class="back">
        <div class="pt-2 text-center d-flex flex-wrap justify-content-center">
            <div>
              <img src="Sankalan.png" class="img-fluid" alt="Code Auction image" width="100" height="100" loading="lazy">
            </div>
            <div>
                <h1 class="text1">ANKALAN</h1>
                <h4 class="text3">Presents</h4>
            </div>

        </div>
        <div class="pt-3 text-center d-flex flex-wrap justify-content-center">
              <div class="px-5 mb-2">
                <img src="logocode12.png" class="img-fluid" alt="Code Auction image" width="300" height="200" loading="lazy">
              </div>
              <div class="px-5 mt-5">
                <h2 class="text2">Sponsered</h2>
                <h2 class="text2">BY</h2>
              </div>
              <div class="px-5 pt-5">
                <img src="cd.png" class="img-fluid" alt="Example image" width="300" height="200" loading="lazy">
              </div>
        </div>
    </section>

    <section #id="content2" class="back">
        <div class="row flex-lg-row-reverse align-items-center py-4">
            <div class="col-10 col-sm-8 col-lg-6">
              <img src="codeauction.jpg" style="border-radius: 10px;" class="d-block mx-lg-auto img-fluid" alt="Bootstrap Themes" width="300" height="500" loading="lazy">
            </div>
            <div class="col-lg-6">
              <p class="lead mx-5" style="font-family: 'Bruno Ace', cursive; color: antiquewhite;">
                Code Auction is a unique Event that brings together individuals who share a passion for coding, teamwork, and problem solving.<br/>
                The event is designed to test both technical and strategic skills, as bidders must evaluate the challenge and determine how much they are willing to bid based on their teammate's capabilities. Meanwhile, the solvers must work together under pressure to crack the code within a set time limit. So if you're looking for a challenge and want to take your coding skills to the next level, join us at Code Bazaar and bid for success!</p>
              <div class="d-grid gap-2 d-md-flex justify-content-center mx-8 mt-5">

                <a href="join.jsp" style="text-decoration: none;color:black;"><button class="btn btn-warning btn-lg px-4 me-md-2">Join team</button></a>
                <a href="registration.jsp" style="text-decoration: none;color:black;"><button class="btn btn-warning btn-lg">Create team</button></a>

              </div>
            </div>
          </div>
    </section>

    <section #id="footer" class="back">
        <footer class="d-flex flex-wrap justify-content-center align-items-center py-3">
            <div class="col-md-4 d-flex align-items-center">
                <h4 style="color: antiquewhite;">
                Team Co-ordinators:<br/>
                &emsp;Aniket Rajput<br/>
                &emsp;Yash Ruhela<br/>
                &emsp;Dev Aggarwal<br/>
                &emsp;Abhishek Sengar<br/>

                </h4>
            </div>
            <div class="col-md-4 d-flex align-items-center">
                <h5 class="mb-3 ml-4 my-5" style="color: antiquewhite;"> © 2023 Team Code-Auction</h5>
            </div>
        </footer>
    </section>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
      <c:if test="${message!= null}">
                            <div id="snackbar">${message}</div>
                            <script>
                            myFunction();
                            </script>
                  </c:if>
</body>
</html>