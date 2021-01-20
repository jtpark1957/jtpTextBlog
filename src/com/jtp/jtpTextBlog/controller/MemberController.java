package com.jtp.jtpTextBlog.controller;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;
	public MemberController() {
		memberService = Container.memberService;
	}
	public String ret(String cmd) {
		// TODO Auto-generated method stub
		return "";
	}

}
