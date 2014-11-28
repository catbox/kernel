/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2011-01-17
 * Project: Opus Dei
 * Package: com.opusdei.dao
 * File: Database.java
 * Description: 
 *
 */
package com.opusdei.dao;

import com.opusdei.common.StringTools;


public class DatabaseConfig {
	
	/**
	 * @uml.property  name="host"
	 */
	private String host;
	/**
	 * @uml.property  name="schema"
	 */
	private String schema;
	/**
	 * @uml.property  name="port"
	 */
	private String port;
	/**
	 * @uml.property  name="username"
	 */
	private String username;
	/**
	 * @uml.property  name="password"
	 */
	private String password;
	
	
	/**
	 * @param host
	 * @param schema
	 * @param port
	 * @param username
	 * @param password
	 */
	public DatabaseConfig() {
		this.host = StringTools.EMPTY_STRING;
		this.schema = StringTools.EMPTY_STRING;
		this.port = StringTools.EMPTY_STRING;
		this.username = StringTools.EMPTY_STRING;
		this.password = StringTools.EMPTY_STRING;
	}

	/**
	 * @param host
	 * @param schema
	 * @param port
	 * @param username
	 * @param password
	 */
	public DatabaseConfig(String host, String schema, String port,
			String username, String password) {
		this.host = host;
		this.schema = schema;
		this.port = port;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return   the host
	 * @uml.property  name="host"
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @param host   the host to set
	 * @uml.property  name="host"
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return   the schema
	 * @uml.property  name="schema"
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @param schema   the schema to set
	 * @uml.property  name="schema"
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @return   the port
	 * @uml.property  name="port"
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port   the port to set
	 * @uml.property  name="port"
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return   the username
	 * @uml.property  name="username"
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username   the username to set
	 * @uml.property  name="username"
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return   the password
	 * @uml.property  name="password"
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password   the password to set
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}	
	
}
