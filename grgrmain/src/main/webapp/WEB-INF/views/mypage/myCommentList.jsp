<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	font-weight: 'bold';
}

.btn-outline-primary {
	font-size: 18px; /* 원하는 크기로 설정 */
}

.selected {
	background-color: #007bff;
	//
	선택된
	배경색
	color
	:
	#ffffff;
	//
	선택된
	텍스트
	색상
}
</style>
<body>
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>

	<div id="top"></div>
	<!-- / top -->



	<!-- 큰 컨테이너  -->
	<section class="big">
		<div class="container">
			<h2 class="hidden">userCommentList</h2>
			<!-- 키워드 -->
			<ul class="list-inline text-center mb-30">
				<li id="keyword-button"><a
					href="<c:url value='/mypage/myBoardWriteList?uno=${param.uno}' />"
					class="btn btn-outline-primary m-y-10 mr-10">내가 쓴 글</a></li>
				<li id="keyword-button"><a
					href="<c:url value='/mypage/myCommentList?uno=${param.uno}' />"
					class="btn btn-outline-primary m-y-10 mr-10">내가 쓴 댓글</a></li>
			</ul>

			<div>
				<ul class="row portfolio project-grid lightbox list-unstyled mb-0"
					id="grid" style="clear: both">
					<!--====================================================================================================  -->
					<!-- project : 게시글 list 출력 -->
					<c:set var="i" value="0" />
					<c:forEach items="${commentList}" var="commentList">
						<li class="col-md-12 col-lg-0 project">
							<!-- &pageNum=${pageNum} -->
							<div class="promo-box">
								<div class="cta p-0">
									<div class="row v-center">
										<div class="col-lg-2 tablet-lg-top-30 tablet-lg-center">

											<c:set var="i" value="${i+1 }" />
										</div>
										<!-- / column -->
										<div class="col-lg-10 text-left tablet-lg-center">

											<c:choose>
												<c:when test="${commentList.boardtype == 'QNA'}">
													<a
														href="<c:url value='/qnaboard/read?qnaBno=${commentList.bno}'/>">
														<p class="mb-20">${commentList.content}</p>
													</a>
												</c:when>
												<c:when test="${commentList.boardtype == 'INFO'}">

													<a
														href="<c:url value='/infoboard/read?infoBno=${commentList.bno}'/>">
														<p class="mb-20">${commentList.content}</p>
													</a>
												</c:when>
												<c:when test="${commentList.boardtype == 'FREE'}">

													<a
														href="<c:url value='/freeboard/read?freeBno=${commentList.bno}'/>">
														<p class="mb-20">${commentList.content}</p>
													</a>
												</c:when>
											</c:choose>
											<p class="fs-16 post-meta-small mt-15 mb-0"
												style="text-align: right">
												<span class="m-x-10 text-muted">|</span> <i
													class="far fa-calendar-alt mr-5"></i>${commentList.regdate}<span
													class="m-x-10 text-muted">|</span> <i
													class="fas fa-tag mr-10"></i>
											</p>
										</div>
										<!-- / column -->
									</div>
									<!-- / row -->
								</div>
								<!-- / cta -->
							</div> <!-- / promo-box -->
						</li>
					</c:forEach>
					<!--====================================================================================================  -->
				</ul>
			</div>
		</div>
		<!-- / container -->
	</section>


	<nav aria-label="pagination-center">
		<ul class="pagination justify-content-center">
			<!-- 이전 블록 이동 표시 -->

			<!-- 페이지 목록 출력 -->
			<c:forEach var="i" begin="${pager.startPage}" end="${pager.endPage}">
				<c:choose>
					<c:when test="${pager.pageNum != i}">
						<li class="page-item"><a class="page-link"
							href="<c:url value='/mypage/myCommentList'>
                            <c:param name='pageNum' value='${i}'/>
                            <c:param name='uno' value='${param.uno}'/>
                        </c:url>">${i}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item active"><span class="page-link">${i}</span>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 다음 블록 이동 표시 -->

		</ul>
	</nav>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-visible smooth-scroll"
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

	<!-- smooth scroll -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.easing.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/smooth-scroll.js"></script>
	<!-- / smooth scroll -->

	<!-- hide nav -->
	<script src="${pageContext.request.contextPath}/assets/js/hide-nav.js"></script>
	<!-- / hide nav -->

	<!-- portfolio script -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.shuffle.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/portfolio.js"></script>
	<script>
		$(document).ready(
				function() {

					var keywordInput = $('input[name="searchKeyword"]');
					$('#search-button').click(
							function() {
								// 선택한 검색 유형과 키워드를 가져옵니다.
								var searchType = $('#select').val();
								var searchKeyword = keywordInput.val();
								var pageNum = $('input[name="pageNum"]').val();

								if (searchKeyword === '') {
									keywordInput.val('검색어를 입력하지 않으셨습니다.'); // 값을 직접 변경
									keywordInput.css('color', 'red'); // 텍스트 색상 변경
									return;
								} else {

									keywordInput.css('color', 'black'); // 일반 색상으로 되돌림
								}

								// url 생성
								var url = "list?pageNum=" + pageNum
										+ "&searchType=" + searchType
										+ "&searchKeyword=" + searchKeyword;

								//리다이렉트합니다.
								window.location.href = url;
							});

					keywordInput.focus(function() {
						if (keywordInput.val() === '검색어를 입력하지 않으셨습니다.') {
							keywordInput.val(''); // 오류 메시지를 지움
							keywordInput.css('color', 'black'); // 일반 텍스트 색상으로 되돌림
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
						$('.close-overlay').click(function(e) {
							e.preventDefault();
							e.stopPropagation();
							if ($(this).closest('.img').hasClass('hover')) {
								$(this).closest('.img').removeClass('hover');
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