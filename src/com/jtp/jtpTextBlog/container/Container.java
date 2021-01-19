package com.jtp.jtpTextBlog.container;

import java.util.Scanner;

import com.jtp.jtpTextBlog.controller.ArticleController;
import com.jtp.jtpTextBlog.controller.Controller;
import com.jtp.jtpTextBlog.dao.ArticleDao;
import com.jtp.jtpTextBlog.service.ArticleService;

public class Container {

	public static ArticleService articleService;
	public static Controller articleController;

	static {
		articleService = new ArticleService();
		articleController = new ArticleController();
	}
}
