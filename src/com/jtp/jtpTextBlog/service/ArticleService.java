package com.jtp.jtpTextBlog.service;

import java.util.List;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.dao.ArticleDao;
import com.jtp.jtpTextBlog.dto.Article;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return articleDao.getArticles();
	}
}
