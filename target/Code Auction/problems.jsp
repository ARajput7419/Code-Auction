<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix ="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <link rel="stylesheet" href="style1.css">
    <script src="toast.js"></script>
        <link rel="stylesheet" href="toast.css">
    <title>Problems</title>
</head>
<body>
    <div class="quiz_wrapper">
        <section class="app">
            <div class="app_header">
                <div class="app_logo">Code Auction</div>
                <div class="info">
                <c:if test="${isSolved}">
                    <div class="isSolved"><i class="fa-solid fa-check"></i> solved </div>
                </c:if>
                    <div class="display_question">Question Solved - <span class="solved_question">${solved}</span></div>
                    <div class="app_team">${team}</div>
                </div>
            </div>
            <div class="question">
                <div class="question_header">
                    <div>Question <span class="current">${current}</span>/<span class="total">${total}</span></div>
                    <div class="tag">${level}</div>
                </div>
                <div class="name">
                    ${statement}
                </div>
                <div class="question_footer">
                    <div class="control">
                        <a href="problems?number=${current-1}" style="text-decoration: none;color:black;"><button class="btn prev">Prev</button></a>
                        <a href="problems?number=${current+1}" style="text-decoration: none;color:black;"><button class="btn next">Next</button></a>
                    </div>
                    <div class="upload">
                    <a href="download_template?number=${current}" style="text-decoration: none;color:black;"><button class="btn">Download Template</button></a>
                    <form action="submit?number=${current}" method="post" enctype="multipart/form-data">
                        <input class="file-upload" type="file" name="file"/>
                        <button type="submit" class="btn submit">Submit</button>
                     </form>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <c:if test="${message!= null}">
                    <div id="snackbar">${message}</div>
                    <script>
                    myFunction();
                    </script>
          </c:if>

</body>
</html>