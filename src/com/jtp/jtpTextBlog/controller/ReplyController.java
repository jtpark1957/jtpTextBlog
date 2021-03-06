package com.jtp.jtpTextBlog.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.dto.Article;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.ReplyService;
import com.jtp.jtpTextBlog.util.Util;

public class ReplyController extends Controller{
	private ReplyService replyService;
	private ArticleService articleService;
	public ReplyController() {
		replyService = Container.replyService;
		articleService = Container.articleService;
	}
	public String ret(String cmd) {
		return "";
	}
	public String doWrite(HttpServletRequest req, HttpServletResponse resp) {
		String redirectUrl = req.getParameter("redirectUrl");

		int loginedMemberId = (int) req.getAttribute("loginedMemberId");

		String relTypeCode = req.getParameter("relTypeCode");

		if (relTypeCode == null) {
			return msgAndBack(req, "관련데이터타입코드를 입력해주세요.");
		}

		int relId = Util.getAsInt(req.getParameter("relId"), 0);

		if (relId == 0) {
			return msgAndBack(req, "관련데이터번호를 입력해주세요.");
		}

		if (relTypeCode.equals("article")) {
			Article article = articleService.getArticle(relId);

			if (article == null) {
				return msgAndBack(req, relId + "번 게시물은 존재하지 않습니다.");
			}
		}

		String body = req.getParameter("reply_text");

		if (Util.isEmpty(body)) {
			return msgAndBack(req, "내용을 입력해주세요.");
		}

		Map<String, Object> writeArgs = new HashMap<>();
		writeArgs.put("memberId", loginedMemberId);
		writeArgs.put("relId", relId);
		writeArgs.put("relTypeCode", relTypeCode);
		writeArgs.put("body", body);

		int newArticleId = replyService.write(writeArgs);

		redirectUrl = redirectUrl.replace("[NEW_REPLY_ID]", newArticleId + "");

		return msgAndReplace(req, newArticleId + "번 댓글이 생성되었습니다.", redirectUrl);
	}
}

