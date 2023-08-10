<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<script>
function toggleSubMenu(show) {
    var submenu = document.getElementById('submenu4');
    submenu.style.display = show ? 'block' : 'none';
}
</script>

<style>
.l-image {
   width: 80px;
}

#li-image {
	width: 60px;
}

ul {
  padding: 0;
  margin: 0;
}

li {
  list-style: none;
  position: relative;
}

#menu4 {
  cursor: pointer;
  display: inline-block;
  position: relative;
}

#submenu4 {
  position: absolute;
  right: -40px;
  display: none;
  background: rgba(135, 207, 235, 0.85);
  border: 1px solid #ccc;
  margin-top: 0px;
  width: 120px;
  padding:5px;
}

#submenu4 li {
  width: 100%;
  padding-top: 5px;    
  padding-bottom: 5px;
}

#submenu4 li a {
  display: block;
  padding: 5px 10px;
  text-decoration: none;
  color: black;
   text-align: center;
}

#submenu4 li a:hover {
  background: orange;
	transition: ease 1s;
}
.cell1 {
  width: 10%;
}
.cell2 {
  width: 30%;
  font-size:25px;
  margin-right: 10px; 
	margin-left: 10px; 
}
.cell4 {
  width: 15%;
}

.link-spacing {
	font-size:25px;
	margin-right: 10px; 
	margin-left: 10px; 
}
.link-spacing2 {
	font-size:22px;
	margin-right: 10px; 
	margin-left: 10px; 
}
</style>

<!-- <link rel="stylesheet" type="text/css" href="/css/topmenu.css"> -->
 
<table style="width:90%" align="center" border="0">
	<tr>
		<td rowspan="2" class="cell1">
      <a href="${contextPath}/main.do"><img src="${contextPath}/image/JooBank.png" class="l-image" alt="로고"></a>
		</td>
		<td>
		<!-- <a href="" class="link-spacing2">공지사항</a>
    	<a href="" class="link-spacing2">지점안내</a> -->
    	<a href="${contextPath}/naver.action" class="link-spacing2">뉴스</a>
    	<a href="${contextPath}/getNewsList.action" class="link-spacing2">게시판</a>
    	<a href="${contextPath}/mario/mario.html" class="link-spacing2" target="_blank">쉬어가기</a>
		</td>
		<td align="right">
	      <c:if test="${ not empty login }">
	      ${ login.name }(${ login.id })님 로그인중...
	      </c:if>
		</td>
	    <td rowspan="2" class="cell4" align="center">
	      <ul>
	        <li id="menu4" onmouseover="toggleSubMenu(true)" onmouseout="toggleSubMenu(false)">
	          <img src="${contextPath}/image/user.png" id="li-image" alt="로고">
	          <ul id="submenu4">
	            <c:choose>
	              <c:when test="${ empty login }">
	                <li><a href="${contextPath}/user/join.jsp">회원가입</a></li>
	                <li><a href="${contextPath}/loginPage.do">로그인</a></li>
	              </c:when>
	              <c:otherwise>
	                <li><a href="${contextPath}/myPage.do">마이페이지</a></li>
	                <li><a href="${contextPath}/logout.do">로그아웃</a></li>
	              </c:otherwise>
	            </c:choose>
	          </ul>
	        </li>
	      </ul>
	    </td>
	</tr>
  <tr>
    <td colspan="2" class="cell3" align="center">
      <a href="${contextPath}/getProductList.do" class="link-spacing">상품</a>
      <c:if test="${ not empty login }">
        <a href="${contextPath}/myAccountList.do" class="link-spacing">계좌</a>
    <a href="${contextPath}/obAcList.do" class="link-spacing">오픈뱅킹</a>
    <a href="${contextPath}/getBoardList.do" class="link-spacing">게시판</a>
      </c:if>
    </td>
  </tr>
</table>

