/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-11-23
 * Project: Opus Dei
 * Package: com.opusdei.controller
 * File: CustomerInsertController.java
 * Description: Mediator between the view and the model representing the data
 */
package com.opusdei.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.opusdei.dao.CustomerDAO;
import com.jgoodies.validation.ValidationResult;
import com.opusdei.common.Messages;
import com.opusdei.common.StringTools;
import com.opusdei.common.ValidationUtilities;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.observer.CustomerMenuObserver;
import com.opusdei.transfer.Customer;
import com.opusdei.validation.CustomerValidator;
import com.opusdei.view.ActionsMenu;
import com.opusdei.view.CustomerInsertView;

public class CustomerInsertController {
	
	private CustomerInsertView customerInsertView;
	private ActionsMenu actionsMenu;
	private CustomerMenuObserver customerMenuObserver;
	private Customer customer;
	private CustomerDAO customerDAO;
	
	/**
	 * CustomerInsertController Default Constructor
	 */
	public CustomerInsertController() {		
		// Do nothing
	}
	
	/**
	 * CustomerInsertController Constructor
	 * @param customerModel
	 * @param customerView
	 */
	public CustomerInsertController(CustomerInsertView customerInsertView, CustomerDAO customerDAO, ActionsMenu actionsMenu, CustomerMenuObserver customerMenuObserver) {		
		this.customerInsertView = customerInsertView;
		this.customerDAO = customerDAO;
		this.actionsMenu = actionsMenu;
		this.customerMenuObserver = customerMenuObserver;
		this.actionsMenu.menuBarListener(new CustomerInsertMenuHandler());
		this.customerInsertView.buttonHandler(new ButtonHandler());		
	}
	
	/**
	 * Inner class for menu actions
	 */
	private class CustomerInsertMenuHandler implements ActionListener {
				
		public void actionPerformed(ActionEvent event) {
			
			if(customerMenuObserver.isInsertCustomerPanelFlag()) {								
				int insertResult = 0;				
				// Insert action
				if(event.getSource() == actionsMenu.getSaveMenuItem()) {
					customer = new Customer(StringTools.EMPTY_STRING, 
											customerInsertView.getCustomerNameTextField().getText().trim(), 
											customerInsertView.getFirstNameTextField().getText().trim(),
											customerInsertView.getMiddleNameTextField().getText().trim(),
											customerInsertView.getLastNameTextField().getText().trim(),
											customerInsertView.getStreetTextField().getText().trim(),
											customerInsertView.getPostalCodeTextField().getText().trim(),
											customerInsertView.getCityTextField().getText().trim(),
											customerInsertView.getStateTextField().getText().trim(),
											customerInsertView.getCountryTextField().getText().trim(),
											customerInsertView.getPrimaryPhoneNumberTextField().getText().trim(),
											customerInsertView.getSecondaryPhoneNumberTextField().getText().trim(),
											customerInsertView.getFaxNumberTextField().getText().trim(),
											customerInsertView.getEmailTextField().getText().trim(),
											customerInsertView.getWebsiteTextField().getText().trim(),
											StringTools.EMPTY_STRING);
					ValidationResult validationResult = new CustomerValidator().validate(customer);					
					
					if(!validationResult.hasErrors()) {										
						try {
							insertResult = insertAction(customer);
							if(insertResult == 0) {
								JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), Messages.DB_INSERT_INFO_001,
															  CustomerConstants.CUSTOMER_INSERT_TITLE, JOptionPane.PLAIN_MESSAGE);
															  customerInsertView.clearValueTextField();
							}
							else if(insertResult == 1) {
								JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), Messages.DB_INSERT_INFO_002,
															  CustomerConstants.CUSTOMER_INSERT_TITLE, JOptionPane.WARNING_MESSAGE);
															  customerInsertView.clearValueTextField();
							}
						} 
						catch(SQLException sqlException) {
							JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), Messages.DB_INSERT_ERROR_001 + " " + sqlException.getMessage(),
							            				  CustomerConstants.CUSTOMER_INSERT_TITLE, JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						ValidationUtilities.showValidationMessage(event, CustomerConstants.CUSTOMER_INSERT_TITLE, validationResult);
					}
				}			
				// Clear action
				else if(event.getSource() == actionsMenu.getClearMenuItem()) {
					customerInsertView.clearValueTextField();				
				}
			}			
		}
	}

	/**
	 * Inner class for button event handling
	 */
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			int insertResult = 0;
			// Insert action
			if(event.getSource() == customerInsertView.getCustomerInsertButton()) {
								
				customer = new Customer(StringTools.EMPTY_STRING, 
												 customerInsertView.getCustomerNameTextField().getText().trim(), 
												 customerInsertView.getFirstNameTextField().getText().trim(),
												 customerInsertView.getMiddleNameTextField().getText().trim(),
												 customerInsertView.getLastNameTextField().getText().trim(),
												 customerInsertView.getStreetTextField().getText().trim(),
												 customerInsertView.getPostalCodeTextField().getText().trim(),
												 customerInsertView.getCityTextField().getText().trim(),
												 customerInsertView.getStateTextField().getText().trim(),
												 customerInsertView.getCountryTextField().getText().trim(),
												 customerInsertView.getPrimaryPhoneNumberTextField().getText().trim(),
												 customerInsertView.getSecondaryPhoneNumberTextField().getText().trim(),
												 customerInsertView.getFaxNumberTextField().getText().trim(),
												 customerInsertView.getEmailTextField().getText().trim(),
												 customerInsertView.getWebsiteTextField().getText().trim(),
												 StringTools.EMPTY_STRING);
				
				ValidationResult validationResult = new CustomerValidator().validate(customer);
				
				if(!validationResult.hasErrors()) {					
					try {
						insertResult = insertAction(customer);
						if(insertResult == 0) {
							JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), Messages.DB_INSERT_INFO_001,
														  CustomerConstants.CUSTOMER_INSERT_TITLE, JOptionPane.PLAIN_MESSAGE);
														  customerInsertView.clearValueTextField();
						}
						else if(insertResult == 1) {
							JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), Messages.DB_INSERT_INFO_002,
														  CustomerConstants.CUSTOMER_INSERT_TITLE, JOptionPane.WARNING_MESSAGE);
														  customerInsertView.clearValueTextField();
						}
					} 
					catch(SQLException sqlException) {
						JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), Messages.DB_INSERT_ERROR_001 + " " + sqlException.getMessage(),
						            				  CustomerConstants.CUSTOMER_INSERT_TITLE, JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					ValidationUtilities.showValidationMessage(event, CustomerConstants.CUSTOMER_INSERT_TITLE, validationResult);
				}
			}			
			// Clear action
			else if(event.getSource() == customerInsertView.getCustomerClearButton()) {
				try {
					customerInsertView.clearValueTextField();
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(customerInsertView.getCustomerInsertPanel(), "Error - The clear operation failed!",
						       CustomerConstants.CUSTOMER_CLEAR_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}
		}		
	}
	
	/**
	 * Action to insert a customer
	 */
	protected int insertAction(Customer c) throws SQLException{
		int insertResult = -1;
		try {		
			insertResult = customerDAO.insertCustomer(c);
		}
		catch(SQLException sqlException) {			
			throw sqlException;
		}
		return insertResult;
	}
	
}
