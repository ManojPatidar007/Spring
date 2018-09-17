
<%--  ----------adminEdit.jsp -------- @author kaas0616--%>
<%@include file="must.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Update Profile ::</title>
<link href="<c:url value="/resources/css/registration.css" />"
	rel="stylesheet">
</head>
<body>

	<div class="headerx"></div>
	<div class="header">
		<div align="center" class="header">
			<h1>Developer Forum</h1>
		</div>

		<div class="main">
			<center>
				<div class="box">
					<form:form method="post" action="update" modelAttribute="regBean">


						<div id="info1">Share,Discuss,Learn</div>
						<br>
						<br>

						<div id="info3">First Name</div>
						<form:input path="firstName" value="${regBean.firstName}"
							pattern="[A-Za-z]{1,20}" title="only alphabets  MAXLENGTH=20"
							style="width:350px; height:30px;" />
						<br>
						<br>

						<div id="info3">Last Name</div>
						<td><form:input path="lastName" value="${regBean.lastName}"
								pattern="[A-Za-z]{1,20}" title="only alphabets MAXLENGTH=20 "
								style="width:350px; height:30px;" /> <br> <br>

							<div id="info3">Mobile Number</div> <form:input
								path="mobileNumber" value="${regBean.mobileNumber}"
								pattern="[0-9]{10}" title="Enter 10 digit mobile number"
								minlength="10" maxlength="10" style="width:350px;height:30px" />
							<br> <br>

							<div id="info3">Password</div> <form:input path="password"
								value="${regBean.password}"
								pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" id="txtPassword"
								title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
								style="width:350px;height:30px" /> <br> <br>

							<div id="info3">User Type Id</div> <form:input path="userTypeId"
								value="${regBean.userTypeId}" pattern="[1-2]{1}"
								title="Enter 1 to change role as admin OR 2 for member "
								minlength="1" maxlength="1" style="width:350px;height:30px" />
							<br> <br>

							<div id="info3">User State</div> <form:input path="userState"
								value="${regBean.userState}" pattern="active|inactive"
								title="enter \"active\" for Account Activation OR \"inactive\" for Deactivation "
								style="width:350px;height:30px" /> <br> <br> <form:input
								class="button2" path="" type="submit" value="Update"></form:input>
							<br> <br> <form:hidden path="emailId"
								value="${regBean.emailId}" />
					</form:form>
				</div>
			</center>
		</div>
		<div class="footer">
			<center>
				<div class="box3">
					<p>
						<span style="font-size: 12px;">Thanks For Visiting Our
							Website</span>
					</p>
				</div>
			</center>
		</div>
</body>
</html>