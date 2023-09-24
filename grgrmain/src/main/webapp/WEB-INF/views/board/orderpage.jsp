<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">

<head>

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

.board-images {
	width: 100px;
}

#comment-reply {
	margin-bottom: 20px; /* 아래쪽 여백을 20px로 설정 */
	clear: both;
}

.comment-style {
	font-size: 15px;
	font-weight: normal;
}

.comment {
	border-top: 1px solid #97989d;
}
</style>
</head>

<body>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="주문/결제" />
	<header class="xl bg-img bg-fixed"
		style="background-color: white; padding-bottom: 50px; padding-top: 80px;">
		<div class="container text-center" style="font-size: 100%;">
			<h1 class="page-title">주문/결제</h1>
			<p class="w-50 m-x-auto mb-30" style="color: white;">
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
	<div class="container relative z-index-1">
		<a class="breadcrumb-item" href="#" style="color: gray;">상품페이지/장바구니</a>
		<a class="breadcrumb-item" href="#">주문/결제</a> <span
			class="breadcrumb-item active" style="color: gray;">완료</span>
	</div>
	<section class="lg" style="padding-top: 30px;">
		<div class="container">
			<div class="promo-box" >
				<div class="row">
					<!-- 주문자 정보 확인 -->
					<!-- 
					<div class="col-lg-6" >
						<h6 class="mb-10">주문자정보</h6>
						<c:if test="${orderPage.loginUno == sesseionScope.loginUno }">
							<p class="card-text mt-15 mb-15">이름 : ${orderPage.userName }</p>
						</c:if>
						<c:if test="${orderPage.loginUno == sesseionScope.loginUno }">
							<p class="card-text mt-15 mb-15">핸드폰번호 : ${orderPage.phone }</p>
						</c:if>
						<c:if test="${orderPage.loginUno == sesseionScope.loginUno }">
							<p class="card-text mt-15 mb-15">이메일 : ${orderPage.email }</p>
						</c:if>
					</div>
					 -->
					<!-- / column -->

					<!-- 결제 상세 확인 -->
					<!-- 
					<div class="col-lg-6" >
						<h6 class="mb-10">결제 상세</h6>
						<c:if test="${not empty cartList}">
							<p class="card-text mt-15 mb-15">상품금액 :
								${cartList[0].productPrice }</p>
						</c:if>
						<c:forEach items="${cartList}" var="cart">

						</c:forEach>
					</div>
					 -->
					<!-- / column -->
				</div>

				<!-- / nav tabs -->
			</div>
			<!-- / tab-content -->
		</div>

		<br>

		<div class="container" style="text-align: center;">
			<div class="row">
				<div class="col-lg-12 tablet-lg-top-30 page-content">
					<div class="project-post">
						<div class="page-content">
							<!-- project : 장바구니 list 출력 -->
							<c:set var="i" value="0" />
							<div class="table-container">
								<table class="table table-xl">
									<thead class="thead-light">
										<tr>
											<th width="100">상품정보</th>
											<th width="100">판매자</th>
											<th width="100">수량</th>
											<th width="100">상품금액</th>
										</tr>
									</thead>
									<!-- 게시글 목록 출력 -->
									<c:forEach items="${cartOrderList}" var="cart">
										<tr>
										<!-- 
											<td width="100">${cart.productTitle }</td>
											<td width="100">${cart.uno }</td>
											<td width="100">${cart.productCount }</td>
											<td width="100">${cart.productPrice }</td>
											 -->
											<td width="100">${cart.orderGrouop }</td>
											<td width="100">${cart.productId }</td>
											<td width="100">${cart.orderQuantity }</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<!-- / owl-carousel -->
						</div>
						<!-- / page-content -->
					</div>
					<!-- / project-post -->
				</div>
				<!-- / column -->
			</div>
			<!-- / row -->
		</div>
		<a href="#x" class="btn btn-outline-primary m-y-10 mr-10">결제하기</a>
	</section>


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

	<!-- form validation -->
	<script
		src="${pageContext.request.contextPath}/assets/js/jquery.validate.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/form-validation.js"></script>
	<!-- end of form validation -->

	<!-- Owl Carousel -->
	<script
		src="${pageContext.request.contextPath}/assets/js/owl.carousel.min.js"></script>
	<script>
		$('#product-carousel').owlCarousel({
			loop : true,
			margin : 0,
			nav : false,
			dots : true,
			items : 1,
			animateIn : 'fadeIn',
			animateOut : 'fadeOut'
		});
	</script>
	<!-- / Owl Carousel -->


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
</body>
</html>