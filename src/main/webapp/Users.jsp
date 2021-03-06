<%@page import="model.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Users Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.2.1.min.js"></script>
		<script src="Components/users.js"></script>
	</head>
	<body> 
		<div class="container">
			<div class="row">
				<div class="col-6"> 
					<h1>User Management</h1>
					<form id="formUserID" name="formUser">
						 User Name: 
						 <input id="UserNameId" name="UserName" type="text" class="form-control form-control-sm">
						 <br> User Email: 
						 <input id="UserEmailId" name="UserEmail" type="text" class="form-control form-control-sm">
						 <br> User Password: 
						 <input id="UserPWDId" name="UserPWD" type="password" class="form-control form-control-sm">
						 <br> User Contact Number: 
						 <input id="UserContactId" name="UserContact" type="text" class="form-control form-control-sm">
						 <br> User Role ID:<i>(Please check user role table to get "Role ID")</i>
						 <input id="UserRoleId" name="UserRole" type="text" class="form-control form-control-sm">
						 <br>
						 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
						 <input type="hidden" id="hidUserIDSave" name="hidItemIDSave" value="">
					</form>
					<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br>
					<div id="divUsersGrid">
						<div class="container">
	  						<h2>User Details</h2>
  					
		    				<%
						 		Users userOBJ = new Users(); 
						  		out.print(userOBJ.readUsers());
						 	%>
    
				    
						</div>
						 
					</div>
	
				</div> 
			</div> 
	
		</div> 
	</body>
</html>