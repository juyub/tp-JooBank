<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/css/main.css">
<style>
table {
    border-collapse: collapse;
}

td {
    padding: 5px;
}

/* a {
    text-decoration: none;
    color: inherit;
}

section {
    margin-left: 5%;
    margin-right: 5%;
    padding: 20px;
} */
#tr_btn_modify {
	display: none;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function backToList(obj) {
    obj.action = "boardList";
    obj.submit();
}

function fn_enable() {
    document.getElementById("i_title").disabled = false;
    document.getElementById("i_content").disabled = false;

    document.getElementById("tr_btn_modify").style.display = "block";
    document.getElementById("tr_btn").style.display = "none";
}

function fn_modify_board(obj) {
    obj.action = "updateNews.action";
    obj.submit();
}

function fn_remove_board(url, boardNO) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", url);
    var boardNOInput = document.createElement("input");
    boardNOInput.setAttribute("type", "hidden");
    boardNOInput.setAttribute("name", "boardNO");
    boardNOInput.setAttribute("value", boardNO);

    form.appendChild(boardNOInput);
    document.body.appendChild(form);
    form.submit();
}

function fn_reply_form(url, boardNO) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", url);
    var boardNOInput = document.createElement("input");
    boardNOInput.setAttribute("type", "hidden");
    boardNOInput.setAttribute("name", "seq");
    boardNOInput.setAttribute("value", seq);

    form.appendChild(boardNOInput);
    document.body.appendChild(form);
    form.submit();
}
</script>
</head>
<body>
    <header>
        <jsp:include page="/topMenu.jsp" />
    </header>
    
    <section>
        <br>
        <button><a href="javascript:history.back()">뒤로가기</a></button>
        <br><br>
        <form action="${contextPath}/updateNews.action" method="post">
            <input type="hidden" name="seq" value="${board.seq}">
            <table border="1">
                <tr>
                    <th>제목</th>
                    <td>
                        <input id="i_title" name="title" type="text" value="${board.title}" disabled>
                    </td>
                </tr>
                <tr>
                    <th>작성자</th>
                    <td>${board.writer}</td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td>
                        <textarea id="i_content" name="content" cols="40" rows="10" disabled>${board.content}</textarea>
                    </td>
                </tr>
                <tr>
                    <th>작성일자</th>
                    <td>
                        <fmt:formatDate value="${board.regDate}" pattern="yy/MM/dd HH:mm:ss"/>
                    </td>
                </tr>
            </table>
            <table>
                <tr id="tr_btn">
                    <td>
                         <input type="button" value="수정하기" onClick="fn_enable()">
                         <input type="button" value="삭제하기" onClick="fn_remove_board('deleteNews.action', ${board.seq})">
                    </td>
                </tr>
                <tr id="tr_btn_modify">
                    <td>
                        <input type="button" value="수정반영하기" onClick="fn_modify_board(this.form)">
                        <input type="button" value="취소" onClick="backToList(this.form)">
                    </td>
                </tr>
            </table>
        </form>
    </section>
</body>
</html>
