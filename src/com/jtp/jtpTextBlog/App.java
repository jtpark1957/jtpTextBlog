package com.jtp.jtpTextBlog;


import com.jtp.jtpTextBlog.container.Container;
import com.jtp.jtpTextBlog.controller.Controller;
public class App {
//	public void run(String cmd) {
//		MysqlUtil.setDBInfo("127.0.0.1", "jttpp", "123412", "textBoard");
//		Controller controller = getControllerByCmd(cmd);
//		if (controller != null) {
//			controller.doCommand(cmd);
//		}
//		MysqlUtil.closeConnection();
//	}
	public String ret(String cmd) {
		
		Controller controller = getControllerByCmd(cmd);
		if (controller != null) {
			return controller.ret(cmd);
		}
		return "";
	}

	private Controller getControllerByCmd(String cmd) {
		// TODO Auto-generated method stub
		if (cmd.startsWith("article ")) {
			return Container.articleController;
		} else if (cmd.startsWith("member ")) {
			return Container.memberController;
		} else if (cmd.startsWith("hello world")) {
			return Container.memberController;
		}
		return null;
	}
}

