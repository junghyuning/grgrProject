<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>

.sidebar {
	text-align:center;
    position: pixed;
    height: 100%; 
    background-color: #fff; 
    padding: 20px; 
}

.sidebar ul {
    list-style-type: none;
    padding: 0;
}

.sidebar ul li {
    margin-bottom: 10px;
    font-size: 20px;!important;
}

.sidebar ul li a {
    text-decoration: none;
    color: #333;
}

</style>
<body>

<!-- Sidebar -->
	<aside class="sidebar col-md-2">
	    <h4>목록</h4>
	    <hr>
	        <ul>
	            <li><a href="<c:url value="/mypage/updateUserProfile"/>">회원정보 수정</a></li>
	            <li><a href="<c:url value="/mypage/myBoardWriteList"/>">내가 쓴 게시글</a></li>
	            <li><a href="<c:url value="/mypage/myCommentList"/>">내가 쓴 댓글</a></li>
	            <li><a href="<c:url value="/mypage/myLikeList"/>">관심게시글</a></li>
	            <li><a href="<c:url value="/cart/list"/>">장바구니</a></li>
	            <li><a href="<c:url value="/"/>">주문목록</a></li>
	        </ul>
	</aside>
</body>
</html>