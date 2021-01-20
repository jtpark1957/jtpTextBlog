package com.jtp.jtpTextBlog.dto;

import java.util.Map;

public class Member {
	public int id;
	public String regDate;
	public String updateDate;
	public String loginId;
	public String loginPw;
	public String name;
	public Member(Map<String, Object> Map) {
		this.id = (int) Map.get("id");
		this.regDate = (String) Map.get("regDate");
		this.updateDate = (String) Map.get("updateDate");
		this.loginId = (String) Map.get("loginId");
		this.loginPw = (String) Map.get("loginPw");
		this.name = (String) Map.get("name");
	}
	@Override
	public String toString() {
		return "Member [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", loginId=" + loginId
				+ ", loginPw=" + loginPw + ", name=" + name + "]";
	}
	public String getType() {
		return isAdmin() ? "관리자" : "일반회원";
	}
	public boolean isAdmin() {
		return loginId.equals("test1");
	}
}
