package org.javaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/MYSQL/")
public class MYSQLController {

	/**
	 * MYSQL DataSource 基线触发 默认数据连接池配置为MYSQL,Oracle、MSSQL 通过JDBC去触发基线检查
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/query")
	public Map<String, Object> mysqlQuery() {
		return jdbcTemplate.queryForMap("select user()");
	}
}
