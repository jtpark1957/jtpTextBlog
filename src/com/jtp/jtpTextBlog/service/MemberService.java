package com.jtp.jtpTextBlog.service;

import java.util.Map;

import com.jtp.jtpTextBlog.dao.MemberDao;
import com.jtp.jtpTextBlog.dto.Member;

public class MemberService {
	private MemberDao memberDao;
	public MemberService() {
		memberDao = new MemberDao();
	}
	public Member getMemberById(int memberId) {
		return memberDao.getMemberById(memberId);
	}
	public Member getMemberByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return memberDao.getMemberByLoginId(loginId);
	}
	public int join(Map<String, Object> joinArgs) {
		return memberDao.join(joinArgs);
	}

}
