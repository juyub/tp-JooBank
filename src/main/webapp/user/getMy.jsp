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
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<script>
function populateAddressFields() {
	  let address = '${login.address}'; // 서버로부터 가져온 '${login.address}' 값으로 가정합니다.

	  if (address) {
	    let addressArray = address.split(", ");

	    if (addressArray.length === 3) {
	      document.getElementById('address-1').value = addressArray[0];
	      document.getElementById('address-1-1').value = addressArray[1];
	      document.getElementById('address-2').value = addressArray[2];
	    }
	  }
	}

	// 페이지가 로드될 때 함수를 호출합니다.
	window.onload = populateAddressFields;

	function execDaumPostcode() {
		new daum.Postcode( {
			oncomplete: function( data ) {
				document.getElementById( 'zip-code' ).value = data.zonecode;
				document.getElementById( 'address-1' ).value = data.address;
				document.getElementById( 'address-1-1' ).value = data.jibunAddress;
				document.getElementById( 'address-2' ).focus();
			}
		} ).open();
	}
	function execDaumPostcodeReset() {
		document.getElementById( 'zip-code' ).value = null;
		document.getElementById( 'address-1' ).value = null;
		document.getElementById( 'address-1-1' ).value = null;
		document.getElementById( 'address-2' ).value = null;
	}
	function checkForm() {
        // 주소 결합
        combineAddress();
        // 기타 폼 검증 처리 로직을 넣을 수 있습니다.
        return true;
    }
    function combineAddress() {
        let address1 = document.getElementById('address-1').value.trim();
        let address11 = document.getElementById('address-1-1').value.trim();
        let address2 = document.getElementById('address-2').value.trim();

        let addressFull = address1 + ", " + address11 + ", " + address2;

        document.getElementById('address').value = addressFull;
    }
	
/* 	function confirmDelete() {
	    if (confirm("정말로 탈퇴하시겠습니까?")) {
	        location.href = "deleteUser.do?userno=${user.userno}";
	    }
	}
	
	<c:if test="${deleteFailed != null}">
   		alert("대여중인 도서가 있어 탈퇴가 불가합니다");
	</c:if> */
	
	function togglePasswordVisibility(id) {
		  var input = document.getElementById(id);
		  if (input.type === "password") {
		    input.type = "text";
		  } else {
		    input.type = "password";
		  }
	}
</script>
<style>
table {
  border-collapse: collapse;
}
td {
  padding: 5px;
}
.side-menu {
    display: flex;
    flex-direction: column;
    position: absolute;
    left: 10%;
    top: 20%;
    margin: 5%;
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
 .updateForm{
 	position: absolute;
	top: 25%;
	left: 40%;
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
                <li><a href="">회원정보</a></li>
                <li><a href="">계좌정보</a></li>
                <li><a href="${contextPath}/noUse.do">탈퇴하기</a></li>
            </ul>
   		</div>
    	
    	<div class="updateForm">
		<form action="${contextPath}/updateUser.do" method="post" onsubmit="return checkForm()">
			<table border="1">
				<tr>
					<td>아이디</td>
					<td><input type="text" name="id" value="${login.id}"
						readonly /></td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
					<span>
					<input type="password" name="password" id="password"
						value="${login.password}" /> <br>
					<input type="checkbox" onclick="togglePasswordVisibility('password')"> 비밀번호 보기
					</span>	
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value="${login.name}"
						readonly /></td>
				</tr>
				<tr>
					<td>주민번호</td>
					<td><input type="text" name="name" value="${login.usercode}"
						readonly /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" name="phone" value="${login.phone}" /></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value="${login.email}" /></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
					<input type="text" id="zip-code" name="postcode" value="${login.postcode}" readonly>
					<input type="button" onclick="execDaumPostcode()" value="우편번호">
					<input type="button" onclick="execDaumPostcodeReset()" value="초기화">
					<br>
					<input type="text" id="address-1"  style="width: 300px" readonly> <br>
					<input type="text" id="address-1-1"  style="width: 300px" readonly> <br>
					<input type="text" id="address-2"  style="width: 300px">
					<input type="hidden" id="address" name="address" value="">
					</td>
				</tr>
				<tr>
					<td>가입일</td>
					<td>
						<fmt:formatDate value="${login.joindate}" pattern="yy/MM/dd" var="formattedDate" />
       				 	<input type="text" name="joindate" value="${formattedDate}" readonly />
					</td>
				</tr>
				<tr>
					<td>권한</td>
					<td><input type="text" name="user_type" value="${login.user_type}" /></td>
				</tr>
				<tr>
					<td colspan="2">
					<input type="submit" value="수정" />
					</td>
				</tr>
			</table>
		</form>
    	</div>
	</div>
	</section>
</body>
</html>
