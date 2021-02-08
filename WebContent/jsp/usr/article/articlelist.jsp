<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@page import="com.jtp.jtpTextBlog.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	

	int totalPage = (int) request.getAttribute("totalPage");
	int paramPage = (int) request.getAttribute("page");
	int boardId = (int) request.getAttribute("boardId");
	String code1 = (String) request.getAttribute("code");
%>
<style>
.page {
	text-align: center;
	padding-top: 10px;
}

.page a {
	padding: 0 5px;
}

.page .current {
	text-decoration: underline;
	color: #787878;
}

.page .none {
	color: red;
	font-size: 3px;
	display: none;
}
.board {
  float: right;
}
</style>
<div class="list">
	<p>article list <%=code1%></p>
	<%
	for (Article article : articles) {
	%>
	<div class="ar">
		<div class="title"> 
		<%
		/* Board board = getBoardByCode(article.boardId); */
		%>
			<a class="w"><%=article.extra__writer%> </a> <a class="time"><%=article.regDate%></a>
			<a class="board"><%=article.extra__boardName%> </a>
		</div>
		<a class="cont" href="/s/detail?id=<%=article.id%>"><%=article.title%></a>
	</div>
	<%
	}
	%>

</div>
<div class="search">
	<script>
	let DoSearchForm__submited = false;
	function DoSearchForm__submit(form) {
		if ( DoSearchForm__submited ) {
			alert('처리중입니다');
			return;
		}
	
		form.searchKeyword.value = form.searchKeyword.value.trim();
		
		
		
		form.submit();
		DoSearchForm__submited = true;
	}
	</script>
	<form action="" onsubmit="DoSearchForm__submit(this); return false;">
		<%-- <input type="hidden" name="boardId" value="<%=boardId%>" /> --%> 
		
		<select name="boardcode">
			<option value="">전체</option>
			<%
			
			List<Board> boards = (List<Board>) request.getAttribute("boards");
			for (Board board : boards) {
				
				%> <option value="<%=board.code%>"><%=board.name%></option> <%  
			}
			%>
		</select> 
		<select name="searchKeywordType">
			<option value="title">제목</option>
			<option value="body">본문</option>
		</select>
		
		<script>
		const param__searchKeywordType = '${param.searchKeywordType}';
		const param__boardcode = '${param.boardcode}';
		
		if ( param__searchKeywordType ) {
			$('select[name="searchKeywordType"]').val(param__searchKeywordType);
		}
		if ( param__boardcode ) {
			$('select[name="boardcode"]').val(param__boardcode);
		}
		url = location.href 



		</script>
		<input value="${param.searchKeyword}" type="text" name="searchKeyword" placeholder="" />
		<input type="submit" value="검색" />
		<a class="writebutton" href="/s/write?boardcode=${param.boardcode}"><i class="fas fa-pen"></i> 쓰기</a>
	</form>
</div>
<script>
</script>
<div class="page">
	<a class="display" href="?boardcode=${param.boardcode}&searchKeywordType=${param.searchKeywordType}
					&searchKeyword=${param.searchKeyword}&page=1"><<</a>
	<%
	for (int i = 1; i <= totalPage; i++) {
		String code = "none";
		int show = 3;
		int min = paramPage-show;
		int max = paramPage+show;
	
		if(paramPage <= show) {
			max += show-paramPage+1;
		}
		if(max >= i) { 
			if(i >= min) {
				code = "display";	
				if(i==paramPage) {
					code="current";
					/* ?boardcode=free&searchKeywordType=title&searchKeyword= */
				}
				%>
	
					<a class="<%=code%>" href="?boardcode=${param.boardcode}&searchKeywordType=${param.searchKeywordType}
					&searchKeyword=${param.searchKeyword}&page=<%=i%>"><%=i%></a>
					
	<% 
			}
		}
	}
%>
<a class="display" href="?boardcode=${param.boardcode}&searchKeywordType=${param.searchKeywordType}
					&searchKeyword=${param.searchKeyword}&page=<%=totalPage%>">>></a>
</div>
<%@ include file="/jsp/part/foot.jspf"%>