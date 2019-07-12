package products.model.build.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Phase1CreateDatabase {

	public static void main(String[] args) {

		try {
			String driverName = "org.apache.derby.jdbc.ClientDriver";
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		String dbUrl = "jdbc:derby://localhost:1527/products.db";
		try (Connection con = DriverManager.getConnection(dbUrl + ";create=true")) {
			String sql = "create table products(";
			sql += "id int primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), ";
			sql += "name varchar(20) not null unique, ";
			sql += "price float not null, ";
			sql += "stock int not null";
			sql += ")";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println("success");
			System.out.println(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
