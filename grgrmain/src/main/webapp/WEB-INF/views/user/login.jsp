<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style>
.socialLogin img:hover {
	cursor: pointer;
	filter: brightness(1.45);
	transition: filter 0.3s ease-in-out;
}

.socialIcon {
	width: auto; /* 원하는 너비의 백분율로 설정 */
	height: 30;
}
</style>
</head>

<body>
	<div id="top"></div>

	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	

	<a href="${pageContext.request.contextPath}/index.html"
		class="top-logo"> <img
		src="${pageContext.request.contextPath}/assets/images/logo-main.png"
		alt=""></a>

	<div class="full-height relative p-y-30 v-center bg-img"
		style="background-image: url(/assets/images/placeholder.jpg)">
		<div class="container p-y-70">
			<div class="m-x-auto" style="max-width: 500px;">
				<div class="card mb-15">
					<div class="card-body p-x-25">
						<ul class="nav nav-tabs nav-pills text-center p-x-50"
							role="tablist">
							<li class="nav-item"><a class="nav-link active p-x-40"
								href="<c:url value="/user/login"/>"> <i
									class="fas fa-lock mr-5 va-middle"></i> <span class="va-middle">로그인</span>
							</a></li>
							<li class="nav-item"><a class="nav-link active p-x-40"
								href="<c:url value="/user/register"/>"> <i
									class="fas fa-user mr-5 va-middle"></i> <span class="va-middle">회원가입</span>
							</a></li>
						</ul>

						<!-- / nav tabs -->
						<div class="tab-content">
							<div class="tab-pane fade show active mt-20 p-15"
								id="tab-sign-in" role="tabpanel">
								<form class="validation-inner" id="login-form3"
									action="${pageContext.request.contextPath}/user/login"
									method="post">
									<div class="form-group">
										<input type="text" class="form-control" name="userId"
											placeholder="&#xf2bd; 아이디"
											style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400">
									</div>
									<!-- / form-group -->

									<div class="form-group">
										<input type="password" class="form-control" name="userPw"
											placeholder="&#xf11c; 비밀번호" required
											style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400">
									</div>
									<c:if test="${result == 0 }">
										<div class="login_warn">사용자 ID 또는 비밀번호를 잘못 입력하셨습니다.</div>
									</c:if>
									<!-- / form-group -->

									<!-- 아이디 찾기와 비밀번호 찾기 링크 -->
									<div class="forgot-links">
										<a href="${pageContext.request.contextPath}/user/find-id"
											class="forgot-link">아이디 찾기</a> <span class="divider">|</span>
										<a
											href="${pageContext.request.contextPath}/user/forgot-password"
											class="forgot-link">비밀번호 찾기</a>
									</div>

									<div class="row v-center mt-30">
										<div class="col-lg-5 tablet-lg-top-30">
											<!-- / checkbox -->
										</div>
										<!-- / column -->
										<div class="col-lg-7 text-right">
											<button type="submit"
												class="btn btn-sm btn-primary tablet-lg-fw">
												<i class="fas fa-sign-in-alt mr-5"></i> Sign In
											</button>
										</div>
										<!-- / column -->
									</div>
									<!-- / row -->
								</form>
								<!-- / login-form -->
								<div class="pt-60 text-center">
									<p style="gap:3px">간편 로그인</p>
										<div class="p-5"><img class="socialIcon"
											src="<c:url value="/images/naver_login.png"/>" alt="네이버 로그인"
											onclick="location.href='<c:url value="/oauth/naver"/>';"></div>
										<div class="p-5"><img class="socialIcon  w-70"
											src="<c:url value="/images/kakaoBtn.png"/>" alt="카카오 로그인"
											onclick="location.href='<c:url value="/oauth/kakao"/>';"></div>
										<div ><img class="socialIcon w-70"
											src="<c:url value="/images/google_OAuth_btn.png"/>"
											alt="구글 로그인"
											onclick="location.href='<c:url value="/oauth/google"/>';"></div>
									
								</div>

							</div>
						</div>
					</div>
					<!-- / tab-content -->
				</div>
				<!-- / card-body -->
			</div>
			<!-- / card -->
		</div>
	</div>

	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />

</html>
