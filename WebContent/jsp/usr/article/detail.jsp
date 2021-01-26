<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<div class="list">
      <p>article detail</p>
  
      <div class="ar">
        <div class="title">
          <a class="w"><%=article.extra__writer%> </a>
          <a class="time"><%=article.regDate%></a>
          <a class="delete" onclick="if ( confirm('정말 삭제하시겠습니까?') == false ) { return false; }" href="doDelete?id=<%=article.id%>">삭제</a>
          <a class="modify" href="modify?id=<%=article.id%>">수정</a>
	      
         </div>
        <a><%=article.title%></a>
      </div>
      <div class="body">
      	<%=article.body %>
      </div>
     
    </div>
<%@ include file="/jsp/part/foot.jspf"%>