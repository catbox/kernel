/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2011-17-01
 * Project: Opus Dei
 * Package: com.opusdei.controller
 * File: DatabaseController.java
 * Description: Mediator between the view and the database for connection validation
 */

package com.opusdei.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.opusdei.common.Messages;
import com.opusdei.factory.ConnectionFactory;
import com.opusdei.view.DatabaseDialogView;

public class DatabaseController {
	
	private DatabaseDialogView databaseDialogView;
	private Properties properties = new Properties();
	
	public DatabaseController(DatabaseDialogView databaseView) {
		this.databaseDialogView = databaseView;
		this.databaseDialogView.buttonHandler(new DatabaseConfigurationHandler());
	}
	
	private class DatabaseConfigurationHandler implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			// Get the variables from the UI
			String host = databaseDialogView.getServerHostTxtFld().getText().trim();
			String schema = databaseDialogView.getDefaultSchemaTxtFld().getText().trim();
			String port = databaseDialogView.getPortTxtFld().getText().trim();
			String username = databaseDialogView.getUserNameTxtFld().getText().trim();
			@SuppressWarnings("deprecation")
			String password = databaseDialogView.getPasswordTxtFld().getText().toString().trim();
			
			// Set the property object
			properties.setProperty("host", host);
			properties.setProperty("schema", schema);
			properties.setProperty("port", port);
			properties.setProperty("username", username);
			properties.setProperty("password", password);
			
			if(event.getSource() == databaseDialogView.getTestButton()) {
				try {
					boolean testConnection = ConnectionFactory.testConnection(properties);
					if(testConnection) {
						databaseDialogView.getSaveButton().setEnabled(true);
						JOptionPane.showMessageDialog(databaseDialogView,
								Messages.DB_CON_MSG_001,
								Messages.DB_CON_TITLE,
								JOptionPane.INFORMATION_MESSAGE);
					}
				} 
				catch(Exception e) {
					JOptionPane.showMessageDialog(databaseDialogView,
							Messages.DB_CON_ERROR_001,
							Messages.DB_CON_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}								
			}
			else if(event.getSource() == databaseDialogView.getSaveButton()) {											
				try {
					boolean updateConnection = ConnectionFactory.updateConnection(properties);
					if(updateConnection) {
						databaseDialogView.getSaveButton().setEnabled(false);
						JOptionPane.showMessageDialog(databaseDialogView,
								Messages.DB_CON_MSG_002,
								Messages.DB_CON_TITLE,
								JOptionPane.INFORMATION_MESSAGE);
						databaseDialogView.dispose();
					}			
				} 
				catch(Exception e) {
					JOptionPane.showMessageDialog(databaseDialogView,
							Messages.DB_CON_ERROR_002,
							Messages.DB_CON_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}
			}
			else if(event.getSource() == databaseDialogView.getClearButton()) {														
					databaseDialogView.clear();				
			}
			else if(event.getSource() == databaseDialogView.getCancelButton()) {														
				databaseDialogView.setVisible(false);
				databaseDialogView.dispose();
			}
		}
	}
}
