package com.jtp.jtpTextBlog.service;

import java.util.Map;

import com.jtp.jtpTextBlog.dao.ReplyDao;

public class ReplyService {

	private ReplyDao replyDao;

	public ReplyService() {
		replyDao = new ReplyDao();
	}

	public int write(Map<String, Object> args) {
		return replyDao.write(args);
	}
}
