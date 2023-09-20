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
		style="background-image: url(${pageContext.request.contextPath}/images/information.jpg); background-size: cover;">
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
						<c:forEach items="${cartList}" var="cartItem">
							<li class="col-md-12 col-lg-0 project"
								id="project_${cartItem.productCartNo}">
								<div class="promo-box mx-auto"
									style="min-height: 50px; padding: 20px;">
									<div class="cta p-0">
										<div class="row align-items-center">
											<div class="col-lg-1 text-center">
												<input type="checkbox" name="selectedItems"
													class="form-check-input" value="${cartItem.productCartNo}">
											</div>
											<div class="col-lg-2 text-center">
												<!-- 가운데 정렬을 위해 text-center 클래스 추가 -->
												<img
													src="${pageContext.request.contextPath}/images/coffee.jpg"
													class="rounded" />
											</div>
											<div class="col-lg-2">
												<a
													href="<c:url value='/productboard/get?productId=${cartItem.productId}'/>">
													<p class="mb-20 text-center">${cartItem.productTitle}</p>
												</a>
											</div>
											<div class="col-lg-2">
												<p class="lead mb-15 text-center">
													${cartItem.productPrice} &nbsp;<i class="fas fa-won-sign"></i>
												</p>
											</div>
											<div class="col-lg-2 text-center">
												<p class="lead mb-15 text-center">
													<input type="number" step="1" min="1" max="10" name="cart"
														value="${cartItem.productCount}" title="cart"
														class="form-control qty mr-10 rounded" id="quantityInput">
											</div>
											<div class="col-lg-2 text-center">
												<button class="btn btn-xs btn-primary pill qty-update-btn"
													style="font-size: 15px"
													onclick="updateCount(${cartItem.productCartNo})">수량
													변경</button>
											</div>

											<div class="col-lg-1 text-center">
												<button class="btn btn-xs btn-primary pill delete-btn"
													style="font-size: 15px"
													onclick="deleteCart(${cartItem.productCartNo})">삭제</button>
											</div>
										</div>
									</div>
								</div>
							</li>
						</c:forEach>


					</ul>
				</div>

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
				.mouseleave(function() {
					$(this).removeClass('hover');
				});
			}
		});
	</script>
	<script>
function deleteCart(productCartNo) {
    if (confirm("정말로 삭제하시겠습니까?")) {
        $.ajax({
            type: "POST",
            url: "<c:url value="/cart/delete"/>",
            data: {
                productCartNo: productCartNo
            },
            dataType: "text", 
            success: function(result) {
                if (result === "success") {
                    // 삭제 성공한 경우, 현재 항목을 화면에서 제거
                	   //$("#project_"+productCartNo).remove();
                    location.reload();
                } else {
                    // 삭제 실패한 경우, 오류 처리
                    alert("삭제 실패");
                }
            },
            error: function() {
                // 서버 오류 발생 시 처리 (예: 경고 메시지)
                alert("서버 오류 발생");
            }
        });
    }
}
</script>
	<script>
	function updateCount(productCartNo) {
		const quantityInput = document.getElementById('quantityInput');
	    const newQuantity = quantityInput.value;
	    
	    if (!quantityInput) {
	        alert("수량 입력 요소를 찾을 수 없습니다. productCartNo: " + productCartNo);
	        return;
	    }
	    $.ajax({
	        type: "POST",
	        url: "<c:url value="/cart/update"/>",
	        data: {
	            productCartNo: productCartNo,
	            productCount: newQuantity
	        },
	        dataType: "text",
	        success: function (result) {
	            if (result === "success") {
	                alert("변경 완료");
	               
	            } else {
	                alert("변경 실패");
	            }
	        },
	        error: function () {
	            alert("변경 오류.");
	        }
	    });
	}
</script>
</body>
</html>
