/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2011-04-11
 * Project: Opus Dei
 * Package: com.opusdei.observer
 * File: ConnectionObserver.java
 * Description: Observer for the connection
 *
 */
package com.opusdei.observer;

import java.sql.Connection;
import java.sql.SQLException;

import com.opusdei.dao.CustomerDAO;

public class ConnectionObserver {

	private CustomerDAO customerDAO;
	
	public ConnectionObserver() {
		// Do nothing
	}

	/**
	 * @param connection the connection to set
	 */
	public void refreshConnection(Connection con) throws SQLException {		
		if(customerDAO != null) {
			customerDAO.refresh(con);
		}			
	}

	/**
	 * @return the customerDAO
	 */
	public CustomerDAO getCustomerDAO() {
		return customerDAO;
	}

	/**
	 * @param customerDAO the customerDAO to set
	 */
	public void registerCustomerDAO(CustomerDAO cDAO) {
		this.customerDAO = cDAO;
	}

}
