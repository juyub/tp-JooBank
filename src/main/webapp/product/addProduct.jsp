<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
</head>
<body>
	<header>
		<jsp:include page="../topMenu.jsp" />
	</header>
	<section style="display: flex; justify-content: center;">
		<form action="${contextPath}/addProduct.do" method="post">
			<table>
				<tr>
					<td>상품코드</td>
					<td><input type="text" name="pd_number"></td>
				</tr>
				<tr>
					<td>상품명</td>
					<td><input type="text" name="pd_name"></td>
				</tr>
				<tr>
					<td>내용</td>
					<td><textarea name="pd_content" rows="10" cols="30"></textarea></td>
				</tr>
				<tr>
					<td>상품만기</td>
					<td><select name="pd_ed_date">
							<c:forEach var="i" begin="1" end="20">
								<option value="${21 - i}">${21 - i}</option>
							</c:forEach>
					</select>년</td>
				</tr>
				<tr>
					<td><input type="submit" value="상품등록" /></td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>