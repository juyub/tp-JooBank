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
<style type="text/css">
  table {
    border-collapse: collapse;
    margin-left: 5%; 
    margin-right: 5%; 
  }
  td{
    padding:5px;
  }
  section {
    white-space: nowrap;
  }
  div.product {
    display: inline-block;
    width: 150px;
  }
  div {
  	margin:10px;
  }
</style>
</head>
<body>
    <header>
        <jsp:include page="/topMenu.jsp" />
    </header>
    <section align="center">
        <c:if test="${ login.user_type eq 'admin' }">
        <button><a href="${contextPath}/product/addProduct.jsp">상품등록</a></button><br>
        </c:if>
        
        <c:forEach var="product" items="${productList}" >
            <div class="product" align="center">
            <a href="${contextPath}/getProduct.do?pd_number=${product.pd_number}">
                <table border="1" align="center">
                    <tr>
                        <td align="center">${product.pd_name}</td>
                    </tr>
                    <tr>
                        <td align="center">${product.pd_content}</td>
                    </tr>
                    <tr>
                        <td align="center">만기 ${product.pd_ed_date}년</td>
                    </tr>
                </table>
            </a>
            </div>
        </c:forEach>
    </section>
</body>
</html>
