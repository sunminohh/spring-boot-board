<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<title>애플리케이션</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
<c:set var="menu" value="로그인" />
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1 class="border bg-light p-2 fs-4">로그인 폼</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<p>아이디와 비밀번호를 입력하고 로그인하세요</p>
			
			<c:choose>
				<c:when test="${param.error eq 'fail' }">
					<div class="alert alert-danger">
						<strong>로그인 실패</strong> 아이디 혹은 비밀번호가 올바르지 않습니다.
					</div>
				</c:when>
				<c:when test="${param.error eq 'denied' }">
					<div class="alert alert-danger">
						<strong>접근 거부</strong> 로그인 후 사용가능한 서비스를 요청하였습니다.
					</div>
				</c:when>
				<c:when test="${param.error eq 'forbidden' }">
					<div class="alert alert-danger">
						<strong>권한 부족</strong> 요청한 서비스에 필요한 권한이 없습니다.
					</div>
				</c:when>
			</c:choose>
			
			<form class="border bg-light p-3" method="post" action="login" >
				<div class="form-group mb-3">
					<label class="form-label">이메일</label>
					<input type="text" class="form-control" id="user-email" name="email" />
				</div>
				<div class="form-group mb-3">
					<label class="form-label">비밀번호</label>
					<input type="password" class="form-control" id="user-password" name="password" />
				</div>
				<div class="text-end">
					<a href="/" class="btn btn-secondary btn-sm">취소</a>
					<button type="submit" class="btn btn-primary btn-sm">로그인</button>
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>