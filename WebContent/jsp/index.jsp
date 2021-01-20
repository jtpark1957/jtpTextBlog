<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Web Socket Example</title>
</head>
<body >
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
	<textarea id="messageTextArea" rows="30" cols="70" style="display:none;"></textarea>
	<div id="divTextArea" style="width:100%; height:600px;font-size:0.9em; overflow:auto;border: 1px solid;" >
	</div>

	<script type="text/javascript">	
	
      var webSocket = new WebSocket("ws://localhost:8083/jtpTextBlog/websocket");
      	
      var messageTextArea = document.getElementById("messageTextArea");	
      var divTextArea =  document.getElementById("divTextArea");
      webSocket.onopen = function(message) {	
        messageTextArea.value += "Server connect...\n";	
        divTextArea.innerHTML += "Server connect..."+"<br>";	 
      };	
      webSocket.onclose = function(message) {	
        messageTextArea.value += "Server Disconnect...\n";	
        divTextArea.innerHTML += "Server Disconnect...\n";	
      };	
      webSocket.onerror = function(message) {	
        messageTextArea.value += "error...\n";	
        divTextArea.innerHTML += "error...\n";	
      };	
      webSocket.onmessage = function(message) {	
      	console.log(message);
        messageTextArea.value += message.data;	
        divTextArea.innerHTML += message.data;	
      };	
    function test() {
    	console.log("asdasd");
    }
    function sendMessage(msg) {
      var message = document.getElementById("textMessage");		
      console.log(msg);
      if(msg != null) {
      	console.log("asdasd");
      	message.value += msg;
      }
      messageTextArea.value += "cmd) "+message.value+"\n";	
      divTextArea.innerHTML += "cmd) "+message.value+"<br>";
      webSocket.send(message.value);	
      if (message.value.indexOf("clear") > -1) { 
      	messageTextArea.value = '';
      	divTextArea.innerHTML = '';
      } else if (message.value.indexOf("test") > -1) { 
      	messageTextArea.value += '<a href=\"http://www.cosmosfarm.com/\">코스모스팜</a>';
     
      } 	
      messageTextArea.scrollTop = messageTextArea.scrollHeight;
      divTextArea.scrollTop = divTextArea.scrollHeight;

      message.value = "";	
    }	
    function disconnect() {	
      webSocket.close();	
    }	
  </script>
</body>
</html>