package com.jtp.jtpTextBlog.dao;

import java.util.Map;

import com.jtp.jtpTextBlog.dto.Member;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class MemberDao {

	public Member getMemberById(int id) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE id = ?", id);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Member(map);
	}

	public Member getMemberByLoginId(String loginId) {
		SecSql sql = new SecSql();
		sql.append("SELECT *");
		sql.append("FROM `member`");
		sql.append("WHERE loginId = ?", loginId);

		Map<String, Object> map = MysqlUtil.selectRow(sql);

		if (map.isEmpty()) {
			return null;
		}

		return new Member(map);
	}

}
