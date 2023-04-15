
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
    <link rel="shortcut icon" href="logo.png" type="image/x-icon">
    <link rel="stylesheet" href="dev_style.css">
    <script src="toast.js"></script>
        <link rel="stylesheet" href="toast.css">
    <title>Code Auction</title>
</head>
<body>
    <div class="wrapper">

        <header>
            <nav>
                <div class="logo">
                    <img height="100px" src="sankalan.png" alt="AUCTION_Logo">
                    <span class="logo_text">ankalan</span>
                </div>
                <div class="nav_item">
                    <a class="admin" href="admin.jsp">Admin</a>
                </div>

            </nav>
        </header>

        <main>
            <div class="hero-section">
                <div class="image-section" >
                    <img  class="main-image" src="hero_image.jpg" alt="auction">
                </div>
                <div class="hero-text">
                    <h1>CODE AUCTION ...</h1>
                    <p>
                        "Get ready for the ultimate coding showdown with #CodeAuction! Bid on varying levels of code to acquire the best one for your teammate to develop, but watch out - with limited coins and a surprise twist, every move counts. Will you outsmart your opponents and come out on top? Join the craze and find out now! #CodeAuction #CodingChallenge #Teamwork"
                    </p>
                    <div class="team-btn">
                        <a href="join.jsp" style="text-decoration: none;color:black;"><button class="btn join-team">Join team</button></a>
                        <a href="registration.jsp" style="text-decoration: none;color:black;"><button class="btn create-team">Create team</button></a>
                        <a href="problems" style="text-decoration: none;color:black;"><button class="btn create-team">Log in</button></a>
                    </div>
                </div>
            </div>
        </main>


        <footer>
                    <hr>
                    <span>Contact Us</span>
                    <a class="mail" href="mailto:aniket.mca21@cs.du.ac.in">Mail us</a>
                    <a class="phone" href="tel:+7419249457">Phone</a>
                </footer>


    </div>
    <c:if test="${message!= null}">
                        <div id="snackbar">${message}</div>
                        <script>
                        myFunction();
                        </script>
              </c:if>


</body>

</html>