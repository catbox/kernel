/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-07
 * Project:Opus Dei
 * Package: com.opusdei.controller
 * File: CustomerQueryController.java
 * Description: Mediator between the view and the model representing the data
 */
package com.opusdei.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.opusdei.client.CustomerUpdateUI;
import com.opusdei.common.CoreConstants;
import com.opusdei.common.Messages;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.dao.CustomerDAO;
import com.opusdei.observer.CustomerMenuObserver;
import com.opusdei.table.model.CustomerTableModel;
import com.opusdei.transfer.Customer;
import com.opusdei.view.ActionsMenu;
import com.opusdei.view.CustomerQueryView;

public class CustomerQueryController {
	
	private CustomerTableModel customerTableModel;
	private CustomerQueryView customerQueryView;
	private ActionsMenu actionsMenu;
	private CustomerMenuObserver customerMenuObserver;
	private Customer customer;
	private CustomerDAO customerDAO ;	
	private String queryBy = CustomerConstants.CUSTOMER_ID;
	private List<Customer> listOfCustomers = new ArrayList<Customer>();
	protected int rowIndex;
		
	/**
	 * Constructor
	 * @param customerQueryView
	 * @param customerDAO
	 * @param actionsMenu
	 * @param customerMenuObserver
	 */
	public CustomerQueryController(CustomerQueryView customerView, CustomerDAO customerDAO, ActionsMenu actionsMenu, CustomerMenuObserver customerMenuObserver) {		
		this.customerTableModel = customerView.getCustomerTableModel();
		this.customerQueryView = customerView;
		this.actionsMenu = actionsMenu;
		this.customerDAO = customerDAO;
		this.customerMenuObserver = customerMenuObserver;
		this.customerQueryView.buttonHandler(new ButtonHandler());
		this.customerQueryView.keyHandler(new KeyHandler());
		this.customerQueryView.comboBoxHandler(new ComboBoxHandler());
		this.customerQueryView.customerTableListener(new TableListener());
		//this.customerQueryView.customerPopupMenuListener(new PopupMenuListener());
		this.customerQueryView.customerMenuItemActionListener(new MenuItemActionListener());
		//this.customerQueryView.customerMenuItemMouseListener(new MenuItemMouseListener());
		this.actionsMenu.menuBarListener(new CustomerQueryMenuHandler());
	}
	
	/**
	 * Inner class for menu actions
	 */
	private class CustomerQueryMenuHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			
			if(customerMenuObserver.isQueryCustomerPanelFlag()) {
				// Query action
				if(event.getSource() == actionsMenu.getQueryMenuItem()) {
					int queryResult;
					try {
						queryResult = queryAction();
						if(queryResult == 0) {
							JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.BD_QUERY_INFO_001,
								      				      CustomerConstants.CUSTOMER_QUERY_TITLE, JOptionPane.INFORMATION_MESSAGE);					
						}
					} 
					catch(SQLException sqlException) {
						JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.BD_QUERY_ERROR_001 + " " + sqlException,
							      					  CustomerConstants.CUSTOMER_QUERY_TITLE, JOptionPane.ERROR_MESSAGE);
					}	
				}			
				// Clear action
				else if(event.getSource() == actionsMenu.getClearMenuItem()) {
						customerQueryView.clearValueTextField();									
				}
				// Reset action
				else if(event.getSource() == actionsMenu.getResetMenuItem()) {
					// Clear query value
					customerQueryView.clearValueTextField();				
					customerTableModel.clear();					
				}
			}		
		}		
	}
	
	/**
	 * Inner class for button event handling
	 */
	private class ButtonHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {			
			// Query action
			if(event.getSource() == customerQueryView.getCustomerQueryButton()) {
				int queryResult;
				try {
					queryResult = queryAction();
					if(queryResult == 0) {
						JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.BD_QUERY_INFO_001,
							      				      CustomerConstants.CUSTOMER_QUERY_TITLE, JOptionPane.INFORMATION_MESSAGE);					
					}
				} 
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.BD_QUERY_ERROR_001 + " " + sqlException,
						      					  CustomerConstants.CUSTOMER_QUERY_TITLE, JOptionPane.ERROR_MESSAGE);
				}									
			}		
			// Clear action
			else if(event.getSource() == customerQueryView.getCustomerClearButton()) {
				customerQueryView.clearValueTextField();				
			}
			// Reset action
			else if(event.getSource() == customerQueryView.getCustomerResetButton()) {
				// Clear query value
				customerQueryView.clearValueTextField();				
				customerTableModel.clear();				
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
				int queryResult;
				try {
					queryResult = queryAction();
					if(queryResult == 0) {
						JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.BD_QUERY_INFO_001,
							      				      CustomerConstants.CUSTOMER_QUERY_TITLE, JOptionPane.INFORMATION_MESSAGE);					
					}
				} 
				catch(SQLException sqlException) {
					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.BD_QUERY_ERROR_001 + " " + sqlException,
						      					  CustomerConstants.CUSTOMER_QUERY_TITLE, JOptionPane.ERROR_MESSAGE);
				}	
			}
		}		
	}
	
	/**
	 * Inner class for combo box event handling
	 */
	private class ComboBoxHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			 JComboBox comboBox = (JComboBox)event.getSource();
		     String selectedField = (String)comboBox.getSelectedItem();
		     selectedField = selectedField.toUpperCase();
		     queryBy = selectedField;
		     selectedField = selectedField.replace(" ", "");
		}
	}
	
	/**
	 * Inner class for table popup events
	 */
	private class TableListener extends MouseAdapter {
				
		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			showPopup(mouseEvent);
		}
		
		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			showPopup(mouseEvent);
		}
		
		public void showPopup(MouseEvent event) {
			if(event.isPopupTrigger()) {
				customerQueryView.getPopupMenu().show(event.getComponent(), event.getX(), event.getY());
			}
		}
		
		@Override    
        public void mouseClicked(MouseEvent event) {
			/*if(event.getButton() == MouseEvent.BUTTON3) {
        		customerQueryView.getPopupMenu().show(event.getComponent(), event.getX(), event.getY());
        	}*/
			if(event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == CoreConstants.MOUSE_LEFT_CLICK) {	        	
	        	javax.swing.SwingUtilities.invokeLater(new Runnable() {
	                public void run() {
        				try {
        					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        					rowIndex = customerQueryView.getRow();
        					customer = customerTableModel.getRow(rowIndex);
        					new CustomerUpdateUI(customer, customerDAO);       					       					
        				}       				
        				catch(ClassNotFoundException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				}
        				catch(InstantiationException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				} 
        				catch(IllegalAccessException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				} 
        				catch(UnsupportedLookAndFeelException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				}
	                }
	            });	        	
	        }
		}		
    }
	
	/**
	 * Inner class for menu actions
	 */
	public class MenuItemActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == customerQueryView.getCustomerUpdateMenuItem()) {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
	                public void run() {
        				try {
        					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        					rowIndex = customerQueryView.getRow();
        					customer = customerTableModel.getRow(rowIndex);
        					new CustomerUpdateUI(customer, customerDAO);       					       					
        				}
        				catch (ClassNotFoundException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				}
        				catch (InstantiationException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				} 
        				catch (IllegalAccessException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				} 
        				catch (UnsupportedLookAndFeelException exception) {
        					JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(),
        							exception.toString(),
        							Messages.UI_MANAGER_TITLE,
        							JOptionPane.ERROR_MESSAGE);
        				}
	                }
	            });	  
			}
			else if(event.getSource() == customerQueryView.getCustomerDeleteMenuItem()) {
				int deleteResult = 0;				
				Object[] options = {"YES", "CANCEL"};
				int choice = JOptionPane.showOptionDialog(customerQueryView.getCustomerScrollPane(),
														  "Please confirm delete", CustomerConstants.CUSTOMER_UPDATE_TITLE,
														  JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);				
				if(choice == 0) {
					try {
						deleteResult = deleteAction();
						if(deleteResult == 0) {
							JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.DB_DELETE_INFO_001,
														  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.WARNING_MESSAGE);
						}
						else if(deleteResult == 1) {
							JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.DB_DELETE_INFO_002,
									  					  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.PLAIN_MESSAGE);
						}
					} 
					catch(SQLException sqlException) {
						JOptionPane.showMessageDialog(customerQueryView.getCustomerScrollPane(), Messages.DB_DELETE_ERROR_001 + " " + sqlException,
	                                                  CustomerConstants.CUSTOMER_UPDATE_TITLE, JOptionPane.ERROR_MESSAGE);
					}										
				}
			}			
		}		
	}
	
	public class MenuItemMouseListener extends MouseAdapter {
		@Override
		public void mouseEntered(MouseEvent e) {
			customerQueryView.getPopupMenu().setVisible(true);		
			System.out.println("MenuItemMouseListener - I am in");
		}

		@Override
		public void mouseExited(MouseEvent e) {
			customerQueryView.getPopupMenu().setVisible(false);	
			System.out.println("MenuItemMouseListener - I am out");
		}		
	}
	
	/**
	 * Query action to retrieve the customers
	 */
	protected int queryAction() throws SQLException {		
		int queryResult = -1;		
		try {		
			synchronized(customerTableModel) {
				listOfCustomers.clear();
				if(queryBy.equals(CustomerConstants.CUSTOMER_ID)) {
					listOfCustomers = customerDAO.getCustomersById(customerQueryView.getCustomerTextField().getText().trim());
				}
				else if(queryBy.equals(CustomerConstants.CUSTOMER_NAME)) {
					listOfCustomers = customerDAO.getCustomersByName(customerQueryView.getCustomerTextField().getText().trim());
				}
				else if(queryBy.equals(CustomerConstants.CUSTOMER_LASTNAME)) {
					listOfCustomers = customerDAO.getCustomersByLastName(customerQueryView.getCustomerTextField().getText().trim());
				}
				
				if(!listOfCustomers.isEmpty()) {
					customerTableModel.clear();
					customerTableModel.addRows(listOfCustomers);
					queryResult = 1;
				}
				else if(listOfCustomers.isEmpty()){
					queryResult = 0;					
				}
				return queryResult;
			}
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}
	}
	
	/**
	 * Delete action
	 */
	protected int deleteAction() throws SQLException {		
		int deleteResult = -1;		
		try {
			synchronized(customerTableModel) {
				rowIndex = customerQueryView.getRow();
				customer =  customerTableModel.getRow(rowIndex);
				deleteResult = customerDAO.deleteCustomer(customer.getCustomerId());				
				customerTableModel.removeRow(rowIndex);
			}
		}
		catch(SQLException sqlException) {
			throw sqlException;
		}	
		return deleteResult;
	}
	
}
