<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Bootstrap 4, from LayoutIt!</title>

<meta name="description"
	content="Source code generated using layoutit.com">
<meta name="author" content="LayoutIt!">

<link href="${pageContext.request.contextPath}/css/style.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.2/jquery.modal.min.js"></script>

<style>
/* 페이저 스타일 */
.pagination {
    display: flex;
    justify-content: center;
    align-items: center;
    list-style: none;
    padding: 0;
    margin-top: 20px;
}

.pagination li {
    margin: 0 5px;
    display: inline-block;
}

.pagination a {
    text-decoration: none;
    padding: 5px 10px;
    border: 1px solid #ccc;
    background-color: #fff;
    color: #333;
    border-radius: 5px;
}

.pagination a:hover {
    background-color: #333;
    color: #fff;
}
.table-container {
    
    display: flex;
    justify-content: center;
    
  }
th, td {
	text-align: center;
	padding: 8px; /* 셀 내부 패딩 값 조절 */
}
.custom-margin{
	margin-botom:100px;
}

.free-image-size {
	width: 100px !important;
	height: 100px !important;
}

.card-body {
	padding: 0;
}
table.table-xl th,
  table.table-xl td {
    text-align: center;
    vertical-align: middle;
  }

  /* 테이블 행 간격 조정 */
  table.table-xl tbody tr {
    margin-bottom: 10px;
    }
  
</style>
</head>
<body  style="background-color: white">
	<jsp:include page="/WEB-INF/views/tiles/header.jsp" />
			<div class="col-md-10">
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
						<h1>회원 목록</h1>
						
						<hr>
					</div>
				</div>

				<div class="table-container">
				<table class="table table-xl">
					<thead class="thead-light">
					<tr>
						<th width="100"  >회원번호</th>
						<th width="100">이름</th>
						<th width="100">닉네임</th>
						<th width="100">아이디</th>
						<th width="100">휴대폰</th>
						<th width="100">이메일</th>
						<th width="100">회원 등급</th>
						<th width="100">활동 상태</th>
						<th width="100">포인트</th>
						<th width="100">사업자 등록번호</th>
						<th width="100">마지막 로그인</th>
						<th width="100">가입 날짜</th>
						<th width="100">수정 날짜</th>
						<th width="100">최근 수정 회원</th>
					</tr>
					</thead>
					<!-- 게시글 목록 출력 -->
					<c:forEach var="user" items="${userList}">
						<tr>
							<td  width="100">${user.uno }</td>
							<td width="100" class="user-name-cell"
								data-user-id="${user.uno}">${user.userName}</td>
							<td align="center">${user.nickName }</td>
							<td align="center">${user.userId }</td>
							<td align="center">${user.phone }</td>
							<td align="center">${user.email }</td>
							<td align="center">${user.userStatus }</td>
							<td align="center">${user.active }</td>
							<td align="center">${user.point }</td>
							<td align="center">${user.businessNo }</td>
							<td align="center"><fmt:formatDate value="${user.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            				<td align="center"><fmt:formatDate value="${user.regDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            				<td align="center"><fmt:formatDate value="${user.userUpdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td align="center">${user.userName }</td>
						</tr>
					</c:forEach>
				</table>
				</div>
				<!-- 페이지 번호 출력 - 링크 제공 -->
				<div class="pagination">
    <c:choose>
        <c:when test="${pager.startPage > pager.blockSize }">
            <a href="<c:url value="user-list"/>?pageNum=${pager.prevPage}" class="page-link">이전</a>
        </c:when>
        <c:otherwise>
            <span class="page-link">이전</span>
        </c:otherwise>
    </c:choose>

    <c:forEach var="i" begin="${pager.startPage }" end="${pager.endPage }" step="1">
        <c:choose>
            <c:when test="${pager.pageNum != i  }">
                <a href="<c:url value="user-list"/>?pageNum=${i}" class="page-link">${i }</a>
            </c:when>
            <c:otherwise>
                <span class="page-link">${i }</span>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:choose>
        <c:when test="${pager.endPage != pager.totalPage }">
            <a href="<c:url value="user-list"/>?pageNum=${pager.nextPage}" class="page-link">다음</a>
        </c:when>
        <c:otherwise>
            <span class="page-link">다음</span>
        </c:otherwise>
    </c:choose>
</div>

			</div>
		</div>
		
	</div>
<hr>
	<!-- Modal -->
	<div class="modal fade" id="userModal" tabindex="-1" role="dialog"
		aria-labelledby="userModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="userModalLabel">사용자 정보</h4>
					<hr>
				</div>
				<div class="modal-body">
					<!-- 기존 사용자 정보 출력 -->

					<!-- 추가적인 사용자 정보도 여기에 표시 -->

					<!-- 추가: 수정 폼 -->
					<form id="userEditForm">
						
						<p><strong>회원 번호 :</strong> <span id="modal-uno"></span></p>
						<input type="hidden" id="modal-uno-input">

						<div class="custom-margin" >
							<label for="modal-username-input">이름 :</label> <input type="text"
								id="modal-username-input" name="userName">
						</div>

						<div class="custom-margin">
							<label for="modal-nickname-input">닉네임 :</label> <input type="text"
								id="modal-nickname-input" name="nickName">
						</div>

						<div class="custom-margin">
							<label for="modal-userid-input">아이디:</label> <input type="text"
								id="modal-userid-input" name="userId">
						</div>
						<div class="custom-margin">
							<label for="modal-phone-input">휴대폰 :</label> <input type="text"
								id="modal-phone-input" name="phone">
						</div>
						<div class="custom-margin">
							<label for="modal-email-input">이메일 :</label> <input type="text"
								id="modal-email-input" name="email">
						</div>
						<div class="custom-margin">
							<label for="modal-userStatus-input">회원 등급 :</label> <input type="text"
								id="modal-userStatus-input" name="userStatus">
						</div>
						<div class="custom-margin">
							<label for="modal-active-input">활동 상태 :</label> <input type="text"
								id="modal-active-input" name="active">
						</div>
						<div class="custom-margin">
							<label for="modal-point-input">포인트 :</label> <input type="text"
								id="modal-point-input" name="point">
						</div>
						<div class="custom-margin">
							<label for="modal-businessNo-input">사업자 등록 번호 :</label> <input type="text"
								id="modal-businessNo-input" name="businessNo">
						</div>
						<p><strong>마지막 로그인 :</strong> <span id="modal-lastLogin"></span></p>
						<p><strong>가입 날짜 :</strong> <span id="modal-regDate"></span></p>
						<p><strong>수정 날짜 :</strong> <span id="modal-userUpdate"></span></p>
						<p><strong>최근 수정 회원 :</strong> <span id="modal-userUpdateUno"></span></p>
					</form>
					
				</div>
				<div class="modal-footer">
					<!-- 수정 버튼 -->
					<button type="button" class="btn btn-primary" id="editUserButton">수정하기</button>
				</div>
			</div>
		</div>
	</div>

<div class="col-md-10">
				<div class="row">
					<div class="col-md-12">
						<h1>신고 게시글 조회</h1>
						<hr>
					</div>
				</div>


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
	
	<script type="text/javascript">
    var userList = ${userListJson};

 	// 날짜 형식 변환 함수
    function formatDate(date) {
        var options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit', timeZone: 'Asia/Seoul' };
        return new Date(date).toLocaleDateString('ko-KR', options);
    }
    
 	var clickedUserId; // 클릭한 사용자의 userId 저장용 변수

    jQuery(document).ready(function() {
        jQuery('.user-name-cell').click(function() {
            var userId = jQuery(this).data('user-id');
            var user = userList.find(u => u.uno === userId);

            if (user) {
                clickedUserId = userId; // 클릭한 사용자의 userId 저장
                var modalBody = jQuery('#userModal').find('.modal-body');

                jQuery('#modal-uno').text(user.uno);
	
             	//입력 필드에 기존 정보를 기본값으로 설정
                $('#modal-username-input').val(user.userName);
                $('#modal-nickname-input').val(user.nickName);
                $('#modal-userid-input').val(user.userId);
                $('#modal-phone-input').val(user.phone);
                $('#modal-email-input').val(user.email);
                $('#modal-userStatus-input').val(user.userStatus);
                $('#modal-active-input').val(user.active);
                $('#modal-point-input').val(user.point);
                $('#modal-businessNo-input').val(user.businessNo);
                
                jQuery('#modal-lastLogin').text(formatDate(user.lastLogin));
                jQuery('#modal-regDate').text(formatDate(user.regDate));
                jQuery('#modal-userUpdate').text(formatDate(user.userUpdate));
                jQuery('#modal-userUpdateUno').text(user.userName);
                
                // 모달을 표시합니다
                jQuery('#userModal').modal('show');

                $('#editUserButton').click(function() {
                    var userName = $('#modal-username-input').val();
                    var nickName = $('#modal-nickname-input').val();
                    var userId = $('#modal-userid-input').val();
                    var phone = $('#modal-phone-input').val();
                    var email = $('#modal-email-input').val();
                    var userStatus = $('#modal-userStatus-input').val();
                    var active = $('#modal-active-input').val();
                    var point = $('#modal-point-input').val();
                    var businessNo = $('#modal-businessNo-input').val();
                    
                    if (!userName || !nickName || !userId) {
                        // 필수 필드가 비어있을 경우 처리
                        alert('필수 정보를 입력해주세요.');
                        return;
                    }
                    
                    var editedUser = {
                        uno: clickedUserId, // 클릭한 사용자의 userId를 uno로 사용
                        userName: userName,
                        nickName: nickName,
                        userId: userId,
                        phone: phone,
                        email: email,
                        userStatus: userStatus,
                        active: active,
                        point: point,
                        businessNo: businessNo,
                        // 추가적인 수정 필드들
                    };
                    
                    $.ajax({
                        type: 'POST',
                        url: "<c:url value="admin/admin/user-update"/>",
                        contentType: 'application/json',
                        data: JSON.stringify(editedUser),
                        success: function(response) {
                            if (response === 'success') {
                                // 성공 시 모달 닫기 및 새로고침
                                $('#userModal').modal('hide');
                                location.reload();
                            } else {
                                // 실패 시 처리
                            }
                        },
                        error: function() {
                            // 에러 처리
                        }
                    });
                });
            }
        });
    });
</script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(document).ready(function() {
    
    $(".modal-content").draggable({
        handle: ".modal-header" // 드래그 핸들로 모달 헤더를 사용
    });
});
</script>
</body>
</html>
