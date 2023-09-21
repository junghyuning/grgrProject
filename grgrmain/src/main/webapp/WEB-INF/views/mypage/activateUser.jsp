<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>

</head>

<body>
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>

	<div id="top"></div>
	<!-- / top -->
	<!-- / navbar -->
	<section
		class="d-flex justify-content-center align-items-center vh-100">
		<div
			class="d-flex flex-column justify-content-center align-items-center vh-100">
			<div class="container relative mb-100">
				<h1 class="page-title text-center">휴면 계정 활성화</h1>
			</div>

			<div class="container d-flex align-items-center">
				<div class="col-md-12">
					<div class="card w-rised-icon lg-icon">
						<div class="card-body text-center">
							<div class="rised-icon bg-light icon-lg pos-center circle raised">
								<i class="fas fa-user text-primary"></i>
							</div>
							<!-- / rised-icon -->
							<h5 class="card-title mt-25">${loginNickname}회원님,</h5>
							<h5 class="card-title mt-25">3개월 이상 로그인하지 않아 휴면계정으로 전환되었습니다.</h5>
							<h5 class="card-title mt-25">계정을 활성화 하시겠습니까?</h5>

							<div class="card mt-50">
								<div class="card-body">
									<p>
										<i class="far fa-envelope-open m-x-5 text-black fs-20"></i> <span
											class="secondary-font fs-18">회원가입시 등록한 이메일을 입력해주세요</span>
									</p>
									<div class="d-block mt-30">
										<div class="input-group input-w-overlap-btn">
											<input type="text" class="form-control pill"
												placeholder="&#xf0e0; Email"
												style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400;" />
											<span class="input-group-btn">
												<button
													id = "chkEmailAccount"
													class="btn btn-sm btn-primary lh-0 overlapping-btn big-btn pill text-danger lead"
													data-bs-toggle="modal" data-bs-target=".default-modal"
													type="button">
													<i class="fas fa-envelope-open mr-5 text-danger regular"></i>
													이메일 확인
												</button>
											</span>
											<!-- / input-group-btn -->
										</div>
										<!-- / input-group -->
									</div>
									<!-- / d-block -->
								</div>
								<!-- / card-body -->
							</div>
							<!-- / card -->
						</div>
					</div>
					<!-- / card-body -->
				</div>
				<!-- / card-->
			</div>
		</div>
	</section>

	<div class="modal fade default-modal" tabindex="-1" role="dialog">
		<div class="modal-dialog modal-dialog-centered text-center">
			<div class="modal-content ">
				<div class="modal-header justify-content-center">
					<!-- 계정활성화 성공여부 - 타이틀 -->
					<h6 class="modal-title text-success">
						<i class="fas fa-unlock"></i>&nbsp;계정 활성화!
					</h6>
				</div>
				<!-- / modal-header -->
				<div class="modal-body">
					<!-- 계정활성화 성공여부 - 문장 -->
					<p>이메일이 일치하여 계정을 활성화 하였습니다.</p>
				</div>
				<!-- / modal-body -->
				<div class="modal-footer justify-content-center">
					<button type="button" class="btn btn-sm btn-info"
						data-bs-dismiss="modal">확인</button>
				</div>
				<!-- / modal-footer -->
			</div>
			<!-- / modal-content -->
		</div>
		<!-- / modal-dialog -->
	</div>


	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
	<script>
		
	</script>


</body>

</html>