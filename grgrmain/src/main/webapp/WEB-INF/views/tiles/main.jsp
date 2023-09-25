<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>

<style>
.card-body {
	max-height: 200px; /* 원하는 최대 높이 설정 */
	overflow: hidden; /* 초과된 내용 숨기기 */ text-overflow : ellipsis;
	/* 텍스트가 초과되면 '...'으로 표시 */
	white-space: normal;
	text-overflow: ellipsis; /* 줄 바꿈 허용 */
}
</style>

<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
<!-- / top -->

<h2 class="p-10 text-center mb-0 va-middle">
	<a
		href="<c:url value='/noticeboard/read?noticeBno=${latestNoticeBno}'/>"
		style="text-decoration: underline;"> <i class="fas fa-bullhorn"></i>
		${latestNoticeTitle}
	</a>
</h2>

<div id="top"></div>
<!-- / top -->


<div id="testimonial-slider"
	class="owl-carousel owl-theme carousel-controls main-banner">
	<div class="card bg-transparent b-0">
		<img class="m-x-auto mb-30"
			src="${pageContext.request.contextPath}/images/friends.jpg" alt=""
			style="width: 100%; height: 100%; object-fit: contain;">
	</div>
	<!-- / card -->
	<div class="card bg-transparent b-0">
		<img class="m-x-auto mb-30"
			src="${pageContext.request.contextPath}/images/main_page2.png" alt=""
			style="width: 100%; height: 100%; object-fit: contain;">
	</div>
	<!-- / card -->
	<div class="card bg-transparent b-0">
		<img class="m-x-auto mb-30"
			src="${pageContext.request.contextPath}/images/main_page3.png" alt=""
			style="width: 100%; height: 100%; object-fit: contain;">
	</div>
	<!-- / card -->
</div>
<!-- / testimonial-slider -->


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
<section class="big bg-white">
	<div class="section-title-area text-center relative z-index-1 mb-50">
		<h3 class="section-title">자유게시판</h3>
	</div>
	<!-- / section-title-area -->
	<div class="container"
		onclick="location.href='<c:url value="/freeboard/list"/>'">
		<!-- posts carousel -->
		<div class="posts-carousel owl-carousel owl-theme">
			<c:forEach var="free" items="${newFreeList}">
				<div class="card bg-img"
					style="background-image: url(
                    <c:choose>
                        <c:when test="${free.freeFileUpload == null}">
                            <c:url value='/images/vanner1.jpg'/>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/upload/${free.freeFileUpload}'/>
                        </c:otherwise>
                    </c:choose>
                );">
					<div class="card-header bg-transparent b-0"  style="visibility: hidden;">
						<p>
							<i class="fas fa-map-marker-alt promo-icon mr-5 text-warning "></i>
							<span class="secondary-font">${free.freeLoc}</span>
						</p>
					</div>

					<!-- / card-header -->
					<div class="card-body pt-150 pb-20">
						<a href="#x"
							class="fs-20 fw-bold d-block text-black primary-hover secondary-font mt-10 mb-0">${free.freeTitle}</a>
						<p class="card-text mt-10 mb-0">${free.freeContent}</p>
					</div>
					<!-- / card-body -->
				</div>
				<!-- / card -->
			</c:forEach>
		</div>
		<!-- / posts-carousel -->
	</div>
	<!-- / container -->
</section>

<!-- 정보공유 게시판 -->
<section class="big bg-white">
	<div class="section-title-area text-center relative z-index-1 mb-50">
		<h3 class="section-title">정보공유게시판</h3>

	</div>
	<!-- / section-title-area -->
	<div class="container"
		onclick="location.href='<c:url value="/infoboard/list"/>'">
		<!-- posts carousel -->
		<div class="posts-carousel owl-carousel owl-theme">
			<c:forEach var="info" items="${newInfoList}">
				<div class="card bg-img"
					style="background-image: url(
                    <c:choose>
                        <c:when test="${info.infoFileUpload == null}">
                            <c:url value='/images/vanner1.jpg'/>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/upload/${info.infoFileUpload}'/>
                        </c:otherwise>
                    </c:choose>
                );">
					<div class="card-header bg-transparent b-0">
						<p>
							<i class="fas fa-map-marker-alt promo-icon mr-5 text-warning "></i>
							<span class="secondary-font">${info.infoLoc}</span> <i
								class="fas fa-tag mr-5 text-primary"></i> <span
								class="secondary-font mr-15"> <c:choose>
									<c:when test="${info.infoKeyword=='food'}">맛집</c:when>
									<c:when test="${info.infoKeyword=='market'}">기타 후기</c:when>
									<c:when test="${info.infoKeyword=='living'}">생활정보</c:when>
									<c:when test="${info.infoKeyword=='crew'}">모임 / 구인</c:when>
									<c:otherwise>이벤트</c:otherwise>
								</c:choose>
							</span>
						</p>
					</div>
					<!-- / card-header -->
					<div class="card-body pt-150 pb-20">
						<a href="#x"
							class="fs-20 fw-bold d-block text-black primary-hover secondary-font mt-10 mb-0">${info.infoTitle}</a>
						<p class="card-text mt-10 mb-0">${info.infoContent}</p>
					</div>
					<!-- / card-body -->
				</div>
				<!-- / card -->
			</c:forEach>
		</div>
		<!-- / posts-carousel -->
	</div>
	<!-- / container -->
</section>


<!-- 판매 게시판 게시글 -->
<section class="big bg-white">
	<div class="section-title-area text-center relative z-index-1 mb-50">
		<h3 class="section-title">판매게시판</h3>
	</div>
	<!-- / section-title-area -->
	<div class="container"
		onclick="location.href='<c:url value="/productboard/list"/>'">
		<!-- posts carousel -->
		<div class="posts-carousel owl-carousel owl-theme">
			<c:forEach var="sales" items="${newSalesList}">
				<div class="card bg-img"
					style="background-image: url(
                    <c:choose>
                        <c:when test="${sales.productFileUpload == null}">
                            <c:url value='/images/vanner1.jpg'/>
                        </c:when>
                        <c:otherwise>
                            <c:url value='/upload/${sales.productFileUpload}'/>
                        </c:otherwise>
                    </c:choose>
                );">
					<div class="card-header bg-transparent b-0">
						<p>
							<i class="fas fa-tag mr-5 text-primary"></i> <span
								class="secondary-font">판매</span>
						</p>
					</div>
					<!-- / card-header -->

					<div class="card-body pt-150 pb-20">
						<a href="#x"
							class="fs-20 fw-bold d-block text-black primary-hover secondary-font mt-10 mb-0 ">${sales.productTitle}</a>
						<p class="card-text mt-10 mb-0 ">${sales.productContent}</p>
					</div>
					<!-- / card-body -->
				</div>
				<!-- / card -->
			</c:forEach>
		</div>
		<!-- / posts-carousel -->
	</div>
	<!-- / container -->
</section>

<!-- 위로 버튼 -->
<a href="#top" class="scroll-to-top" data-nav-status="toggle"><i
	class="fas fa-chevron-up"></i></a>

<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
<script>
	$(document).ready(function() {
		$("#testimonial-slider").owlCarousel({
			items : 1, // 보여질 슬라이드 수
			loop : true, // 무한 루프
			autoplay : true, // 자동 재생
		});
	});
</script>

</body>

</html>