<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>끼리끼리 - 자유게시판</title>
<style>
.titleAndError {
	display: flex;
	align-items: center; /* 세로 중앙 정렬 */
	height: 100px;
}
</style>
</head>
<body>
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="자유게시판" />
	<header class="xl bg-img bg-fixed"
		style="background-image: url(${pageContext.request.contextPath}/images/free2.jpg); background-size: cover;">
		<div class="container text-center">
			<h1 class="page-title">Free</h1>
			<p class="w-50 m-x-auto mb-30" style="color: white;">
				<c:out value="${boardName}" />
			</p>
		</div>
		<!-- / container -->
	</header>

	<div id="top"></div>
	<!-- / top -->

	<section class="lg bg-light-grey">
		<div class="container">
			<div class="w-90 m-x-auto mt-70" id="titleAndError">
				<div style="display: flex; align-items: left;">
					<h4 class="mb-30 text-left">글 쓰기</h4>
					<span id="error-message" style="color: red;">${message}</span>
				</div>

				<!-- 폼 제출 영역 -->
				<form action="write" method="post" enctype="multipart/form-data"
					class="validation-inner" id="form-validation"
					novalidate="novalidate">
					<input type="hidden" name="uno" value="${sessionScope.loginUno}" />
					<input type="hidden" name="freeUpdateUno"
						value="${sessionScope.loginUno}" /> <input type="hidden"
						name="freeLoc" value="${searchCondition.loginLocation}" />
					<div class="row">

						<div class="col-md-12">
							<div class="form-group">
								<input type="text" class="form-control" id="contact-email"
									name="freeTitle" placeholder="제목을 입력해주세요" required
									style=" color: black; font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400"
									aria-required="true">
							</div>
							<!-- / form-group -->
						</div>
						<!-- / column -->

						<div class="form-group">
							<textarea id="contact-message" class="form-control"
								name="freeContent" rows="8" placeholder="내용을 입력하세요"
								required
								style=" color: black; font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; min-height: 500px; max-height: 500px;"
								aria-required="true"></textarea>
						</div>

						<div>
							<input type="file" name="files" multiple="multiple"
								accept="image/*" id="file-button" style="display: none;">
							<div class="btn btn-instagram m-y-10 mr-10"
								onclick="document.getElementById('file-button').click()">
								<span class="mr-5"><i class="fab fa-instagram"></i></span> <span>사진업로드</span>
							</div>
							<!-- 이미지 미리보기 영역 -->
							<div id="imagePreviewContainer" class="mb-10"></div>
						</div>


						<!-- / column -->

						<div style="text-align: right;">
							<a
								href="<c:url value='/freeboard/list${searchCondition.getQueryString()}'/>"
								target="_blank">
								<button type="button" class="btn btn-primary-gradient">글목록</button>
							</a> <a
								href="<c:url value='/freeboard/list${searchCondition.getQueryString()}'/>">
								<button type="button" class="btn btn-primary-gradient">작성
									취소</button>
							</a>
							<button type="button" class="btn btn-primary-gradient"
								id="write-submit">등록</button>
						</div>
					</div>
				</form>
				<!-- / form-group 폼 제출 완료 -->
			</div>
		</div>
	</section>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-hidden "
		data-nav-status="toggle"><i class="fas fa-chevron-up"></i></a>

	<!-- footer 영역 -->
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />


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
							$('#write-submit')
									.click(
											function() {
												var title = document
														.getElementsByName('freeTitle')[0].value;
												var content = document
														.getElementsByName('freeContent')[0].value;
												var contentErrorMessage = "";
												var imgErrorMessage = "";
												if (title.trim() === ''
														|| content.trim() === '') {
													alert('제목과 내용을 모두 입력해주세요.');
												}
												if (files
														&& files.length > 0
														&& !Array
																.from(files)
																.every(
																		isImageFile)) {
													imgErrorMessage = '유효하지 않은 파일 형식입니다. 이미지 파일만 업로드 해주세요.';
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

						});
	</script>
</body>
</html>
