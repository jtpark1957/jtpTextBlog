<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@page import="com.jtp.jtpTextBlog.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
Article article = (Article) request.getAttribute("article");
%>
<style>
.te-ww-container {
	/* background-color:black; */
}
</style>
<script>
	let DoWriteForm__submited = false;
	let DoWriteForm__checkedLoginId = "";
	//insertText
	
	// 폼 발송전 체크
	function DoWriteForm__submit(form) {
		if ( DoWriteForm__submited ) {
			alert('처리중입니다.');
			return;
		}
	
		form.title.value = form.title.value.trim();
	
		if ( form.title.value.length == 0 ) {
			alert('제목을 입력해주세요.');
			form.title.focus();
			
			return;
		}
		
		const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
		const body = editor.getMarkdown().trim();
		
		if ( body.length == 0 ) {
			alert('내용을 입력해주세요.');
			editor.focus();
			
			return;
		}
		
		form.body.value = body;
		
		form.submit();
		DoWriteForm__submited = true;
	}
	</script>
<div class="list">
      <p>article modify</p>
  	<form action="doModify" method="POST" onsubmit="DoWriteForm__submit(this); return false;">
  	<input type="hidden" name="body">
  	<input type="hidden" name="id" value="<%=article.id%>" />
      <div class="ar">
        <div class="title">
          <a class="w">게시글수정</a>
          <a class="time"></a>
	      <select name="boardcode">
						<%
			
			List<Board> boards = (List<Board>) request.getAttribute("boards");
			for (Board board : boards) {
				if(board.id != 1) {
				%> <option value="<%=board.id%>"><%=board.name%></option> <%  
			} }
			%>
		</select> 
         </div>
        <b><input autocomplete ="off" value="<%=article.title %>" type="text" name="title" style="width: 100%; padding: 5px 0px;"placeholder=""></b>
      </div>
      <div class="body">
     	 <script type="text/x-template"><%=article.body%></script>
		<div class="toast-ui-editor"></div>
      </div>
      <input type="submit" value="수정">
    </form> 
  </div>
    
<%@ include file="/jsp/part/foot.jspf"%>