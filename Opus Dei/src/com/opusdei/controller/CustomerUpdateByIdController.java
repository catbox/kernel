/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-11-23
 * Project: Opus Dei
 * Package: com.opusdei.controller
 * File: CustomerUpdateByIdController.java
 * Description: Mediator between the view and the model for data update
 *
 */
package com.opusdei.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.jgoodies.validation.ValidationResult;
import com.opusdei.common.Messages;
import com.opusdei.common.StringTools;
import com.opusdei.common.ValidationUtilities;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.dao.CustomerDAO;
import com.opusdei.observer.CustomerMenuObserver;
import com.opusdei.transfer.Customer;
import com.opusdei.validation.CustomerValidator;
import com.opusdei.view.ActionsMenu;
import com.opusdei.view.CustomerUpdateByIdView;

public class CustomerUpdateByIdController {

	private CustomerUpdateByIdView customerUpdateByIdView;
	private ActionsMenu actionsMenu;
	private CustomerDAO customerDAO;
	private Customer customer;
	private CustomerMenuObserver customerMenuObserver;
	
	protected boolean allMandatoryFieldsAreFilled;
	protected boolean updateMode;
	
	/**
	 * CustomerInsertController Constructor
	 */
	public CustomerUpdateByIdController() {		
		// Do nothing
	}
	
	/**
	 * CustomerUpdateController Constructor
	 * @param customerModel
	 * @param customerView
	 * @param rowIndex
	 */
	public CustomerUpdateByIdController(CustomerUpdateByIdView customerUpdateByIdView, CustomerDAO customerDAO, ActionsMenu actionsMenu, CustomerMenuObserver customerMenuObserver) {
		this.customerUpdateByIdView = customerUpdateByIdView;
		this.actionsMenu = actionsMenu;
		this.customerDAO = customerDAO;
		this.customerMenuObserver = customerMenuObserver;
		this.customerUpdateByIdView.buttonHandler(new ButtonHandler());
		this.customerUpdateByIdView.keyHandler(new KeyHandler());
		this.actionsMenu.menuBarListener(new CustomerUpdateByIDActionsMenuHandler());
		allMandatoryFieldsAreFilled = true;
		updateMode = true;		
	}
	
	private class CustomerUpdateByIDActionsMenuHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			
			int updateResult = 0;
			
			if(customerMenuObserver.isModifyCustomerPanelFlag()) {				
				//Query action
				if(event.getSource() == actionsMenu.getQueryMenuItem()) {
					try {
						customer = queryAction();
						if(customer != null) {
							customerUpdateByIdView.setFields(customer);
						}
						else {
							JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_001,
									                      CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
						}
					} 
					catch(SQLException sqlException) {
						JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_UPDATE_ERROR_001 + " " + sqlException.getMessage(),
													  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
					}
				}
				// Update action
				else if(event.getSource() == actionsMenu.getSaveMenuItem()) {							
					if(validateMandatoryFields(event)) {					
						Object[] options = {"YES", "CANCEL"};
						int choice = JOptionPane.showOptionDialog(customerUpdateByIdView.getCustomerUpdatePanel(),
																  "Please confirm update", CustomerConstants.CUSTOMER_UPDATE_TITLE,
																  JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, 
																  null, options, options[0]);						
						if(choice == 0) {
							try {
								updateResult = updateAction(customer);
								if(updateResult == 0) {
									JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_001,
									CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
								}
								else if(updateResult == 1) {
									JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_002,
									CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.INFORMATION_MESSAGE);
								}
							} 
							catch(SQLException sqlException) {
								JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_DELETE_ERROR_001 + " " + sqlException.getMessage(),
															  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
							}
						}
					}
				}
				// Delete action
				else if(event.getSource() == actionsMenu.getDeleteMenuItem()) {
					int deleteResult = 0;						
					Object[] options = {"YES", "CANCEL"};
					int choice = JOptionPane.showOptionDialog(customerUpdateByIdView.getCustomerUpdatePanel(),
							"Please confirm delete", CustomerConstants.CUSTOMER_UPDATE_TITLE,
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options,
							options[0]);
					
					if(choice == 0) {				
						try {
							deleteResult = deleteAction();
							if(deleteResult == 0) {
								JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_DELETE_INFO_001,
															  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
							}
							else if(deleteResult == 1) {
								JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_DELETE_INFO_002,
										  					  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.PLAIN_MESSAGE);
							}
						} 
						catch(SQLException sqlException) {
							JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_DELETE_ERROR_001 + " " + sqlException,
		                                                  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
						}	
					}
				}
				// Clear action
				else if(event.getSource() == actionsMenu.getClearMenuItem()) {
					customerUpdateByIdView.clearFields();				
				}
				// Reset action
				else if(event.getSource() == actionsMenu.getResetMenuItem()) {
					customerUpdateByIdView.resetFields();					
				}
			}
		}
	}
	
	/**
	 * Inner class for button event handling
	 */
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			int updateResult = 0;			
			// Switch view-update mode
			if(event.getSource() == customerUpdateByIdView.getCustomerUpdateButton()) {				
				// Switching to the update mode
				if(updateMode && validateMandatoryFields(event)) {	
					customerUpdateByIdView.enableEditing(updateMode);
					customerUpdateByIdView.setMandatoryFields(updateMode);
					customerUpdateByIdView.getCustomerUpdateButton().setToolTipText("Click to switch to viewing mode");
					updateMode = false;
				}
				// Switching to the viewing mode
				else if(!updateMode && validateMandatoryFields(event)) {	
					customerUpdateByIdView.enableEditing(updateMode);
					customerUpdateByIdView.setMandatoryFields(updateMode);
					customerUpdateByIdView.getCustomerUpdateButton().setToolTipText("Click to switch to update mode");
					updateMode = true;
				}				
			}
			// Query action
			else if(event.getSource() == customerUpdateByIdView.getCustomerQueryButton()) {												
				try {
					customer = queryAction();
					if(customer != null) {
						customerUpdateByIdView.setFields(customer);
					}
					else {
						JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_001,
								  					  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
					}
				} 
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_UPDATE_ERROR_001 + " " + sqlException.getMessage(),
												  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}	
			// Update action
			else if(event.getSource() == customerUpdateByIdView.getCustomerInsertButton()) {				
				if(validateMandatoryFields(event)) {									
					Object[] options = {"YES", "CANCEL"};
					int choice = JOptionPane.showOptionDialog(customerUpdateByIdView.getCustomerUpdatePanel(),
															  "Please confirm update", CustomerConstants.CUSTOMER_UPDATE_TITLE,
															  JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, 
															  null, options, options[0]);
					if(choice == 0) {
						try {
							updateResult = updateAction(customer);
							if(updateResult == 0) {
								JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_001,
															  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
							}
							else if(updateResult == 1) {
								JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_002,
															  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.INFORMATION_MESSAGE);
							}
						} 
						catch(SQLException sqlException) {
							JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_DELETE_ERROR_001 + " " + sqlException.getMessage(),
														  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}			
			// Clear action
			else if(event.getSource() == customerUpdateByIdView.getCustomerClearButton()) {
				customerUpdateByIdView.clearFields();
			}
			// Reset action
			else if(event.getSource() == customerUpdateByIdView.getCustomerResetButton()) {
				customerUpdateByIdView.resetFields();
			}
		}		
	}
		
	/**
	 * Inner class for key events
	 */
	private class KeyHandler extends KeyAdapter {
		
		@Override
		public void keyPressed(KeyEvent keyEvent) {
			if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) {
				try {
					customer = queryAction();
					if(customer != null) {
						customerUpdateByIdView.setFields(customer);
					}
					else {
						JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.BD_UPDATE_INFO_001,
								                      CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
					}
				} 
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(customerUpdateByIdView.getCustomerUpdatePanel(), Messages.DB_UPDATE_ERROR_001 + " " + sqlException.getMessage(),
												  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
				}
			}
		}		
	}
	
	/**
	 * Return true if the mandatory fields are filled; false otherwise.
	 * @param event
	 */
	protected boolean validateMandatoryFields(ActionEvent event) {
		
		customer = new Customer(
				customerUpdateByIdView.getCustomerIdTextField().getText(),
				customerUpdateByIdView.getCustomerNameTextField().getText(),
				customerUpdateByIdView.getFirstNameTextField().getText(),
				customerUpdateByIdView.getMiddleNameTextField().getText(),
				customerUpdateByIdView.getLastNameTextField().getText(),
				customerUpdateByIdView.getStreetTextField().getText(),
				customerUpdateByIdView.getPostalCodeTextField().getText(),
				customerUpdateByIdView.getCityTextField().getText(),
				customerUpdateByIdView.getStateTextField().getText(),
				customerUpdateByIdView.getCountryTextField().getText(),
				customerUpdateByIdView.getPrimaryPhoneNumberTextField().getText(),
				customerUpdateByIdView.getSecondaryPhoneNumberTextField().getText(),
				customerUpdateByIdView.getFaxNumberTextField().getText(),
				customerUpdateByIdView.getEmailTextField().getText(),
				customerUpdateByIdView.getWebsiteTextField().getText(),
				StringTools.EMPTY_STRING);

		ValidationResult validationResult = new CustomerValidator().validate(customer);	
		
		if(validationResult.hasErrors()) {
			ValidationUtilities.showValidationMessage(event, CustomerConstants.CUSTOMER_UPDATE_TITLE, validationResult);
			return false;
		}
		
		// All the mandatory fields are fine
		return true;
	}
	
	/**
	 * Action to update the customer data
	 * return int updateResult
	 */
	protected int updateAction(Customer c) throws SQLException{				
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
	 * Query action to retrieve the customers
	 */
	protected Customer queryAction() throws SQLException {		
		customer = null;		
		try {
			customer = customerDAO.getCustomerById(customerUpdateByIdView.getCustomerIdTextField().getText().trim());								
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}
		
		if(customer != null) {
			customerUpdateByIdView.enableButtons();
		}
		return customer;
	}
	
	/**
	 * Delete action to remove a customer
	 */
	protected int deleteAction() throws SQLException {			
		int deleteResult = -1;				
		try {			
			deleteResult = customerDAO.deleteCustomer(customerUpdateByIdView.getCustomerIdTextField().getText().trim());								
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}	
		return deleteResult;
	}
			
}
