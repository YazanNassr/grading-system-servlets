<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.List, domain.Grade" %>


<%
    String id = (String) session.getAttribute("userId");
    String type = (String) session.getAttribute("userType");
    if (id == null || type == null || !type.equals("student")) {
        response.sendRedirect("/login");
    }

    String studentName = (String) request.getAttribute("name");

    List<Grade> grades = (List<Grade>) request.getAttribute("grades");
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
    <h1 class="title">Grading System: Home Page</h1>

    <h2>Hello <%= studentName %></h2>

    <h3>Your Grades</h3>

    <table class="table">
        <thead>
        <tr>
            <th scope="col">Course Name</th>
            <th scope="col">Midterm Exam Grade</th>
            <th scope="col">Final Exam Grade</th>
        </tr>
        </thead>

        <tbody>
        <% for (Grade grade : grades) { %>
        <tr>
            <td><%= grade.lecture().course().name() %></td>
            <td><%= grade.midtermExam() %></td>
            <td><%= grade.finalExam() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

</body>
</html>

