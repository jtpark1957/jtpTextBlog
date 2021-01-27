package com.jtp.jtpTextBlog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			int articlesCount = articleService.getArticlesCount(board.id, "" , "");
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
		int totalCount = articleService.getArticlesCount(board.id, "", "");
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
		List<Article> articles = articleService.getForPrintArticlesByBoardId(page, board.id, itemsInAPage, "", "");

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
		String boardCode;
		String code = "";
		String searchKeyword = req.getParameter("searchKeyword");
		String searchKeywordType = req.getParameter("searchKeywordType");
		int boardId = 0;
		
		if (req.getParameter("boardcode") != null) {
			boardCode = req.getParameter("boardcode");
			Board board = articleService.getBoardByCode(boardCode);
			if(board != null) {
				boardId = board.id; 
				code = board.name;
			}
			
		}
		int itemsInAPage = 10;
		int totalCount = articleService.getArticlesCount(boardId, searchKeywordType, searchKeyword);
		int totalPage = (int) Math.ceil(totalCount / (double) itemsInAPage);
		int page = 1;
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
		}
		
		List<Board> boards = articleService.getForPrintBoards();

		req.setAttribute("boards", boards);
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("page", page);
		
		req.setAttribute("code", code);
		req.setAttribute("boardId", boardId);
		List<Article> articles = articleService.getForPrintArticlesByBoardId(page, boardId, itemsInAPage, searchKeywordType, searchKeyword);
		
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
	public String doDelete(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		} 

		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleById(id);
		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		if (article.memberId != Container.session.getLoginedMemberId()) {
			req.setAttribute("alertMsg", "권한이 없습니다");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		} 

		articleService.delete(id);

		int boardId = article.boardId;

		req.setAttribute("alertMsg", id + "번 게시물이 삭제되었습니다.");
		req.setAttribute("replaceUrl", "articles");
		//req.setAttribute("replaceUrl", String.format("articles?boardcode=%d", boardId));
		return "common/redirect";
	}
	public String articleWrite(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		List<Board> boards = articleService.getForPrintBoards();
		req.setAttribute("boards", boards);
		return "usr/article/write";
	}
	public String articleDoWrite(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		int memberId = (int) (session.getAttribute("loginedMemberId"));
		String boardcode= req.getParameter("boardcode");
		Board board = articleService.getBoardByCode(boardcode);
		int boardId = board.id;
		
		
		String title = req.getParameter("title");
		String body = req.getParameter("body");

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("memberId", memberId);
		writeArgs.put("boardId", boardId);
		writeArgs.put("title", title);
		writeArgs.put("body", body);

		int newArticleId = articleService.write(writeArgs);

		req.setAttribute("alertMsg", newArticleId + "번 게시물이 생성되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", newArticleId));
		return "common/redirect";
	}
	public String showModify(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (session.getAttribute("loginedMemberId") == null) {
			req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		} 
		int id = Integer.parseInt(req.getParameter("id"));

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		if (article.memberId != (int)session.getAttribute("loginedMemberId") ) {
			req.setAttribute("alertMsg", "권한이 없습니다");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		} 
		List<Board> boards = articleService.getForPrintBoards();
		req.setAttribute("boards", boards);
		req.setAttribute("article", article);

		return "usr/article/modify";
	
	}
	public String doModify(HttpServletRequest req, HttpServletResponse resp) {
		int id = Integer.parseInt(req.getParameter("id"));
		HttpSession session = req.getSession();

		Article article = articleService.getForPrintArticleById(id);

		if (article == null) {
			req.setAttribute("alertMsg", id + "번 게시물은 존재하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}


		if (article.memberId != (int)session.getAttribute("loginedMemberId") ) {
			req.setAttribute("alertMsg", id + "번 게시물에 대한 권한이 없습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		String title = req.getParameter("title");
		String body = req.getParameter("body");

		Map<String, Object> modifyArgs = new HashMap<>();
		modifyArgs.put("id", id);
		modifyArgs.put("title", title);
		modifyArgs.put("body", body);

		articleService.modify(modifyArgs);

		req.setAttribute("alertMsg", id + "번 게시물이 수정되었습니다.");
		req.setAttribute("replaceUrl", String.format("detail?id=%d", id));
		return "common/redirect";
	}
	
	
	
}