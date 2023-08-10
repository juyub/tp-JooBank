<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <script
      src="https://code.jquery.com/jquery-3.6.4.js"
      integrity="sha256-a9jBBRygX1Bh5lt8GZjXDzyOB+bWve9EiO7tROUtj/E="
      crossorigin="anonymous"
    ></script>
    <script src="util.js"></script>
    <link rel="stylesheet" href="style.css" />
  </head>

  <body>
  	<header>
		<jsp:include page="/topMenu.jsp" />
	</header>
	
    <div id="container">
      <div class="sliding-background"></div>
      <img id="mario" src="source/mario.gif" alt="" />
      <img id="enemy" src="source/enemy.png" alt="" />
      <img id="missile" src="source/missile.png" alt="" />
      <div id="intro">
        <h1>마리오</h1>
        jump -> <img src="source/w.png" style="height: 20px" /> 키<br />
        미사일 -> <img src="source/d.png" style="height: 20px" /> 키<br />
        <br />
        <input id="start" type="button" value="Start" />
        <h4>이주엽<br /></h4>
      </div>
      <div id="gameover_screen">
        GameOver <br />
        <input id="restart" type="button" value="재시작" />
      </div>

      <div id="score">0</div>
      <div id="div1"></div>
      <div id="div2"></div>
    </div>
  </body>
</html>
