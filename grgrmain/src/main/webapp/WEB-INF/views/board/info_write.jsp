<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>끼리끼리 - 정보공유게시판</title>
<style>
.titleAndError {
	display: flex;
	align-items: center; /* 세로 중앙 정렬 */
	font-size: 20px height: 100px;
}
</style>
</head>
<!-- 헤더 -->
<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
<div id="preloader">
	<div class="preloader">
		<span></span> <span></span>
	</div>
</div>
<!-- 배너 -->
<c:set var="boardName" value="정보공유게시판" />
<header class="xl bg-img bg-fixed"
	style="background-image: url(${pageContext.request.contextPath}/images/information.jpg); background-size: cover;">
	<div class="container text-center">
		<h1 class="page-title">Info</h1>
		<p class="w-50 m-x-auto mb-30" style="color: white;">
			<c:out value="${boardName}" />
		</p>
	</div>
	<!-- / container -->
</header>

<div id="top"></div>
<!-- / top -->

<section class="lg bg-white">
	<div class="container">
		<div class="w-90 m-x-auto mt-70" id="titleAndError">
			<div style="display: flex; align-items: left;">
				<h4 class="mb-30 text-left">글 쓰기</h4>
				<span id="error-message" style="color: red;">${message}</span> <span
					id="content-error-message" style="color: red; padding-left: 20px"></span>
			</div>

			<!-- 폼 제출 영역 -->
			<form action="<c:url value="/infoboard/write"/>" method="post"
				enctype="multipart/form-data" class="validation-inner"
				id="form-validation" novalidate="novalidate">
				<input type="hidden" name="uno" value="${sessionScope.loginUno}" />
				<input type="hidden" name="infoUpdateUno"
					value="${sessionScope.loginUno}" /> <input type="hidden"
					name="infoLoc" value="${searchCondition.loginLocation}" />
				<div class="row">
					<div class="col-md-3">
						<div class="col-md-0 tablet-top">
							<select class="custom-select" id="select" name="infoKeyword"
								style="color: black;">
								<c:choose>
									<c:when test="${searchCondition.keyword == 'food'}">
										<option value="food" selected>맛집</option>
									</c:when>
									<c:otherwise>
										<option value="food">맛집</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${searchCondition.keyword == 'market'}">
										<option value="market" selected>기타후기</option>
									</c:when>
									<c:otherwise>
										<option value="market">기타후기</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${searchCondition.keyword == 'living'}">
										<option value="living" selected>생활정보</option>
									</c:when>
									<c:otherwise>
										<option value="living">생활정보</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${searchCondition.keyword == 'crew'}">
										<option value="crew" selected>모임 / 구인</option>
									</c:when>
									<c:otherwise>
										<option value="crew">모임 / 구인</option>
									</c:otherwise>
								</c:choose>

								<c:choose>
									<c:when test="${searchCondition.keyword == 'event'}">
										<option value="event" selected>이벤트</option>
									</c:when>
									<c:otherwise>
										<option value="event">이벤트</option>
									</c:otherwise>
								</c:choose>
							</select>
							<!-- / custom-select -->
						</div>
						<!-- / form-group -->
					</div>
					<!-- / column -->

					<div class="col-md-9">
						<div class="form-group">
							<input type="text" class="form-control" id="contact-email"
								name="infoTitle" placeholder="제목을 입력해주세요" required
								style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400;  color: black;"
								aria-required="true">
						</div>
						<!-- / form-group -->
					</div>
					<!-- / column -->

					<div class="form-group">
						<textarea id="contact-message" class="form-control"
							name="infoContent" rows="8" placeholder="내용을 입력하세요"
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
						<span id="img-error-message" style="color: red; font-size: 15px;"></span>
						<!-- 이미지 미리보기 영역 -->
						<div id="imagePreviewContainer" class="mb-10"></div>
					</div>


					<!-- / column -->

					<div style="text-align: right;">
						<a
							href="<c:url value='/infoboard/list${searchCondition.getQueryString()}'/>"
							target="_blank">
							<button type="button" class="btn btn-primary-gradient">글목록</button>
						</a> <a
							href="<c:url value='/infoboard/list${searchCondition.getQueryString()}'/>">
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

<a href="#top" class="scroll-to-top is-hidden " data-nav-status="toggle"><i
	class="fas fa-chevron-up"></i></a>
<!-- footer -->
<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />

<script>
	//파일 업로드 input태그에서 선택한 파일을 저장하기위한 변수
	var files;
	//이미지가 선택되고나면 실행될 이벤트 리스너 
	document.getElementById('file-button').addEventListener(
			'change',
			function(event) {
				//이벤트실행시 선택된 파일의정보를 파일에 저장함
				files = event.target.files;
				var previewContainer = document
						.getElementById('imagePreviewContainer');
				previewContainer.innerHTML = '';

				for (var i = 0; i < files.length; i++) {
					var file = files[i];
					//파일을 읽어오기위해 FileReader 객체 생성
					var reader = new FileReader();
					//
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

					//파일리더를 통해 읽어온 파일 데이터를 URL 형태로 가져옴
					reader.readAsDataURL(file);
				}
			});
</script>

<script>
	$(document)
			.ready(
					function() {
						//파일 형식 검사
						function isImageFile(file) {
							const validImageTypes = [ 'image/gif',
									'image/jpeg', 'image/png', 'image/jpg' ];
							return file && validImageTypes.includes(file.type);
						}

						$('#write-submit')
								.click(
										function() {
											var title = document
													.getElementsByName('infoTitle')[0].value;
											var content = document
													.getElementsByName('infoContent')[0].value;
											var contentErrorMessage = "";
											var imgErrorMessage = "";

											if (title.trim() === ''
													|| content.trim() === '') {
												console.log('title trim 진입');
												contentErrorMessage = '제목과 내용을 모두 입력해주세요.';
												console
														.log(contentErrorMessage);
											}

											if (files
													&& files.length > 0
													&& !Array.from(files)
															.every(isImageFile)) {
												console.log('img trim 진입');
												imgErrorMessage = '유효하지 않은 파일 형식입니다. 이미지 파일만 업로드 해주세요.';
												console.log('img error save');
											}

											if (contentErrorMessage !== "") {
												console
														.log('title error not null');
												document
														.getElementById('content-error-message').textContent = contentErrorMessage;
												$('#content-error-message')
														.show();
												console.log('title error show');
												setTimeout(function() {
													$('#content-error-message')
															.fadeOut('slow');
												}, 5000);
											}

											if (imgErrorMessage !== '') {
												console
														.log('img error not null');
												document
														.getElementById('img-error-message').textContent = imgErrorMessage;
												$('#img-error-message').show();
												console.log('img error show');
												setTimeout(function() {
													$('#img-error-message')
															.fadeOut('slow');
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

