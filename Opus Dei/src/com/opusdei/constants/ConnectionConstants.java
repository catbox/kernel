/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.constants
 * File: Constants.java
 * Description: Constants for database connection
 *
 */
package com.opusdei.constants;

public class ConnectionConstants {
		
	public static final String DRIVER = "com.mysql.jdbc.Driver"; 
	public static final String MYSQL = "jdbc:mysql://";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "admin";
		
	public static final String DB_CONFIG_FILE_LOCATION = ".\\resources\\database\\Database.properties";
	
	public static final int TIME_OUT = 5; // Time out after 5 seconds
}
