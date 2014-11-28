/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.dao
 * File: ICustomerDAO.java
 * Description: The Data Access Object abstracts the underlying data access implementation 
 * for the BusinessObject to enable transparent access to the data source. 
 * The BusinessObject also delegates data load and store operations to the DataAccessObject.
 *
 */
package com.opusdei.dao;

import java.util.List;

import com.opusdei.transfer.Customer;

public interface ICustomerDAO {
	public Customer getCustomerById(String customerId) throws Exception;
	public List<Customer> getCustomersById(String customerId) throws Exception;
	public List<Customer> getCustomersByLastName(String lastName) throws Exception;
	public List<Customer> getCustomersByName(String lastName) throws Exception;
	public int insertCustomer(Customer customer) throws Exception;
	public int updateCustomer(Customer customer) throws Exception;
	public int deleteCustomer(String customerId) throws Exception;
}
