<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="kor">
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

.uploaded-img {
	width: 300px;
	height: auto;
}
</style>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="문의게시판" />
	<header class="xl bg-img bg-fixed"
		style="height: 300px; padding-top: 200px;">
		<div class="container text-center">
			<h1 class="page-title">QNA</h1>
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
			<div class="w-90 m-x-auto mt-70">
				<div>
					<h4 class="mb-30 text-left">글수정</h4>
					<span id="content-error-message"
						style="color: red; padding-left: 20px"></span>
					<c:if test="${not empty msg}">
						<div class="alert alert-danger">${msg}</div>
					</c:if>
				</div>
				<form action="modify" method="post" class="validation-inner"
					enctype="multipart/form-data" id="form-validation"
					novalidate="novalidate">
					<input type="hidden" name="uno" value="${qnaBoard.uno}" /> <input
						type="hidden" name="qnaBno" value="${qnaBoard.qnaBno}" /> <input
						type="hidden" name="qnaBno" value="${sessionScope.loginUno}" />
					<div class="row">
						<div class="col-md-3">
							<div class="col-md-0 tablet-top">
								<select class="custom-select" id="select" name="qnaKeyword"
									style="color: black;">
									<c:choose>
										<c:when test="${qnaBoard.qnaKeyword == 'member'}">
											<option value="member" selected>일반회원</option>
										</c:when>
										<c:otherwise>
											<option value="member">일반회원</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${qnaBoard.qnaKeyword == 'trade'}">
											<option value="trade" selected>상권회원</option>
										</c:when>
										<c:otherwise>
											<option value="trade">상권회원</option>
										</c:otherwise>
									</c:choose>
									<!-- Similar pattern for other options -->
								</select>
								<!-- / custom-select -->
							</div>
							<!-- / form-group -->
						</div>
						<!-- / column -->

						<div class="col-md-9">
							<div class="form-group">
								<input type="text" class="form-control" id="contact-email"
									name="qnaTitle" required="true"
									style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; color: #000;"
									aria-required="true" value="${qnaBoard.qnaTitle}">
							</div>
							<!-- / form-group -->
						</div>
						<!-- / column -->

						<div class="form-group">
							<textarea id="contact-message" class="form-control"
								name="qnaContent" rows="12" required="true"
								style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; min-height: 500px; max-height: 500px; color: #000;"
								aria-required="true">${qnaBoard.qnaContent}</textarea>
						</div>
						<!-- / form-group -->
					</div>
					<!-- / column -->

					<!-- 사진업로드 버튼 -->
					<div
						style="display: flex; justify-content: space-between; align-items: center;">
						<div>
							<input type="file" name="files" multiple="multiple"
								accept="image/*" id="file-button" style="display: none;">
							<div class="btn btn-instagram m-y-10 mr-10"
								onclick="document.getElementById('file-button').click()">
								<span class="mr-5"><i class="fab fa-instagram"></i></span> <span>사진업로드</span>
							</div>
						</div>

						<!-- 비밀글 여부 -->
						<div class="checkbox checkbox-secondary">
							<input type="hidden" id="qnaBlindstate" name="qnaBlindstate"
								value="1"> <input id="secretCheckbox" type="checkbox">
							<label for="secretCheckbox">비밀글</label>
						</div>
					</div>

					<!-- 글 목록/ 수정 버튼 -->
					<div style="text-align: right;">
						<a
							href="<c:url value='/qnaboard/list${searchCondition.getQueryString()}'/>"
							target="_blank">
							<button type="button" class="btn btn-primary-gradient">글목록</button>
						</a> <a
							href="<c:url value='/qnaboard/list${searchCondition.getQueryString()}'/>">
							<button type="button" class="btn btn-primary-gradient">수정취소</button>
						</a>
						<button type="button" id="modify-submit"
							class="btn btn-primary-gradient">수정</button>
					</div>
					<!-- 기존 업로드됐던 파일들 -->
					<c:forEach var="file" items="${qnaFiles}">
						<div data-hide="false">
							<img class="uploaded-img"
								src="<c:url value="/upload/${file.qnaFileUpload}"/>"
								alt="${file.qnaFileOrigin }">
							<!-- ${file.qnaFileNo}사용해야함 -->
							<a class="btn btn-xs btn-danger m-y-10 mr-10 delete-file"
								data-file-no="${file.qnaFileNo}">이미지 삭제</a>
						</div>
					</c:forEach>
				</form>
				<!-- / form-group -->
			</div>
		</div>
	</section>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-hidden smooth-scroll"
		data-nav-status="toggle"> <i class="fas fa-chevron-up"></i>
	</a>

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
							//파일 화면에서 hide()
							var deleteFileList = [];
							$('.delete-file').click(
									function() {
										var $selectedImg = $(this).closest(
												'div');
										var fileNo = $(this).data('file-no');
										deleteFileList.push(fileNo)
										$(this).closest('div').attr(
												"data-hide", "true").hide();

									});

							//파일 형식 검사
							function isImageFile(file) {
								const validImageTypes = [ 'image/gif',
										'image/jpeg', 'image/png', 'image/jpg' ];
								return file
										&& validImageTypes.includes(file.type);
							}
							document
									.querySelector('#modify-submit')
									.addEventListener(
											'click',
											function() {
												var qnaBoardUno = "${qnaBoard.uno}";
												var loginUno = "${sessionScope.loginUno}";

												// 권한이 없는 사용자가 get방식으로 페이지를 요청하여 수정하는 것 방지
												if (qnaBoardUno !== loginUno) {
													window.location.href = "<c:url value="/404"/>";
													return;
												}

												var title = document
														.getElementsByName('qnaTitle')[0].value;
												var content = document
														.getElementsByName('qnaContent')[0].value;
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

													$
															.ajax({
																type : 'DELETE',
																url : "<c:url value='/qnafile/delete'/>",
																contentType : "application/json",
																data : JSON
																		.stringify(deleteFileList),
																success : function(
																		data) {
																	document
																			.getElementById(
																					'form-validation')
																			.submit(); // 폼을 제출
																},
																error : function(
																		err) {
																	console
																			.error(
																					"파일 삭제에 실패하였습니다.",
																					err.responseText);
																}
															});
												}
											});

							setTimeout(function() {
								$('#error-message').fadeOut('slow');
							}, 5000);

							if (Modernizr.touch) {
								// 터치 이벤트를 지원하는 환경 처리
								$('.close-overlay').removeClass('hidden');
								$('.img').click(function(e) {
									if (!$(this).hasClass('hover')) {
										$(this).addClass('hover');
									}
								});

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
								// 터치 이벤트를 지원하지 않는 환경 처리
								$('.img').mouseenter(function() {
									$(this).addClass('hover');
								}).mouseleave(function() {
									$(this).removeClass('hover');
								});
							}
						});
	</script>

	<!-- 비밀글 값 받기 -->
	<script>
		// 비밀글 체크박스 요소 가져오기
		const secretCheckbox = document.getElementById('secretCheckbox');

		// 체크박스 상태가 변경될 때 이벤트 리스너 추가
		secretCheckbox.addEventListener('change',
				function() {
					// 체크박스가 체크되었는지 여부 확인
					const isSecret = secretCheckbox.checked;

					// 비밀글 상태 변수
					let qnaBlindstate;

					// 체크되었을 때는 4(비밀글), 아닐 때는 1(공개글)
					if (isSecret) {
						qnaBlindstate = 4;
					} else {
						qnaBlindstate = 1;
					}

					// qnaBlindstate 값을 숨겨진 필드에 설정
					const qnaBlindstateField = document
							.getElementById('qnaBlindstate');
					if (qnaBlindstateField) {
						qnaBlindstateField.value = qnaBlindstate;
					} else {
						console.error('qnaBlindstate 요소를 찾을 수 없습니다.');
					}

					// 여기에서는 간단하게 콘솔에 출력
					console.log('비밀글 여부:', isSecret);
					console.log('게시글 상태:', qnaBlindstate);
				});
	</script>
	<!-- / portfolio script -->
</body>
</html>

