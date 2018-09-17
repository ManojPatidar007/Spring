<%--  ----------registration.jsp -------- --%>
<%-- Registration Page for developer  forum
where create Account,already have an account
        
        1,after clicking create account,the entered values will be bind to modelAttribute regBean 
        2,Already have An Account is a hyperlink redirects to login page
 --%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title>Registration ::</title>
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
					<form:form name="home" method="post" action="registration"
						modelAttribute="regBean" autocomplete="off">

						<div id="info1">Share,Discuss,Learn</div>
						<br>
						<div id="info2">Get started - it's free.</div>
						<br>

						<div id="info3">Email Id</div>
						<!--An <input> element with type="email" that must be in the following order: characters@characters.domain (characters followed by an @ sign, followed by more characters, and then a "."
                           After the "." sign, you can only write 2 to 3 letters from a to z:-->
						<form:input type="email" path="emailId" name="emailId"
							pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$"
							style="width:350px; height:30px;"
							title="Email  must be in the following order: characters@characters.domain (characters followed by an @ sign, followed by more characters, and then a .
                           After the . sign, you can only write 2 to 3 letters from a to z" />
						<br>
						<br>

						<div id="info3">First Name</div>
						<form:input path="firstName" type="text" name="firstName"
							pattern="[A-Za-z]{1,20}" title="only alphabets MAXLENGTH=20"
							style="width:350px; height:30px;" />
						<br>
						<br>

						<div id="info3">Last Name</div>
						<form:input type="text" path="lastName" name="lastName"
							pattern="[A-Za-z]{1,20}" title="only alphabets MAXLENGTH=20"
							style="width:350px; height:30px;" />
						<br>
						<br>

						<div id="info3">Mobile Number</div>
						<form:input path="mobileNumber" type="text" name="mobileNumber"
							pattern="[0-9]{10}" title="Enter 10 digit mobile number"
							minlength="10" maxlength="10" style="width:350px;height:30px" />
						<br>
						<br>

						<div id="info3">Password (8 or more characters)</div>
						<form:input path="password" type="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" id="txtPassword"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							style="width:350px;height:30px;" />
						<br>
						<br>

						<div id="info3">Confirm Password (8 or more characters)</div>
						<form:input path="" type="password"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
							id="txtConfirmPassword"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
							style="width:350px;height:30px;" />

						<br>
						<br>

						<form:input class="button2" path="" type="submit"
							value="Create account" onclick="return Validate()"></form:input>
						<br>
						<br>

						<a href="login">Already Have an Account?</a>
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
    	  var x = document.forms["home"]["emailId"].value;
   	   var y = document.forms["home"]["password"].value;
   	   if ( x == null || x == "" ) {
   	      alert("Email  Id must be filled out");
   	      return false;
   	   }
   	   if ( y == null || y == "" ) {
   	      alert("Password must be filled out");
   	      return false;
   	     
   	   }

        var password = document.getElementById("txtPassword").value;
        var confirmPassword = document.getElementById("txtConfirmPassword").value;
        if (password != confirmPassword) {
            alert("Passwords do not match.");
            return false;
        }
        return true;
    }

</script>
</body>
</html>