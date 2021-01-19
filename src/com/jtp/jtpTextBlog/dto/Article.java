package com.jtp.jtpTextBlog.dto;

import java.util.Map;

public class Article {
	public int id;
	public String title;
	public String regDate;
	public String updateDate;
	public String body;
	public int memberId;
	public int boardId ;
	
	public String extra__writer;

	public Article(Map<String, Object> Map) {
		this.id = (int) Map.get("id");
		this.regDate = (String) Map.get("regDate");
		this.updateDate = (String) Map.get("updateDate");
		this.title = (String) Map.get("title");
		this.body = (String) Map.get("body");
		this.memberId = (int) Map.get("memberId");
		this.boardId = (int) Map.get("boardId");
		if (Map.containsKey("extra__writer")) {
			this.extra__writer = (String) Map.get("extra__writer");
		}
	}
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", regDate=" + regDate + ", updateDate=" + updateDate
				+ ", body=" + body + ", memberId=" + memberId + ", boardId=" + boardId + "]";
	}
}
