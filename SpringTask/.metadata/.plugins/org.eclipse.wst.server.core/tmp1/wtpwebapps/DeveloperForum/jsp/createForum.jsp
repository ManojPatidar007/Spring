<%@include file="must.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Create Forums</title>
<style>
body {
	margin: 0px;
	border: 0px;
}

#header {
	width: 100%;
	height: 100px;
	background: grey;
	color: black;
	text-align: center;
}

#sidebar {
	width: 180px;
	height: 650px;
	background: #6D7662;
	float: left;
}

#sidebarR {
	width: 250px;
	height: 650px;
	background: #6D7662;
	float: right;
}

#data {
	height: 700px;
	background: #EAFAF1;
}

ul li {
	padding: 20px;
	border-bottom: 2px solid grey;
}

#footer {
	background-color: grey;
	height: 80px;
}

#footer ul li {
	list-style-type: none;
	float: left;
	width: 180px;
	height: 50px;
	text-align: center;
	line-height: 55px;
	font-size: 19px;
}

#footer ul li a {
	text-decoration: none;
	color: black;
	font-color: black;
}

.dropbtn {
	background-color: #6D7662;
	color: white;
	padding: 6px;
	font-size: 15px;
	border: none;
	cursor: pointer;
}

#myInput {
	border-box: box-sizing;
	background-position: 14px 12px;
	background-repeat: no-repeat;
	font-size: 16px;
	padding: 14px 20px 12px 45px;
	border: none;
}

.dropdown {
	position: relative;
	display: inline-block;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #f6f6f6;
	min-width: 230px;
	overflow: auto;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
}

.dropdown-content a {
	color: black;
	padding: 12px 16px;
	text-decoration: none;
	display: block;
}

.dropdown a:hover {
	background-color: #ddd
}

.show {
	display: block;
}

div#box {
	margin-top: 15px;
}

div#box .box-top {
	color: #fff;
	text-shadow: 0 1px #000;
	background: #2980b9;
	padding: 5px;
	padding-left: 15px;
	font-weight: 300;
}

div#box .box-panel {
	padding: 15px;
	background: #fff;
	color: #333;
}
</style>
</head>
<body>
	<div id="header">

		<br>This is admin Panel,Please proceed with Caution !
		<h2 align="left">Welcome ${loggedInUser}</h2>

	</div>




	<div id="sidebar">
		<ul>
			<li>
				<div class="dropdown">
					<button onclick="myFunction()" class="dropbtn"
						style="color: black;">Forums</button>
					<div id="myDropdown" class="dropdown-content">
						<input type="text" placeholder="Search.." id="myInput"
							onkeyup="filterFunction()"> <a href="java">Java</a>
						<c:forEach var="forum" items="${forumBean}">
							<a href="${forum.forumName}">${forum.forumName}</a>
						</c:forEach>
					</div>
				</div>
			</li>
			<li><a href="createForum"
				style="text-decoration: none; color: black;"> Create Forums</a></li>
		</ul>
	</div>
	<div id="sidebarR">
		<ul>
			<a href="getList" style="text-decoration: none; color: black;"><li>User
					Management</li></a>
			<a href="content" style="text-decoration: none; color: black;"><li>Content
					Management</li></a>
			<a href="editProfile?emailId=${loggedInUser}"
				style="text-decoration: none; color: black;"><li>Edit
					Profile</li></a>
			<a href="changePassword" style="text-decoration: none; color: black;"><li>Change
					Password</li></a>
			<a href="logout" style="text-decoration: none; color: black;"><li>LogOut</li></a>



		</ul>
	</div>
	<div id="data">
		<form action="insertForum" modelAtribute="forumBean" method="POST">
			<br> <br>Enter Forum Name <input type="text"
				path="forumName" name="forumName" style="margin: 3px; color: red"></input>
			<input type="submit" path="" name="submit" value="submit"
				style="margin: 3px; color: red"></input>
		</form>

	</div>


	<script>
/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction() {
    document.getElementById("myDropdown").classList.toggle("show");
}

function filterFunction() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown");
    a = div.getElementsByTagName("a");
    for (i = 0; i < a.length; i++) {
        if (a[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            a[i].style.display = "";
        } else {
            a[i].style.display = "none";
        }
    }
}
</script>

</body>
</html>