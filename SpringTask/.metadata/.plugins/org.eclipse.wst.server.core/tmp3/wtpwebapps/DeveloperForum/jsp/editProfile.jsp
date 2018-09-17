<%--  ----------editProfile.jsp -------- --%>

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
					<form:form method="post" action="eupdate" modelAttribute="regBean">


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
							<br> <br> <form:input class="button2" path=""
								type="submit" value="Update" onclick="return Validate()"></form:input>
							<br> <br> <form:hidden path="emailId"
								value="${loggedInUser}" />
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