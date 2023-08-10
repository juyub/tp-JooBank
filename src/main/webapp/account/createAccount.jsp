<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
</head>
<body>
	<header>
		<jsp:include page="../topMenu.jsp" />
	</header>
	<section>
		<h1>Create Account</h1><br>
		<form action="${contextPath}/createAccount.do" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td>${login.name} <input type="hidden" name="id"
						value="${login.id}">
					</td>
				</tr>
				<tr>
					<td>상품명</td>
					<td>${product.pd_name} <input type="hidden" name="pd_number"
						value="${product.pd_number}"> <input type="hidden"
						name="pd_ed_date" value="${product.pd_ed_date}">
					</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="AC_PW" maxlength="4" required></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create Account"></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>
