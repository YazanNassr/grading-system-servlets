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
<div class="small-middle-container">
    <h1 class="title">Grading System: Enrollment Management</h1>

    <h2>Add a Student</h2>
    <form action="/admin/enrollment/controller" method="post">
        <div class="form-group">
            <label for="sid">Student ID: </label>
            <input id="sid" name="studentId" required>
        </div>

        <div class="form-group">
            <label for="lid">Lecture ID: </label>
            <input id="lid" name="lectureId" required>
        </div>

        <label><input type="radio" name="action" value="enrol" checked>Enrol</label>
        <label><input type="radio" name="action" value="expel">Expel</label><br>
        <button type="submit" value="login" class="btn btn-primary">Create</button>
    </form>

</div>
</body>
</html>

