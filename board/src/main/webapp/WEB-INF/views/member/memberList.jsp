<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직원 목록</title>
</head>
<body>

<div id="">
<c:if test="${empty memberList}">
	등록된 직원 정보가 없습니다.
</c:if>
<c:if test="${!empty memberList}">
<h2>직원 목록</h2>
	<form method="post">
	<input type="hidden" name="memberList" value="${memberList}">
		<table style="text-align: center;">
			<caption>
				<input type="text" placeholder="검색 단어 입력" name="searchWord" value="${searchWord}">
				<button type="submit" onclick="form.action='<c:url value="/search" />'">검색</button>	
				<button type="button" onclick="location.href='<c:url value="/memberIn" />'">직원등록</button>	
				<button type="submit" onclick="form.action='<c:url value="/downDoc" />'">파일 저장</button>	
			</caption>
			<thead>
				<tr>
					<th>번호</th><th>직원번호</th><th>이름</th><th>전화번호</th><th>직급</th><th>이메일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${memberList}" var="memberOne" varStatus="status">
					<tr onClick="location.href='<c:url value="/memberUp?num=${memberOne.getNum()}" />'">
						<td>${status.count}</td>
						<td>
							<fmt:formatNumber value="${memberOne.getNum()}" var="numFormat" pattern="000"></fmt:formatNumber>
							${numFormat}
						</td>
						<td>${memberOne.getName()}</td>
						<td>${memberOne.getPhone()}</td>
						<td>${memberOne.getMemberRank()}</td>
						<td>${memberOne.getEmail()}</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot></tfoot>
		</table>
	</form>
</c:if>
</div>
</body>
</html>