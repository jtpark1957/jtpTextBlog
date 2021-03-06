package com.jtp.jtpTextBlog.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jtp.jtpTextBlog.dto.Reply;
import com.sbs.example.mysqlutil.MysqlUtil;
import com.sbs.example.mysqlutil.SecSql;

public class ReplyDao {

	public int write(Map<String, Object> args) {
		SecSql sql = new SecSql();
		sql.append("INSERT INTO reply");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", relTypeCode = ?", args.get("relTypeCode"));
		sql.append(", relId = ?", args.get("relId"));
		sql.append(", memberId = ?", args.get("memberId"));
		sql.append(", `body` = ?", args.get("body"));

		return MysqlUtil.insert(sql);
	}

	public List<Reply> getForPrintReplies(String relTypeCode, int relId) {
		List<Reply> replies = new ArrayList<>();

		SecSql sql = new SecSql();
		sql.append("SELECT R.*");
		sql.append(", M.name AS extra__writer");
		sql.append("FROM reply AS R");
		sql.append("INNER JOIN `member` AS M");
		sql.append("ON R.memberId = M.id");
		sql.append("WHERE 1");
		sql.append("AND R.relTypeCode = ?", relTypeCode);
		sql.append("AND R.relId = ?", relId);
		sql.append("GROUP BY R.id");
		sql.append("ORDER BY R.id DESC");

		List<Map<String, Object>> mapList = MysqlUtil.selectRows(sql);

		for (Map<String, Object> map : mapList) {
			replies.add(new Reply(map));
		}

		return replies;
	}


}
