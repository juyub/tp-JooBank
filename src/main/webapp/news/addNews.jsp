<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<style>
table {
	border-collapse: collapse;
}
td {
	padding: 5px;
}
/* a {
  text-decoration: none;
  color: inherit;
}
section {
  margin-left: 5%;
  margin-right: 5%;
  padding: 20px;
} */
</style>
</head>
<body>
	<header>
		<jsp:include page="/topMenu.jsp" />
	</header>
	<section>
		<h1>새글 등록</h1>
		<form action="${contextPath}/addNews.action" method="post">
		<table>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="title"/> </td>
			</tr>
			<tr>
				<td> 작성자 </td>
				<td> <input type="text" name="writer" /> </td>
			</tr>
			<tr>
				<td> 글 내용 </td>
				<td> <textarea name="content" cols="40" rows="10"></textarea> </td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="등록"/></td>
			</tr>
		</table>
		</form>
	</section>	
</body>
</html>