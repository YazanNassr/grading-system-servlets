<%@ page language="java"
		 contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<html lang="en">
<head>
	<title>Grading System</title>
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
	<h1 class="title">Grading System</h1>

	<form action="/authenticate" method="post">
		<div class="form-group">
			<label for="id">ID: </label><br>
			<input id="id" name="userid" required>
		</div>

		<div class="form-group">
			<label for="password">Password: </label><br>
			<input id="password" type="password" name="password" required>
		</div>

		<label><input type="radio" name="type" value="admin">Admin</label>
		<label><input type="radio" name="type" value="student" checked>Student</label>
		<label><input type="radio" name="type" value="instructor">Instructor</label>
		<br>
		<button type="submit" value="login" class="btn btn-primary">Create</button>
	</form>
</div>

</body>
</html>

