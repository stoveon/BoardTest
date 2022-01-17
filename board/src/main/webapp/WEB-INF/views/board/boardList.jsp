<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>
<div id="">
	<form action="">
		<table>
			<thead>
				<tr>
					<th>직원번호</th><th>이름</th><th>전화번호</th><th>직급</th><th>이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${memberList}" var="memberOne">
				<tr>
					<td>${memberOne.num}</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
	</form>
</div>
</body>
</html>