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
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
  #password-dialog {
    display: none;
  }
</style>
<script>
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
        removeCommas(form.transferAmount);
        return true;
    }
    function onSubmitForm(form) {
        var accountBalance = parseInt(form.AC_MONEY.value.replace(/,/g, ''), 10);
        var transferAmount = parseInt(form.transferAmount.value.replace(/,/g, ''), 10);

        if (transferAmount > accountBalance) {
            alert("잔액이 부족합니다.");
            return false;
        }

        removeCommas(form.transferAmount);
        return true;
    }
</script>
</head>
<body>
<form action="${contextPath}/TrasferConfrimPage.do" method="post" onsubmit="return onSubmitForm(this);">
송금계좌 : <input type="text" name="sendAc_number" value= "${account.ac_number}" readonly> <br>
계좌잔액 : <input type="text" name="AC_MONEY" value= "<fmt:formatNumber type="number" pattern="###,###" value="${account.AC_MONEY}" />" readonly> <br>
예금주 : <input type="text" name="name" value= "${account.name}" readonly> <br>
<br>
이체할 은행 : <select name="selected_bank_cd">
                <c:forEach items="${bankList}" var="bank">
                    <option value="${bank.bank_cd}">${bank.bank_name}</option>
                </c:forEach>
             </select> <br>
이체할 계좌 : <input type="text" name="receivAc_number"> <br>
이체할 금액 : 	<input type="text" name="transferAmount" onkeyup="formatInput(this);"> 원 <br>				
<input type="submit" value="이체">
</form>

<!-- 계좌번호 확인을 위한 modal form -->
<div id="checkAc_number-dialog" title="계좌번호 확인">
  <form>
    <fieldset>
      <label for="password">비밀번호</label>
      <input type="password" name="password" id="password" class="text ui-widget-content ui-corner-all">
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>

</body>
</html>