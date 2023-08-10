<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/JooBank/css/main.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<style>
#password-dialog {
	display: none;
}
table {
    border-collapse: collapse;
}
td {
padding:10px;
}
</style>
<script>
	$(function() {
		var passwordDialog, transferForm;

		function checkPassword() {
			var password = $("#password");

			// Ajax를 사용하여 비밀번호 확인을 위한 서버 측 요청을 수행합니다.
			$.ajax({
				url : '${contextPath}/checkPassword.do',
				type : 'POST',
				data : {
					sendAc_number : $('input[name="sendAc_number"]').val(),
					sendBank_cd : $('input[name="sendBank_cd"]').val(),
					AC_PW : password.val()
				},
				success : function(response) {
					if (response === 'true') {
						transferForm.submit(); // 비밀번호 확인이 완료되면 전송
					} else {
						alert("비밀번호가 일치하지 않습니다."); // 비밀번호 확인 실패 시 경고 메시지를 표시
					}
					passwordDialog.dialog("close"); // 패스워드 확인 대화상자를 닫습니다.
				}
			});

			passwordDialog.dialog("close");
		}

		passwordDialog = $("#password-dialog").dialog({
			autoOpen : false,
			height : 200,
			width : 350,
			modal : true,
			buttons : {
				"확인" : checkPassword,
				"취소" : function() {
					passwordDialog.dialog("close");
				}
			}
		});

		transferForm = $("form[action='${contextPath}/transfer.do']");
		$("#transfer").on("click", function() {
			passwordDialog.dialog("open");
		});

		passwordDialog.find("form").on("submit", function(event) {
			event.preventDefault();
			checkPassword();
		});
	});
</script>
</head>
<body>
	<header>
		<jsp:include page="../topMenu.jsp" />
	</header>
	<section style="display: flex; justify-content: center;">
		<form id="transfer-form" action="${contextPath}/transfer.do"
			method="post">
			<table border="1">
				<tr>
					<td>이체할 은행</td>
					<td>${account.bank_name} </td>
				</tr>
				<tr>
					<td>이체할 계좌</td>
					<td>${receivAc_number}</td>
				</tr>
				<tr>
					<td>예금주</td>
					<td>${account.name}</td>
				</tr>
				<tr>
					<td>이체할 금액</td>
					<td>
					<fmt:formatNumber type="number" pattern="###,###" value="${transferAmount}" /> 원</td>
				</tr>
				<tr>
					<td>메모</td>
					<td><input type="text" name="rc_text" value="${rc_text}"></td>
				</tr>
				<tr>
					<td colspan="2">
						<!-- 이체 버튼 -->
						<button id="transfer" type="button">이체</button>
					</td>
				</tr>
			</table>
			<input type="hidden" name="transferAmount" value="${transferAmount}">
			<input type="hidden" name="sendAc_number" value="${sendAc_number}">
			<input type="hidden" name="sendBank_cd" value="${sendBank_cd}">
			<input type="hidden" name="receivAc_number"	value="${receivAc_number}">
			<input type="hidden" name="receivBank_cd"	value="${selected_bank_cd}">
	   <%-- <input type="hidden" name="receivAc_number"	value="${account.ac_number}">
			<input type="hidden" name="receivBank_cd"	value="${account.bank_cd}"> --%>
		</form>
	</section>

	<!-- 패스워드 확인을 위한 modal form -->
	<div id="password-dialog" title="이체 비밀번호 확인">
		<form>
			<fieldset>
				<label for="password">비밀번호</label> <input type="password"
					name="password" id="password"
					class="text ui-widget-content ui-corner-all"> <input
					type="submit" tabindex="-1"
					style="position: absolute; top: -1000px">
			</fieldset>
		</form>
	</div>

</body>
</html>
