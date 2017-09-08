package com.app.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.app.constants.DBConstant;

public class DBConnect {


	public static Connection conn;
	private PreparedStatement statement;
	public static DBConnect db;

	private DBConnect() {
		String url= DBConstant.DB_URL;
		String dbName = DBConstant.DB_NAME;
		String driver = DBConstant.DB_DRIVER;
		String userName = DBConstant.DB_USER;
		String password = DBConstant.DB_PASSWORD;
		try {
			Class.forName(driver).newInstance();
			this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
		}
		catch (Exception sqle) {
			sqle.printStackTrace();
		}
	}
	/**
	 *
	 * @return MysqlConnect Database connection object
	 */
	 public static synchronized DBConnect getDbCon() {
		 if ( db == null ) {
			 db = new DBConnect();
		 }
		 return db;

	 }

}
