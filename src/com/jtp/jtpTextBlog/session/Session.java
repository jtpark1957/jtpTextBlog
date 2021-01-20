package com.jtp.jtpTextBlog.session;

public class Session {

	private static int loginedMemberId;
	
	private String currentBoardCode;

	public Session() {
		// 공지사항을 기본 선택된 게시판으로 지정
		currentBoardCode = "notice";
	}
	public boolean isLogined() {
		return loginedMemberId > 0;
	}

	public int getLoginedMemberId() {
		return loginedMemberId;
	}

	public void logout() {
		loginedMemberId = 0;

	}

	public String getCurrentBoardCode() {
		// TODO Auto-generated method stub
		return currentBoardCode;
	}
	public void login(int id) {
		loginedMemberId = id;
	}
	public void setCurrentBoardCode(String code) {
		this.currentBoardCode = code;
		
	}

}
