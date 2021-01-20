package com.jtp.jtpTextBlog.container;


import com.jtp.jtpTextBlog.controller.ArticleController;
import com.jtp.jtpTextBlog.controller.Controller;
import com.jtp.jtpTextBlog.controller.MemberController;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;
import com.jtp.jtpTextBlog.session.Session;


public class Container {

	
	public static ArticleService articleService;
	public static MemberService memberService;
	
	public static Controller articleController;
	public static Controller memberController;
	public static Session session;
	

	static {
		
		articleService = new ArticleService();
		memberService = new MemberService();
		session = new Session();
		articleController = new ArticleController();
		memberController = new MemberController();
	}
}
