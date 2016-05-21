package org.arthur.mvcdemo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataConnect {


	public static Connection getConnection() {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection con = DriverManager.getConnection(
					"jdbc:sqlserver://127.0.0.1:1433;DatabaseName=Internationalization", 
					"sa", "Cellfone01");
			return con;
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("Database.getConnection() Error -->"
					+ ex.getMessage());
			return null;
		}
	}

	public static void closeConnection(PreparedStatement ps, Connection con) {
		try {
			if (ps != null) {
				ps.close();
			}
			if (con != null) {
				con.close();
			}
		} catch (SQLException ex) {

		}
	}

}
