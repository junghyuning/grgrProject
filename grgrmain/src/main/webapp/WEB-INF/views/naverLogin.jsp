<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>네이버로그인</title>
</head>
<body>
	<img src="<c:url value="/images/naver_login.png"/>" art="네이버 로그인" onclick="location.href='<c:url value="/oauth/naver"/>';">
</body>
</html>