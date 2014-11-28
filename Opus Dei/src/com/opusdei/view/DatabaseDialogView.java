/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2011-17-01
 * Project: Opus Dei
 * Package: com.opusdei.view
 * File: DatabaseDialogView.java
 * Description: Database Configuration UI
 */

package com.opusdei.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.opusdei.common.StringTools;
import com.opusdei.dao.DatabaseConfig;

public class DatabaseDialogView extends JDialog {

	private static final long serialVersionUID = 1L;
	/**
	 * @uml.property  name="mainPanel"
	 */
	private JPanel mainPanel;
	private JPanel userInfoPanel;
	private JPanel buttonPanel;
	/**
	 * @uml.property  name="serverHostLbl"
	 */
	private JLabel serverHostLbl;
	/**
	 * @uml.property  name="serverHostTxtFld"
	 */
	private JTextField serverHostTxtFld;
	/**
	 * @uml.property  name="userNameTxtFld"
	 */
	private JTextField userNameTxtFld;
	/**
	 * @uml.property  name="portTxtFld"
	 */
	private JTextField portTxtFld;
	/**
	 * @uml.property  name="passwordLbl"
	 */
	private JLabel passwordLbl;
	/**
	 * @uml.property  name="passwordTxtFld"
	 */
	private JPasswordField passwordTxtFld;
	/**
	 * @uml.property  name="defaultSchemaLbl"
	 */
	private JLabel defaultSchemaLbl;
	/**
	 * @uml.property  name="defaultSchemaTxtFld"
	 */
	private JTextField defaultSchemaTxtFld;
	/**
	 * @uml.property  name="testButton"
	 */
	private JButton testButton;
	/**
	 * @uml.property  name="saveButton"
	 */
	private JButton saveButton;
	/**
	 * @uml.property  name="clearButton"
	 */
	private JButton clearButton;
	
	private JButton cancelButton;
	
	/**
	 * @uml.property  name="databaseConfig"
	 * @uml.associationEnd  
	 */
	private DatabaseConfig databaseConfig;
	/**
	 * Create the frame.
	 */
	public DatabaseDialogView(Frame frame, DatabaseConfig databaseConfig) {		
		super(frame, true);
		setTitle("Database Configuration");
		// Get the location of the owner
		Point point = frame.getLocationOnScreen();
		int xPos = (int)point.getX();
		int yPos = (int)point.getY();
		// Reposition the point where the database UI should be displayed
		point = new Point(xPos+100, yPos+150);
		setLocation(point);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(DatabaseDialogView.class.getResource("/images/CustomerIcon.jpeg")));				
		// Main Panel
		mainPanel = new JPanel(new MigLayout("", "[]", "[][]"));
		setContentPane(mainPanel);
		setMinimumSize(new Dimension(400, 275));
		pack();
		
		// User Info Panel
		userInfoPanel = new JPanel();
		userInfoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), 
				BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("Connect to Server Instance"), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(0, 5, 5, 5))));
		
		userInfoPanel.setLayout(new MigLayout("", "[][150!]15[][50!]", "10[]"));
		
		serverHostLbl = new JLabel("Server Host:");
		userInfoPanel.add(serverHostLbl, "cell 0 0,alignx trailing");
		
		serverHostTxtFld = new JTextField();
		serverHostTxtFld.getCaret().setVisible(false);
		userInfoPanel.add(serverHostTxtFld, "cell 1 0,growx");
		serverHostTxtFld.setColumns(20);
		
		JLabel portLbl = new JLabel("Port:");
		userInfoPanel.add(portLbl, "cell 2 0,alignx trailing");
		
		portTxtFld = new JTextField();
		userInfoPanel.add(portTxtFld, "cell 3 0,growx");
		portTxtFld.setColumns(5);
		
		JLabel userNameLbl = new JLabel("Username:");
		userInfoPanel.add(userNameLbl, "cell 0 1,alignx trailing");
		
		userNameTxtFld = new JTextField();
		userInfoPanel.add(userNameTxtFld, "cell 1 1,growx");
		userNameTxtFld.setColumns(10);
		
		passwordLbl = new JLabel("Password:");
		userInfoPanel.add(passwordLbl, "cell 0 3,alignx trailing");
		
		passwordTxtFld = new JPasswordField();
		userInfoPanel.add(passwordTxtFld, "cell 1 3,growx");
		passwordTxtFld.setColumns(10);
		
		defaultSchemaLbl = new JLabel("Default Schema:");
		userInfoPanel.add(defaultSchemaLbl, "cell 0 4,alignx trailing");
		
		defaultSchemaTxtFld = new JTextField();
		userInfoPanel.add(defaultSchemaTxtFld, "cell 1 4,growx, wrap");
		defaultSchemaTxtFld.setColumns(10);
			
		// Button Panel
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new MigLayout("", "[]", "[]"));
		
		testButton = new JButton("Test");
		buttonPanel.add(testButton, "gapleft 5");
		
		saveButton = new JButton("Save");
		saveButton.setEnabled(false);
		buttonPanel.add(saveButton, "gapleft 100");
		
		clearButton = new JButton("Clear");
		buttonPanel.add(clearButton);
		
		cancelButton = new JButton("Cancel");
		buttonPanel.add(cancelButton);
		
		// Add the sub-panels to the main one
		mainPanel.add(userInfoPanel, "wrap");
		mainPanel.add(buttonPanel);
		
		this.databaseConfig = databaseConfig;
		fillTextFields();
					
	}
	
	/**
	 * Fill text fields with default values
	 */
	public void fillTextFields() {	

		serverHostTxtFld.setText(databaseConfig.getHost());
		serverHostTxtFld.getCaret().setVisible(false);
		portTxtFld.setText(databaseConfig.getPort());
		portTxtFld.getCaret().setVisible(false);
		defaultSchemaTxtFld.setText(databaseConfig.getSchema());
		defaultSchemaTxtFld.getCaret().setVisible(false);
		userNameTxtFld.setText(databaseConfig.getUsername());
		userNameTxtFld.getCaret().setVisible(false);
		passwordTxtFld.setText(databaseConfig.getPassword());
		passwordTxtFld.getCaret().setVisible(false);
	}
	
	/**
	 * Button handler
	 */
	public void buttonHandler(ActionListener actionListener) {
		testButton.addActionListener(actionListener);
		saveButton.addActionListener(actionListener);
		cancelButton.addActionListener(actionListener);
		clearButton.addActionListener(actionListener);
	}
	
	/**
	 * @return   the mainPanel
	 * @uml.property  name="mainPanel"
	 */
	public JPanel getMainPanel() {
		return mainPanel;
	}

	/**
	 * @param mainPanel   the mainPanel to set
	 * @uml.property  name="mainPanel"
	 */
	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}

	/**
	 * @return   the serverHostLbl
	 * @uml.property  name="serverHostLbl"
	 */
	public JLabel getServerHostLbl() {
		return serverHostLbl;
	}

	/**
	 * @param serverHostLbl   the serverHostLbl to set
	 * @uml.property  name="serverHostLbl"
	 */
	public void setServerHostLbl(JLabel serverHostLbl) {
		this.serverHostLbl = serverHostLbl;
	}

	/**
	 * @return   the serverHostTxtFld
	 * @uml.property  name="serverHostTxtFld"
	 */
	public JTextField getServerHostTxtFld() {
		return serverHostTxtFld;
	}

	/**
	 * @param serverHostTxtFld   the serverHostTxtFld to set
	 * @uml.property  name="serverHostTxtFld"
	 */
	public void setServerHostTxtFld(JTextField serverHostTxtFld) {
		this.serverHostTxtFld = serverHostTxtFld;
	}

	/**
	 * @return   the userNameTxtFld
	 * @uml.property  name="userNameTxtFld"
	 */
	public JTextField getUserNameTxtFld() {
		return userNameTxtFld;
	}

	/**
	 * @param userNameTxtFld   the userNameTxtFld to set
	 * @uml.property  name="userNameTxtFld"
	 */
	public void setUserNameTxtFld(JTextField userNameTxtFld) {
		this.userNameTxtFld = userNameTxtFld;
	}

	/**
	 * @return   the portTxtFld
	 * @uml.property  name="portTxtFld"
	 */
	public JTextField getPortTxtFld() {
		return portTxtFld;
	}

	/**
	 * @param portTxtFld   the portTxtFld to set
	 * @uml.property  name="portTxtFld"
	 */
	public void setPortTxtFld(JTextField portTxtFld) {
		this.portTxtFld = portTxtFld;
	}

	/**
	 * @return   the passwordLbl
	 * @uml.property  name="passwordLbl"
	 */
	public JLabel getPasswordLbl() {
		return passwordLbl;
	}

	/**
	 * @param passwordLbl   the passwordLbl to set
	 * @uml.property  name="passwordLbl"
	 */
	public void setPasswordLbl(JLabel passwordLbl) {
		this.passwordLbl = passwordLbl;
	}

	/**
	 * @return   the passwordTxtFld
	 * @uml.property  name="passwordTxtFld"
	 */
	public JPasswordField getPasswordTxtFld() {
		return passwordTxtFld;
	}

	/**
	 * @param passwordTxtFld   the passwordTxtFld to set
	 * @uml.property  name="passwordTxtFld"
	 */
	public void setPasswordTxtFld(JPasswordField passwordTxtFld) {
		this.passwordTxtFld = passwordTxtFld;
	}

	/**
	 * @return   the defaultSchemaLbl
	 * @uml.property  name="defaultSchemaLbl"
	 */
	public JLabel getDefaultSchemaLbl() {
		return defaultSchemaLbl;
	}

	/**
	 * @param defaultSchemaLbl   the defaultSchemaLbl to set
	 * @uml.property  name="defaultSchemaLbl"
	 */
	public void setDefaultSchemaLbl(JLabel defaultSchemaLbl) {
		this.defaultSchemaLbl = defaultSchemaLbl;
	}

	/**
	 * @return   the defaultSchemaTxtFld
	 * @uml.property  name="defaultSchemaTxtFld"
	 */
	public JTextField getDefaultSchemaTxtFld() {
		return defaultSchemaTxtFld;
	}

	/**
	 * @param defaultSchemaTxtFld   the defaultSchemaTxtFld to set
	 * @uml.property  name="defaultSchemaTxtFld"
	 */
	public void setDefaultSchemaTxtFld(JTextField defaultSchemaTxtFld) {
		this.defaultSchemaTxtFld = defaultSchemaTxtFld;
	}

	/**
	 * @return   the testButton
	 * @uml.property  name="testButton"
	 */
	public JButton getTestButton() {
		return testButton;
	}

	/**
	 * @param testButton   the testButton to set
	 * @uml.property  name="testButton"
	 */
	public void setTestButton(JButton testButton) {
		this.testButton = testButton;
	}

	/**
	 * @return   the saveButton
	 * @uml.property  name="saveButton"
	 */
	public JButton getSaveButton() {
		return saveButton;
	}

	/**
	 * @param saveButton   the saveButton to set
	 * @uml.property  name="saveButton"
	 */
	public void setSaveButton(JButton okButton) {
		this.saveButton = okButton;
	}

	/**
	 * @return   the clearButton
	 * @uml.property  name="clearButton"
	 */
	public JButton getClearButton() {
		return clearButton;
	}

	/**
	 * @param clearButton   the clearButton to set
	 * @uml.property  name="clearButton"
	 */
	public void setClearButton(JButton clearButton) {
		this.clearButton = clearButton;
	}

	/**
	 * @return the cancelButton
	 */
	public JButton getCancelButton() {
		return cancelButton;
	}

	/**
	 * @param cancelButton the cancelButton to set
	 */
	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}
	
	/**
	 * Clear the text fields
	 */
	public void clear() {
		serverHostTxtFld.setText(StringTools.EMPTY_STRING);	
		portTxtFld.setText(StringTools.EMPTY_STRING);
		defaultSchemaTxtFld.setText(StringTools.EMPTY_STRING);
		userNameTxtFld.setText(StringTools.EMPTY_STRING);
		passwordTxtFld.setText(StringTools.EMPTY_STRING);
	}
	
}
