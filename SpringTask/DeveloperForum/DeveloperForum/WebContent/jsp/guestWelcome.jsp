<%@include file="include.jsp"%>
<%@include file="must.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Guest :: Home</title>

<!-- Bootstrap -->
<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/drop.css" />" rel="stylesheet">

<!-- <link href="css/bootstrap.min.css" rel="stylesheet"> -->

<!-- Custom -->
<link href="<c:url value="/resources/css/custom.css" />"
	rel="stylesheet">
<!--  <link href="css/custom.css" rel="stylesheet"> -->

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
          <![endif]-->

<!-- fonts -->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="font-awesome-4.0.3/css/font-awesome.min.css">

<!-- CSS STYLE-->
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"
	type="text/css" media="screen">
<!-- <link rel="stylesheet" type="text/css" href="css/style.css" media="screen" /> -->

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link href="<c:url value="/resources/css/settings.css" />"
	rel="stylesheet" type="text/css" media="screen">
<!-- <link rel="stylesheet" type="text/css" href="css/settings.css" media="screen" /> -->

</head>
<body>

	<div class="container-fluid">

		<!-- Slider -->

		<!-- //Slider -->
		<div class="tp-banner-container" style="overflow: visible;">
			<div class="tp-banner revslider-initialised tp-simpleresponsive"
				id="revslider-559" style="max-height: 278px; height: 229px;">
				<ul
					style="display: block; overflow: hidden; width: 100%; height: 100%; max-height: none;">
					<!-- SLIDE  -->
					<li data-transition="fade" data-slotamount="7"
						data-masterspeed="1500"
						style="width: 100%; height: 100%; overflow: hidden; visibility: visible; left: 0px; top: 0px; z-index: 20; opacity: 1;">
						<!-- MAIN IMAGE -->
						<div class="slotholder" style="width: 100%; height: 100%;">
							<div class="tp-bgimg defaultimg" data-lazyload="undefined"
								data-bgfit="cover" data-bgposition="left top"
								data-bgrepeat="no-repeat" data-lazydone="undefined"
								data-src="<c:url value= "/resources/images/dev.jpg"/>"
								style="background-color: rgba(0, 0, 0, 0); background-repeat: no-repeat; background-image: url(&quot;<c:url value= "/resources/images/dev.jpg&quot"/>;); background-size: cover; background-position: left top; width: 100%; height: 100%; opacity: 1; position: relative;"></div>
						</div> <!-- LAYERS -->
					</li>
				</ul>
				<div class="tp-loader" style="display: none;"></div>
				<div class="tp-bannertimer" style="visibility: hidden; width: 100%;"></div>
			</div>
		</div>
		<div class="headernav">
			<div class="container">
				<div class="row">
					<div class="col-lg-1 col-xs-3 col-sm-2 col-md-2 logo ">
						<a href="#"><img
							src="<c:url value="/resources/images/logo.jpg" />" alt="" /></a>
					</div>
					<div class="col-lg-3 col-xs-9 col-sm-5 col-md-3 selecttopic">

					</div>
					<div class="col-lg-4 search hidden-xs hidden-sm col-md-3"></div>
					<div class="col-lg-4 col-xs-12 col-sm-5 col-md-4 avt">
						<div class="stnt pull-left">
							<form action="guestWelcome" method="get" class="form">
								<button class="btn btn-primary">Home</button>
							</form>
						</div>
						<div class="env pull-left">
							<i class="fa fa-envelope"></i>
						</div>

						<div class="avatar pull-left dropdown"></div>
						<div class="clearfix"></div>
					</div>


				</div>
			</div>
		</div>




		<section class="content">

			<br> <br>
			
			<div class="container">
				<h4>${Questions}</h4>
				<div class="row">
					<div class="col-lg-8 col-md-8">
						<!-- POST -->
						<c:forEach var="que" items="${queList}">
							<div class="post">

								<div class="wrap-ut pull-left">
									<div class="userinfo pull-left">
										<div class="avatar">
											<h4>${que.userId}</h4>

										</div>

									</div>
									<br> <br>
									<div class="posttext pull-left">
										<h2>
											<a href="guestWelcome2?queryId=${que.queryId}">${que.queryHeading}</a>
										</h2>
										<p>${que.queryDesc}</p>
									</div>
									<div class="clearfix"></div>
								</div>
								<div class="postinfo pull-left">
									<div class="comments">
										<div class="commentbg">
											${que.no_of_answers}
											<div class="mark"></div>
										</div>

									</div>
									<div class="views">${que.no_of_likes}Likes</div>
									<div class="time">
										<i class="fa fa-clock-o"></i> ${que.date_of_posting}
									</div>
								</div>
								<div class="clearfix"></div>
							</div>
							<!-- POST -->
						</c:forEach>
					</div>



					<div class="col-lg-4 col-md-4">

						<!-- -->
						<div class="sidebarblock">
							<h3>Developer Forums</h3>
							<div class="divline"></div>
							<div class="blocktxt">
								<div class="dropdown">
									<button onclick="myFunction()" class="dropbtn"
										style="color: white;">Forums</button>
									<div id="myDropdown" class="dropdown-content">
										<input type="text" placeholder="Search.." id="myInput"
											onkeyup="filterFunction()">
										<c:forEach var="forum" items="${forumBean}">
											<a href="viewQue?forumName=${forum.forumName}">${forum.forumName}</a>
										</c:forEach>
									</div>


								</div>
								<div class="dropdown1">
									<button onclick="myFunction1()" class="dropbtn1"
										style="color: white;">Latest</button>
									<div id="myDropdown1" class="dropdown-content1">
										<input type="text" placeholder="Search.." id="myInput1"
											onkeyup="filterFunction1()">
										<c:forEach var="forum" items="${forumBean}">
											<a href="filterByLatestQue?forumName=${forum.forumName}">${forum.forumName}</a>
										</c:forEach>
									</div>
								</div>
								<div class="dropdown2">
									<button onclick="myFunction2()" class="dropbtn2"
										style="color: white;">Likes</button>
									<div id="myDropdown2" class="dropdown-content2">
										<input type="text" placeholder="Search.." id="myInput2"
											onkeyup="filterFunction2()">
										<c:forEach var="forum" items="${forumBean}">
											<a href="filterByLikeQue?forumName=${forum.forumName}">${forum.forumName}</a>
										</c:forEach>
									</div>
								</div>

							</div>
						</div>


						<div class="sidebarblock">
							<h3>Categories</h3>
							<div class="divline"></div>
							<div class="blocktxt">
								<ul class="cats">

									<li><a href="login">Sign In <span
											class="badge pull-right"></span></a></li>
									<li><a href="registration">Register Here <span
											class="badge pull-right"></span></a></li>

								</ul>
							</div>
						</div>

					</div>
				</div>

			</div>


		</section>

		<footer>
			<div class="container">
				<div class="row">
					<div class="col-lg-1 col-xs-3 col-sm-2 logo ">
						<a href="#"><img
							src="<c:url value="/resources/images/logo.jpg" />" alt="" /></a>
					</div>
					<div class="col-lg-8 col-xs-9 col-sm-5 ">forums.developer.com</div>
				</div>
			</div>
		</footer>
	</div>

	<!-- get jQuery from the google apis -->
	<script type="text/javascript"
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.js"></script>


	<!-- SLIDER REVOLUTION 4.x SCRIPTS  -->
	<script type="text/javascript"
		src="rs-plugin/js/jquery.themepunch.plugins.min.js"></script>
	<script type="text/javascript"
		src="rs-plugin/js/jquery.themepunch.revolution.min.js"></script>

	<script src="js/bootstrap.min.js"></script>


	<!-- LOOK THE DOCUMENTATION FOR MORE INFORMATIONS -->
	<script type="text/javascript">
            
            var revapi;

            jQuery(document).ready(function() {
                "use strict";
                revapi = jQuery('.tp-banner').revolution(
                        {
                            delay: 15000,
                            startwidth: 1200,
                            startheight: 278,
                            hideThumbs: 10,
                            fullWidth: "on"
                        });

            });	//ready

        </script>
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
	<script>
/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction1() {
    document.getElementById("myDropdown1").classList.toggle("show1");
}

function filterFunction1() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput1");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown1");
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

	<script>
/* When the user clicks on the button,
toggle between hiding and showing the dropdown content */
function myFunction2() {
    document.getElementById("myDropdown2").classList.toggle("show2");
}

function filterFunction2() {
    var input, filter, ul, li, a, i;
    input = document.getElementById("myInput2");
    filter = input.value.toUpperCase();
    div = document.getElementById("myDropdown2");
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

	<!-- END REVOLUTION SLIDER -->
</body>
</html>