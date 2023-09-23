<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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
	font-weight: "bold"
}

.uploaded-img {
	width: 300px;
	height: auto;
}
</style>
</head>
<body>
	<div id="preloader">
		<div class="preloader">
			<span></span> <span></span>
		</div>
	</div>
	<!-- 헤더 -->
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
	<!-- 배너 -->
	<c:set var="boardName" value="자유게시판" />
	<header class="xl bg-img bg-fixed"
		style="background-image: url(${pageContext.request.contextPath}/images/free2.jpg); background-size: cover;">
		<div class="container text-center">
			<h1 class="page-title">Free</h1>
			<p class="w-50 m-x-auto mb-30" style="color: white;">
				<c:out value="${boardName}" />
			</p>
		</div>
		<!-- / container -->
	</header>


	<div id="top"></div>
	<!-- / top -->

	<script type="text/javascript">
		
	</script>
	<!-- 큰 컨테이너  -->
	<section class="lg bg-light-grey">
		<div class="container">
			<div class="w-90 m-x-auto mt-70">
				<h4 class="mb-30 text-left">글수정</h4>
				<c:if test="${not empty msg}">
					<div class="alert alert-danger">${msg}</div>
				</c:if>
				<form action="modify" method="post" class="validation-inner"
					id="form-validation" novalidate="novalidate">
					<input type="hidden" name="uno" value="${freeBoard.uno}" /> <input
						type="hidden" name="freeBno" value="${freeBoard.freeBno}" /> <input
						type="hidden" name="freeUpdateUno"
						value="${sessionScope.loginUno}" /> <input type="hidden"
						name="freeLoc" value="${freeBoard.freeLoc}" />
					<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								<input type="text" class="form-control" id="contact-email"
									name="freeTitle" required
									style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; color: #000;"
									aria-required="true" value="${freeBoard.freeTitle}">
							</div>
							<!-- / form-group -->
						</div>
						<!-- / column -->
						<!-- 글쓰기 영역 -->
						<div class="form-group">
							<textarea id="contact-message" class="form-control"
								name="freeContent" rows="8" required
								style="font-family: 'Font Awesome 5 Free', sans-serif !important; font-weight: 400; min-height: 500px; max-height: 500px; color: #000;"
								aria-required="true">${freeBoard.freeContent}</textarea>
						</div>

						<!-- / form-group -->
					</div>
					<!-- / column -->
					<!-- 사진업로드 버튼 -->
					<div>
						<input type="file" name="files" multiple="multiple"
							accept="image/*" id="file-button" style="display: none;">
						<div class="btn btn-instagram m-y-10 mr-10"
							onclick="document.getElementById('file-button').click()">
							<span class="mr-5"><i class="fab fa-instagram"></i></span> <span>사진업로드</span>
							<span id="img-error-message" style="color: red; font-size: 15px;"></span>
							<!-- 이미지 미리보기 영역 -->
						</div>
						<div id="imagePreviewContainer" class="mb-10"></div>
					</div>
					<!-- 글 목록/ 수정 버튼 -->
					<div style="text-align: right;">
						<a
							href="<c:url value='/freeboard/list${searchCondition.getQueryString()}'/>"
							target="_blank">
							<button type="button" class="btn btn-primary-gradient">글목록</button>
						</a> <a
							href="<c:url value='/freeboard/list${searchCondition.getQueryString()}'/>">
							<button type="button" class="btn btn-primary-gradient">수정취소</button>
						</a>
						<button type="button" id="modify-submit"
							class="btn btn-primary-gradient">수정</button>
					</div>
					<!-- 기존 업로드됐던 파일들 -->
					<c:forEach var="file" items="${freeFiles}">
						<div data-hide="false">
							<img class="uploaded-img"
								src="<c:url value="/upload/${file.freeFileUpload}"/>"
								alt="${file.freeFileOrigin }">
							<!-- ${file.freeFileNo}사용해야함 -->
							<a class="btn btn-xs btn-danger m-y-10 mr-10 delete-file"
								data-file-no="${file.freeFileNo}">이미지 삭제</a>
						</div>
					</c:forEach>
				</form>
				<!-- / form-group -->
			</div>
		</div>
	</section>
	<!-- / pagination-center -->

	<a href="#top" class="scroll-to-top is-hidden" data-nav-status="toggle"><i
		class="fas fa-chevron-up"></i></a>

	<!-- footer 영역 -->
	<jsp:include page="/WEB-INF/views/tiles/footer.jsp" />
	<!-- 사진 삭제 -->
	<script>
		//파일 업로드 input태그에서 선택한 파일을 저장하기위한 변수
		var files;
		//이미지가 선택되고나면 실행될 이벤트 리스너 
		document.getElementById('file-button').addEventListener(
				'change',
				function(event) {
					//이벤트실행시 선택된 파일의정보를 파일에 저장함
					files = event.target.files;
					var previewContainer = document
							.getElementById('imagePreviewContainer');
					previewContainer.innerHTML = '';

					for (var i = 0; i < files.length; i++) {
						var file = files[i];
						//파일을 읽어오기위해 FileReader 객체 생성
						var reader = new FileReader();
						reader.onload = (function(file) {
							return function(e) {
								var div = document.createElement('div');
								div.style.display = 'inline-block';
								div.style.marginRight = '10px';

								var img = document.createElement('img');
								img.src = e.target.result;
								img.alt = "Image Preview";
								img.width = 30;
								div.appendChild(img);
								previewContainer.appendChild(div);
							};
						})(file);

						//파일리더를 통해 읽어온 파일 데이터를 URL 형태로 가져옴
						reader.readAsDataURL(file);
					}
				});
	</script>


	<script>
	$(document).ready(function() {
	    // 파일 화면에서 hide()
	    var deleteFileList = [];

	    $('.delete-file').click(function() {
	        var $selectedImg = $(this).closest('div');
	        var fileNo = $(this).data('file-no');
	        deleteFileList.push(fileNo);
	        $(this).closest('div').attr("data-hide", "true").hide();
	    });

	    // 파일 형식 검사
	    function isImageFile(file) {
	        const validImageTypes = ['image/gif', 'image/jpeg', 'image/png', 'image/jpg'];
	        return file && validImageTypes.includes(file.type);
	    }

	    document.querySelector('#modify-submit').addEventListener('click', function() {
	        var freeBoardUno = "${freeBoard.uno}";
	        var loginUno = "${sessionScope.loginUno}";

	        // 권한이 없는 사용자가 get방식으로 페이지를 요청하여 수정하는 것 방지
	        if (freeBoardUno !== loginUno) {
	            window.location.href = "<c:url value='/404'/>";
	            return;
	        }

	        var title = document.getElementsByName('freeTitle')[0].value;
	        var content = document.getElementsByName('freeContent')[0].value;
	        var contentErrorMessage = "";
	        var imgErrorMessage = "";

	        if (title.trim() === '' || content.trim() === '') {
	            contentErrorMessage = '제목과 내용을 모두 입력해주세요.';
	        }

	        if (files && files.length > 0 && !Array.from(files).every(isImageFile)) {
	            imgErrorMessage = '유효하지 않은 파일 형식입니다. 이미지 파일만 업로드 해주세요.';
	        }

	        if (contentErrorMessage !== "") {
	            document.getElementById('content-error-message').textContent = contentErrorMessage;
	            $('#content-error-message').show();
	            setTimeout(function() {
	                $('#content-error-message').fadeOut('slow');
	            }, 5000);
	        }

	        if (imgErrorMessage !== '') {
	            document.getElementById('img-error-message').textContent = imgErrorMessage;
	            $('#img-error-message').show();
	            setTimeout(function() {
	                $('#img-error-message').fadeOut('slow');
	            }, 5000);
	        }

	        if (contentErrorMessage === "" && imgErrorMessage === "") {
	            $.ajax({
	                type: 'DELETE',
	                url: "<c:url value='/freefile/delete'/>",
	                contentType: "application/json",
	                data: JSON.stringify(deleteFileList),
	                success: function(data) {
	                    document.getElementById('form-validation').submit(); // 폼을 제출
	                },
	                error: function(err) {
	                    console.error("파일 삭제에 실패하였습니다.", err.responseText);
	                }
	            });
	        }
	    });

	    setTimeout(function() {
	        $('#error-message').fadeOut('slow');
	    }, 5000);
	});
	</script>
	<!-- / portfolio script -->
</body>
</html>
