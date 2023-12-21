package preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import crudstatement.DbConnection;

public class JdbcOperationUsingPreparedStatement {

	private static Object preparedStatement;

	public static void main(String[] args) {
		saveRecord();
		//updateRecord();
		//selectAll();
	}

	private static void saveRecord() {
		Connection connection = null;
		// Statement statement = null;
		PreparedStatement prepareStatement = null;
		Scanner s = null;
		try {
			// step3
			connection = DbConnection.establishConnection();

			// step 4 -> ready the statement to process SQl query
			// statement = connection.createStatement();
			prepareStatement = connection.prepareStatement("insert into student(city,name,id) values(?,?,?)");
			s = new Scanner(System.in);
			System.out.println("Enter city name");
			String city = s.next();
			System.out.println("Enter student name");
			String name = s.next();
			System.out.println("Enter student id");
			int id = s.nextInt();

			prepareStatement.setInt(3, id);
			prepareStatement.setString(1, city);
			prepareStatement.setString(2, name);

			// step5 -> execute the query
//			s = new Scanner(System.in);
//			System.out.println("Enter city name");
//			String city = s.next();
//			System.out.println("Enter student name");
//			String name = s.next();
//			System.out.println("Enter student id");
//			int id = s.nextInt();
//			int rows = statement.executeUpdate("insert into student(city,name,id) values('"+city+"', '"+name+"',"+id+")");

			int rows = prepareStatement.executeUpdate();
			if (rows > 0) {
				System.out.println("Data has been saved successfully");
			} else {
				System.out.println("Something went wrong to save record..");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
//					if(statement != null) {
//						statement.close();
//					}
					if (prepareStatement != null) {
						prepareStatement.close();
					}
					connection.close();
					if (s != null) {
						s.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	private static void updateRecord() {
		Connection con = null;
		PreparedStatement ps = null;
		Scanner s = null;
		try {
			con = DbConnection.establishConnection();
			// st = con.createStatement();
			ps = con.prepareStatement("update student set city =?  where id =? ");
			s = new Scanner(System.in);
			System.out.println("Enter student id");
			int id = s.nextInt();
			ps.setInt(2, id);
			System.out.println("Enter student city");
			String city = s.next();
			ps.setString(1, city);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				System.out.println("Record is updated successfully");
			} else {
				System.out.println("Failed to update record");
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private static void selectAll() {
		Connection con = null;
		PreparedStatement ps = null;
		Scanner s = null;
		try {
			con = DbConnection.establishConnection();
			//step 4
			ps = con.prepareStatement("select * from student");
			
			//step 5
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String city = rs.getString(3);
				System.out.println("Id: "+id+" Name: "+name+" City: "+city);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//step 6
			try {
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	
}