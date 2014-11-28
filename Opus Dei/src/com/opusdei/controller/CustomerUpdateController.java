/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-10-18
 * Project: Opus Dei
 * Package: com.opusdei.controller
 * File: CustomerUpdateController.java
 * Description: Mediator between the view and the model representing the data
 */
package com.opusdei.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jgoodies.validation.ValidationResult;
import com.opusdei.common.Messages;
import com.opusdei.common.StringTools;
import com.opusdei.common.ValidationUtilities;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.dao.CustomerDAO;
import com.opusdei.transfer.Customer;
import com.opusdei.validation.CustomerValidator;
import com.opusdei.view.CustomerUpdateView;

public class CustomerUpdateController {
	
	private CustomerUpdateView customerUpdateView;
	private CustomerDAO customerDAO;
	private Customer customer;	
	protected boolean allMandatoryFieldsAreFilled;
	protected boolean updateMode;
	
	/**
	 * CustomerInsertController Constructor
	 */
	public CustomerUpdateController() {		
		// Do nothing
	}
	
	/**
	 * CustomerUpdateController Constructor
	 * @param customerModel
	 * @param customerView
	 */
	public CustomerUpdateController(CustomerUpdateView customerUpdateView, CustomerDAO customerDAO) {
		this.customerUpdateView = customerUpdateView;
		this.customerDAO = customerDAO;
		this.customerUpdateView.buttonHandler(new ButtonHandler());
		allMandatoryFieldsAreFilled = true;
		updateMode = true;
	}
	
	/**
	 * Inner class for button event handling
	 */
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			int updateResult = 0;			
			// Switch view-update mode
			if(event.getSource() == customerUpdateView.getCustomerUpdateButton()) {				
				// Switching to the update mode
				if(updateMode && validateMandatoryFields(event)) {	
					customerUpdateView.enableEditing(updateMode);
					customerUpdateView.setMandatoryFields(updateMode);
					customerUpdateView.getCustomerUpdateButton().setToolTipText("Click to switch to viewing mode");
					updateMode = false;
				}
				// Switching to the viewing mode
				else if(!updateMode && validateMandatoryFields(event)) {	
					customerUpdateView.enableEditing(updateMode);
					customerUpdateView.setMandatoryFields(updateMode);
					customerUpdateView.getCustomerUpdateButton().setToolTipText("Click to switch to update mode");
					updateMode = true;
				}				
			}
			// Update action
			else if(event.getSource() == customerUpdateView.getCustomerInsertButton()) {							
				if(validateMandatoryFields(event)) {					
					try {
						updateResult = updateAction(customer);
						if(updateResult == 0) {
							JOptionPane.showMessageDialog(customerUpdateView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_001,
														  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
						}
						else if(updateResult == 1) {
							JOptionPane.showMessageDialog(customerUpdateView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_002,
														  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.PLAIN_MESSAGE);
						}
					} 
					catch(SQLException sqlException) {
						JOptionPane.showMessageDialog(customerUpdateView.getCustomerUpdatePanel(), Messages.DB_DELETE_ERROR_001 + " " + sqlException.getMessage(),
													  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
					}
				}
			}			
			// Clear action
			else if(event.getSource() == customerUpdateView.getCustomerClearButton()) {
				customerUpdateView.clearValueTextField();			
			}
		}		
	}
	
	/**
	 * Return true if the mandatory fields are filled; false otherwise.
	 * @param event
	 */
	protected boolean validateMandatoryFields(ActionEvent event) {		
		customer = new Customer(customerUpdateView.getCustomerIdTextField().getText(),
								customerUpdateView.getCustomerNameTextField().getText(),
								customerUpdateView.getFirstNameTextField().getText(),
								customerUpdateView.getMiddleNameTextField().getText(),
								customerUpdateView.getLastNameTextField().getText(),
								customerUpdateView.getStreetTextField().getText(),
								customerUpdateView.getPostalCodeTextField().getText(),
								customerUpdateView.getCityTextField().getText(),
								customerUpdateView.getStateTextField().getText(),
								customerUpdateView.getCountryTextField().getText(),
								customerUpdateView.getPrimaryPhoneNumberTextField().getText(),
								customerUpdateView.getSecondaryPhoneNumberTextField().getText(),
								customerUpdateView.getFaxNumberTextField().getText(),
								customerUpdateView.getEmailTextField().getText(),
								customerUpdateView.getWebsiteTextField().getText(),
								StringTools.EMPTY_STRING);

		ValidationResult validationResult = new CustomerValidator().validate(customer);	
		
		if(validationResult.hasErrors()) {
			ValidationUtilities.showValidationMessage(event, CustomerConstants.CUSTOMER_UPDATE_TITLE, validationResult);
			return false;
		}	
		// All the mandatory fields are filled
		return true;
	}
	
	/**
	 * Action to update the customer data
	 * return int updateResult
	 */
	protected int updateAction(Customer c) throws SQLException {	
		int updateResult = -1;		
		try {				
			updateResult = customerDAO.updateCustomer(c);				
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}		
		return updateResult;		
	}
	
	/**
	 * Switch to the viewing mode
	 * return void
	 */
	protected void switchToViewMode(ActionEvent event) {		
		if(validateMandatoryFields(event)) {
			updateMode = false;
			customerUpdateView.enableEditing(updateMode);
			customerUpdateView.setMandatoryFields(updateMode);
			customerUpdateView.getCustomerUpdateButton().setToolTipText("Click to switch to update mode");			
		}		
	}
			
}
