package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnect {
	
	
	// Db connection method
		public static void dbConnect(String username, String password, String Query) throws SQLException {
			String host = "localhost";
			String port = "3306";
			String uname = username;
			String pword = password;

			Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/databaseName", uname,
					pword);
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("Query");

		}
		

}
