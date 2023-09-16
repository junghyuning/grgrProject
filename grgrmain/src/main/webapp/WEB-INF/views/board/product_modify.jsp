<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Soft UI - Neumorphism Style UI Kit">
<meta name="author" content="kingstudio.ro">
<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<!-- Site Title -->
<title>Soft UI - Neumorphism Style UI Kit</title>
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
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="판매" />
	<header class="xl bg-img bg-fixed"
		style="background-image: url(/grgrmain/images/free2.jpg); background-size: cover;">
		<div class="container text-center">
			<h1 class="page-title">Market</h1>
			<p class="w-50 m-x-auto mb-30" style="color: white;">
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

	<header class="xxl pb-30 bg-img bg-fixed"
		style="background-image: url(${pageContext.request.contextPath}/assets/images/placeholder.jpg)">

		<div class="container relative z-index-1">
			<h1 class="page-title text-left mb-15">상품 조회</h1>
			<nav class="breadcrumb relative z-index-1 pt-10 pl-5">
				<a class="breadcrumb-item" href="#x">Home</a> <a
					class="breadcrumb-item" href="/board/list">Market</a> <span
					class="breadcrumb-item active">Product</span>
			</nav>
		</div>
		<!-- / container -->
	</header>

	<section class="big page-section">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 tablet-lg-top-30 page-content">
					<div class="project-post">
						<div class="page-content">
							<div id="imagePreviewContainer" class="mb-10"></div>
							<div id="product-carousel" class="owl-carousel owl-theme">
								<c:forEach var="file" items="${productFiles}">
									<%-- <img src="${pageContext.request.contextPath}/resources/upload/${file.infoFileUpload}" --%>
									<img src="<c:url value="/upload/${file.productFileUpload}"/>"
										alt="${file.productFileOrigin }" width="200">
								</c:forEach>
							</div>
							<!-- / owl-carousel -->
						</div>
						<!-- / page-content -->
					</div>
					<!-- / project-post -->
				</div>
				<!-- / column -->

				<div class="col-lg-6 sidebar">
					<form action="modify" method="post" class="validation-inner"
						id="form-validation" novalidate="novalidate">
						<div class="row">
							<input type="hidden" name="uno" value="${productBoard.uno}" /> <input
								type="hidden" name="productId" value="${productBoard.productId}" />
							<input type="hidden" name="productUpdateUno"
								value="${sessionScope.loginUno}" />
							<div class="col-md-12">
								<div class="form-group">
									제목 : <input type="text" class="form-control border-faded"
										name="productTitle" value="${productBoard.productTitle}"
										placeholder="제목" style="font-weight: bold;">
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									가격 : <input type="text" class="form-control border-faded"
										name="productPrice" value="${productBoard.productPrice}"
										placeholder="가격" style="font-weight: bold;">
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									상품 설명 :
									<textarea class="form-control border-faded"
										name="productContent" placeholder="상품 설명"
										style="height: 413px; font-weight: bold; resize: none;">${productBoard.productContent}</textarea>
								</div>

							</div>
							<div>
								<input type="file" name="file" multiple="multiple"
									accept="image/*" id="file-button" style="display: none;">
								<div class="btn btn-instagram m-y-10 mr-10"
									onclick="document.getElementById('file-button').click()">
									<span class="mr-5"><i class="fab fa-instagram"></i></span> <span>사진업로드</span>
									<%-- <span id="upload-error-message" style="color: red;">${message}</span></div> --%>
								</div>

							</div>
						</div>

						<div class="col-md-12">
							<div style="text-align: left;">
								<a
									href="<c:url value='/productboard/list${searchCondition.getQueryString()}'/>">
									<button type="button" class="btn btn-primary">수정취소</button>
								</a>
								<button type="button" id="modify-submit" class="btn btn-primary">수정</button>
							</div>
						</div>

					</form>
					<!-- / column -->
				</div>
				<!-- / row -->

			</div>
			<!-- / container -->
	</section>


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

	<!-- form validation -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/form-validation.js"></script>
	<!-- end of form validation -->

	<!-- Owl Carousel -->
	<script
		src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {

							document
									.querySelector('#modify-submit')
									.addEventListener(
											'click',
											function() {
												var productBoardUno = "${productBoard.uno}";
												var loginUno = "${sessionScope.loginUno}";

												//권한이 없는 사용자가 get방식으로 페이지를 요청하여 수정하는것 방지
												if (productBoardUno !== loginUno) {
													window.location.href = "<c:url value="/404"/>";
													return;
												}

												var title = document
														.getElementsByName('productTitle')[0].value;
												var price = document
														.getElementsByName('productPrice')[0].value;
												var content = document
														.getElementsByName('productContent')[0].value;

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
	<script>
		var files;
		document.getElementById('file-button').addEventListener(
				'change',
				function(event) {
					files = event.target.files;
					var previewContainer = document
							.getElementById('imagePreviewContainer');
					previewContainer.innerHTML = '';

					for (var i = 0; i < files.length; i++) {
						var file = files[i];
						var reader = new FileReader();

						reader.onload = (function(file) {
							return function(e) {
								var div = document.createElement('div');
								div.style.display = 'inline-block';
								div.style.marginRight = '10px';

								var img = document.createElement('img');
								img.src = e.target.result;
								img.alt = "Image Preview";
								img.width = 30;
								div.appendChild(img);
								previewContainer.appendChild(div);
							};
						})(file);

						reader.readAsDataURL(file);
					}
				});
	</script>

	<script>
		$(document)
				.ready(
						function() {

							function isImageFile(file) {
								const validImageTypes = [ 'image/gif',
										'image/jpeg', 'image/png', 'image/jpg' ]; // 원하는 이미지 타입에 따라 확장 가능
								return file
										&& validImageTypes.includes(file.type);
							}
							document
									.querySelector('#insert-submit')
									.addEventListener(
											'click',
											function() {
												var title = document
														.getElementsByName('productTitle')[0].value;
												var content = document
														.getElementsByName('productContent')[0].value;

												var contentErrorMessage = "";
												var imgErrorMessage = "";

												if (title.trim() === ''
														|| content.trim() === '') {
													console
															.log('title trim 진입');
													contentErrorMessage = '제목과 내용을 모두 입력해주세요.';
													console
															.log(contentErrorMessage);

												}

												if (files
														&& files.length > 0
														&& !Array
																.from(files)
																.every(
																		isImageFile)) {
													console.log('img trim 진입');
													imgErrorMessage = '유효하지 않은 파일 형식입니다. 이미지 파일만 업로드 해주세요.';
													console
															.log('img error save');
												}

												if (contentErrorMessage !== "") {
													console
															.log('title error not null');

													document
															.getElementById('content-error-message').textContent = contentErrorMessage;

													$('#content-error-message')
															.show();
													console
															.log('title error show');

													setTimeout(
															function() {
																$(
																		'#content-error-message')
																		.fadeOut(
																				'slow');
															}, 5000);
												}

												if (imgErrorMessage !== '') {
													console
															.log('img error not null');
													document
															.getElementById('img-error-message').textContent = imgErrorMessage;
													$('#img-error-message')
															.show();
													console
															.log('img error show');

													setTimeout(
															function() {
																$(
																		'#img-error-message')
																		.fadeOut(
																				'slow');
															}, 5000);
												}

												if (contentErrorMessage === ""
														&& imgErrorMessage === "") {
													document.getElementById(
															'form-validation')
															.submit(); // 폼을 제출
												}
											});

							setTimeout(function() {
								$('#error-message').fadeOut('slow');
							}, 5000);

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
	<!-- / Owl Carousel -->

</body>

</html>