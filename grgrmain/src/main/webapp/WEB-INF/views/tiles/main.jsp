<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="kor">
<head>
<style type="text/css">
.free-image-size {
	width: 100px !important;
	height: 100px !important;
}

.card-body {
	padding: 0;
}
</style>
</head>
<body style="background-color: white">
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>

	<div id="top"></div>
	<!-- / top -->


	<!-- 배너 이미지 -->
	<section class="big bg-white">
		<div class="container">
			<div
				class="col-md-12 bg-img box-bg-image tablet-top-30 rounded raised">
				<div class="row">
					<div
						class="col-md-12 bg-img box-bg-image tablet-top-30 rounded raised"
						style="background-image: url(${pageContext.request.contextPath}/images/vanner1.jpg); min-height:360px">
						<p class="mb-0"></p>
					</div>


				</div>
				<!-- / row -->
			</div>
			<!-- box-w-image -->
		</div>
		<!-- / container -->
	</section>

	<!-- 메인 카테고리 -->
	<div class="main-container" style="background-color: white">
		<section class="big bg-white" style="item-align: center;">
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-md-2">
						<div class="card-body text-center">
							<a href="<c:url value="/freeboard/list"/>"> <img
								src="${pageContext.request.contextPath}/images/free_logo.png"
								alt="자유게시판"
								style="height: 100px; width: 90px; padding-bottom: 20px;">
							</a>
							<h6 class="card-title fw-regular mb-20">자유</h6>
						</div>
					</div>
					<div class="col-md-2">
						<div class="card-body text-center">
							<a href="<c:url value="/infoboard/list"/>"> <img
								src="${pageContext.request.contextPath}/images/info_logo.png"
								alt="정보공유게시판"
								style="height: 100px; width: 90px; padding-bottom: 20px;">
							</a>
							<h6 class="card-title fw-regular mb-20">정보공유</h6>
						</div>
					</div>
					<div class="col-md-2">
						<div class="card-body text-center">
							<a href="<c:url value="productboard/list"/>"> <img
								src="${pageContext.request.contextPath}/images/sale_logo.png"
								alt="판매게시판"
								style="height: 100px; width: 90px; padding-bottom: 20px;">
							</a>
							<h6 class="card-title fw-regular mb-20">판매</h6>
						</div>
					</div>
					<div class="col-md-2">
						<div class="card-body text-center">
							<a href="<c:url value="/qnaboard/list"/>"> <img
								src="${pageContext.request.contextPath}/images/qna_logo.png"
								alt="문의게시판"
								style="height: 100px; width: 90px; padding-bottom: 10px;">
							</a>
							<h6 class="card-title fw-regular mb-20">문의</h6>
						</div>
					</div>
					<div class="col-md-2">
						<div class="card-body text-center">
							<a href="<c:url value="noticeboard/list"/>"> <img
								src="${pageContext.request.contextPath}/images/notice_logo.png"
								alt="공지게시판" style="width: 80px; padding-bottom: 10px;">
							</a>
							<h6 class="card-title fw-regular mb-20">공지</h6>
						</div>
					</div>
					<!-- / column -->
				</div>
				<!-- / row -->
			</div>
			<!-- / container -->
		</section>
	</div>
	<!-- 자유게시판 게시글 출력 -->
	<section class="lg" style="padding-top: 0px;">
		<div class="container">
			<div class="section-title-area text-center relative mb-70">
				<h3 class="section-title" style="text-align: center;">자유게시판</h3>
			</div>
			<!-- / portfolio -->
			<ul class="row">
				<c:forEach var="free" items="${newFreeList }">
					<li class="col-md-4">
						<div class="card-body">
							<a href="<c:url value="/freeboard/list"/>">
								<div class="card-body text-center">
									<img class="mb-30 w-90"
										src="${pageContext.request.contextPath}/images/vanner1.jpg"
										alt="끼리끼리 favicon">
									<h6 class="card-title fw-bold mb-10 text-black">${free.freeTitle }</h6>
									<p class="card-text mb-30">${free.freeContent }</p>

								</div>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<!-- / row -->
		</div>
		<!-- / container -->
	</section>

	<!-- 정보공유 게시판 -->
	<section class="lg" style="padding-top: 0px;">
		<div class="container">
			<div class="section-title-area text-center relative mb-70">
				<h3 class="section-title" style="text-align: center;">정보공유 게시판</h3>
			</div>
			<!-- / portfolio -->
			<ul class="row">
				<c:forEach var="info" items="${newInfoList }">
					<li class="col-md-4">
						<div class="card-body">
							<a href="<c:url value="/infoboard/list"/>">
								<div class="card-body text-center">
									<c:if test="${info.infoFileUpload==null }">
										<img class="mb-30 w-90"
											src="${pageContext.request.contextPath}/images/vanner1.jpg"
											alt="끼리끼리 favicon">
									</c:if>
									<c:if test="${info.infoFileUpload != null }">
										<img class="mb-30 w-90"
											src="${pageContext.request.contextPath}/upload/${info.infoFileUpload}"
											alt="Thumbnail">
									</c:if>
									<h6 class="card-title fw-bold mb-10 text-black">${info.infoTitle }</h6>
									<p class="card-text mb-30">${info.infoContent }</p>
									<p class="fs-12 post-meta-small mt-15 mb-0">
										<i class="fas fa-tag mr-10"></i>
										<c:choose>
											<c:when test="${info.infoKeyword=='food'}">맛집</c:when>
											<c:when test="${info.infoKeyword=='market'}">기타 후기</c:when>
											<c:when test="${info.infoKeyword=='living'}">생활정보</c:when>
											<c:when test="${info.infoKeyword=='crew'}">모임 / 구인</c:when>
											<c:otherwise>이벤트</c:otherwise>
										</c:choose>
									</p>
								</div>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<!-- / row -->
		</div>
		<!-- / container -->
	</section>


	<!-- 판매 게시판 게시글 -->
	<section class="lg" style="padding-top: 0px;">
		<div class="container">
			<div class="section-title-area text-center relative mb-70">
				<h3 class="section-title" style="text-align: center;">판매 게시판</h3>
			</div>
			<!-- / portfolio -->
			<ul class="row">
				<c:forEach var="sales" items="${newSalesList }">
					<li class="col-md-4">
						<div class="card-body">
							<a href="<c:url value="/productboard/list"/>">
								<div class="card-body text-center">
									<img class="mb-30 w-90"
										src="${pageContext.request.contextPath}/images/vanner1.jpg"
										alt="끼리끼리 favicon">
									<h6 class="card-title fw-bold mb-10 text-black">${sales.productTitle }</h6>
									<p class="card-text mb-30">${sales.productContent }</p>
									<p class="fs-12 post-meta-small mt-15 mb-0"></p>
								</div>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<!-- / row -->
		</div>
		<!-- / container -->
	</section>

	<!-- 위로 버튼 -->
	<a href="#top" class="scroll-to-top smooth-scroll"
		data-nav-status="toggle"><i class="fas fa-chevron-up"></i></a>
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

</body>

</html>