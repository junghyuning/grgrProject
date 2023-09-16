<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Meta -->
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="Soft UI - Neumorphism Style UI Kit" />
<meta name="author" content="kingstudio.ro" />
<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.png" />
<!-- Site Title -->
<title>Soft UI - Neumorphism Style UI Kit</title>
<!-- Bootstrap 4 core CSS -->
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<!-- Custom Styles -->
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet" />
<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;600;800&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;600;800&display=swap"
	rel="stylesheet" />
<link
	href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css"
	rel="stylesheet" type="text/css" />
</head>
<style>
.va-middle {
	font-size: 20px;
}

.page-link {
	font-size: 20px;
}

.mb-20 {
	font-size: 2rem;
	font-weight: "bold"
}
</style>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="공지게시판" />
	<header class="xl bg-img bg-fixed"
		style="height: 300px; padding-top: 200px;">
		<div class="container text-center">
			<h1 class="page-title">Notice</h1>
			<p class="w-50 m-x-auto mb-30">
				<c:out value="${boardName}" />
			</p>
		</div>
		<!-- / container -->
	</header>

	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>

	<div id="top"></div>
	<!-- / top -->

	<script type="text/javascript">
		
	</script>
	<!-- 큰 컨테이너  -->
	<section class="lg bg-light-grey">
		<div class="container">
			<div class="w-90 m-x-auto mt-70" id="titleAndError">
				<div style="display: flex; align-items: left;">
					<h4 class="mb-30 text-left">글 수정</h4>
					<span id="error-message" style="color: red;">${message}</span>
				</div>
				<form action="modify" method="post" class="validation-inner"
					id="form-validation" novalidate="novalidate">
					<input type="hidden" name="uno" value="${noticeBoard.uno}" /> 
					<input type="hidden" name="noticeBno" value="${noticeBoard.noticeBno}" /> 
					<input type="hidden" name="loginUserStatus" value="${sessionScope.loginUserStatus}" />
					<input type="hidden" name="noticeUpdateUno" value="${sessionScope.loginUno}" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<input type="text" class="form-control" id="contact-email"
									name="noticeTitle" required="true"
									style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; color: #000;"
									aria-required="true" value="${noticeBoard.noticeTitle}">
							</div>
							<!-- / form-group -->
						</div>
						<!-- / column -->

						<div class="form-group">
							<textarea id="contact-message" class="form-control"
								name="noticeContent" rows="8" required="true"
								style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; min-height: 500px; max-height: 500px; color: #000;"
								aria-required="true">${noticeBoard.noticeContent}</textarea>
						</div>

						<!-- / form-group -->
					</div>
					<!-- / column -->

					<!-- 글 목록/ 수정 버튼 -->
					<div style="text-align: right;">
						<a
							href="<c:url value='/noticeboard/list${searchCondition.getQueryString()}'/>"
							target="_blank">
							<button type="button" class="btn btn-primary-gradient">글목록</button>
						</a> <a
							href="<c:url value='/noticeboard/list${searchCondition.getQueryString()}'/>">
							<button type="button" class="btn btn-primary-gradient">수정취소</button>
						</a>
						<c:if test="${sessionScope.loginUserStatus == 1 }">
						<button type="button" id="modify-submit"
							class="btn btn-primary-gradient">수정</button>
						</c:if>
					</div>
				</form>
				<!-- / form-group -->
			</div>
		</div>
	</section>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-hidden smooth-scroll"
		data-nav-status="toggle"><i class="fas fa-chevron-up"></i></a>

	<!-- footer 영역 -->
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />

	<!-- core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
	<!-- / core JavaScript -->

	<!-- preloader -->
	<script src="${pageContext.request.contextPath}/assets/js/preloader.js"></script>
	<!-- / preloader -->

	<!-- hide nav -->
	<script src="${pageContext.request.contextPath}/assets/js/hide-nav.js"></script>
	<!-- / hide nav -->

	<!-- portfolio script -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.shuffle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/portfolio.js"></script>
	<script>
		$(document)
		.ready(
			function() {

			document
				.querySelector('#modify-submit')
				.addEventListener(
					'click',
					function() {
						
						var title = document
								.getElementsByName('noticeTitle')[0].value;
						var content = document
								.getElementsByName('noticeContent')[0].value;

						if (title.trim() === ''
								|| content.trim() === '') {
							alert('제목과 내용을 모두 입력해주세요.');
						} else {
							document.getElementById(
									'form-validation')
									.submit(); // 폼을 제출
						}
					});
							if (Modernizr.touch) {
								// show the close overlay button
								$('.close-overlay').removeClass('hidden');
								// handle the adding of hover class when clicked
								$('.img').click(function(e) {
									if (!$(this).hasClass('hover')) {
										$(this).addClass('hover');
									}
								});
								// handle the closing of the overlay
								$('.close-overlay').click(
										function(e) {
											e.preventDefault();
											e.stopPropagation();
											if ($(this).closest('.img')
													.hasClass('hover')) {
												$(this).closest('.img')
														.removeClass('hover');
											}
										});
							} else {
								// handle the mouseenter functionality
								$('.img').mouseenter(function() {
									$(this).addClass('hover');
								})
								// handle the mouseleave functionality
								.mouseleave(function() {
									$(this).removeClass('hover');
								});
							}
						});
	</script>
	<!-- / portfolio script -->
</body>
</html>

