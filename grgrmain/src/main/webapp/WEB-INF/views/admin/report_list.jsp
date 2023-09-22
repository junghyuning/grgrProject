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
	color: #ffffff;
}
</style>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />

	<!-- 큰 컨테이너  -->
	<section class="big">
		<div class="container">
			<ul class="list-inline text-left mb-30">
				<li id="keyword-button"><a
					href="<c:url value='/admin/user-list' />"
					class="btn btn-outline-primary m-y-10 mr-10">회원목록</a></li>
				<li id="keyword-button"><a
					href="<c:url value='/admin/report_list' />"
					class="btn btn-outline-primary m-y-10 mr-10">신고목록</a></li>
			</ul>

		<div class="row">
					<div class="col-md-12">
						<h1>신고 게시글 조회</h1>
						<hr>
					</div>
				</div>
			<div>

				<table class="table table-xl">
					<thead class="thead-light">
						<tr>
							<td width="10" align="center">게시판</td>
							<td width="120" align="left">게시글 제목</td>
							<td width="10" align="center">작성자 ID</td>
							<td width="180" align="left">신고사유</td>
						</tr>
					</thead>
					
					
					<c:forEach var="reportList" items="${reportList}">
						<tr>
							<td width="10" align="center">${reportList.boardType }</td>
							<td width="120" align="left">
           						<c:choose>
									<c:when test="${reportList.boardType == 'QNA'}">
					                    <a href="<c:url value='/qnaboard/read?qnaBno=${reportList.bno}'/>">${reportList.title }</a>
					                </c:when>
					                <c:when test="${reportList.boardType == 'INFO'}">
					                    <a href="<c:url value='/infoboard/read?infoBno=${reportList.bno}'/>">${reportList.title }</a>
						            </c:when>
						    	</c:choose>
        					</td>
							<td width="10" align="center">${reportList.id }</td>
							<td width="180" align="left">${reportList.reportReason }</td>
						</tr>
					</c:forEach>
				</table>					

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
					<c:when test="${i == pager.pageNum}">
                		<li class="page-item"><span class="page-link">${i}</span></li>
					</c:when>
					<c:otherwise>
						<li class="page-item active"><a href="<c:url value='/admin/report_list'/>?pageNum=${i}" class="page-link">${i}</a>
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
	<!-- / container -->


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