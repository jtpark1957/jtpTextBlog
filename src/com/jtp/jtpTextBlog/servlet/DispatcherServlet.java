package com.jtp.jtpTextBlog.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		//System.out.println(requestUri + " ! !! " + controllerName);
		MysqlUtil.setDBInfo("127.0.0.1", "jttpp", "123412", "textBoard");

		String jspPath = null;
		if (controllerName.equals("login")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.showLogin(req, resp);
		} else if (controllerName.equals("doLogin")) {
			MemberController memberController =Controller.memberController;
			jspPath = memberController.doLogin(req, resp);
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
		} else if (controllerName.equals("doDelete")) {
			ArticleController articleController =Controller.articleController;
			jspPath = articleController.doDelete(req, resp);
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