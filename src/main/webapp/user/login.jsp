<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function checkForm() {
		let f = document.loginForm;
		if (f.id.value == '') {
			alert('ID를 입력하세요')
			f.id.focus()
			return false
		}
		if (f.password.value == '') {
			alert('패스워드를 입력하세요')
			f.password.focus()
			return false
		}
		return true
	}
</script>
<!-- <link rel="stylesheet" type="text/css" href="/JooBank/css/user.css"> -->
<style>
html{
	background: url("${contextPath}/image/backWall.jpg");
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

body {
	width : 500px;
	padding: 0;
	font-family: verdana, sans-serif;
	font-size: 15px;
	margin: 0 auto;
	margin-top:5%;
}

#user-box {
	position: absolute;
	top: 50%;
	left: 50%;
	padding: 20px;
	transform: translate(-50%, -50%);
	background: rgb(255, 255, 255, 0.8);
	box-sizing: border-box;
	box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
	border-radius: 10px;
	color: black;
}
input {
	height: 30px;
	font-size: 18px;
	border-color: black;
	border-radius: 7px;
}
td{
padding:5px;
}

</style>
</head>
<body>
	<div id="user-box" align="center">
		<h1>로그인</h1>
		<form method="post" action="${contextPath}/login.do" name="loginForm"
			onsubmit="return checkForm()">
		<table>
			<tr>
				<td align="center" style="height: 30px;">
				<label for="id">ID</label> 
				</td>
			</tr>
			<tr>
				<td >
 				<input type="text" class="form-control" id="id" name="id" >
				</td>
			</tr>
			<tr>
				<td align="center" style="height: 30px;">
				<label for="password">Password</label>
				</td>
			</tr>
			<tr>
				<td>
				<input type="password" class="form-control" id="password" name="password" >
				</td>
			</tr>
		</table>
		<br>
			<button type="submit" class="btn btn-primary">로그인</button>
		</form> <br>		
	<a href="addUserPage.do"> 회원가입 </a>
	<a href="indexPage.do"> 뒤로가기 </a>
	</div>
	
	<% if (request.getAttribute("loginFailed") != null) { %>
    <script>alert("아이디 또는 비밀번호가 잘못되었습니다.");</script>
  	<% } %>
</body>
</html>
