package com.jtp.jtpTextBlog.controller;

import java.util.List;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.dto.Article;
import com.jtp.jtpTextBlog.service.ArticleService;

public class ArticleController extends Controller {
	private ArticleService articleService;

	public ArticleController() {
		articleService = Container.articleService;
	}
	public void doCommand(String cmd) {
		if(cmd.startsWith("article list")) {
			showList();
		}
	}
	public String ret(String cmd) {
		if(cmd.startsWith("article list")) {
			return showListR();
		}
		return null;
	}
	private String showListR() {
		StringBuilder sb = new StringBuilder();
		sb.append("==게시물 리스트==\n");		
		List<Article> articles = articleService.getArticles();
		sb.append("번호 / 작성 / 수정 / 작성자 / 제목\n");
		for (Article article : articles) {
			sb.append(article.id+" / "+ article.regDate+" / "+article.updateDate+" / "+article.memberId+" / "+article.title);
			sb.append("\n");
		}
		return sb.toString();
	}
	private void showList() {
		System.out.println("== 게시물 리스트 ==");

		List<Article> articles = articleService.getArticles();

		System.out.println("번호 / 작성 / 수정 / 작성자 / 제목");

		for (Article article : articles) {
			System.out.printf("%d / %s / %s / %s / %s\n", article.id, article.regDate, article.updateDate,
					article.memberId, article.title);
		}
	}
}