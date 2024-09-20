<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    String id = (String) session.getAttribute("userId");
    String type = (String) session.getAttribute("userType");
    if (id == null || type == null || !type.equals("admin")) {
        response.sendRedirect("/login");
    }
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
            width: 60%;
        }
    </style>
</head>

<body>
<div class="small-middle-container">
    <h1 class="title">Grading System: Deletion Page</h1>

    <h2>Delete a Student</h2>
    <form action="/admin/delete/controller" method="post">
        <input type="hidden" name="entity" value="student">
        <div class="form-group">
            <label>Student ID: <input name="id" required></label>
            <input type="submit">
        </div>
    </form>

    <h2>Delete an Instructor</h2>
    <form action="/admin/delete/controller" method="post">
        <input type="hidden" name="entity" value="instructor">
        <div class="form-group">
            <label>Instructor ID: <input name="id" required></label>
            <input type="submit">
        </div>
    </form>

    <h2>Delete a Course</h2>
    <form action="/admin/delete/controller" method="post">
        <input type="hidden" name="entity" value="course">
        <div class="form-group">
            <label>Course ID: <input name="id" required></label>
            <input type="submit">
        </div>
    </form>

    <h2>Delete a Lecture</h2>
    <form action="/admin/delete/controller" method="post">
        <input type="hidden" name="entity" value="lecture">
        <div class="form-group">
            <label>Lecture ID: <input name="id" required></label>
            <input type="submit">
        </div>
    </form>

</div>
</body>
</html>

