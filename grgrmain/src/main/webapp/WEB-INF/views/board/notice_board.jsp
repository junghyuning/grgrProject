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
	font-weight: 'bold';
}
.board-images{
	width: 100px;
}
</style>
<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp"/>
	<!-- 배너 -->
	<c:set var="boardName" value="공지게시판" />
   <header class="xl bg-img bg-fixed" style="height: 300px; padding-top: 200px;">
      <div class="container text-center">
         <h1 class="page-title">Notice</h1>
         <p class="w-50 m-x-auto mb-30"><c:out value="${boardName}" /></p>
      </div>
      <!-- / container -->
   </header>
   
<div class="container">
    <div class="row">
    
    <jsp:include page="/WEB-INF/views/tiles/sidebar.jsp"/>   

	<!-- 큰 컨테이너  -->

		<div class="container col-md-10">
			<div class="w-90 m-x-auto mt-20">
				<div class="col-xl-auto">
					<a href="#x"
						class="d-inline title-color primary-hover fs-24 fw-bold mb-15"
						style="margin: 10px">${noticeBoard.noticeTitle} </a>
					<c:if test="${sessionScope.loginUserStatus==1 }">
						<!-- 수정버튼 제출시 제출된 게시글로 진입 + 자신의 게시글에서 글목록 누를시 원래보던 페이지로 이동할 것  -->
						<a
							href="<c:url value='/noticeboard/modify${searchCondition.getQueryString()}&noticeBno=${noticeBoard.noticeBno}'/>"
							class="btn btn-xs btn-primary pill"
							style="float: right; font-size: 15px; margin: 10px"><span>수정</span></a>
					</c:if>
					
					<c:if test="${sessionScope.loginUserStatus==1 }">
						<!-- 관리자일 경우 해당 글 삭제할 수 있음 -->
						<a
							href="<c:url value='/noticeboard/remove${searchCondition.getQueryString()}&noticeBno=${noticeBoard.noticeBno}'/>"
							class="btn btn-xs btn-primary pill"
							style="float: right; font-size: 15px; margin: 10px"><span>삭제</span></a>
					</c:if>
					<p
						class="fs-12 post-meta-small p-y-15 pl-15 mb-15 border-secondary"
						style="clear: both; padding: 10px">

						<i class="fas fa-user mr-5"></i>관리자 <span
							class="mr-5 ml-5 text-muted">|</span> <i 
							class="fas fa-calendar-alt mr-5"></i>${noticeBoard.noticeRegdate }
							<span class="mr-5 ml-5 text-muted">|</span>
							<span>조회수</span>
						<span class="mr-5 ml-5 text-muted" style="color: #6c757d!important;">${noticeBoard.noticeViewCnt}</span>
					</p>
					
					<p class="m-y-30">${noticeBoard.noticeContent}</p>
					
					<!-- 보던페이지로 이동 -->
					<a
						href="<c:url value='/noticeboard/list${searchCondition.getQueryString()}' />"
						class="btn btn-xs btn-primary pill"
						style="float: right; font-size: 15px"><span>글목록</span></a>
				</div>
				<!-- / column -->
			
				<!-- 이전글, 다음글 -->
				<nav aria-label="pager" style="clear: both; padding-top: 30px">
					<ul class="pager">
						<c:if test="${!isFirstPost }">
							<li class="pager-left"><a
								href="<c:url value='/noticeboard/read?${searchCondition.getQueryString()}&noticeBno=${prevNoticeBno}'/>"><i
									class="fas fa-arrow-left"></i> <span class="ml-5">이전글</span></a></li>
						</c:if>
						<c:if test="${isFirstPost }">
							<li class="pager-left disabled"><i class="fas fa-arrow-left"></i>
								<span class="ml-5">이전글</span></li>
						</c:if>

						<c:if test="${!isLastPost}">
							<li class="pager-right"><a
								href="<c:url value='/noticeboard/read?${searchCondition.getQueryString()}&noticeBno=${nextNoticeBno}'/>"><span
									class="mr-5">다음글</span> <i class="fas fa-arrow-right"></i></a></li>
						</c:if>

						<c:if test="${isLastPost}">
							<li class="pager-right disabled"><span class="mr-5">다음글</span>
								<i class="fas fa-arrow-right"></i></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</div>

	</div>
</div>
	<!-- / pagination-center -->

	<!-- 위로 스크롤 -->
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
	
		$(document).ready(function() {
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
				.mouseleave(function() { // 추가된 부분
					$(this).removeClass('hover');
				}); // 추가된 부분
			}
		});
	</script>
	<!-- / portfolio script -->
</body>
</html>
