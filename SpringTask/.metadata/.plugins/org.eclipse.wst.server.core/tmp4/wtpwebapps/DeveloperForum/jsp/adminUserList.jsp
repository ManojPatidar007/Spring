<%--  ------------adminUserList.jsp---------- --%>
<%-- cache-control --%>
<%@include file="must.jsp"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User's Information</title>
<style>
body {
	font-size: 20px;
	color: teal;
	font-family: Calibri;
}

td {
	background-color: #A9A9A9;
	font-size: 15px;
	color: white;
	width: 100px;
	height: 22px;
	text-align: center;
}

.heading {
	font-size: 18px;
	color: white;
	font: bold;
	background-color: grey;
	border: thick;
}
</style>
</head>
<body>
	<center>
		<br /> <br /> <br /> <b>User List </b><br /> <br />



		<table>
			<tr>

				<td class="heading">Email Id</td>
				<td class="heading">First Name</td>
				<td class="heading">Last Name</td>
				<td class="heading">Password</td>
				<td class="heading">Mobile Number</td>
				<td class="heading">UserTypeId</td>
				<td class="heading">User State</td>
				<td class="heading">Edit</td>
				<td class="heading">Delete</td>
			</tr>
			<c:forEach var="reg" items="${regBean}">
				<tr>
					<td>${reg.emailId}</td>
					<td>${reg.firstName}</td>
					<td>${reg.lastName}</td>
					<td>${reg.password}</td>
					<td>${reg.mobileNumber}</td>
					<td>${reg.userTypeId}</td>
					<td>${reg.userState}</td>

					<td><a href="adminEdit?emailId=${reg.emailId}">Edit</a></td>
					<td><a href="deleteUser?emailId=${reg.emailId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<a href="admin">Home Page</a>

	</center>
</body>
</html>
