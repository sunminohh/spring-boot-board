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
			<h1 class="border bg-light p-2 fs-4">게시글 목록</h1>
		</div>
	</div>
	<div class="row mb-3">
		<div class="col-12">
			<p>게시글 목록을 확인하세요.</p>
			<table class="table">
				<thead>
					<tr>
						<th style="width: 10%;">번호</th>
						<th style="width: 35%;">제목</th>
						<th style="width: 10%;">조회수</th>
						<th style="width: 10%;">댓글수</th>
						<th style="width: 20%;">작성자</th>
						<th style="width: 15%;">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty boardList }">
							<c:forEach var="board" items="${boardList }">
								<tr>
									<td>${board.no }</td>
									<td><a href="detail?no=${board.no }&page=${pagination.page }" class="text-black text-decoration-none" >${board.title }</a></td>
									<td>${board.readCount }</td>
									<td>${board.reviewCount }</td>
									<td>${board.user.email }</td>
									<td><fmt:formatDate value="${board.createDate }"/></td>
								</tr>
							</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="6" class="text-center">게시글이 존재하지 않습니다.</td>
						</tr>
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<div class="text-end">
				<a href="register" class="btn btn-primary btn-sm">새 게시글</a>
			</div>
		</div>
	</div>
	<div class="row mb-3" >
		<div class="col-12">
			<nav> 
				<ul class="pagination justify-content-center">
					<li class="page-item ${pagination.first ? 'disabled' : ''}">
						<a href="list?page=${pagination.prePage }" class="page-link" onclick="changePage(event, ${pagination.prePage})">이전</a>
					</li>
					
					<c:forEach var="num" begin="${pagination.beginPage }" end="${pagination.endPage  }">
						<li class="page-item ${pagination.page eq num ? 'active' : ''}">
							<a href="list?page=${num }" class="page-link">${num }</a>
						</li>
					</c:forEach>
					
					<li class="page-item ${pagination.last ? 'disabled' : ''}">
						<a href="list?page=${pagination.nextPage }" class="page-link"  onclick="changePage(event, ${pagination.nextPage})">다음</a>
					</li>
				</ul>
			</nav>

		</div>
	</div>
</div>
</body>
</html>