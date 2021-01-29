package com.jtp.jtpTextBlog.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.dto.Member;
import com.jtp.jtpTextBlog.service.ArticleService;
import com.jtp.jtpTextBlog.service.MemberService;

public class MemberController extends Controller {
	private MemberService memberService;
	public MemberController() {
		memberService = Container.memberService;
	}
	public String ret(String cmd) {
		if(cmd.startsWith("member login")) {
			
			return doLogin(cmd);
		} else if(cmd.startsWith("hello world")) {
			return helloWorld(cmd);
		}
		return "";
	}
	private String helloWorld(String cmd) {
		if (Container.session.isLogined() == false) {
			return "";
		}

		int loginedMemberId = Container.session.getLoginedMemberId();
		Member member = memberService.getMemberById(loginedMemberId);
		
		return(member.name+"님 환영합니다<br>");
	}
	private String doLogin(String cmd) {
		System.out.println("== 로그인 ==");
		if (Container.session.isLogined()) {
			return("로그아웃 후 이용해주세요<br>");
		}
		String cmdsp[] = cmd.split(" ");
		String loginId = "";
		String loginPw = "";
		System.out.println(cmdsp.length);

		if (cmdsp.length == 2) {
			return("로그인아이디를 입력해주세요.<br>");
			
		}
		loginId = cmdsp[2];

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return("존재하지 않는 회원입니다.<br>");
		}


		if (cmdsp.length <= 3) {
			return("로그인비밀번호를 입력해주세요.<br>");
		}
		loginPw = cmdsp[3];

		if (member.loginPw.equals(loginPw) == false) {
			return("비밀번호가 일치하지 않습니다.<br>");
		}
		Container.session.login(member.id);
		return("/");
		//return(member.name+"님 환영합니다<br>");
	}
	public String showLogin(HttpServletRequest req, HttpServletResponse resp) {
		
		return "usr/member/login";
	}
	public String doLogin(HttpServletRequest req, HttpServletResponse resp) {
		if (Container.session.isLogined()) {
			req.setAttribute("alertMsg","로그아웃 후 이용해주세요");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		System.out.println(loginPw);
		
		Member member = memberService.getMemberByLoginId(loginId);
		
		if (member == null) {
//			req.setAttribute("alertMsg", "");
//			req.setAttribute("replaceUrl", "/s/login");
//			HttpSession session = req.getSession();
//			session.setAttribute("errorMsg", "잘못된 값입니다 ");
			req.setAttribute("alertMsg", "잘못된 아이디입니다");
			req.setAttribute("historyBack", true);

			return "common/redirect";
				
		}

		if (member.loginPw.equals(loginPw) == false) {
			req.setAttribute("alertMsg", "비밀번호가 일치하지 않습니다.");
			req.setAttribute("historyBack", true);
			return "common/redirect";
		}

		HttpSession session = req.getSession();
		session.setAttribute("loginedMemberId", member.id);
		Container.session.login(member.id);
		req.setAttribute("alertMsg", "");
		req.setAttribute("replaceUrl", "/");
		return "common/redirect";
	}
	public String doLogout(HttpServletRequest req, HttpServletResponse resp) {
		
		Container.session.logout();
		req.setAttribute("alertMsg","");
		req.setAttribute("replaceUrl", "/");
		HttpSession session = req.getSession();
		session.removeAttribute("loginedMemberId");
		return "common/redirect";
	}
	public String showJoin(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return "usr/member/join";
	}
	public String doJoin(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");
		String loginPw = req.getParameter("loginPwReal");
		String username = req.getParameter("username");
		String email = req.getParameter("email");

		Map<String, Object> joinArgs = new HashMap<>();
		joinArgs.put("loginId", loginId);
		joinArgs.put("loginPw", loginPw);
		joinArgs.put("username", username);
		joinArgs.put("email", email);

		int newArticleId = memberService.join(joinArgs);

		req.setAttribute("alertMsg", newArticleId + "번 회원이 생성되었습니다.");
		req.setAttribute("replaceUrl", "join");
		return "common/redirect";
	}
	public String getLoginIdDup(HttpServletRequest req, HttpServletResponse resp) {
		String loginId = req.getParameter("loginId");

		Member member = memberService.getMemberByLoginId(loginId);

		String data = "";

		if ( member != null ) {
			data = "NO";
		}
		else {
			data = "YES";
		}

		req.setAttribute("data", data);
		return "common/pure";
	}

}
