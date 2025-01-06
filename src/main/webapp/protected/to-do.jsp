<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 06/01/2025
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>To Do List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">

</head>
<body>

    <div class="container">
        <h1>To-Do Activities</h1><hr />

        <form method="get" action="${pageContext.request.contextPath}/AddActivityServlet">
            <p>Add new activity to the list:</p>
            <input type="text" name="activity">
            <input type="submit" value="Add">
        </form>
        <hr />

        <ol>
            <c:forEach var="activity" items="${listActivities}">
                <li>
                    <span class="activity-text">${activity}</span>
                    <div class="buttons">
                        <form method="post" action="${pageContext.request.contextPath}/RemoveActivityServlet">
                            <input type="hidden" name="remove" value="${activity}">
                            <input type="submit" value="Remove">
                        </form>
                    </div>
                </li>
            </c:forEach>
        </ol>


        <hr />

        <form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
            <input type="submit" class="logout" value="Logout">
        </form>
    </div>


</body>
</html>
