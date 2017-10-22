/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2011-03-17
 * Project: Opus Dei
 * Package: com.opusdei.factory
 * File: ConnectionFactory.java
 * Description: Manages connection
 */
package com.wh.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.wh.constants.ConnectionConstants;
import com.wh.dao.DatabaseConfig;

public class ConnectionFactory {
	
	protected static Connection connection = null;
	protected static Connection testConnection = null;
	private static Statement testStatement;
	private static DatabaseConfig databaseConfig = new DatabaseConfig();
	private static Properties properties = new Properties();
	private static String host;
	private static String schema;
	private static String port;
	private static String username;
	private static String password;
	private static String url;	
	private static File file = new File(ConnectionConstants.DB_CONFIG_FILE_LOCATION);
		
	/**
	 * Create a connection
	 * @return Connection
	 */
	public static boolean createConnection() throws SQLException, Exception {
		try {
			Class.forName(ConnectionConstants.DRIVER);

			host = ConnectionConstants.HOST;
			schema =  ConnectionConstants.SCHEMA;
			port = ConnectionConstants.PORT;
			username = ConnectionConstants.USERNAME;
			password = ConnectionConstants.PASSWORD;
			url = ConnectionConstants.MYSQL + host + ":" + port + "/" + schema;
			
			databaseConfig.setHost(host);
			databaseConfig.setSchema(schema);
			databaseConfig.setPort(port);
			databaseConfig.setUsername(username);
			databaseConfig.setPassword(password);		
	
			connection = DriverManager.getConnection(url, username, password);
			
			return true;
		}
		catch(Exception exception) {
			throw exception;
		}
	}
	
	/**
	 * Open a connection
	 */
	public static boolean openConnection() throws SQLException, Exception {
		boolean open = false;
		if(connection != null) {
			try {
				connection.clearWarnings();
			} 
			catch(SQLException sqlException) {
				throw sqlException;
			}
			catch(Exception exception) {
				throw exception;
			}
		}
		else {						
			open = createConnection();
		}
		return open;
	}
	
	
	/**
	 * Close a connection
	 */
	public static void closeConnection()throws SQLException, Exception {
		try {
			connection.close();
		} 
		catch(SQLException sqlException) {
			throw sqlException;
		}
		catch(Exception exception) {
			throw exception;
		}
	}
	
	/**
	 * Update the connection
	 */
	public static boolean updateConnection(Properties newProperties) throws SQLException, Exception {	
		try {
			if(connection != null) {
				connection.clearWarnings();
			}
			
			// Temporary variables for the new connection
			String tempHost = newProperties.getProperty("host");
			String tempSchema = newProperties.getProperty("schema");
			String tempPort = newProperties.getProperty("port");
			String tempUsername = newProperties.getProperty("username");
			String tempPassword = newProperties.getProperty("password");
			String tempUrl = ConnectionConstants.MYSQL + tempHost + ":" + tempPort + "/" + tempSchema;
						
			connection = DriverManager.getConnection(tempUrl, tempUsername, tempPassword);
			
			// If the connection was recreated successfully these will be executed, else they would be skipped!
			host = newProperties.getProperty("host");
			schema = newProperties.getProperty("schema");
			port = newProperties.getProperty("port");
			username = newProperties.getProperty("username");
			password = newProperties.getProperty("password");
			url = ConnectionConstants.MYSQL + host + ":" + port + "/" + schema;
			
			databaseConfig.setHost(host);
			databaseConfig.setSchema(schema);
			databaseConfig.setPort(port);
			databaseConfig.setUsername(username);
			databaseConfig.setPassword(password);
			
			OutputStream outputStream = new FileOutputStream(file);
			properties = newProperties;
			properties.store(outputStream, "Database Configurations");			
			
			return true;
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}
		catch(Exception exception) {
			throw exception;
		}
	}
	
	/**
	 * Test the connection
	 */
	public static boolean testConnection(Properties newProperties) throws SQLException, Exception {
		try {
			Class.forName(ConnectionConstants.DRIVER);
			
			String tempHost = newProperties.getProperty("host");
			String tempSchema = newProperties.getProperty("schema");
			String tempPort = newProperties.getProperty("port");
			String tempUsername = newProperties.getProperty("username");
			String tempPassword = newProperties.getProperty("password");
			String tempUrl = ConnectionConstants.MYSQL + tempHost + ":" + tempPort + "/" + tempSchema;
			String tempStatement = "USE " + tempSchema;			
			
			DriverManager.setLoginTimeout(ConnectionConstants.TIME_OUT);
			testConnection = DriverManager.getConnection(tempUrl, tempUsername, tempPassword);		
			testStatement = testConnection.createStatement();
			testStatement.execute(tempStatement);
			
			return true;
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}
		catch(Exception exception) {
			throw exception;
		}
		finally {
			try {
				testConnection.close();
			} 
			catch(SQLException sqlException) {
				throw sqlException;
			}
			catch(Exception exception) {
				throw exception;
			}
		}
	}
		
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		ConnectionFactory.host = host;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		ConnectionFactory.schema = schema;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		ConnectionFactory.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		ConnectionFactory.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		ConnectionFactory.password = password;
	}

	public static Connection getConnection() {
		return connection;
	}
	
	public static DatabaseConfig getDatabaseConfig() {
		return databaseConfig;
	}	

}
