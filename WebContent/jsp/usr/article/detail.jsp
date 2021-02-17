<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>

<%
	Article article = (Article) request.getAttribute("article");
	System.out.println(session.getAttribute("loginedMemberId"));
	int loginId;
	if(session.getAttribute("loginedMemberId") == null) {
		loginId = 0;
	} else {
		loginId = (int) session.getAttribute("loginedMemberId");
	}
%>
<style>
.reply {
  position: relative;
  width: 100%;
  border: solid 2px var(--whitesmoke);
}
.reply textarea {
  border:none;
  resize: vertical;
  width:calc(98%); 
  height:7vh;
}
.reply label {
  /* display: block; */
}
.reply input[type="submit"] {
/*   position: absolute; */
  float: right;
  border:none;
  background-color:var(--whitesmoke);
  color:var(--black);
  height: auto;
  padding: 5px;
}
</style>
<div class="list">
      <p>article detail</p>
  
      <div class="ar">
        <div class="title">
          <a class="w"><%=article.extra__writer%> </a>
          <a class="time"><%=article.regDate%></a>
          <% if(article.memberId == loginId) {%>
          <a class="delete" onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }" href="doDelete?id=<%=article.id%>">삭제</a>
          <a class="modify" href="modify?id=<%=article.id%>">수정</a>
          <%} %>
	      
         </div>
        <a><%=article.title%></a>
      </div>
      <div class="body">
      	<script type="text/x-template"><%=article.body%></script>
  		 <div class="toast-ui-viewer"></div>
      </div>
<script>
	let Reply__DoWriteForm__submited = false;
	let Reply__DoWriteForm__checkedLoginId = "";
	
	// 폼 발송전 체크
	function Reply__DoWriteForm__submit(form) {
		if ( Reply__DoWriteForm__submited ) {
			alert('처리중입니다.');
			return;
		}
			
		form.reply_text.value = form.reply_text.value.trim();	
		form.redirectUrl.value = 	
		if ( form.reply_text.value.length == 0 ) {
			alert('내용을 입력해주세요.');
			form.reply_text.focus();
			
			return;
		}
		
		
		form.submit();
		Reply__DoWriteForm__submited = true;
	}
</script>
	<div class="reply">
      <form action="replydoWrite" method="POST"
			onsubmit="Reply__DoWriteForm__submit(this); return false;">
        <label for="reply_text">댓글쓰기</label>
        <input type="hidden" name="redirectUrl" value="/s/detail?id=<%=article.id%>" />
		<input type="hidden" name="relTypeCode" value="article" />
		<input type="hidden" name="relId" value="<%=article.id%>" />
        <input type="submit" value="등록" />
        <textarea wrap="hard" name="reply_text" id="reply_text" placeholder="댓글을 남겨주세요.."></textarea>
      </form>
    </div>
     
    </div>
    
  
<%@ include file="/jsp/part/foot.jspf"%>