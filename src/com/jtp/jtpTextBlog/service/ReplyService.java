package com.jtp.jtpTextBlog.service;

import java.util.List;
import java.util.Map;

import com.jtp.jtpTextBlog.dao.ReplyDao;
import com.jtp.jtpTextBlog.dto.Reply;

public class ReplyService {

	private ReplyDao replyDao;

	public ReplyService() {
		replyDao = new ReplyDao();
	}

	public int write(Map<String, Object> args) {
		return replyDao.write(args);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		// TODO Auto-generated method stub
		return replyDao.getForPrintReplies(relTypeCode, relId);
	}
}
