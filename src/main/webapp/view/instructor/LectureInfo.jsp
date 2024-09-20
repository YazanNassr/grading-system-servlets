<%@ page language="java"
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<%@ page import="java.util.List, domain.Lecture, domain.Grade" %>

<%
	String id = (String) session.getAttribute("userId");
	String type = (String) session.getAttribute("userType");
	if (id == null || type == null || !type.equals("instructor")) {
		response.sendRedirect("/login");
	}
%>

<%
	Lecture lecture = (Lecture) request.getAttribute("lecture");
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
	<h1 class="title">Grading System: Lecture Info</h1>

	<h2>General Info</h2>
	<table class="table">
		<thead>
		<tr>
			<th scope="col">Course Name</th>
			<th scope="col">Course ID</th>
			<th scope="col">Lecture ID</th>
			<th scope="col">Instructor Name</th>
			<th scope="col">Instructor ID</th>
		</tr>
		</thead>

		<tbody>
		<tr>
			<td><%= lecture.course().name() %></td>
			<td><%= lecture.course().id() %></td>
			<td><%= lecture.id() %></td>
			<td><%= lecture.instructor().name() %></td>
			<td><%= lecture.instructor().id() %></td>
		</tr>
		</tbody>
	</table>

	<h2>Students</h2>

	<a href="/instructor/grades/update-form"><h3>Update Student Grades Form</h3></a>

	<table class="table">
		<thead>
		<tr>
			<th scope="col">Student Name</th>
			<th scope="col">Student ID</th>
			<th scope="col">Midterm Exam Grade</thj>
			<th scope="col">Final Exam Grade</th>
		</tr>
		</thead>
		<tbody>
		<% for (Grade grade : grades) { %>
		<tr>
			<td><%= grade.student().name() %></td>
			<td><%= grade.student().id() %></td>
			<td><%= grade.midtermExam() %></td>
			<td><%= grade.finalExam() %></td>
		</tr>
		<% } %>
		</tbody>
	</table>
</div>
</body>
</html>

