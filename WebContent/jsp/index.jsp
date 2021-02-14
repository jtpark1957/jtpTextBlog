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
	
	border-bottom: var(--whitesmoke) solid 2vh;
	font-size: 0.9em;
	overflow: auto;
}
.con1 a{
	 text-decoration: underline; 

}

.textMessage {
	background-color: var(--whitesmoke);
	color: var(--black);
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
	  var url = location.href;
	  url = url.split('//');
	  // console.log("index.jsp "+url[1]);
      var webSocket = new WebSocket("ws://"+url[1]+"/websocket");
      	
      var divTextArea =  document.getElementById("divTextArea");
      webSocket.onopen = function(message) {	
        divTextArea.innerHTML += "Server connect..."+"<br>"; 
        // Object lobj_getdata = session.getAttribute("loginedMemberId");
        
        
        // console.log("<%=session.getAttribute("loginedMemberId")%>");
        
        sendMessage("hello world " + <%=session.getAttribute("loginedMemberId")%>, 0);	 
      };	
      webSocket.onclose = function(message) {	
        divTextArea.innerHTML += "Server Disconnect...\n";	
      };	
      webSocket.onerror = function(message) {	
        divTextArea.innerHTML += "error...\n";	
      };	
      webSocket.onmessage = function(message) {	
      	console.log(message);
      	if(message.data.startsWith("/")) {
      		location.href=message.data;
      	
      	} else {
      		divTextArea.innerHTML += message.data;
      	} 
        	
      };	
    function sendMessage(msg, vid) {
      var message = document.getElementById("textMessage");		
      if(msg != null) {
      	message.value += msg;
      }
      if(vid != 0) {
      	divTextArea.innerHTML += "cmd) "+message.value+"<br>";
      }
      
      
      webSocket.send(message.value);	
      if (message.value.indexOf("clear") > -1) { 
      	messageTextArea.value = '';
      	divTextArea.innerHTML = '';
      } if(message.value.indexOf("dark") > -1) {
	      if($.cookie('dark') && $.cookie('dark').indexOf("Y") > -1) {
	      	$.cookie('dark', 'N', {path: '/' });
	      } else {
	      	$.cookie('dark', 'Y', {path: '/' });
	      }
	      location.href="/";
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