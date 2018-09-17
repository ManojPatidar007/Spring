<%@include file="must.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<title>Forums Page with Login</title>
<head>
<style type="text/css">
body {
	background: linear-gradient(to bottom right, white, #bcbdc4);
	height: 750px;
}

.headerx {
	position: absolute;
	width: 100%;
	height: 70px;
	top: 0px;
	left: 0px;
	color: white;
	z-index: 7;
	font-family: verdana;
	-webkit-box-shadow: 0 3px 8px rgba(0, 0, 0, .25);
}

.header {
	position: absolute;
	width: 100%;
	height: 70px;
	background: #333333;
	top: 0px;
	left: 0px;
	color: white;
	z-index: 7;
	font-family: verdana;
}

#logo {
	height: 80;
	width: 100;
	left: 36%;
}

.main {
	position: absolute;
	width: 100%;
	height: 700px;
	background-image: url("background.png");
	background-repeat: no-repeat;
	background-size: cover;
	top: 70px;
	left: 0px;
	color: white;
	z-index: 20;
	font-family: verdana;
}

.box {
	margin-top: 20px;
	height: 480px;
	width: 350px;
	background: #efefef;
	padding-top: 15px;
	padding-left: 28px;
	padding-right: 28px;
	padding-bottom: 25px;
}

#info1 {
	text-align: center;
	color: black;
	font-family: arial;
	font-size: 20px;
}

#info2 {
	text-align: center;
	color: black;
	font-family: arial;
	font-size: 16px;
}

#info3 {
	text-align: left;
	color: #888889;
	font-family: arial;
	font-size: 12px;
}

#info4 {
	text-align: center;
	color: #888889;
	font-family: arial;
	font-size: 12px;
}

.button2 {
	width: 350px;
	height: 35px;
	top: 625px;
	background: #f1c218;
	font-family: verdana;
	font-size: 16px;
	font-weight: bold;
	color: black;
	border-radius: 5px 5px 5px 5px;
	border-width: 1px;
	border-style: solid;
	border-color: gray;
	cursor: pointer;
	outline: none;
}

.sub-footer {
	position: absolute;
	width: 100%;
	height: 20px;
	background: #474644;
	top: 500px;
	left: 0px;
	color: white;
	z-index: 20;
	font-family: verdana;
}

.box2 {
	color: white;
	height: 100px;
	width: 848px;
	background: #474644;
	padding-top: 15px;
	padding-left: 10px;
	padding-right: 10px;
	padding-bottom: 25px;
}

#submit2 {
	background: white;
	color: black;
	z-index: 20;
	height: 23px;
	width: 60px;
	cursor: pointer;
}

.footer {
	position: absolute;
	width: 100%;
	height: 80px;
	background: #000;
	top: 650px;
	left: 0px;
	color: white;
	z-index: 20;
	font-family: verdana;
}

.box3 {
	color: white;
	height: 50px;
	width: 1000px;
	padding-top: 10px;
	padding-left: 10px;
	padding-right: 10px;
	padding-bottom: 20px;
}

.box3 li {
	float: left;
	margin-right: 20px;
	font-size: 12px;
}
</style>
</head>
<body>

	<div class="headerx"></div>
	<div class="header">
		<div align="center" class="header">
			<h1>Developer's Forum</h1>
		</div>

		<div class="main">
			<center>
				<div class="box">
					<form:form name="home" method="post" action="changePassword"
						modelAttribute="regBean">

						<div id="info1">Share,Discuss,Learn</div>
						<br>

						<h1>${loggedInUser}</h1>
						<br>
						<br>
						<div id="info3">Enter Old Password</div>
						<input path="password" type="password" id="txtOldPassword"
							name="password" style="width: 350px; height: 30px" />
						<br>
						<br>
						<div id="info3">Enter Password</div>
						<input path="firstName" type="password" id="txtPassword"
							name="firstName" style="width: 350px; height: 30px" />
						<br>
						<br>
						<div id="info3">Please ReEnter Password</div>
						<input path="" type="password" id="txtConfirmPassword"
							name="confirmpassword" style="width: 350px; height: 30px" />
						<br>
						<br>
						<br>
						<br>
						<input class="button2" path="" type="submit" value="Change"
							onclick="return Validate()" />
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

		<!--   Validate()  will be called if two passwords doesn't match 
   using javscript -->
		<script type="text/javascript">
    function Validate() {
        var newpassword = document.getElementById("txtPassword").value;
        var confirmPassword = document.getElementById("txtConfirmPassword").value;
        if ( newpassword == null ||newpassword == "" ) {
     	      alert("New password  must be filled out");
     	      return false;
     	   }
     	   if (confirmPassword== null || confirmPassword == "" ) {
     	      alert("Please ReEnterPassword must  To reconfirm New password");
     	      return false;
     	     
     	   }
        if (newpassword != confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }
</script>
</body>
</html>