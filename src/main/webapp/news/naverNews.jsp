<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
table {
	border-collapse: collapse;
}
td {
	padding: 5px;
}
#news{
	margin:5px;
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
		<h1>뉴스 목록</h1>
		
		<c:forEach var="news" items="${newsList}">
			<table border="1" style="width: 60%;" id="news">
				<tr>
					<td><a href="${news.link}">${news.title}</a></td>
				</tr>
				<tr>
					<%-- <td align="right">${news.pubDate}</td> --%>
					<td align="right">(<fmt:formatDate value="${news.pubDate}" pattern="yy/MM/dd HH:mm:ss"/>)</td>
				</tr>
			</table>
		</c:forEach>
	</section>
	
</body>
</html>
