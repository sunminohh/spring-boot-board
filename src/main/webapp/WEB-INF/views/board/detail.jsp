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
<%@ include file="../common/navbar.jsp" %>
<div class="container">
	<div class="row mb-3">
		<div class="col-12">
			<h1 class="border bg-light p-2 fs-4">게시글 상세정보</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<p>게시글 상세정보를 확인하세요.</p>
			<table class="table border ">
				<tbody>
				
					<tr>
						<th class="table-secondary" style="width: 15%;">제목</th>
						<td colspan="3" style="widows: 85%;">${board.title }</td>
					</tr>
					<tr>
						<th class="table-secondary" style="width: 15%;">번호</th>
						<td style="width: 35%;">${board.no }</td>
						<th class="table-secondary" style="width: 15%;">작성자</th>
						<td style="width: 35%;">${board.user.email }</td>
					</tr>
					<tr>
						<th class="table-secondary">조회수</th>
						<td>${board.readCount }</td>
						<th class="table-secondary">댓글수</th>
						<td>${board.reviewCount }</td>
					</tr>
					<tr>
						<th class="table-secondary">등록일</th>
						<td><fmt:formatDate value="${board.createDate }"/></td>
						<th class="table-secondary">최종수정일</th>
						<td><fmt:formatDate value="${board.updateDate }"/></td>							
					</tr>
					<tr>
						<th class="table-secondary">내용</th>
						<td colspan="3">
							${board.content }
						</td>
					</tr>
				</tbody>
			</table>
			<div>
				<a href="modify?no=${board.no }" class="btn btn-warning btn-sm" >수정</a>
				<a href="delete?no=${board.no }" class="btn btn-danger btn-sm">삭제</a>
				<a href="list?page=${param.page }" class="btn btn-primary btn-sm float-end">목록</a>
			</div>
		</div>
	</div>
	
</div>
</body>
</html>