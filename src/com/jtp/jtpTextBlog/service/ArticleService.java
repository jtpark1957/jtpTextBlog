package com.jtp.jtpTextBlog.service;

import java.util.List;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.dao.ArticleDao;
import com.jtp.jtpTextBlog.dto.Article;
import com.jtp.jtpTextBlog.dto.Board;

public class ArticleService {
	private ArticleDao articleDao;

	public ArticleService() {
		articleDao = new ArticleDao();
	}

	public List<Article> getArticles() {
		// TODO Auto-generated method stub
		return articleDao.getArticles();
	}

	public Article getArticle(int id) {
		// TODO Auto-generated method stub
		return articleDao.getArticle(id);
	}

	public Board getBoardByCode(String boardCode) {
		return articleDao.getBoardByCode(boardCode);
	}
	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	}

	public List<Article> getForPrintArticles(int id) {
		// TODO Auto-generated method stub
		return articleDao.getForPrintArticles(id);
	}

	public List<Board> getForPrintBoards() {
		return articleDao.getForPrintBoards();
	}

	public int getArticlesCount(int boardId) {
		return articleDao.getArticlesCount(boardId);
	}

	public List<Article> getForPrintArticlesByBoardId(int page, int boardId ,int itemsInAPage) {
		return articleDao.getForPrintArticlesByBoardId(page, boardId ,itemsInAPage);
	}

	public Article getForPrintArticleById(int id) {
		// TODO Auto-generated method stub
		return articleDao.getForPrintArticleById(id);
	}

}
