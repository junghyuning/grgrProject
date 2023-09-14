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
	color: #ffffff;
}

.viewCnt {
	text-align: right;
	margin-bottom: 0;
}
</style>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="장바구니" />
	<header class="xl bg-img bg-fixed"
		style="background-image: url(/grgrmain/images/information.jpg); background-size: cover;">
		<div class="container text-center">

			<h1 class="page-title">
				<c:out value="${boardName}" />
			</h1>

		</div>
		<!-- / container -->
	</header>

	<div class="container">
		<div class="row">

			<jsp:include page="/WEB-INF/views/tiles/sidebar.jsp" />

			<!-- Main Content -->
			<main class="main-content col-md-10">

				<div>
					<ul class="row portfolio project-grid lightbox list-unstyled mb-0"
						id="grid" style="clear: both">
						<!-- project : 게시글 list 출력 -->
						<c:set var="i" value="0" />
						<c:forEach items="${cartList}" var="cartList">
							<li class="col-md-12 col-lg-0 project">
								<!-- &pageNum=${pageNum} -->
								<div class="promo-box">
									<div class="cta p-0">
										<div class="row v-center">
											<div class="col-lg-2 tablet-lg-top-30 tablet-lg-center" >
												<img
													src="${pageContext.request.contextPath}/upload/${fileList[i]}"
													alt="Thumbnail" class="rounded" />
												<c:set var="i" value="${i+1 }" />
											</div>
											<!-- / column -->
											<div class="row">
												<div class="col-lg-4 text-left tablet-lg-center">
													<a
														href="<c:url value='/productboard/get?productId=${cartList.productId}'/>">
														<p class="mb-20">상품명: ${cartList.productTitle}</p>
													</a>
												</div>

												<div class="col-lg-4 text-left tablet-lg-center">
													<p class="lead mb-20">
														가격: &nbsp; &nbsp; <i class="fas fa-won-sign">&nbsp;
															${cartList.productPrice}</i>
													</p>
												</div>

												<div class="col-lg-4 text-left tablet-lg-center">
													<p class="lead mb-20">
														수량: &nbsp; &nbsp; <input type="number" step="1" min="1"
															max="10" name="cart" value="${cartList.productCount }"
															title="qty" class="form-control qty mr-10 rounded"
															id="quantityInput">
														<button class="btn btn-xs btn-primary pill"
															style="font-size: 15px">변경</button>
													</p>
												</div>
											</div>

											<!-- / column -->
										</div>
										<!-- / row -->
									</div>
									<!-- / cta -->
								</div> <!-- / promo-box --> </a>
							</li>
						</c:forEach>
					</ul>
				</div>

				<!-- / container -->



				<nav aria-label="pagination-center">
					<ul class="pagination justify-content-center">
						<!-- 이전 블록 이동 표시 -->
						<c:choose>
							<c:when test="${pager.startPage != 1 }">
								<li class="page-item"><a class="page-link"
									href="<c:url value='/infoboard/list${pager.searchCondition.getQueryString(pager.startPage-1)}'/>"><i
										class="fas fa-arrow-left mb-5"></i></a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link"
									href="#x"><i class="fas fa-arrow-left mb-5"></i></a></li>
							</c:otherwise>
						</c:choose>
						<!-- 페이지 목록 출력 -->
						<c:forEach var="i" begin="${pager.startPage}"
							end="${pager.endPage }">
							<c:choose>
								<c:when test="${i == pager.searchCondition.pageNum}">
									<li class="page-item active"><a class="page-link"
										href="<c:url value='/infoboard/list${pager.searchCondition.getQueryString(i)}'/>">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item"><a class="page-link"
										href="<c:url value='/infoboard/list${pager.searchCondition.getQueryString(i)}'/>">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!-- 다음 블록 이동 표시 -->
						<c:choose>
							<c:when test="${pager.endPage != pager.totalPage}">
								<li class="page-item"><a class="page-link"
									href="<c:url value='/infoboard/list${pager.searchCondition.getQueryString(pager.endPage+1)}'/>"><i
										class="fas fa-arrow-right mb-5"></i></a></li>
							</c:when>
							<c:otherwise>
								<li class="page-item disabled"><a class="page-link"
									href="#x"><i class="fas fa-arrow-right mb-5"></i></a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</nav>
			</main>
		</div>
	</div>
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
