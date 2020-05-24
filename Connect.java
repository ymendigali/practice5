package kz.aitu.oop.practice.practice5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	//variables
	private Connection con;
	private Statement stmt;
	private static boolean isConnected;
	
	//constructor and connection to database
	public Connect(String hostname, String portname, String username, String pass, String dbName) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");  
		this.con = DriverManager.getConnection("jdbc:mysql://" + hostname + ":" + portname + "/" + dbName+"", username, pass);   
		this.stmt = con.createStatement();
		Connect.setConnected(true);
	}
	
	//method to close the connection between database
	public void closeCon() throws SQLException {
		con.close();
	}
	
	//setter and getter methods
	public Statement getStmt() {
		return stmt;
	}

	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public static boolean isConnected() {
		return isConnected;
	}

	public static void setConnected(boolean isConnected) {
		Connect.isConnected = isConnected;
	}
}
