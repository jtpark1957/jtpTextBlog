<%@page import="com.jtp.jtpTextBlog.dto.Article"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/part/head.jspf"%>
<%
	Article article = (Article) request.getAttribute("article");
%>
<div class="list">
      <p>article list</p>
  
      <div class="ar">
        <div class="title">
          <a class="w"><%=article.extra__writer%> </a>
          <a class="time"><%=article.regDate%></a>
         </div>
        <b><%=article.title%></b>
      </div>
      <div class="body">
      	<%=article.body %>
      </div>
     
    </div>
<%@ include file="/jsp/part/foot.jspf"%>