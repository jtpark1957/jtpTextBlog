<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<style>
.con1 {
	padding-top: 10px;
	height: auto;
	
}
.con1 .con1-form {
 /* position: sticky;
  top: 10px; */
 }
.con1 .divTextArea {
	width: 100%;
	height: 80vh;
	
	border-bottom: #181818 solid 2vh;
	font-size: 0.9em;
	overflow: auto;
}
.con1 a{
	 text-decoration: underline; 

}

.textMessage {
	background-color: #181818;
	color: white;
	border: 0 none;
	padding: 5px 10px;
	border-radius: 5px;
	width: calc(50% - 0px);
}

.input {
	display: none;
}
</style>
<div class="con1">
	<form class="con1-form"name="test" onsubmit="return false;" autocomplete=off>


		<!-- 송신 메시지를 작성하는 텍스트 박스 -->
		<input class="textMessage" id="textMessage" placeholder="command..."
			type="text" onkeypress="if(event.keyCode==13) {sendMessage();}">
		<!-- 메시지 송신을 하는 버튼 -->
		<input class="input" onclick="sendMessage()" value="Send"
			type="button">
		<!-- WebSocket 접속 종료하는 버튼 -->
		<input class="input" onclick="disconnect()" value="Disconnect"
			type="button">
	</form>
	<br />
	<!-- 콘솔 메시지의 역할을 하는 로그 텍스트 에리어.(수신 메시지도 표시한다.) -->
	<textarea id="messageTextArea" rows="30" cols="70"
		style="display: none;"></textarea>
	<div class="divTextArea" id="divTextArea"></div>
</div>

<script type="text/javascript">	
	
      var webSocket = new WebSocket("ws://localhost:8083/websocket");
      	
      var messageTextArea = document.getElementById("messageTextArea");	
      var divTextArea =  document.getElementById("divTextArea");
      webSocket.onopen = function(message) {	
        messageTextArea.value += "Server connect...\n";	
        divTextArea.innerHTML += "Server connect..."+"<br>";
        sendMessage("hello world");	 
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
    function sendMessage(msg) {
      var message = document.getElementById("textMessage");		
      if(msg != null) {
      	message.value += msg;
      }
      messageTextArea.value += "cmd) "+message.value+"\n";	
      divTextArea.innerHTML += "cmd) "+message.value+"<br>";
      webSocket.send(message.value);	
      if (message.value.indexOf("clear") > -1) { 
      	messageTextArea.value = '';
      	divTextArea.innerHTML = '';
      } 
      
      messageTextArea.scrollTop = messageTextArea.scrollHeight;
      divTextArea.scrollTop = divTextArea.scrollHeight;
      

      message.value = "";	
    }	
    function disconnect() {	
      webSocket.close();	
    }	
  </script>
<%@ include file="/jsp/part/foot.jspf"%>