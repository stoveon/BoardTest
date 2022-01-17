<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
</head>
<body>

<div id="">
<c:if test="${empty memberList}">
	등록된 직원 정보가 없습니다.
</c:if>
<c:if test="${!empty memberList}">
	<form method="post">
		<table>
			<caption>
				<input type="text" placeholder="검색 단어 입력" name="searchWord">
				<button type="submit" onclick="form.action='<c:url value="/search" />'">검색</button>	
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
						${numFormat}</td>
						<td>${memberOne.getName()}</td>
						<td>
	<%--  					<c:set var="phone1"value="${fn:substring(memberOne.getPhone(), 1, 3)}" />
						<c:set var="phone2"value="${fn:substring(memberOne.getPhone(), 3, 7)}" />
						<c:set var="phone3"value="${fn:substring(memberOne.getPhone(), 7, 10)}" />
						<c:set var="phone"value="${phone1-phone2-phone3}" /> --%>
						${memberOne.getPhone()}
						</td>
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