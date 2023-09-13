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
</style>
<body>
<!-- 헤더 -->
   <jsp:include page="/WEB-INF/views/tiles/header.jsp"/>
   <!-- 배너 -->
   <c:set var="boardName" value="문의게시판" />
   <header class="xl bg-img bg-fixed" style="height: 300px; padding-top: 200px;">
      <div class="container text-center">
         <h1 class="page-title">QNA</h1>
         <p class="w-50 m-x-auto mb-30"><c:out value="${boardName}" /></p>
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
					<h4 class="mb-30 text-left">글쓰기</h4>
					<span id="error-message" style="color: red;">${message}</span>
				</div>

				<!-- 폼 제출 영역 -->
				<form action="write" method="post"
					class="validation-inner" id="form-validation"
					novalidate="novalidate">
					<!-- <input type="hidden" name="uno" value="loginUno" /><input type="hidden" name="infoUpdateUno" value="loginUno" /><input type="hidden"
					name="infoLoc" value="loginLoc" />  -->
					<input type="hidden" name="uno" value="${sessionScope.loginUno}" /> <input type="hidden"
						name="qnaUpdateUno" value="${sessionScope.loginUno}" />
					<div class="row">
						<div class="col-md-3">
							<div class="col-md-0 tablet-top">
								<select class="custom-select" id="select" name="qnaKeyword"
									style="color: black;">
									<c:choose>
										<c:when test="${searchCondition.keyword == 'member'}">
											<option value="member" selected>일반회원</option>
										</c:when>
										<c:otherwise>
											<option value="member">일반회원</option>
										</c:otherwise>
									</c:choose>
									<c:choose>
										<c:when test="${searchCondition.keyword == 'trade'}">
											<option value="trade" selected>상권회원</option>
										</c:when>
										<c:otherwise>
											<option value="trade">상권회원</option>
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
									name="qnaTitle" placeholder="제목을 입력해주세요" required="true"
									style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400"
									aria-required="true">
							</div>
							<!-- / form-group -->
						</div>
						<!-- / column -->

						<div class="form-group">
							<textarea id="contact-message" class="form-control"
								name="qnaContent" rows="8" placeholder="내용을 입력하세요"
								required="true"
								style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; min-height: 500px; max-height: 500px;"
								aria-required="true"></textarea>
						</div>


						<!-- / form-group -->

						<!-- / column -->
						<div style="text-align: right;">
							<a
								href="<c:url value='/qnaboard/list${searchCondition.getQueryString()}'/>"
								target="_blank">
								<button type="button" class="btn btn-primary-gradient">글목록</button>
							</a> <a
								href="<c:url value='/qnaboard/list${searchCondition.getQueryString()}'/>">
								<button type="button" class="btn btn-primary-gradient">작성
									취소</button>
							</a>
							<button type="button" class="btn btn-primary-gradient"
								id="write-submit">등록</button>
						</div>

					</div>
				</form>
			</div>
			<!-- / form-group 폼 제출 완료 -->
		</div>
	</section>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-hidden smooth-scroll"
		data-nav-status="toggle"><i class="fas fa-chevron-up"></i></a>

	<!-- footer 영역 -->
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp"/>

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
									.querySelector('#write-submit')
									.addEventListener(
											'click',
											function() {
												var title = document
														.getElementsByName('qnaTitle')[0].value;
												var content = document
														.getElementsByName('qnaContent')[0].value;
												if (title.trim() === ''
														|| content.trim() === '') {
													alert('제목과 내용을 모두 입력해주세요.');
												} else {
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
	<!-- / portfolio script -->
</body>
</html>

