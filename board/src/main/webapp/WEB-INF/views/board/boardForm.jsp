<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 등록 페이지</title>
</head>
<body>
<h2>게시글 등록 페이지</h2>
<div id="BoardMain">
	<form action="/boardIn" method="post" enctype="multipart/form-data">
		작성자 : <input type="text" name="writer" placeholder="작성자 입력"><br>
		제목 : <input type="text" name="title" placeholder="제목 입력"><br>
		내용<br>
		<textarea rows="5" cols="50" name="content" placeholder="내용 입력"></textarea><br>
		<input type="file" name="fileName" ><br>
		<br>
		<input type="submit" value="등록">
		<button type="button" onclick="history.go(-1)">취소</button>
	</form>
</div>
</body>
</html>