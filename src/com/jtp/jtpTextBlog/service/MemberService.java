package com.jtp.jtpTextBlog.service;

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

}
