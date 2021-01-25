<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@page import="com.jtp.jtpTextBlog.dto.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	List<Article> articles = (List<Article>) request.getAttribute("articles");
	
	int totalPage = (int) request.getAttribute("totalPage");
	int paramPage = (int) request.getAttribute("page");
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
	<p>article list</p>
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
<div class="page">
	<a class="display" href="?page=1"><<</a>
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
				}
				%>
					<a class="<%=code%>" href="?page=<%=i%>"><%=i%></a>
	<% 
			}
		}
	}
%>
<a class="display" href="?page=<%=totalPage%>">>></a>
</div>
<%@ include file="/jsp/part/foot.jspf"%>