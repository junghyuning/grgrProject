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
	    <h4>BOARD</h4>
	    <hr>
	        <ul>
	            <li><a href="<c:url value="/noticeboard/list"/>">공지사항</a></li>
	            <li><a href="<c:url value="/infoboard/list"/>">정보공유</a></li>
	            <li><a href="<c:url value="/productboard/list"/>">판매</a></li>
	            <li><a href="<c:url value="/freeboard/list"/>">자유</a></li>
	            <li><a href="<c:url value="/qnaboard/list"/>">문의</a></li>
	        </ul>
	</aside>
</body>
</html>