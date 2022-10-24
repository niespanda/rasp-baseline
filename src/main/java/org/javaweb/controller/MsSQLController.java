package org.javaweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@RestController
@RequestMapping("/MsSQL/")
public class MsSQLController {

//	@Autowired
//	private JdbcTemplate jdbcTemplate;

	/**
	 * SQLServer JDBC 基线Test1
	 */
	@RequestMapping("/queryForJDBCUrl")
	public String testMSSQL() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String            connectionUrl = "jdbc:sqlserver://localhost:1433;database=MyDB;user=SA;password=qwer1234;";
		Connection        con           = DriverManager.getConnection(connectionUrl);
		PreparedStatement statement     = con.prepareStatement("SELECT Name from sys.Databases;");
		ResultSet         resultSet     = statement.executeQuery();
		String            db            = null;
		while (resultSet.next()) {
			db = resultSet.getString(1);
		}
		con.close();
		return db;
	}

	/**
	 * SQLServer JDBC 基线Test2
	 */
	@RequestMapping("/queryForJDBCUser")
	public String testMSSQLForJDBC() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String            jdbcUrl   = "jdbc:sqlserver://localhost:1433;database=MyDB;";
		Connection        con       = DriverManager.getConnection(jdbcUrl, "SA", "qwer1234");
		PreparedStatement statement = con.prepareStatement("SELECT Name from sys.Databases;");
		ResultSet         resultSet = statement.executeQuery();
		String            db        = null;
		while (resultSet.next()) {
			db = resultSet.getString(1);
		}
		con.close();
		return db;
	}

	/**
	 * SQLServer DataSource 基线检查test
	 */
//	@RequestMapping("/queryForDataSource")
//	public List dataSourceMSSQL() {
//		return jdbcTemplate.queryForList("SELECT Name from sys.Databases;");
//	}


}
