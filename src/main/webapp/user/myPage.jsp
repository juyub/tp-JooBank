<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<style>
.side-menu {
    display: flex;
    flex-direction: column;
    position: absolute;
    left: 10%;
    top: 20%;
    margin:5%;
 }

 section {
    display: flex;
    justify-content: center;
 }

 .container {
    display: flex;
 }

 .side-menu ul {
    list-style-type: none;
    padding: 0;
 }

 .side-menu li {
    background-color: rgba(135, 207, 235, 0.85);
    color: #000;
    text-align: center;
    border-radius: 5px;
    padding: 10px;
    margin-bottom: 10px;
    cursor: pointer;
 }

 .side-menu li:hover {
     background-color: orange;
 }
</style>
</head>
<body>
	<header>
		<jsp:include page="../topMenu.jsp" />
	</header>

	<section style="display: flex; justify-content: center;">
	 <div class="container">
		 <div class="side-menu">
      		<ul>
                <li><a href="/JooBank/user/getMy.jsp">회원정보</a></li>
                <li><a href="">계좌정보</a></li>
                <li><a href="${contextPath}/noUse.do">탈퇴하기</a></li>
            </ul>
    	</div>
     </div>
    </section>	
</body>
</html>