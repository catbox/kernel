/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-11-23
 * Project: Opus Dei
 * Package: com.opusdei.controller
 * File: ActionMenuController.java
 * Description: Mediator between the view and the actions
 */
package com.opusdei.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.opusdei.dao.DatabaseConfig;
import com.opusdei.factory.ConnectionFactory;
import com.opusdei.view.ActionsMenu;
import com.opusdei.view.DatabaseDialogView;

public class ActionsMenuController {
	
	private ActionsMenu actionsMenu;
	private DatabaseConfig databaseConfig;
	private ActionsMenuHandler actionsMenuHandler;
	private Frame parent;
	
	public ActionsMenuController(ActionsMenu actionsMenu, Frame frame) {
		
		this.actionsMenu = actionsMenu;
		this.databaseConfig = ConnectionFactory.getDatabaseConfig();
		this.actionsMenuHandler = new ActionsMenuHandler();
		this.actionsMenu.databaseConfigurationListener(actionsMenuHandler);
		this.actionsMenu.menuBarExitListener(actionsMenuHandler);
		parent = frame;
	}

	private class ActionsMenuHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			
			if(event.getSource() == actionsMenu.getDatabaseMenuItem()) {
			
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							// DatabaseView databaseView = new DatabaseView(databaseConfig);
							// The frame owner parent is passed in order to know where to position the Database UI relative to the former
							DatabaseDialogView databaseDialogView = new DatabaseDialogView(parent, databaseConfig);
							new DatabaseController(databaseDialogView);
							databaseDialogView.setVisible(true);
							
							
						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
				});	
			}
			else if(event.getSource() == actionsMenu.getExitMenuItem()) {
				System.exit(0);
			}			
		}		
	}
}
