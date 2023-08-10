<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
</head>
<body>
<header>
        <jsp:include page="/topMenu.jsp" />
    </header>
    <section style="display: flex; justify-content: center;">
        <form action="${contextPath}/updateProduct.do" method="post">
        <table>
        <c:if test="${ login.user_type eq 'admin' }">
        <tr>
            <td>
            상품번호
            </td>
            <td>
            ${product.pd_number}
            </td>
        </tr>
        </c:if>
        <tr>
            <td>
            상품명
            </td>
            <td>
                <input type="text" name="pd_name" value="${product.pd_name}" ${ login.user_type ne 'admin' ? 'disabled' : '' }>
            </td>
        </tr>
        <tr>
            <td>
            상품설명
            </td>
            <td>
                <input type="text" name="pd_content" value="${product.pd_content}" ${ login.user_type ne 'admin' ? 'disabled' : '' }>
            </td>
        </tr>
        <tr>
            <td>
            상품만기
            </td>
            <td>
                <select name="pd_ed_date" ${ login.user_type ne 'admin' ? 'disabled' : '' }>
                    <c:forEach var="i" begin="1" end="20">
                        <c:set var="reverseIndex" value="${21 - i}" />
                        <option value="${reverseIndex}" ${product.pd_ed_date == reverseIndex ? 'selected' : ''}>${reverseIndex}</option>
                    </c:forEach>
                </select>년
            </td>
        </tr>
        <tr>
            <td colspan="2">
            <c:if test="${ !empty login }">
                <button>
                <a href="${contextPath}/createAccountPage.do?pd_number=${product.pd_number}">계좌개설하기</a>
                </button>
            </c:if>
            </td>
        </tr>
        <tr>
            <td colspan="2">
            <c:if test="${ login.user_type eq 'admin' }"><input type="submit" value="수정"></c:if>
            </td>
        </tr>
        </table>
        </form>
    </section>
</body>
</html>
