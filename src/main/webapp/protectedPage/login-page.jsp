<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/loginStyles.css">
</head>
<body>
<div class="container">
    <h1>Enter your credentials!</h1>
    <hr />

    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <p>Username: <input type="text" name="username" /></p>
        <p>Password: <input type="password" name="password" /></p>
        <input type="submit" value="Login">
    </form>



</div>
</body>
</html>
