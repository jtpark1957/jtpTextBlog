<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%@ include file="/jsp/part/toastUiEditor.jspf"%>
<%

%>
<style>
.te-ww-container {
	/* background-color:black; */
}
</style>
<script>
	var DoWriteForm__submitDone = false;
	function DoWriteForm__submit(form) {
		if ( DoWriteForm__submitDone ) {
			alert('처리중입니다.');
			return;
		}
		
		form.title.value = form.title.value.trim();
		if ( form.title.value.length == 0 ) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			return false;
		}
		var editor = $(form).find('.toast-editor').data('data-toast-editor');
		var body = editor.getMarkdown();
		body = body.trim();
		if ( body.length == 0 ) {
			alert('내용을 입력해주세요.');
			editor.focus();
			return false;
		}
		form.body.value = body;
		form.submit();
		DoWriteForm__submitDone = true;
	}
</script>
<div class="list">
      <p>article write</p>
  	<form action="doWrite" method="POST" onsubmit="DoWriteForm__submit(this); return false;">
  	<input type="hidden" name="body">
      <div class="ar">
        <div class="title">
          <a class="w">게시글작성</a>
          <a class="time"></a>
	      
         </div>
        <b><input value="" type="text" name="title" style="width: 100%; padding: 5px 0px;"placeholder=""></b>
      </div>
      <div class="body">
     	 <script type="text/x-template"></script>
		<div class="toast-editor"></div>
      </div>
      <input type="submit" value="등록">
    </form> 
  </div>
    
<%@ include file="/jsp/part/foot.jspf"%>