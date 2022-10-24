package org.javaweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/Oracle/")
public class OracleController {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	@RequestMapping("/oracleJDBCQuery")
	public String oracleJDBCForURL() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:test/test@localhost:1521:helowin");
		PreparedStatement statement = con.prepareStatement("select version from v$instance");
		ResultSet         resultSet = statement.executeQuery();
		String            result    = null;
		while (resultSet.next()) {
			result = resultSet.getString(1);
		}
		con.close();
		return result;
	}

	@RequestMapping("/oracleJDBCUserQuery")
	public String oracleJDBCForUser() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:helowin", "test", "test");
		PreparedStatement statement = con.prepareStatement("select version from v$instance");
		ResultSet         resultSet = statement.executeQuery();
		String            result    = null;
		while (resultSet.next()) {
			result = resultSet.getString(1);
		}
		con.close();
		return result;
	}

//	@RequestMapping("/oracleDataSourceQuery")
//	public List oracleDataSourceForQuery() {
//		return jdbcTemplate.queryForList("select version from v$instance");
//	}

}
