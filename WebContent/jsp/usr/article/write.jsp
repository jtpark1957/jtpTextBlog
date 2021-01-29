<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@page import="com.jtp.jtpTextBlog.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
/* System.out.println(session.getAttribute("loginedMemberId")); */
%>
<style>
.textbody {
	width: 100%;
}
</style>
<script>

	let DoWriteForm__submited = false;
	let DoWriteForm__checkedLoginId = "";

	// 폼 발송전 체크
	function DoWriteForm__submit(form) {
		if ( DoWriteForm__submited ) {
			alert('처리중입니다.');
			return;
		}
		form.boardcode.value = form.boardcode.value.trim();		
		if ( form.boardcode.value.length == 0 ) {
			alert('게시판을 설정해주세요.');
			form.boardcode.focus();
			
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
<div class="articlewrite">
      <p>article write</p>
  	<form action="doWrite" method="POST" onsubmit="DoWriteForm__submit(this); return false;">
  	<input type="hidden" name="body">
      <div class="ar">
        <div class="title">
          <a class="w">게시글작성</a>
          <a class="time"></a>
	      <select name="boardcode">
						<%
			
			List<Board> boards = (List<Board>) request.getAttribute("boards");
			for (Board board : boards) {
				if(board.id != 1) {
				%> <option value="<%=board.code%>"><%=board.name%></option> <%  
			} }
			%>
		</select> 
		
	      <script>
	      	const param__boardcode = '${param.boardcode}';

		if ( param__boardcode ) {
			$('select[name="boardcode"]').val(param__boardcode);
		}
	      </script>
         </div>
        <b><input autocomplete ="off" value="" type="text" name="title" style="width: 100%; padding: 5px 0px;"placeholder=""></b>
      </div>
      <div class="textbody">
     	 <script type="text/x-template"></script>
		<div class="toast-ui-editor"></div>
      </div>
      <input style="margin-top: 10px;"type="submit" value="등록">
    </form> 
  </div>
    
<%@ include file="/jsp/part/foot.jspf"%>