package preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	private static Connection con;

	public static Connection establishConnection() {
		try {
			// step2-> Load Driver
			Class.forName("com.mysql.cj.jdbc.Driver");// Checked exception
			System.out.println("Driver loaded successfully...");

			// Step3-> Establish connection
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db6135", "root", "Krishna@18");

			// return con;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

}
