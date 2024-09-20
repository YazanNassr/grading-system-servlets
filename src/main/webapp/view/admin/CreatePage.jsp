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
            width: 40%;
        }
    </style>
</head>

<body>

<div class="container">
    <h1 class="title">Grading System: Creation Page</h1>

    <h2>Create a Student</h2>
    <form action="/admin/create/controller" method="post">
        <input type="hidden" name="entity" value="student">

        <div class="form-group">
            <label for="sid">Student ID: </label>
            <input id="sid" name="id" required>
        </div>

        <div class="form-group">
            <label for="sname">Student Name: </label>
            <input id="sname" name="name" required>
        </div>

        <div class="form-group">
            <label for="spass">Password: </label>
            <input id="spass" type="password" name="password" required>
        </div>

        <button type="submit" value="login" class="btn btn-primary">Create</button>
    </form>

    <h2>Create an Instructor</h2>
    <form action="/admin/create/controller" method="post">
        <input type="hidden" name="entity" value="instructor">

        <div class="form-group">
            <label for="iid">Instructor ID: </label>
            <input id="iid" name="id" required>
        </div>

        <div class="form-group">
            <label for="iname">Instructor Name: </label>
            <input id="iname" name="name" required>
        </div>

        <div class="form-group">
            <label for="ipass">Password: </label>
            <input id="ipass" type="password" name="password" required>
        </div>

        <button type="submit" value="login" class="btn btn-primary">Create</button>
    </form>

    <h2>Create a Course</h2>
    <form action="/admin/create/controller" method="post">
        <input type="hidden" name="entity" value="course">

        <div class="form-group">
            <label for="cid">Course ID: </label>
            <input id="cid" name="id" required>
        </div>

        <div class="form-group">
            <label for="cname">Course Name: </label>
            <input id="cname" name="name" required>
        </div>

        <div class="form-group">
            <label for="cinst">Instructor ID: </label>
            <input id="cinst" name="instructorId" required>
        </div>

        <button type="submit" value="login" class="btn btn-primary">Create</button>
    </form>

    <h2>Create a Lecture</h2>
    <form action="/admin/create/controller" method="post">
        <input type="hidden" name="entity" value="lecture">

        <div class="form-group">
            <label for="lid">Lecture ID: </label>
            <input id="lid" name="id" required>
        </div>

        <div class="form-group">
            <label for="lcid">Course ID: </label>
            <input id="lcid" name="courseId" required>
        </div>

        <div class="form-group">
            <label for="ccid">Coordinator ID: </label>
            <input id="ccid" name="coordinatorId" required>
        </div>

        <button type="submit" value="login" class="btn btn-primary">Create</button>
    </form>
</div>

</body>
</html>

