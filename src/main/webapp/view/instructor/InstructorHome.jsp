<%@ page language="java"
		contentType="text/html; charset=UTF-8"
		pageEncoding="UTF-8"%>

<%@ page import="java.util.List, domain.Lecture" %>

<%
    String id = (String) session.getAttribute("userId");
    String type = (String) session.getAttribute("userType");
    if (id == null || type == null || !type.equals("instructor")) {
        response.sendRedirect("/login");
    }

    String instructorName = (String) request.getAttribute("name");

    List<Lecture> lectures = (List<Lecture>) request.getAttribute("lectures");
%>


<html lang="en">
<head>
    <title>Grading System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style>
        .small-middle-container{
            margin: auto;
            width: 40%;
        }
    </style>

</head>

<body>
<div class="container">
    <h1 class="title">Grading System: Instructors Home Page</h1>

    <h2>Hello <%= instructorName %></h2>

    <h2>Your Classes</h2>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Course Name</th>
            <th scope="col">Lecture ID</th>
        </tr>
        </thead>

        <tbody>
        <% for (Lecture lecture : lectures) { %>
        <tr>
            <td><%= lecture.course().name() %></td>
            <td><a href="/instructor/lectureInfo?id=<%= lecture.id() %>"><%= lecture.id() %></a></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>

