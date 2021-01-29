package com.jtp.jtpTextBlog.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.controller.ArticleController;
import com.jtp.jtpTextBlog.controller.Controller;
import com.jtp.jtpTextBlog.controller.MemberController;
import com.sbs.example.mysqlutil.MysqlUtil;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet("/s/*")
public class DispatcherServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String requestUri = req.getRequestURI();
		String[] requestUriBits = requestUri.split("/");

		

		String controllerName = requestUriBits[2];
		System.out.println(requestUri + " ! !! " + controllerName);
		MysqlUtil.setDBInfo("127.0.0.1", "jttpp", "123412", "textBoard");
		// 데이터 추가 인터셉터 시작
		boolean isLogined = false;
		int loginedMemberId = 0;
		

		HttpSession session = req.getSession();

		if (session.getAttribute("loginedMemberId") != null) {
			isLogined = true;
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
		
		}

		req.setAttribute("isLogined", isLogined);
		req.setAttribute("loginedMemberId", loginedMemberId);
		

		// 데이터 추가 인터셉터 끝

		// 로그인 필요 필터링 인터셉터 시작

		List<String> needToLoginActionUrls = new ArrayList<>();

		needToLoginActionUrls.add("/s/doLogout");
		needToLoginActionUrls.add("/s/write");
		needToLoginActionUrls.add("/s/doWrite");
		needToLoginActionUrls.add("/s/modify");
		needToLoginActionUrls.add("/s/doModify");
		needToLoginActionUrls.add("/s/doDelete");

		if (needToLoginActionUrls.contains(requestUri)) {
			if ((boolean) req.getAttribute("isLogined") == false) {
				req.setAttribute("alertMsg", "로그인 후 이용해주세요.");
				req.setAttribute("replaceUrl", "/s/login");

				RequestDispatcher rd = req.getRequestDispatcher("/jsp/common/redirect.jsp");
				rd.forward(req, resp);
			}
		}
		String jspPath = null;
		if (controllerName.equals("login")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.showLogin(req, resp);
		} else if (controllerName.equals("doLogin")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.doLogin(req, resp);
		} else if (controllerName.equals("join")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.showJoin(req, resp);
		} else if (controllerName.equals("doJoin")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.doJoin(req, resp);
		} else if (controllerName.equals("doLogout")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.doLogout(req, resp);
		} else if (controllerName.equals("cmd")) {
			jspPath = "index";
		} else if (controllerName.equals("articles")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.articleList(req, resp);
		} else if (controllerName.equals("write")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.articleWrite(req, resp);
		}else if (controllerName.equals("doWrite")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.articleDoWrite(req, resp);
		} else if (controllerName.equals("detail")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.showDetail(req, resp);
		} else if (controllerName.equals("modify")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.showModify(req, resp);
		} else if (controllerName.equals("doModify")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.doModify(req, resp);
		} else if (controllerName.equals("doDelete")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.doDelete(req, resp);
		} else if (controllerName.equals("getLoginIdDup")) { 
			MemberController memberController =Controller.memberController;
			jspPath = memberController.getLoginIdDup(req, resp);
		}
//		if (controllerName.equals("member")) {
//			MemberController memberController = Container.memberController;
//
//			if (actionMethodName.equals("list")) {
//				jspPath = memberController.showList(req, resp);
//			}
//		} else if (controllerName.equals("article")) {
//			ArticleController articleController = Container.articleController;
//
//			if (actionMethodName.equals("list")) {
//				jspPath = articleController.showList(req, resp);
//			}
//		}

		MysqlUtil.closeConnection();

		RequestDispatcher rd = req.getRequestDispatcher("/jsp/" + jspPath + ".jsp");
		rd.forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}