/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.factory
 * File: DAOFactory.java
 * Description: The Data Access Object abstracts the underlying data access implementation 
 * for the business object to enable transparent access to the data source. 
 * The BusinessObject also delegates data load and store operations to the DataAccessObject.
 */
package com.opusdei.factory;

import java.sql.SQLException;

import com.opusdei.dao.CustomerDAO;

public class DAOFactory implements IDAOFactory {
	
	/**
	 * Default constructor
	 */
	public DAOFactory() {
		// Do nothing
	}
	
	/**
	 * Return the CustomerDAO
	 * @return CustomerDAO
	 */
	public CustomerDAO getCustomerDAO() throws SQLException {
		return new CustomerDAO();
	}
	
}
