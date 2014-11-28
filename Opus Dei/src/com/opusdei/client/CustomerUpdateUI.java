/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-10-18
 * Project: Opus Dei
 * Package: com.opusdei.client
 * File: CustomerUpdateUI.java
 * Description: UI to update a customer data
 */
package com.opusdei.client;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.opusdei.common.Utilities;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.controller.CustomerUpdateController;
import com.opusdei.dao.CustomerDAO;
import com.opusdei.transfer.Customer;
import com.opusdei.view.CustomerUpdateView;

public class CustomerUpdateUI extends JFrame {

	private static final long serialVersionUID = 1L;

	public CustomerUpdateUI(Customer customer, CustomerDAO customerDAO) {

   	   // Create and set up the window.
       super(CustomerConstants.CUSTOMER_MANAGER_LABEL);

       ImageIcon icon = Utilities.createImageIcon(CustomerConstants.CUSTOMER_MANAGER_ICON_FILENAME, CustomerConstants.CUSTOMER_MANAGER_ICON_DESCRIPTION);
       if(icon != null) {
	       Image image = icon.getImage();
	       setIconImage(image);
       }
       
       // Set up the MVC components
       CustomerUpdateView customerUpdateView = new CustomerUpdateView(customer);    
       new CustomerUpdateController(customerUpdateView, customerDAO);
     
       // Add the UI components to the container
       getContentPane().add(customerUpdateView.getCustomerUpdateScrollPane());
   
       // Set window size and display window
       setMinimumSize(new Dimension(540, 275));
       pack();
       setVisible(true);
	}	
}