<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>

<%
    String id = (String) session.getAttribute("userId");
    String type = (String) session.getAttribute("userType");
    if (id == null || type == null || !type.equals("admin")) {
        response.sendRedirect("/login");
    }
%>
<%

    List<String> stdIds = (List<String>) request.getAttribute("studentsIds");
    List<String> instIds = (List<String>) request.getAttribute("instructorsIds");
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
<div class="small-middle-container">
    <h1 class="title">Admin Home Page</h1>

    <h2>Hello Admin@<%= id %></h2>

    <table class="table">
        <thead>
            <th scope="col">Students' IDs</th>
        </thead>
        <tbody>
            <% for (String stdId : stdIds) { %>
            <tr>
                <td><%= stdId %><td>
            </tr>
            <% } %>
        </tbody>
    </table>

    <table class="table">
        <thead>
        <th scope="col">Instructors' IDs</th>
        </thead>
        <tbody>
        <% for (String instId : instIds) { %>
        <tr>
            <td><%= instId %><td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <table class="table">
        <thead>
            <tr>
                <th scope="col">Links</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><a href="/admin/create">Create Entities</a></td>
            </tr>
            <tr>
                <td><a href="/admin/delete">Delete Entities</a></td>
            </tr>
            <tr>
                <td><a href="/admin/enrollment">Enrollment Management</a></td>
            </tr>
        </tbody>
    </table>
</div>
</body>
</html>

