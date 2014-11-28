/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.dao
 * File: CustomerDAO.java
 * Description: Customer DAO implementation
 *
 */
package com.opusdei.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.opusdei.common.StringTools;
import com.opusdei.factory.ConnectionFactory;
import com.opusdei.transfer.Customer;

public class CustomerDAO implements ICustomerDAO {
		
	private PreparedStatement selectCustomersById = null;
	private PreparedStatement selectCustomersByName = null; 
	private PreparedStatement selectCustomersByLastName = null; 
	private PreparedStatement insertCustomer = null;
	private PreparedStatement updateCustomer = null;
	private PreparedStatement deleteCustomer = null;
	private Connection connection = null;
	
	public CustomerDAO() throws SQLException {
		connection = ConnectionFactory.getConnection();
		createCustomerQueries();						
	}
	
	/**
	 * Create the queries
	 */
	protected void createCustomerQueries() throws SQLException {
		try {
			// Select statements
			selectCustomersById = connection.prepareStatement("SELECT * FROM customers WHERE customerId = ?");
			selectCustomersByLastName = connection.prepareStatement("SELECT * FROM customers WHERE lastName = ?");
			selectCustomersByName = connection.prepareStatement("SELECT * FROM customers WHERE customerName = ?");	
			// Insert statement
			insertCustomer = connection.prepareStatement("INSERT INTO customers " + 
														 "(customerName, firstName, middleName, lastName, street, " +
														 "postalCode, city, state, country, primaryPhoneNumber, " +
														 "secondaryPhoneNumber, faxNumber, email, website) " + 
            											 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");			
			// Update statement
			updateCustomer = connection.prepareStatement("UPDATE customers " + 
														 "SET customerName = ?" +
														 ",   firstName = ?" +
														 ",   middleName = ?" +
														 ",   lastName = ?" +
														 ",   street = ?" +
														 ",   postalCode = ?" +
														 ",   city = ?" +
														 ",   state = ?" +
														 ",   country = ?" +
														 ",   primaryPhoneNumber = ?" +
														 ",	  secondaryPhoneNumber = ?" +
														 ",   faxNumber = ?" + 
														 ",   email = ?" +
														 ",	  website = ?" +
														 "WHERE customerId = ?");			
			// Delete statement
			deleteCustomer = connection.prepareStatement("DELETE FROM customers " +
														 "WHERE customerId = ?");
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}		
	}
	
	/**
	 *  Query to get all the customers by customer id.
	 */
	@Override
	public Customer getCustomerById(String customerId) throws SQLException {				
		Customer customer = null;
	    ResultSet resultSet = null;
	    
	    if(customerId == null) {
	    	customerId = StringTools.ZERO;
	    }
	    
	    try {
	    	selectCustomersById.setInt(1, Integer.parseInt(customerId));
	    	resultSet = selectCustomersById.executeQuery(); 	       
	    	while(resultSet.next()) {
	    		customer = new Customer(Integer.toString(resultSet.getInt("customerId")),	               
	            						resultSet.getString("customerName"),
	            						resultSet.getString("firstName"),
	            						resultSet.getString("middleName"),
	            						resultSet.getString("lastName"),
	            						resultSet.getString("street"),
	            						resultSet.getString("postalCode"),
	            						resultSet.getString("city"),
	            						resultSet.getString("state"),
	            						resultSet.getString("country"),
	            						resultSet.getString("primaryPhoneNumber"),
	            						resultSet.getString("secondaryPhoneNumber"),
	            						resultSet.getString("faxNumber"),
	            						resultSet.getString("email"),
	            						resultSet.getString("website"),
	            						resultSet.getDate("dateOfCreation").toString());
	        }
	    }
	    catch(SQLException sqlException) {
	    	throw sqlException;
	    }
	    finally {
	    	try {
	    		resultSet.close();
	    	}
	    	catch(SQLException sqlException) {
	    		close();
	    		throw sqlException;   		
	    	}
	    }	      
	    return customer;
	}
		
	/**
	 *  Query to get all the customers by customer id.
	 */
	@Override
	public List<Customer> getCustomersById(String customerId) throws SQLException {		
		List<Customer> results = null;
	    ResultSet resultSet = null;
	    try {
	    	selectCustomersById.setInt(1, Integer.parseInt(customerId));	
	        resultSet = selectCustomersById.executeQuery(); 	
	        results = new ArrayList<Customer>();	
	        while(resultSet.next()) {
	        	results.add(new Customer(Integer.toString(resultSet.getInt("customerId")),	               
							             resultSet.getString("customerName"),
							             resultSet.getString("firstName"),
							             resultSet.getString("middleName"),
							             resultSet.getString("lastName"),
							             resultSet.getString("street"),
							             resultSet.getString("postalCode"),
							             resultSet.getString("city"),
							             resultSet.getString("state"),
							             resultSet.getString("country"),
							             resultSet.getString("primaryPhoneNumber"),
							             resultSet.getString("secondaryPhoneNumber"),
							             resultSet.getString("faxNumber"),
							             resultSet.getString("email"),
							             resultSet.getString("website"),
							        	 resultSet.getDate("dateOfCreation").toString()));
	        }
	    }
	    catch(SQLException sqlException) {
	    	throw sqlException;
	    }
	    finally {
	    	try {
	    		resultSet.close();
	        }
	        catch(SQLException sqlException) {
	        	close();
	        	throw sqlException;	            
	        }
	    }	      
	    return results;
	}
	
	/**
	 *  Query to get all the customers by last name.
	 */
	@Override
	public List<Customer> getCustomersByLastName(String lastName) throws SQLException {	
		List<Customer> results = null;
	    ResultSet resultSet = null;
	    try {
	    	 selectCustomersByLastName.setString(1, lastName);	
	         resultSet = selectCustomersByLastName.executeQuery(); 	
	         results = new ArrayList<Customer>();	
	         while(resultSet.next()) {
	        	 results.add(new Customer(Integer.toString(resultSet.getInt("customerId")),	               
							              resultSet.getString("customerName"),
							              resultSet.getString("firstName"),
							              resultSet.getString("middleName"),
							              resultSet.getString("lastName"),
							              resultSet.getString("street"),
							              resultSet.getString("postalCode"),
							              resultSet.getString("city"),
							              resultSet.getString("state"),
							              resultSet.getString("country"),
							              resultSet.getString("primaryPhoneNumber"),
							              resultSet.getString("secondaryPhoneNumber"),
							              resultSet.getString("faxNumber"),
							              resultSet.getString("email"),
							              resultSet.getString("website"),
							        	  resultSet.getDate("dateOfCreation").toString()));
	         }
	      }
	      catch(SQLException sqlException) {
	    	  throw sqlException;
	      }
	      finally {
	         try {
	        	 resultSet.close();
	         }
	         catch(SQLException sqlException) {    
	        	 close();
	        	 throw sqlException;
	         }
	      }	      
	      return results;
	}
	
	/**
	 *  Query to get all the customers by customer name.
	 */
	@Override
	public List<Customer> getCustomersByName(String name) throws SQLException {		
		List<Customer> results = null;
	    ResultSet resultSet = null;	    
	    try {
	    	selectCustomersByName.setString(1, name);	
	    	resultSet = selectCustomersByName.executeQuery(); 
	    	results = new ArrayList<Customer>();	
	        while(resultSet.next()) {
	        	results.add(new Customer(Integer.toString(resultSet.getInt("customerId")),	               
							             resultSet.getString("customerName"),
							             resultSet.getString("firstName"),
							             resultSet.getString("middleName"),
							             resultSet.getString("lastName"),
							             resultSet.getString("street"),
							             resultSet.getString("postalCode"),
							             resultSet.getString("city"),
							             resultSet.getString("state"),
							             resultSet.getString("country"),
							             resultSet.getString("primaryPhoneNumber"),
							             resultSet.getString("secondaryPhoneNumber"),
							             resultSet.getString("faxNumber"),
							             resultSet.getString("email"),
							             resultSet.getString("website"),
							        	 resultSet.getDate("dateOfCreation").toString()));
	         }
	      }
	      catch(SQLException sqlException) {
	    	  throw sqlException;
	      }
	      finally {
	         try {
	        	 resultSet.close();
	         }
	         catch(SQLException sqlException) {     
	        	 close();
	        	 throw sqlException;
	         }
	      }	      
	      return results;
	}
	
	/**
	 * Insert a customer
	 */
	@Override
	public int insertCustomer(Customer customer) throws SQLException {		
		int insertResult = -1;		
		try {
			insertCustomer.setString(1, customer.getCustomerName());
			insertCustomer.setString(2, customer.getFirstName());
			insertCustomer.setString(3, customer.getMiddleName());
		    insertCustomer.setString(4, customer.getLastName());
		    insertCustomer.setString(5, customer.getStreet());
		    insertCustomer.setString(6, customer.getPostalCode());
		    insertCustomer.setString(7, customer.getCity());
		    insertCustomer.setString(8, customer.getState());
		    insertCustomer.setString(9, customer.getCountry());
		    insertCustomer.setString(10, customer.getPrimaryPhoneNumber());	    	 
		    insertCustomer.setString(11, customer.getSecondaryPhoneNumber());
		    insertCustomer.setString(12, customer.getFaxNumber());
		    insertCustomer.setString(13, customer.getEmail());
		    insertCustomer.setString(14, customer.getWebsite());		 
	        insertResult = insertCustomer.executeUpdate(); 
	      }
	      catch(SQLException sqlException) {
	    	  close();
	          throw sqlException;
	      }	      
	      return insertResult;
	} 
	
	/**
	 * Update the customer information
	 */
	@Override
	public int updateCustomer(Customer customer) throws SQLException {	
		int updateResult = -1;	 	
		try {
			updateCustomer.setString(1, customer.getCustomerName());
			updateCustomer.setString(2, customer.getFirstName());
			updateCustomer.setString(3, customer.getMiddleName());
			updateCustomer.setString(4, customer.getLastName());
			updateCustomer.setString(5, customer.getStreet());
			updateCustomer.setString(6, customer.getPostalCode());
			updateCustomer.setString(7, customer.getCity());
			updateCustomer.setString(8, customer.getState());
			updateCustomer.setString(9, customer.getCountry());
			updateCustomer.setString(10, customer.getPrimaryPhoneNumber());	    	 
			updateCustomer.setString(11, customer.getSecondaryPhoneNumber());
			updateCustomer.setString(12, customer.getFaxNumber());
			updateCustomer.setString(13, customer.getEmail());
			updateCustomer.setString(14, customer.getWebsite());
			updateCustomer.setString(15, customer.getCustomerId());        
			updateResult = updateCustomer.executeUpdate(); 
	      }
	      catch(SQLException sqlException) {
	    	  close();
	          throw sqlException;
	      }	      
	      return updateResult;
	} 
	
	/**
	 * Delete a customer
	 */
	@Override
	public int deleteCustomer(String customerId) throws SQLException {
		int deleteResult = -1;		
		try {
			deleteCustomer.setInt(1, Integer.parseInt(customerId));		
			deleteResult = deleteCustomer.executeUpdate();
		}
		catch (SQLException sqlException) {	         
	         close();
	         throw sqlException;
	    }	     
		return deleteResult;
	}
	
	/**
	 * Close the database connection
	 */
    public void close() {
       try {
          connection.close();
       }
       catch (SQLException sqlException) {
          sqlException.printStackTrace();
       }
	}
    
    /**
     * Update the connection and its associated queries
     * @param con
     * @throws SQLException
     */
    public void refresh(Connection con) throws SQLException {
    	this.connection = con;
    	createCustomerQueries();  	
    }

}