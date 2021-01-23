package com.jtp.jtpTextBlog.controller;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;

public abstract class Controller {
	public static ArticleController articleController;
	public static MemberController memberController;

	static {
		articleController = new ArticleController();
		memberController = new MemberController();
	}
	public abstract String ret(String cmd);
}