<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>금융송금</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<style>
#checkAc_numberDialog {
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
		var checkAc_numberDialog, transferForm;

		function checkAc_number() {
			var receivAc_number = $("#receivAc_number").val();
			var selected_bank_cd = $("select[name='selected_bank_cd']").val();

			// Ajax를 사용하여 계좌번호 확인을 위한 서버 측 요청을 수행합니다.
			$.ajax({
				url : '${contextPath}/checkAc_number.do',
				type : 'POST',
				data : {
					receivAc_number : receivAc_number,
					selected_bank_cd : selected_bank_cd
				},
				success : function(response) {
					if (response === 'true') {
						transferForm.off("submit"); // 폼 제출 이벤트 핸들러를 언바인드하세요
						transferForm.submit(); // 계좌번호 확인이 완료되면 전송
					} else {
						checkAc_numberDialog.dialog("open"); // 계좌번호 확인 실패 시 다이얼로그를 엽니다.
					}
				}
			});

			return false;
		}

		checkAc_numberDialog = $("#checkAc_numberDialog").dialog({
			autoOpen : false,
			height : 200,
			width : 350,
			modal : true,
			buttons : {
				"확인" : function() {
					checkAc_numberDialog.dialog("close");
				}
			}
		});

		transferForm = $("#transferForm");
		/* transferForm = $("form#transferForm"); */

		transferForm.on("submit", function(event) {
			event.preventDefault(); // 기본 동작인 폼 제출을 막습니다.
			checkAc_number(); // 계좌번호 검증 함수 호출
		});
	});
</script>
</head>
<body>
<body>
	<header>
		<jsp:include page="../topMenu.jsp" />
	</header>
	<section style="display: flex; justify-content: center;">

		<form id="transferForm" action="${contextPath}/obTransferConfirmPage.do"
			method="post" > <!-- onSubmit="return onSubmitForm(this);" -->
			<table border="1">
				<tr>
					<td>송금계좌</td>
					<td><input type="text" name="sendAc_number"
						value="${account.ac_number}" readonly>
						<input type="hidden" name="sendBank_cd" value="${account.bank_cd}"></td>
				</tr>
				<tr>
					<td>계좌잔액</td>
					<td><input type="text" name="AC_MONEY"
						value="<fmt:formatNumber type='number' pattern='###,###' value='${account.AC_MONEY}' />"
						readonly></td>
				</tr>
				<tr>
					<td>예금주</td>
					<td><input type="text" name="name" value="${account.name}"
						readonly></td>
				</tr>
				<tr>
					<td>이체할 은행</td>
					<td>
					<select name="selected_bank_cd">
				        <c:forEach items="${bankList}" var="bank">
				          <option value="${bank.bank_cd}">${bank.bank_name}</option>
				        </c:forEach>
				      </select>
					</td>
				</tr>
				<tr>
					<td>이체할 계좌</td>
					<td>
					<input type="text" id="receivAc_number"	name="receivAc_number"></td>
				</tr>
				<tr>
					<td>이체할 금액</td>
					<td>
					<input type="text" name="transferAmount" > 원</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" id="transfer" value="이체"></td>
				</tr>
			</table>
		</form>
	</section>
	
	<!-- 계좌번호 확인을 위한 modal form -->
	<div id="checkAc_numberDialog" title="계좌번호 확인">
		<form>
			<fieldset>
				<p class="errorMessage" style="color: red;">계좌번호가 유효하지 않습니다.</p>
			</fieldset>
		</form>
	</div>
	
	<!-- <script>
		function numberWithCommas(x) {
			return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
		}

		function formatInput(input) {
			input.value = numberWithCommas(input.value.replace(/,/g, ''));
		}

		function removeCommas(input) {
			input.value = input.value.replace(/,/g, '');
		}

		function onSubmitForm(form) {
			var accountBalance = parseInt(
					form.AC_MONEY.value.replace(/,/g, ''), 10);
			var transferAmount = parseInt(form.transferAmount.value.replace(
					/,/g, ''), 10);

			if (transferAmount > accountBalance) {
				alert("잔액이 부족합니다.");
				return false;
			}

			removeCommas(form.transferAmount);
			return true;
		}
	</script> -->
</body>
</html>
