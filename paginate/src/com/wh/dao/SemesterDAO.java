/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.dao
 * File: CustomerDAO.java
 * Description: Customer DAO implementation
 *
 */
package com.wh.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.wh.factory.ConnectionFactory;
import com.wh.transfer.Semester;

public class SemesterDAO {
		
	private Connection connection = null;
	private Statement statement;
	private String semestersQuery;
	private int numOfRecords;
	
	/**
	 * Constructor
	 */
	public SemesterDAO() throws SQLException {
		connection = ConnectionFactory.getConnection();						
	}
	
		
	/**
	 *  Query to get all the semesters.
	 */
	public List<Semester> getSemesters(int offset, int numOfRecords) throws SQLException {		
		List<Semester> results =new ArrayList<Semester>();
	    ResultSet resultSet = null;
	    semestersQuery = "SELECT SQL_CALC_FOUND_ROWS * FROM minerva.semesters ORDER BY timeStamp LIMIT "  + offset + ", " + numOfRecords;
	    statement = connection.createStatement();

	    try {
	    	
	    	resultSet = statement.executeQuery(semestersQuery);
	        
	        while(resultSet.next()) {
	        	results.add(new Semester(resultSet.getString("semesterId"),	               
							             resultSet.getString("semester"),
							             resultSet.getString("timestamp")));
	        }
	        
	        resultSet.close();
	        
	        resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
            if(resultSet.next())
                this.numOfRecords = resultSet.getInt(1);
	       
	    }
	    catch(SQLException sqlException) {
	    	throw sqlException;
	    }
	    finally {
	    	try {
	    		if(statement != null) {
	    			statement.close();
	    		}
	    		close();
	        }
	        catch(SQLException sqlException) {
	        	close();
	        	throw sqlException;	            
	        }
	    }	      
	    return results;
	}
	
	/**
	 * Close the database connection
	 */
    public void close() {
       try {
    	   if(connection != null)
    		   connection.close();
       }
       catch (SQLException sqlException) {
          sqlException.printStackTrace();
       }
	}
    
    /**
	 * Get the number of records
	 */
    public int getNumOfRecords() {
        return numOfRecords;
    }

}