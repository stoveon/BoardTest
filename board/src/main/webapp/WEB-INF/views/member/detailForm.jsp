<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록 페이지</title>
<script type="text/javascript">
let reg = "<c:out value="${reg}" />";
if(reg === 'isnull'){
	alert("입력하지 않은 칸이 있습니다.");
}else if(reg === 'nottype'){
	alert("입력 형식 불일치 확인 요망!");
}
</script>
</head>
<body>
<div class="RegMain">
	<h2>직원 등록</h2>
	<form action="/memberUp" method="post">
	<input type="hidden" name="agoNum" value="${num}">
	직원 번호 : <input type="text" name="num" placeholder="직원번호" maxlength="3" value="${num}"><br>
	직급 : <input type="text" name="memberRank" placeholder="직급" value="${memberRank}"><br>
	이름 : <input type="text" name="name" placeholder="이름" value="${name}"><br>
	전화전호 : <input type="text" name="phone" placeholder="전화번호" maxlength="13" value="${phone}"><br>
	이메일 : <input type="email" name="email" placeholder="이메일" value="${email}"><br>
	<input type="submit" value="수정">
	<button onclick="location.href='<c:url value="/memberDel?num=${num}" />'">삭제</button>
	<button onclick="history.go(-1)">취소</button>
	</form>
</div>
</body>
</html>