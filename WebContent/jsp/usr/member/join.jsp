<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/js-sha256/0.9.0/sha256.min.js"></script>
<style>
.line {
  overflow: hidden;
  margin-bottom: 15px;

}
.line input{
  background-color: var(--whitesmoke);
    border: 0 none;
    border-radius: var(--radius);
    color: var(--black);
  padding:5px 10px;
  width:calc(100% - 20px);
}
.line input[type="submit"] {
  width: auto;
  background-color:var(--black);
  color: var(--white);
  float:right;
}
.line label {
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
    white-space: nowrap;
}
.left {
  float: left;
  width: calc(50%);

}
.right {
  float: right;
  width: calc(50% - 1px);

}
.msg {
	font-size: 13px;
}
</style>

<div class="title">
	<p>Register</p>
</div>
<script>
	let DoJoinForm__checkedLoginId = "";
	let DoJoinForm__submited = false;
	// 로그인 아이디 중복체크
	function DoJoinForm__checkLoginIdDup(el) {
		const from = $(el).closest('form').get(0);
		const loginId = from.loginId.value;
		$.get(
			"getLoginIdDup",
			{
				loginId
			},
			function(data) {
				if(loginId.length==0 ) {
					$(".id_check").text("*");
					$(".id_check").css('color','var(--black)');
				} else if ( data == "YES" ) {
					$(".id_check").text("사용가능한 아이디입니다");
					$(".id_check").css('color','green');
					DoJoinForm__checkedLoginId = loginId;
				}
				else {
					$(".id_check").text("이미사용중인 아이디입니다");
					$(".id_check").css('color','red');				
				}
			},
			"html"
		);
	}
	// 폼 발송전 체크
	function DoJoinForm__submit(form) {
		if ( DoJoinForm__submited ) {
			alert('처리중입니다.');
			return;
		}
			form.username.value = form.username.value.trim();
	
		if ( form.username.value.length == 0 ) {
			alert('별명을 입력해주세요.');
			form.username.focus();
			return;
		}
		form.loginId.value = form.loginId.value.trim();
		if ( form.loginId.value.length == 0 ) {
			alert('로그인 아이디를 입력해주세요.');
			form.loginId.focus();
			return;
		}
		if ( form.loginId.value != DoJoinForm__checkedLoginId ) {
			alert('이미 사용중인 아이디입니다.');
			return;
		}
		form.password.value = form.password.value.trim();
	
		if ( form.password.value.length == 0 ) {
			alert('로그인 비밀번호를 입력해주세요.');
			form.password.focus();
			
			return;
		}
		
		form.passwordc.value = form.passwordc.value.trim();
	
		if ( form.passwordc.value.length == 0 ) {
			alert('로그인 비밀번호 확인을 입력해주세요.');
			form.passwordc.focus();
			
			return;
		}
		
		if ( form.password.value != form.passwordc.value ) {
			alert('로그인 비밀번호가 일치하지 않습니다.');
			form.passwordc.focus();
			
			return;
		}
		form.email.value = form.email.value.trim();
	
		if ( form.email.value.length == 0 ) {
			alert('이메일을 입력해주세요.');
			form.email.focus();
			return;
		}
		form.loginPwReal.value = sha256(form.password.value);
		form.password.value = "";
		form.passwordc.value = "";
		
		form.submit();
		DoJoinForm__submited = true;
	}
</script>
<div class="register">
	<form action="doJoin" method="POST" onsubmit="DoJoinForm__submit(this); return false;">
	<input type="hidden" name="loginPwReal" />
		<div class="line">
			<div class="left">
				<label for="id_username" >nickname<i>*</i></label> <input type="text"
					name="username" id="id_nickname" maxlength="20" value="" autocomplete="off">
			</div>
			<div class="right">
				<label for="id_userId">ID  <i class="msg id_check">*</i></label> <input onblur="DoJoinForm__checkLoginIdDup(this);" type="text"
					name="loginId" id="id_userId" maxlength="30" value="" autocomplete="off">
					<!-- <button onclick="DoJoinForm__checkLoginIdDup(this);" name="btnLoginIdDupCheck" type="button">중복체크</button> -->
			</div>
		</div>
		<div class="line">
			<div class="left">
				<label for="id_password">Password<i>*</i></label> <input type="password"
					name="password" id="id_password" maxlength="120" value="">
			</div>
			<div class="right">
				<label for="id_passwordc">Password confirmation<i>*</i></label> <input
					type="password" name="passwordc" id="id_passwordc" maxlength="120" value="">
			</div>
		</div>
		<div class="line">
			<div class="left">
				<label for="id_email">Email address<i>*</i></label> <input
					type="email" name="email" id="id_email" maxlength="50"
					value="" autocomplete="off">
			</div>
			<div class="right">
				<br> <input type="submit" value="register" />
			</div>
		</div>

	</form>
</div>
<%@ include file="/jsp/part/foot.jspf"%>