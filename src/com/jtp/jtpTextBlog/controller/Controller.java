package com.jtp.jtpTextBlog.controller;

import javax.servlet.http.HttpServletRequest;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;

public abstract class Controller {

	public static ArticleController articleController;
	public static MemberController memberController;
	public static ReplyController replyController;

	static {
		articleController = new ArticleController();
		memberController = new MemberController();
		replyController = new ReplyController();
	}

	public abstract String ret(String cmd);

	protected String msgAndBack(HttpServletRequest req, String msg) {
		req.setAttribute("alertMsg", msg);
		req.setAttribute("historyBack", true);
		return "common/redirect";
	}

	protected String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
		req.setAttribute("alertMsg", msg);
		req.setAttribute("replaceUrl", replaceUrl);
		return "common/redirect";
	}

//	protected String json(HttpServletRequest req, ResultData resultData) {
//		req.setAttribute("data", resultData);
//		return "common/json";
//	}

}