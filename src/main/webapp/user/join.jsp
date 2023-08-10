<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
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
</script>
<!-- <link rel="stylesheet" type="text/css" href="/JooBank/css/user.css"> -->
<style>
html{
	background: url("${contextPath}/image/backWall.jpg");
	background-size: 100% 100%;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

body {
	width : 500px;
	padding: 0;
	font-family: verdana, sans-serif;
	font-size: 15px;
	margin: 0 auto;
	margin-top:5%;
}

#user-box {
	position: absolute;
	top: 50%;
	left: 50%;
	padding: 20px;
	transform: translate(-50%, -50%);
	background: rgb(255, 255, 255, 0.8);
	box-sizing: border-box;
	box-shadow: 0 15px 25px rgba(0, 0, 0, .6);
	border-radius: 10px;
	color: black;
}
td {
  padding: 5px;
}
</style>

</head>
<body>
	<div id="user-box">
		<h1>회원가입</h1>
		<form action="${contextPath}/join.do" method="post" name="joinForm" 
			onsubmit="return checkForm()">
			<table id="join-table" border="1">
				<tr>
					<td><label for="id">아이디</label></td>
					<td><input type="text" class="form-control" id="id" name="id"
						pattern="[a-z0-9]+" />
						<input type="button" value="ID중복확인" class="btc"
						onClick="idCheck(this.form.id.value)">
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>
					<input type="password" class="form-control" id="password"
						name="password" pattern="[a-z0-9]+" required />
					</td>
				</tr>
				<tr>
					<td>비밀번호<br>확인</td>
					<td>
					<input type="password" class="form-control" id="passwordCheck"
							name="passwordCheck" pattern="[a-z0-9]+" required 
							oninput="passwordChecked()" />
					</td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" class="form-control" id="name"
						name="name" /></td>
				</tr>
				<tr>
					<td>주민번호</td>
					<td><input type="text" class="form-control" id="usercode"
						name="usercode" /></td>
				</tr>
				<tr>
					<td>연락처</td>
					<td><input type="text" class="form-control" id="phone"
						name="phone" maxlength="13"
           				oninput="formatPhoneNumber(this)"
           				/></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" class="form-control" id="mail" name="email"></td>
				</tr>
				<tr>
					<td>주소</td>
					<td>
					<input type="text" id="zip-code" placeholder="우편번호" name="postcode" readonly>
					<input type="button" onclick="execDaumPostcode()" value="우편번호">
					<input type="button" onclick="execDaumPostcodeReset()" value="초기화">
					<br>
					<input type="text" id="address-1" placeholder="도로명주소" style="width: 300px" readonly> <br>
					<input type="text" id="address-1-1" placeholder="지번주소" style="width: 300px" readonly> <br>
					<input type="text" id="address-2" placeholder="상세주소" style="width: 300px">
					<input type="hidden" id="address" name="address" value="">
					</td>
				</tr>
			</table>
			<br>
			<button type="submit" class="btn btn-primary">회원가입</button>
		</form>
		<br> 
		<a href="loginPage.do"> 로그인 </a> &nbsp;&nbsp;&nbsp;
		<a href="indexPage.do"> 뒤로가기 </a>
	</div>
</body>
</html>