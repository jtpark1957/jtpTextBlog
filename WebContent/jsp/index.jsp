<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Web Socket Example</title>
</head>
<body>
	<form name="test" onsubmit="return false;" autocomplete=off>


		<!-- 송신 메시지를 작성하는 텍스트 박스 -->
		<input id="textMessage" type="text"
			onkeypress="if(event.keyCode==13) {sendMessage();}">
		<!-- 메시지 송신을 하는 버튼 -->
		<input onclick="sendMessage()" value="Send" type="button">
		<!-- WebSocket 접속 종료하는 버튼 -->
		<input onclick="disconnect()" value="Disconnect" type="button">
	</form>
	<br />
	<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
	<textarea id="messageTextArea" rows="30" cols="70"></textarea>
	<script type="text/javascript">	
	
      var webSocket = new WebSocket("ws://localhost:8083/jtpTextBlog/websocket");	
      var messageTextArea = document.getElementById("messageTextArea");	
      webSocket.onopen = function(message) {	
        messageTextArea.value += "Server connect...\n";	
      };	
      webSocket.onclose = function(message) {	
        messageTextArea.value += "Server Disconnect...\n";	
      };	
      webSocket.onerror = function(message) {	
        messageTextArea.value += "error...\n";	
      };	
      webSocket.onmessage = function(message) {	
        messageTextArea.value += message.data;	
      };	
    function sendMessage() {
    	var message = document.getElementById("textMessage");		
      messageTextArea.value += "cmd) "+message.value+"\n";	
      webSocket.send(message.value);	
      if (message.value.indexOf("clear") > -1) { 
      	messageTextArea.value = '';
      } 	
      messageTextArea.scrollTop = messageTextArea.scrollHeight;
      message.value = "";	
    }	
    function disconnect() {	
      webSocket.close();	
    }	
  </script>
</body>
</html>