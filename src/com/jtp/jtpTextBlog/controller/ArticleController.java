package com.jtp.jtpTextBlog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.dto.Article;
import com.jtp.jtpTextBlog.dto.Board;
import com.jtp.jtpTextBlog.dto.Member;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;

public class ArticleController extends Controller {
	private ArticleService articleService;
	private MemberService memberService;

	public ArticleController() {
		articleService = Container.articleService;
		memberService = Container.memberService;
		//memberService = new MemberService();
	}
	public String ret(String cmd) {
		if(cmd.startsWith("article list")) {
			return showList(cmd);
		} else if(cmd.startsWith("article detail")) {
			return showDeatil(cmd);
		} else if( cmd.startsWith("article board")) {
			return ListBoard(cmd);
		} else if(cmd.startsWith("article selectBoard")) {
			return doSelectBoard(cmd);
		}
		return "";
	}
	
	private String doSelectBoard(String cmd) {
		if (cmd.split("article")[1].equals(" selectBoard")) {
			return "";
		}
		String input = cmd.split(" ")[2];
		Board board = articleService.getBoardByCode(input);

		if (board == null) {
			
			return "잘못된 코드입니다";
		}

		Container.session.setCurrentBoardCode(board.code);
		return "게시물선택 : "+board.code+"<br>";
	}
	private String ListBoard(String cmd) {

		
		StringBuilder sb = new StringBuilder();
		sb.append("= 게시판 목록 =" + "<br>");
		sb.append("번호 / 생성날짜 / 코드 / 이름 / 게시물 수" + "<br>");
		List<Board> boards = articleService.getForPrintBoards();

		for (Board board : boards) {
			int articlesCount = articleService.getArticlesCount(board.id);
			sb.append(board.id + " / " + board.regDate+ " / " + 
			"<a href='#' onclick=\"sendMessage('article selectBoard " + board.code  +"')\">"+ board.name +"</a>"+ " / " + articlesCount + "<br>");
		}

		return sb.toString();
			
	}
	private String showList(String cmd) {
		
		String boardCode = Container.session.getCurrentBoardCode();
		Board board = articleService.getBoardByCode(boardCode);
		StringBuilder sb = new StringBuilder();
		int itemsInAPage = 5;
		int totalCount = articleService.getArticlesCount(0);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		int page = 1;
		sb.append("==게시물 리스트== " + board.name +"\n" );		
		sb.append("<br>");
		if (cmd.split("article")[1].equals(" list")) {
			page = 1;
		}else {
			page = Integer.parseInt(cmd.split(" ")[2]);
		}
		

		//List<Article> articles = articleService.getArticles();
		//List<Article> articles = articleService.getForPrintArticles(board.id);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(page, 0, itemsInAPage);

		sb.append("번호 / 작성 / 수정 / 작성자 / 제목" + " [" +page+" / "+totalPage+"]");
		sb.append("<br>");
		for (Article article : articles) {
			String writer = article.extra__writer;
			
			sb.append(article.id+" / "+ article.regDate+" / "+article.updateDate+" / " +writer+" / "
			+"<a href='#' onclick=\"sendMessage('article detail " + article.id  +"')\">"+ article.title +"</a>");
			sb.append("\n");
			//messageTextArea.value += '<a href=\"http://www.cosmosfarm.com/\">코스모스팜</a>';
			sb.append("<br>");
		}
		return sb.toString();
	}
	private String showDeatil(String cmd) {
		System.out.println("== 게시물 디테 ==");
		if (cmd.split("article")[1].equals(" detail")) {
			return "";
		}
		int input = Integer.parseInt(cmd.split(" ")[2]);

		Article article = articleService.getArticle(input);
		if (article == null) {
			return "존재하지않는게시물";
		}
		Member member = memberService.getMemberById(article.memberId);
		String writer = member.name;
		
		StringBuilder sb = new StringBuilder();
		sb.append("번호 : " + article.id+ "<br>");
		sb.append("작성날짜 : " + article.regDate+ "<br>");
		sb.append("수정날짜 : " + article.updateDate+ "<br>");
		sb.append("작성자 : " + writer + "<br>");
		sb.append("제목 : " + article.title + "<br>");
		sb.append("내용 : " + article.body + "<br>");
		
		return sb.toString();

	}
	public String articleList(HttpServletRequest req, HttpServletResponse resp) {
		int itemsInAPage = 10;
		int totalCount = articleService.getArticlesCount(0);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		int page = 1;
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(page, 0, itemsInAPage);
		
		req.setAttribute("articles", articles);

		return "usr/article/articlelist";
	}
	public String showDetail(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		req.setAttribute("article", article);

		return "usr/article/detail";
	}
	
}