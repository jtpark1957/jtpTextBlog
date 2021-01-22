package com.jtp.jtpTextBlog.controller;

import java.util.Scanner;

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
		System.out.println("== 회원확인 ==");
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

		return(member.name+"님 환영합니다<br>");
	}

}
