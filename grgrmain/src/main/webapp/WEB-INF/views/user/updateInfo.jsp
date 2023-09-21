<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- Meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Soft UI - Neumorphism Style UI Kit">
<meta name="author" content="kingstudio.ro">
<!-- Favicon -->
<link rel="icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.png">
<!-- Site Title -->
<title>Soft UI - Neumorphism Style UI Kit</title>
<!-- Bootstrap 4 core CSS -->
<link
	href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Custom Styles -->
<link href="${pageContext.request.contextPath}/assets/css/animate.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/assets/css/style.css"
	rel="stylesheet">
<!-- Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;600;800&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@300;600;800&display=swap"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/assets/css/fontawesome-all.min.css"
	rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/css/login-register.css"
	rel="stylesheet">
<style>
.phone_input_re {
	color: red;
	display: none;
}
</style>
</head>

<body>

	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>

	<a href="${pageContext.request.contextPath}/index.html"
		class="top-logo"><img
		src="${pageContext.request.contextPath}/assets/images/logo-main.png"
		alt=""></a>

	<div class="full-height relative p-y-30 v-center bg-img"
		style="background-image: url(${pageContext.request.contextPath}/assets/images/placeholder.jpg)">
		<div class="container p-y-70">
			<div class="m-x-auto" style="max-width: 500px;">
				<div class="card mb-15">
					<div class="card-body p-x-25">
						<ul class="nav nav-tabs nav-pills text-center p-x-50"
							role="tablist">
							<li class="nav-item m-x-auto"><a
								class="nav-link active p-x-40" data-bs-toggle="tab"
								href="#tab-sign-in" role="tab"> <i
									class="fas fa-lock mr-5 va-middle"></i> <span class="va-middle">기본
										정보 설정</span>
							</a></li>
						</ul>
						<!-- / nav tabs -->
						<div class="tab-content">
							<div class="tab-pane fade show active mt-20 p-15"
								id="tab-sign-in" role="tabpanel">
								<form id="updateForm" action="/oauth/updateKakaoInfo"
									method="post">
									<!-- 폼 필드 수정 -->
									<div class="form-group">
										<label for="nickName">닉네임:</label> <input type="text"
											class="form-control" name="nickName" id="nickName"
											placeholder="&#xf2bd; 닉네임을 입력하세요"
											style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400">
										<span class="final_nickName_ck">닉네임을 입력해주세요.</span>
									</div>
									<div class="form-group">
										<label for="phone">연락처:</label> <input type="text"
											class="form-control" name="phone" id="phone"
											placeholder="&#xf2bd; 연락처를 입력하세요"
											style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400">
										<span class="phone_input_re">형식에 맞는 전화번호를
											입력해주세요.(010-xxxx-xxxx)</span> <span class="final_phone_ck">전화번호를
											입력해주세요.</span>
									</div>

									<!-- 서브밋 버튼 수정 -->
									<div class="col-lg-7 text-right">
										<input type="hidden" name="kakaoId" value="${user.kakaoId}" />
										<button type="submit" class="btn btn-primary"
											id="updateButton">변경하기</button>
									</div>

									<!-- / row -->
								</form>

								<!-- / column -->
							</div>
							<!-- / row -->
						</div>
					</div>
					<!-- / tab-content -->
				</div>
				<!-- / card-body -->
			</div>
			<!-- / card -->
		</div>
	</div>

	<!-- / container -->
	<p class="absolute bottom-15 x-0 text-center">
		<a href="${pageContext.request.contextPath}/index.html"
			class="text-link fs-16 m-x-15">Home</a> <a
			href="${pageContext.request.contextPath}/components.html"
			class="text-link fs-16 m-x-15">Components</a> <a
			href="${pageContext.request.contextPath}/sections.html"
			class="text-link fs-16 m-x-15">Sections</a> <a
			href="${pageContext.request.contextPath}/templates.html"
			class="text-link fs-16 m-x-15">Templates</a>
	</p>


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

	<!-- form validation -->
	<script>
	// 페이지 이동 form(리스트 페이지 이동, 조회 페이지 이동)
	let mForm = $("#updateForm"); // 페이지 데이터 수정 form
	let phoneFormCheck = false;
	let nickNameNullCheck = false;
	let phoneNullCheck = false;

	// 수정 하기 버튼 클릭 이벤트
	$("#updateButton").on("click", function(e) {
		// 닉네임 입력 필드 값 가져오기
		let nickNameInput = $("#nickName").val();
		let phoneInput = $("#phone").val();
		// 전화번호 형식 검사

		var userPhone = $("#phone").val();
		// 전화번호 정규식을 검사하여 메시지를 설정
		if (/^010-[0-9]{4}-[0-9]{4}$/.test(userPhone)) {
			$('.phone_input_re').css('display', 'none');
			phoneFormCheck = true;
		} else {
			e.preventDefault();
			$('.phone_input_re').css('display', 'block');
			phoneFormCheck = false;
		}

		// 닉네임이 비어있는 경우 알림 메시지 표시
		if (nickNameInput === "") {
			e.preventDefault(); // 기본 동작 방지
			$(".final_nickName_ck").css("display", "block"); // 알림 메시지 표시
			nickNameNullCheck = false;
		} else {
			$(".final_nickName_ck").css("display", "none"); // 알림 메시지 감춤
			nickNameNullCheck = true;
		}

		// 전화번호가 비어있는 경우 알림 메시지 표시
		if (phoneInput === "") {
			e.preventDefault(); // 기본 동작 방지
			$(".final_phone_ck").css("display", "block"); // 알림 메시지 표시
			phoneNullCheck = false;
		} else {
			$(".final_phone_ck").css("display", "none"); // 알림 메시지 감춤
			phoneNullCheck = true;
		}

		// 모든 입력 값이 비어 있지 않고 전화번호 형식도 올바른 경우 폼 제출
		if (nickNameNullCheck && phoneNullCheck && phoneFormCheck) {
			mForm.submit();
		}
	});
</script>


	<!-- end of custom script -->

	<!-- end of custom script -->

</body>

</html>
