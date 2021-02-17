package com.jtp.jtpTextBlog.dto;

import java.util.Map;

public class Reply {
	public int id;
	public String regDate;
	public String updateDate;
	public String relTypeCode;
	public int relId;
	public String body;
	public int memberId;


	public String extra__writer;

	public Reply(Map<String, Object> map) {
		this.id = (int) map.get("id");
		this.regDate = (String) map.get("regDate");
		this.updateDate = (String) map.get("updateDate");
		this.body = (String) map.get("body");
		this.memberId = (int) map.get("memberId");

		this.relTypeCode = (String) map.get("relTypeCode");
		this.relId = (int) map.get("relId");
		if (map.containsKey("extra__writer")) {
			this.extra__writer = (String) map.get("extra__writer");
		}

	}

	@Override
	public String toString() {
		return "Reply [id=" + id + ", regDate=" + regDate + ", updateDate=" + updateDate + ", relTypeCode="
				+ relTypeCode + ", relId=" + relId + ", body=" + body + ", memberId=" + memberId + ", extra__writer="
				+ extra__writer + "]";
	}


}