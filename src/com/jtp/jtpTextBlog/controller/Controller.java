package com.jtp.jtpTextBlog.controller;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;

public abstract class Controller {
	public static ArticleController articleController;

	static {
		articleController = new ArticleController();
	}
	public abstract String ret(String cmd);
}