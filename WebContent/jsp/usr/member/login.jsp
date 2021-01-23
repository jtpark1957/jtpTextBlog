<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
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
		
		form.submit();
		DoLoginForm__submited = true;
	}
	</script>
	<div class="title">
		<p>login</p>
	</div>
	<form action="doLogin" method="POST" onsubmit="DoLoginForm__submit(this); return false;">
		<div class="left">
			<label for="id_loginId">Username or email</label> <input type="text"
				name="loginId" id="id_loginId" value="">
		</div>
		<div class="right">
			<label for="id_loginPw">Password</label> <input type="password"
				name="loginPw" id="id_loginPw" value="">
		</div>
		<input type="submit" value="Login">
	</form>
	
</div>
<%@ include file="/jsp/part/foot.jspf"%>