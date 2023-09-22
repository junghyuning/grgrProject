<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<head>
<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Soft UI - Neumorphism Style UI Kit">
<meta name="author" content="kingstudio.ro">
<!-- 파비콘 변경 -->
<link rel="icon"
	href="${pageContext.request.contextPath}/images/grgr_logo.png">
<!-- Site Title -->
<title>끼리끼리</title>
<!-- Bootstrap 4 core CSS -->
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom Styles -->
<link
	href="${pageContext.request.contextPath}/assets/css/nouislider.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/owl.carousel.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/magnific-popup.css"
	rel="stylesheet">
<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;600;800&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;600;800&display=swap"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css"
	rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style type="text/css">
.custom-text {
	font-size: 16px;
	color: #333333;
	margin-bottom: 15px;
}

.update-location-button {
	background-color: #ffffff;
	color: #0074cc;
	border: none;
	padding: 10px 20px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

.card.bg-img {
	background-color: rgba(0, 0, 0, 0.4); /* 이는 검은색 40% 투명도를 추가합니다 */
	background-blend-mode: overlay;
}

.card-body a, .card-text, .card-header p {
	text-shadow: -1px -1px 0 #ffffff, 1px -1px 0 #ffffff, -1px 1px 0 #ffffff,
		1px 1px 0 #ffffff;
}

.main-banner {
	weight: auto;
	height: 500px;
	overflow: hidden;
}

.loc {
	width: 60px;
	height: 60px;
}

</style>
</head>
<body class="bg-light">
	<div style="background-color: #E2E6FC;">
		<nav
			class="navbar navbar-expand-lg navbar-light bg-white custom-menu split-menu bg-light">
			<div class="container">

				<!-- ---------------------------------------------------------------------------------------------------------- -->
				<div class="collapse navbar-collapse d-flex align-items-center"
					id="navbar-toggle-split-left">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item loc "><a
							href="<c:url value="/info/matzib"/>"><img class="mb-10"
								alt="맛집정보" src="<c:url value="/images/restaurant.png"/>" />맛집</a></li>
						<li class="nav-item"><a class="nav-link" href="#x">Link</a></li>
						<li class="nav-item"><a class="nav-link" href="#x">More</a></li>
					</ul>
					<!-- / navbar-nav -->
				</div>
				<!-- / navbar-collapse -->

				<a class="navbar-brand m-auto" href="#x"><img
					src="${pageContext.request.contextPath}/images/grgr_favicon.png"
					alt="메인로고"></a>

				<div class="collapse navbar-collapse" id="navbar-toggle-split-right">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link" href="#x">Right</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="#x">Link</a></li>
						<li class="nav-item"><a class="nav-link" href="#x">More</a></li>
						<li class="nav-item"><a class="nav-link last-menu-item"
							href="#x">Last</a></li>
					</ul>
					<!-- / navbar-nav -->
				</div>
				<!-- / navbar-collapse -->
			</div>
			<!-- / container -->
		</nav>
		<!-- / split-navbar -->
	</div>