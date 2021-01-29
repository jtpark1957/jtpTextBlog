<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<%
%>
<style>
.login label {
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}
.login .title {
  /* font-weight: bold; */
}
.login input[type="text"],
.login input[type="password"] {
  width: calc(100% - 20px);
  padding: 5px 10px;
  color: var(--black);
  background-color: var(--whitesmoke);
  border: 0 none;
  border-radius: var(--radius);
}
.login input[type="submit"] {
  margin-top: 10px;
  background-color: var(--black);
  border: 0 none;
  border-radius: var(--radius);
  color: var(--whitesmoke);
  cursor: pointer;
  float: right;
  font-size: var(--small);
  padding: 5px 15px;
}
.left {
  float: left;
  width: calc(50% - 1px);
}
.right {
  float: right;
  width: calc(50% - 1px);
}
.error {
	color: red;
	font-size: var(--small);
}
.reg {
    margin-top: 10px;
    display: flex;
    float: left;
}
/* .reg a {
  background-color: var(--whitesmoke);
  color: var(--black);
  border-radius: var(--radius);
  border: solid 3px var(--black);
  font-size: 15px;
  overflow: hidden;
  white-space: nowrap;
  padding: 5px;
 } */
 .reg a:hover {
 	text-decoration:underline;
 }
</style>

<div class="login">
<script>
	let DoLoginForm__submited = false;
	function DoLoginForm__submit(form) {
		if ( DoLoginForm__submited ) {
			alert('처리중입니다.');
			return;
		}
	
		form.loginId.value = form.loginId.value.trim();
	
		if ( form.loginId.value.length == 0 ) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			
			return;
		}
		
		form.loginPw.value = form.loginPw.value.trim();
	
		if ( form.loginPw.value.length == 0 ) {
			alert('로그인 비밀번호를 입력해주세요.');
			form.loginPw.focus();
			
			return;
		}
		form.loginPwReal.value = sha256(form.loginPw.value);
		form.loginPw.value = "";
		
		form.submit();
		DoLoginForm__submited = true;
	}
	</script>
	<%

	%>
	<div class="title">
		<p>login</p>
	</div>
	<form action="doLogin" method="POST" onsubmit="DoLoginForm__submit(this); return false;">
		<input type="hidden" name="loginPwReal" />
		<div class="left">
			<label for="id_loginId">Username or email</label> <input type="text"
				name="loginId" id="id_loginId" value="">
		</div>
		<div class="right">
			<label for="id_loginPw">Password</label> <input type="password"
				name="loginPw" id="id_loginPw" value="">
		</div>
		<div class="reg">
			<a href="/s/join"><i class="fas fa-user-plus"></i>register</a>
		</div>
		<%
			String error = (String)session.getAttribute("errorMsg");
			if(error != null) { 
		%>
	    <div class="error"><p><%=error%></p></div>
	    <%session.removeAttribute("errorMsg");}%>
		<input type="submit" value="Login">
	</form>
	
</div>
<%@ include file="/jsp/part/foot.jspf"%>