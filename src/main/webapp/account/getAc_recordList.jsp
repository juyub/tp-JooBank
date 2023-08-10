<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
}
td {
    padding:10px;
}
table.no-inner-edges td {
    border: none;
}
.table-no-inner-edges td {
    border: none;
}
</style>
<style type="text/css">
table {
    border-collapse: collapse;
}
</style>
</head>
<body>
	<header>
		<jsp:include page="../topMenu.jsp" />
	</header>
	<section style="display: flex; flex-direction: column; align-items: center;">
		<table border="1">
		<tr>
		<td></td><td>상품명</td><td>예금주</td><td>계좌번호</td><td>잔액</td><td>개설일</td><td>만기일</td>
		</tr>
			<tr>
			<td>${ account.bank_name }</td>
			<td>${ account.pd_name }</td>
			<td>${ account.name }</td>
			<td>${ account.ac_number }</td>
			<td><fmt:formatNumber type="number" pattern="###,###" value="${account.AC_MONEY}" /> 원</td>
			<td>${ account.AC_OP_DATE }</td>
			<td>${ account.AC_ED_DATE }</td>
			</tr>
		</table>
		<br>
		<c:forEach var="record" items="${recordList}" >
			<table class="table-no-inner-edges" border="1" width="60%" style="margin-bottom: 1%;">
			<tr align="center">
				<td >${record.rc_type}</td>
				<td >${record.rc_name}</td>
				<td width="20%">
				<fmt:formatNumber value="${record.rc_money}" pattern="###,###"/>원</td>
				<td width="20%">
				<fmt:formatNumber value="${record.rc_balance}" pattern="###,###"/>원</td>
				<td><fmt:formatDate value="${record.rc_time}" pattern="yy/MM/dd HH:mm:ss"/></td>
				<td width="10%">${record.rc_text}</td>
			</tr>
			</table>
		</c:forEach>
		<br>
		
		<table>
			<tr>
				<td>
				 <c:choose>
			        <c:when test="${currentPage > 1}">
			            <a href="${contextPath}/getAc_recordList.do?pageNo=${currentPage-1}&ac_number=${ac_number}">이전</a>
			        </c:when>
			        <c:otherwise>
			            <span>이전</span>
			        </c:otherwise>
	    		</c:choose>
				</td>
				
			    <c:forEach begin="1" end="${totalPageCount}" var="pageNumber">
				<td>
			        <c:choose>
			            <c:when test="${pageNumber == currentPage}">
			                <span>${pageNumber}</span>
			            </c:when>
			            <c:otherwise>
			                <a href="${contextPath}/getAc_recordList.do?pageNo=${pageNumber}&ac_number=${ac_number}">${pageNumber}</a>
			            </c:otherwise>
			        </c:choose>
				</td>
			    </c:forEach>
			    
				<td>
				    <c:choose>
				        <c:when test="${currentPage < totalPageCount}">
				            <a href="${contextPath}/getAc_recordList.do?pageNo=${currentPage+1}&ac_number=${ac_number}">다음</a>
				        </c:when>
				        <c:otherwise>
				            <span>다음</span>
				        </c:otherwise>
				    </c:choose>
				</td>
			</tr>
		</table>
	</section>
	
</body>
</html>