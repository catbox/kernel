/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-11-23
 * Project: Opus Dei
 * Package: com.opusdei.view
 * File: CustomerInsertByIdView.java
 * Description: UI for updating data by id
 *
 */
package com.opusdei.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;

import com.jgoodies.validation.view.ValidationComponentUtils;
import com.opusdei.common.CoreConstants;
import com.opusdei.common.StringTools;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.transfer.Customer;

import javax.swing.UIManager;

public class CustomerUpdateByIdView {
		
	/**
	 * @uml.property  name="customerUpdateScrollPane"
	 */
	private JScrollPane customerUpdateScrollPane;
	private JPanel customerUpdateByIdPanel;
	private JPanel customerPersonalInfoPanel;
	private JPanel customerAddressPanel;
	private JPanel customerContactPanel;
	private JPanel customerButtonPanel;
	
	/**
	 * @uml.property  name="customerIdLabel"
	 */
	private JLabel customerIdLabel;
	/**
	 * @uml.property  name="customerNameLabel"
	 */
	private JLabel customerNameLabel;
	/**
	 * @uml.property  name="firstNameLabel"
	 */
	private JLabel firstNameLabel;
	/**
	 * @uml.property  name="middleNameLabel"
	 */
	private JLabel middleNameLabel;
	/**
	 * @uml.property  name="lastNameLabel"
	 */
	private JLabel lastNameLabel;
	/**
	 * @uml.property  name="streetLabel"
	 */
	private JLabel streetLabel;
	/**
	 * @uml.property  name="postalCodeLabel"
	 */
	private JLabel postalCodeLabel;
	/**
	 * @uml.property  name="cityLabel"
	 */
	private JLabel cityLabel;
	/**
	 * @uml.property  name="stateLabel"
	 */
	private JLabel stateLabel;
	/**
	 * @uml.property  name="countryLabel"
	 */
	private JLabel countryLabel;
	/**
	 * @uml.property  name="primaryPhoneNumberLabel"
	 */
	private JLabel primaryPhoneNumberLabel;
	/**
	 * @uml.property  name="secondaryPhoneNumberLabel"
	 */
	private JLabel secondaryPhoneNumberLabel;
	/**
	 * @uml.property  name="faxNumberLabel"
	 */
	private JLabel faxNumberLabel;
	/**
	 * @uml.property  name="emailLabel"
	 */
	private JLabel emailLabel;
	/**
	 * @uml.property  name="websiteLabel"
	 */
	private JLabel websiteLabel;
	
	/**
	 * @uml.property  name="customerIdTextField"
	 */
	private JTextField customerIdTextField;
	/**
	 * @uml.property  name="customerNameTextField"
	 */
	private JTextField customerNameTextField;
	/**
	 * @uml.property  name="firstNameTextField"
	 */
	private JTextField firstNameTextField;
	/**
	 * @uml.property  name="middleNameTextField"
	 */
	private JTextField middleNameTextField;
	/**
	 * @uml.property  name="lastNameTextField"
	 */
	private JTextField lastNameTextField;
	/**
	 * @uml.property  name="streetTextField"
	 */
	private JTextField streetTextField;
	/**
	 * @uml.property  name="postalCodeTextField"
	 */
	private JTextField postalCodeTextField;
	/**
	 * @uml.property  name="cityTextField"
	 */
	private JTextField cityTextField;
	/**
	 * @uml.property  name="stateTextField"
	 */
	private JTextField stateTextField;
	/**
	 * @uml.property  name="countryTextField"
	 */
	private JTextField countryTextField;
	/**
	 * @uml.property  name="primaryPhoneNumberTextField"
	 */
	private JTextField primaryPhoneNumberTextField;
	/**
	 * @uml.property  name="secondaryPhoneNumberTextField"
	 */
	private JTextField secondaryPhoneNumberTextField;
	/**
	 * @uml.property  name="faxNumberTextField"
	 */
	private JTextField faxNumberTextField;
	/**
	 * @uml.property  name="emailTextField"
	 */
	private JTextField emailTextField;
	/**
	 * @uml.property  name="websiteTextField"
	 */
	private JTextField websiteTextField;
	
	private JToggleButton customerUpdateByIdButton;
	/**
	 * @uml.property  name="customerQueryButton"
	 */
	private JButton customerQueryButton;
	/**
	 * @uml.property  name="customerInsertButton"
	 */
	private JButton customerInsertButton;
	/**
	 * @uml.property  name="customerClearButton"
	 */
	private JButton customerClearButton;
	/**
	 * @uml.property  name="customerResetButton"
	 */
	private JButton customerResetButton;
		
	protected final Color TEXTFIELD_DISABLED_BACKGROUND_COLOR = UIManager.getColor("TextField.disabledBackground");
	
	public CustomerUpdateByIdView() {
			
		// UI Components
		customerIdLabel = new JLabel(CustomerConstants.CUSTOMER_ID);
		customerIdTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		customerIdTextField.setEditable(true);
		
		customerNameLabel = new JLabel(CustomerConstants.CUSTOMER_NAME);
		customerNameTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		customerNameTextField.setEditable(false);
		
		firstNameLabel = new JLabel(CustomerConstants.CUSTOMER_FIRSTNAME);
		firstNameTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		firstNameTextField.setEditable(false);
		
		middleNameLabel = new JLabel(CustomerConstants.CUSTOMER_MIDDLENAME);
		middleNameTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		middleNameTextField.setEditable(false);
		
		lastNameLabel = new JLabel(CustomerConstants.CUSTOMER_LASTNAME);
		lastNameTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		lastNameTextField.setEditable(false);
		
		streetLabel = new JLabel(CustomerConstants.CUSTOMER_STREET);
		streetTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		streetTextField.setEditable(false);
		
		postalCodeLabel = new JLabel(CustomerConstants.CUSTOMER_POSTAL_CODE);
		postalCodeTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		postalCodeTextField.setEditable(false);
		
		cityLabel = new JLabel(CustomerConstants.CUSTOMER_CITY);
		cityTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		cityTextField.setEditable(false);
		
		stateLabel = new JLabel(CustomerConstants.CUSTOMER_STATE);
		stateTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		stateTextField.setEditable(false);
		
		countryLabel = new JLabel(CustomerConstants.CUSTOMER_COUNTRY);
		countryTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		countryTextField.setEditable(false);
		
		primaryPhoneNumberLabel = new JLabel(CustomerConstants.CUSTOMER_PRIMARY_PHONE_NUMBER);
		primaryPhoneNumberTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		primaryPhoneNumberTextField.setEditable(false);
		
		secondaryPhoneNumberLabel = new JLabel(CustomerConstants.CUSTOMER_SECONDARY_PHONE_NUMBER);
		secondaryPhoneNumberTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		secondaryPhoneNumberTextField.setEditable(false);
		
		faxNumberLabel = new JLabel(CustomerConstants.CUSTOMER_FAX_NUMBER);
		faxNumberTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		faxNumberTextField.setEditable(false);
		
		emailLabel = new JLabel(CustomerConstants.CUSTOMER_EMAIL);
		emailTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		emailTextField.setEditable(false);
		
		websiteLabel = new JLabel(CustomerConstants.CUSTOMER_WEBSITE);
		websiteTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		websiteTextField.setEditable(false);
		
		customerQueryButton = new JButton(CoreConstants.QUERY_BUTTON);
		customerQueryButton.setEnabled(true);
		customerInsertButton = new JButton(CoreConstants.ADD_BUTTON);
		customerInsertButton.setEnabled(false);
		customerClearButton = new JButton(CoreConstants.CLEAR_BUTTON);
		customerClearButton.setEnabled(false);
		customerResetButton = new JButton(CoreConstants.RESET_BUTTON);
		customerResetButton.setEnabled(false);
		
		// Panel
		customerUpdateByIdPanel = new JPanel(new MigLayout("", "[]", "[][][]"));
	    			    
		customerUpdateByIdPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10), BorderFactory.createCompoundBorder(null,
				BorderFactory.createEmptyBorder(0, 5, 5, 5))));

		// Customer Personal Information Panel
		customerPersonalInfoPanel = new JPanel(new MigLayout("", "25[150!]25[]", "10[]"));
		customerPersonalInfoPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10), BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(CustomerConstants.CUSTOMER_UI_BORDER_LABEL), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(0, 5, 5, 5))));
		
		customerPersonalInfoPanel.add(customerIdLabel, "cell 0 0");
		customerPersonalInfoPanel.add(customerIdTextField, "cell 1 0");
		
		customerPersonalInfoPanel.add(customerNameLabel, "cell 0 1");
		customerPersonalInfoPanel.add(customerNameTextField, "cell 1 1");
		
		customerPersonalInfoPanel.add(firstNameLabel, "cell 0 2");
		customerPersonalInfoPanel.add(firstNameTextField, "cell 1 2");
		
		customerPersonalInfoPanel.add(middleNameLabel, "cell 0 3");
		customerPersonalInfoPanel.add(middleNameTextField, "cell 1 3");
		
		customerPersonalInfoPanel.add(lastNameLabel, "cell 0 4");
		customerPersonalInfoPanel.add(lastNameTextField, "cell 1 4");
		
		// Add Customer Personal Information Panel to the main panel
		customerUpdateByIdPanel.add(customerPersonalInfoPanel, "wrap");
		
		// Customer Address Panel
		customerAddressPanel = new JPanel(new MigLayout("", "25[150!]25[]", "10[]"));
		customerAddressPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10), BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(CustomerConstants.CUSTOMER_UI_BORDER_LABEL_ADDRESS), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(0, 5, 5, 5))));
		
		customerAddressPanel.add(streetLabel, "cell 0 0");
		customerAddressPanel.add(streetTextField, "cell 1 0");
		
		customerAddressPanel.add(postalCodeLabel, "cell 0 1");
		customerAddressPanel.add(postalCodeTextField, "cell 1 1");
		
		customerAddressPanel.add(cityLabel, "cell 0 2");
		customerAddressPanel.add(cityTextField, "cell 1 2");
		
		customerAddressPanel.add(stateLabel, "cell 0 3");
		customerAddressPanel.add(stateTextField, "cell 1 3");
		
		customerAddressPanel.add(countryLabel, "cell 0 4");
		customerAddressPanel.add(countryTextField, "cell 1 4");
		
		// Add Customer Address Panel to the main panel
		customerUpdateByIdPanel.add(customerAddressPanel, "wrap");
		
		// Customer Contact Panel
		customerContactPanel = new JPanel(new MigLayout("", "25[150!]25[]", "10[]"));
		customerContactPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10), BorderFactory.createCompoundBorder(BorderFactory
				.createTitledBorder(CustomerConstants.CUSTOMER_UI_BORDER_LABEL_CONTACT), //$NON-NLS-1$
				BorderFactory.createEmptyBorder(0, 5, 5, 5))));
	
		customerContactPanel.add(primaryPhoneNumberLabel, "cell 0 0");
		customerContactPanel.add(primaryPhoneNumberTextField, "cell 1 0");
		
		customerContactPanel.add(secondaryPhoneNumberLabel, "cell 0 1");
		customerContactPanel.add(secondaryPhoneNumberTextField, "cell 1 1");
		
		customerContactPanel.add(faxNumberLabel, "cell 0 2");
		customerContactPanel.add(faxNumberTextField, "cell 1 2");
		
		customerContactPanel.add(emailLabel, "cell 0 3");
		customerContactPanel.add(emailTextField, "cell 1 3");
		
		customerContactPanel.add(websiteLabel, "cell 0 4");
		customerContactPanel.add(websiteTextField, "cell 1 4");
		
		// Add Customer Address Panel to the main panel
		customerUpdateByIdPanel.add(customerContactPanel, "wrap");
	
		// Buttons
		customerButtonPanel = new JPanel(new MigLayout("", "[]", "[]"));
		customerButtonPanel.add(customerQueryButton, CustomerConstants.CUSTOMER_BUTTON_GAP_RIGHT);
		customerButtonPanel.add(customerInsertButton, CustomerConstants.CUSTOMER_BUTTON_GAP_RIGHT);
		customerButtonPanel.add(customerClearButton, CustomerConstants.CUSTOMER_BUTTON_GAP_RIGHT);
		customerButtonPanel.add(customerResetButton);
		
		// Add Customer Button Panel to the main panel
		customerUpdateByIdPanel.add(customerButtonPanel);
		
		// Add the main panel into a scroll pane fore scrolling purposes
		customerUpdateScrollPane = new JScrollPane(customerUpdateByIdPanel,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		customerUpdateScrollPane.setWheelScrollingEnabled(true);
	}	

	/**
	 * Enable fields and buttons for editing
	 */
	public void enableEditing(boolean edit) {
		customerNameTextField.setEditable(edit);
		firstNameTextField.setEditable(edit);
		middleNameTextField.setEditable(edit);
		lastNameTextField.setEditable(edit);
		streetTextField.setEditable(edit);
		postalCodeTextField.setEditable(edit);
		cityTextField.setEditable(edit);
		stateTextField.setEditable(edit);
		countryTextField.setEditable(edit);
		primaryPhoneNumberTextField.setEditable(edit);
		secondaryPhoneNumberTextField.setEditable(edit);
		faxNumberTextField.setEditable(edit);
		emailTextField.setEditable(edit);
		websiteTextField.setEditable(edit);
		
		customerInsertButton.setEnabled(edit);
		customerClearButton.setEnabled(edit);
		customerResetButton.setEnabled(edit);
		
		if(!edit) {
			customerNameTextField.getCaret().setVisible(false);
			firstNameTextField.getCaret().setVisible(false);
			middleNameTextField.getCaret().setVisible(false);
			lastNameTextField.getCaret().setVisible(false);
			streetTextField.getCaret().setVisible(false);
			postalCodeTextField.getCaret().setVisible(false);
			cityTextField.getCaret().setVisible(false);
			stateTextField.getCaret().setVisible(false);
			countryTextField.getCaret().setVisible(false);
			primaryPhoneNumberTextField.getCaret().setVisible(false);
			secondaryPhoneNumberTextField.getCaret().setVisible(false);
			faxNumberTextField.getCaret().setVisible(false);
			emailTextField.getCaret().setVisible(false);
			websiteTextField.getCaret().setVisible(false);
		}
	}
	
	/**
	 * Set the mandatory fields
	 */
	public void setMandatoryFields(boolean edit) {	
		if(edit){
			ValidationComponentUtils.setMandatory(customerIdTextField, true);
			ValidationComponentUtils.setMandatory(customerNameTextField, true);
			ValidationComponentUtils.setMandatory(firstNameTextField, true);
			ValidationComponentUtils.setMandatory(lastNameTextField, true);
			ValidationComponentUtils.setMandatory(primaryPhoneNumberTextField, true);
			ValidationComponentUtils.setMandatory(emailTextField, true);
			ValidationComponentUtils.updateComponentTreeMandatoryBackground(customerUpdateByIdPanel);
		}
		else {
			customerNameTextField.setBackground(TEXTFIELD_DISABLED_BACKGROUND_COLOR);
			firstNameTextField.setBackground(TEXTFIELD_DISABLED_BACKGROUND_COLOR);
			lastNameTextField.setBackground(TEXTFIELD_DISABLED_BACKGROUND_COLOR);
			primaryPhoneNumberTextField.setBackground(TEXTFIELD_DISABLED_BACKGROUND_COLOR);
			emailTextField.setBackground(TEXTFIELD_DISABLED_BACKGROUND_COLOR);	
		}				
	}
	
	/**
	 * Set the fields with the data that was queried
	 * @param customer
	 */
	public void setFields(Customer customer) {
		
		setMandatoryFields(true);
		
		customerIdTextField.setEditable(false);
		customerIdTextField.getCaret().setVisible(false);
		
		customerNameTextField.setText(customer.getCustomerName());
		customerNameTextField.setEditable(true);
		
		firstNameTextField.setText(customer.getFirstName());
		firstNameTextField.setEditable(true);

		middleNameTextField.setText(customer.getMiddleName());
		middleNameTextField.setEditable(true);

		lastNameTextField.setText(customer.getLastName());
		lastNameTextField.setEditable(true);

		streetTextField.setText(customer.getStreet());
		streetTextField.setEditable(true);

		postalCodeTextField.setText(customer.getPostalCode());
		postalCodeTextField.setEditable(true);

		cityTextField.setText(customer.getCity());
		cityTextField.setEditable(true);

		stateTextField.setText(customer.getState());
		stateTextField.setEditable(true);

		countryTextField.setText(customer.getCountry());
		countryTextField.setEditable(true);

		primaryPhoneNumberTextField.setText(customer.getPrimaryPhoneNumber());
		primaryPhoneNumberTextField.setEditable(true);

		secondaryPhoneNumberTextField.setText(customer.getSecondaryPhoneNumber());
		secondaryPhoneNumberTextField.setEditable(true);

		faxNumberTextField.setText(customer.getFaxNumber());
		faxNumberTextField.setEditable(true);

		emailTextField.setText(customer.getEmail());
		emailTextField.setEditable(true);

		websiteTextField.setText(customer.getWebsite());
		websiteTextField.setEditable(true);
	}
	
	/**
	 * Clear all the fields excluding the id field
	 */
	public void clearFields() {
		
		customerNameTextField.setText(StringTools.EMPTY_STRING);
		customerNameTextField.setEditable(true);
		
		firstNameTextField.setText(StringTools.EMPTY_STRING);
		firstNameTextField.setEditable(true);

		middleNameTextField.setText(StringTools.EMPTY_STRING);
		middleNameTextField.setEditable(true);

		lastNameTextField.setText(StringTools.EMPTY_STRING);
		lastNameTextField.setEditable(true);

		streetTextField.setText(StringTools.EMPTY_STRING);
		streetTextField.setEditable(true);

		postalCodeTextField.setText(StringTools.EMPTY_STRING);
		postalCodeTextField.setEditable(true);

		cityTextField.setText(StringTools.EMPTY_STRING);
		cityTextField.setEditable(true);

		stateTextField.setText(StringTools.EMPTY_STRING);
		stateTextField.setEditable(true);

		countryTextField.setText(StringTools.EMPTY_STRING);
		countryTextField.setEditable(true);

		primaryPhoneNumberTextField.setText(StringTools.EMPTY_STRING);
		primaryPhoneNumberTextField.setEditable(true);

		secondaryPhoneNumberTextField.setText(StringTools.EMPTY_STRING);
		secondaryPhoneNumberTextField.setEditable(true);

		faxNumberTextField.setText(StringTools.EMPTY_STRING);
		faxNumberTextField.setEditable(true);

		emailTextField.setText(StringTools.EMPTY_STRING);
		emailTextField.setEditable(true);

		websiteTextField.setText(StringTools.EMPTY_STRING);
		websiteTextField.setEditable(true);
	}
	
	/**
	 * Clear all the fields including the id field
	 */
	public void resetFields() {
		
		setMandatoryFields(false);
		
		customerIdTextField.setText(StringTools.EMPTY_STRING);
		customerIdTextField.setEditable(true);
		customerIdTextField.setBackground(Color.WHITE);
		
		customerNameTextField.setText(StringTools.EMPTY_STRING);
		customerNameTextField.setEditable(false);
		
		firstNameTextField.setText(StringTools.EMPTY_STRING);
		firstNameTextField.setEditable(false);

		middleNameTextField.setText(StringTools.EMPTY_STRING);
		middleNameTextField.setEditable(false);

		lastNameTextField.setText(StringTools.EMPTY_STRING);
		lastNameTextField.setEditable(false);

		streetTextField.setText(StringTools.EMPTY_STRING);
		streetTextField.setEditable(false);

		postalCodeTextField.setText(StringTools.EMPTY_STRING);
		postalCodeTextField.setEditable(false);

		cityTextField.setText(StringTools.EMPTY_STRING);
		cityTextField.setEditable(false);

		stateTextField.setText(StringTools.EMPTY_STRING);
		stateTextField.setEditable(false);

		countryTextField.setText(StringTools.EMPTY_STRING);
		countryTextField.setEditable(false);

		primaryPhoneNumberTextField.setText(StringTools.EMPTY_STRING);
		primaryPhoneNumberTextField.setEditable(false);

		secondaryPhoneNumberTextField.setText(StringTools.EMPTY_STRING);
		secondaryPhoneNumberTextField.setEditable(false);

		faxNumberTextField.setText(StringTools.EMPTY_STRING);
		faxNumberTextField.setEditable(false);

		emailTextField.setText(StringTools.EMPTY_STRING);
		emailTextField.setEditable(false);

		websiteTextField.setText(StringTools.EMPTY_STRING);
		websiteTextField.setEditable(false);
	}
		
	/**
	 * Enable the disabled buttons
	 */
	public void enableButtons() {
		customerInsertButton.setEnabled(true);
		customerClearButton.setEnabled(true);
		customerResetButton.setEnabled(true);
	}
	
	/**
	 * Handler for buttons
	 * @param actionListener
	 */
	public void buttonHandler(ActionListener actionListener) {
		customerQueryButton.addActionListener(actionListener);
		customerInsertButton.addActionListener(actionListener);
		customerClearButton.addActionListener(actionListener);
		customerResetButton.addActionListener(actionListener);
	}
		
	public void mouseHandler(MouseListener mouseListener) {
		customerUpdateByIdButton.addMouseListener(mouseListener);	
	}
	
	/**
	 * Handler for key actions
	 */
	public void keyHandler(KeyListener keyListener) {
		customerIdTextField.addKeyListener(keyListener);
	}
	
	/**
	 * @return   the customerUpdateScrollPane
	 * @uml.property  name="customerUpdateScrollPane"
	 */
	public JScrollPane getCustomerUpdateScrollPane() {
		return customerUpdateScrollPane;
	}

	/**
	 * @param customerUpdateScrollPane   the customerUpdateScrollPane to set
	 * @uml.property  name="customerUpdateScrollPane"
	 */
	public void setCustomerUpdateScrollPane(JScrollPane customerUpdateScrollPane) {
		this.customerUpdateScrollPane = customerUpdateScrollPane;
	}

	/**
	 * @return the customerUpdateByIdPanel
	 */
	public JPanel getCustomerUpdatePanel() {
		return customerUpdateByIdPanel;
	}
	
	/**
	 * @param customerUpdateByIdPanel the customerUpdateByIdPanel to set
	 */
	public void setCustomerUpdatePanel(JPanel customerUpdatePanel) {
		this.customerUpdateByIdPanel = customerUpdatePanel;
	}

	/**
	 * @return   the customerIdLabel
	 * @uml.property  name="customerIdLabel"
	 */
	public JLabel getCustomerIdLabel() {
		return customerIdLabel;
	}

	/**
	 * @param customerIdLabel   the customerIdLabel to set
	 * @uml.property  name="customerIdLabel"
	 */
	public void setCustomerIdLabel(JLabel customerIdLabel) {
		this.customerIdLabel = customerIdLabel;
	}

	/**
	 * @return   the customerIdTextField
	 * @uml.property  name="customerIdTextField"
	 */
	public JTextField getCustomerIdTextField() {
		return customerIdTextField;
	}

	/**
	 * @param customerIdTextField   the customerIdTextField to set
	 * @uml.property  name="customerIdTextField"
	 */
	public void setCustomerIdTextField(JTextField customerIdTextField) {
		this.customerIdTextField = customerIdTextField;
	}

	/**
	 * @return   the customerNameLabel
	 * @uml.property  name="customerNameLabel"
	 */
	public JLabel getCustomerNameLabel() {
		return customerNameLabel;
	}

	/**
	 * @param customerNameLabel   the customerNameLabel to set
	 * @uml.property  name="customerNameLabel"
	 */
	public void setCustomerNameLabel(JLabel customerNameLabel) {
		this.customerNameLabel = customerNameLabel;
	}

	/**
	 * @return   the firstNameLabel
	 * @uml.property  name="firstNameLabel"
	 */
	public JLabel getFirstNameLabel() {
		return firstNameLabel;
	}

	/**
	 * @param firstNameLabel   the firstNameLabel to set
	 * @uml.property  name="firstNameLabel"
	 */
	public void setFirstNameLabel(JLabel firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	/**
	 * @return   the middleNameLabel
	 * @uml.property  name="middleNameLabel"
	 */
	public JLabel getMiddleNameLabel() {
		return middleNameLabel;
	}

	/**
	 * @param middleNameLabel   the middleNameLabel to set
	 * @uml.property  name="middleNameLabel"
	 */
	public void setMiddleNameLabel(JLabel middleNameLabel) {
		this.middleNameLabel = middleNameLabel;
	}

	/**
	 * @return   the lastNameLabel
	 * @uml.property  name="lastNameLabel"
	 */
	public JLabel getLastNameLabel() {
		return lastNameLabel;
	}

	/**
	 * @param lastNameLabel   the lastNameLabel to set
	 * @uml.property  name="lastNameLabel"
	 */
	public void setLastNameLabel(JLabel lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	/**
	 * @return   the streetLabel
	 * @uml.property  name="streetLabel"
	 */
	public JLabel getStreetLabel() {
		return streetLabel;
	}

	/**
	 * @param streetLabel   the streetLabel to set
	 * @uml.property  name="streetLabel"
	 */
	public void setStreetLabel(JLabel streetLabel) {
		this.streetLabel = streetLabel;
	}

	/**
	 * @return   the postalCodeLabel
	 * @uml.property  name="postalCodeLabel"
	 */
	public JLabel getPostalCodeLabel() {
		return postalCodeLabel;
	}

	/**
	 * @param postalCodeLabel   the postalCodeLabel to set
	 * @uml.property  name="postalCodeLabel"
	 */
	public void setPostalCodeLabel(JLabel postalCodeLabel) {
		this.postalCodeLabel = postalCodeLabel;
	}

	/**
	 * @return   the cityLabel
	 * @uml.property  name="cityLabel"
	 */
	public JLabel getCityLabel() {
		return cityLabel;
	}

	/**
	 * @param cityLabel   the cityLabel to set
	 * @uml.property  name="cityLabel"
	 */
	public void setCityLabel(JLabel cityLabel) {
		this.cityLabel = cityLabel;
	}

	/**
	 * @return   the stateLabel
	 * @uml.property  name="stateLabel"
	 */
	public JLabel getStateLabel() {
		return stateLabel;
	}

	/**
	 * @param stateLabel   the stateLabel to set
	 * @uml.property  name="stateLabel"
	 */
	public void setStateLabel(JLabel stateLabel) {
		this.stateLabel = stateLabel;
	}

	/**
	 * @return   the countryLabel
	 * @uml.property  name="countryLabel"
	 */
	public JLabel getCountryLabel() {
		return countryLabel;
	}

	/**
	 * @param countryLabel   the countryLabel to set
	 * @uml.property  name="countryLabel"
	 */
	public void setCountryLabel(JLabel countryLabel) {
		this.countryLabel = countryLabel;
	}

	/**
	 * @return   the primaryPhoneNumberLabel
	 * @uml.property  name="primaryPhoneNumberLabel"
	 */
	public JLabel getPrimaryPhoneNumberLabel() {
		return primaryPhoneNumberLabel;
	}

	/**
	 * @param primaryPhoneNumberLabel   the primaryPhoneNumberLabel to set
	 * @uml.property  name="primaryPhoneNumberLabel"
	 */
	public void setPrimaryPhoneNumberLabel(JLabel primaryPhoneNumberLabel) {
		this.primaryPhoneNumberLabel = primaryPhoneNumberLabel;
	}

	/**
	 * @return   the secondaryPhoneNumberLabel
	 * @uml.property  name="secondaryPhoneNumberLabel"
	 */
	public JLabel getSecondaryPhoneNumberLabel() {
		return secondaryPhoneNumberLabel;
	}

	/**
	 * @param secondaryPhoneNumberLabel   the secondaryPhoneNumberLabel to set
	 * @uml.property  name="secondaryPhoneNumberLabel"
	 */
	public void setSecondaryPhoneNumberLabel(JLabel secondaryPhoneNumberLabel) {
		this.secondaryPhoneNumberLabel = secondaryPhoneNumberLabel;
	}

	/**
	 * @return   the emailLabel
	 * @uml.property  name="emailLabel"
	 */
	public JLabel getEmailLabel() {
		return emailLabel;
	}

	/**
	 * @param emailLabel   the emailLabel to set
	 * @uml.property  name="emailLabel"
	 */
	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}

	/**
	 * @return   the websiteLabel
	 * @uml.property  name="websiteLabel"
	 */
	public JLabel getWebsiteLabel() {
		return websiteLabel;
	}

	/**
	 * @param websiteLabel   the websiteLabel to set
	 * @uml.property  name="websiteLabel"
	 */
	public void setWebsiteLabel(JLabel websiteLabel) {
		this.websiteLabel = websiteLabel;
	}

	/**
	 * @return   the customerNameTextField
	 * @uml.property  name="customerNameTextField"
	 */
	public JTextField getCustomerNameTextField() {
		return customerNameTextField;
	}

	/**
	 * @param customerNameTextField   the customerNameTextField to set
	 * @uml.property  name="customerNameTextField"
	 */
	public void setCustomerNameTextField(JTextField customerNameTextField) {
		this.customerNameTextField = customerNameTextField;
	}

	/**
	 * @return   the firstNameTextField
	 * @uml.property  name="firstNameTextField"
	 */
	public JTextField getFirstNameTextField() {
		return firstNameTextField;
	}

	/**
	 * @param firstNameTextField   the firstNameTextField to set
	 * @uml.property  name="firstNameTextField"
	 */
	public void setFirstNameTextField(JTextField firstNameTextField) {
		this.firstNameTextField = firstNameTextField;
	}

	/**
	 * @return   the middleNameTextField
	 * @uml.property  name="middleNameTextField"
	 */
	public JTextField getMiddleNameTextField() {
		return middleNameTextField;
	}

	/**
	 * @param middleNameTextField   the middleNameTextField to set
	 * @uml.property  name="middleNameTextField"
	 */
	public void setMiddleNameTextField(JTextField middleNameTextField) {
		this.middleNameTextField = middleNameTextField;
	}

	/**
	 * @return   the lastNameTextField
	 * @uml.property  name="lastNameTextField"
	 */
	public JTextField getLastNameTextField() {
		return lastNameTextField;
	}

	/**
	 * @param lastNameTextField   the lastNameTextField to set
	 * @uml.property  name="lastNameTextField"
	 */
	public void setLastNameTextField(JTextField lastNameTextField) {
		this.lastNameTextField = lastNameTextField;
	}

	/**
	 * @return   the streetTextField
	 * @uml.property  name="streetTextField"
	 */
	public JTextField getStreetTextField() {
		return streetTextField;
	}

	/**
	 * @param streetTextField   the streetTextField to set
	 * @uml.property  name="streetTextField"
	 */
	public void setStreetTextField(JTextField streetTextField) {
		this.streetTextField = streetTextField;
	}

	/**
	 * @return   the postalCodeTextField
	 * @uml.property  name="postalCodeTextField"
	 */
	public JTextField getPostalCodeTextField() {
		return postalCodeTextField;
	}

	/**
	 * @param postalCodeTextField   the postalCodeTextField to set
	 * @uml.property  name="postalCodeTextField"
	 */
	public void setPostalCodeTextField(JTextField postalCodeTextField) {
		this.postalCodeTextField = postalCodeTextField;
	}

	/**
	 * @return   the cityTextField
	 * @uml.property  name="cityTextField"
	 */
	public JTextField getCityTextField() {
		return cityTextField;
	}

	/**
	 * @param cityTextField   the cityTextField to set
	 * @uml.property  name="cityTextField"
	 */
	public void setCityTextField(JTextField cityTextField) {
		this.cityTextField = cityTextField;
	}

	/**
	 * @return   the stateTextField
	 * @uml.property  name="stateTextField"
	 */
	public JTextField getStateTextField() {
		return stateTextField;
	}

	/**
	 * @param stateTextField   the stateTextField to set
	 * @uml.property  name="stateTextField"
	 */
	public void setStateTextField(JTextField stateTextField) {
		this.stateTextField = stateTextField;
	}

	/**
	 * @return   the countryTextField
	 * @uml.property  name="countryTextField"
	 */
	public JTextField getCountryTextField() {
		return countryTextField;
	}

	/**
	 * @param countryTextField   the countryTextField to set
	 * @uml.property  name="countryTextField"
	 */
	public void setCountryTextField(JTextField countryTextField) {
		this.countryTextField = countryTextField;
	}

	/**
	 * @return   the primaryPhoneNumberTextField
	 * @uml.property  name="primaryPhoneNumberTextField"
	 */
	public JTextField getPrimaryPhoneNumberTextField() {
		return primaryPhoneNumberTextField;
	}

	/**
	 * @param primaryPhoneNumberTextField   the primaryPhoneNumberTextField to set
	 * @uml.property  name="primaryPhoneNumberTextField"
	 */
	public void setPrimaryPhoneNumberTextField(
			JTextField primaryPhoneNumberTextField) {
		this.primaryPhoneNumberTextField = primaryPhoneNumberTextField;
	}

	/**
	 * @return   the secondaryPhoneNumberTextField
	 * @uml.property  name="secondaryPhoneNumberTextField"
	 */
	public JTextField getSecondaryPhoneNumberTextField() {
		return secondaryPhoneNumberTextField;
	}

	/**
	 * @param secondaryPhoneNumberTextField   the secondaryPhoneNumberTextField to set
	 * @uml.property  name="secondaryPhoneNumberTextField"
	 */
	public void setSecondaryPhoneNumberTextField(JTextField secondaryPhoneNumberTextField) {
		this.secondaryPhoneNumberTextField = secondaryPhoneNumberTextField;
	}

	/**
	 * @return   the faxNumberLabel
	 * @uml.property  name="faxNumberLabel"
	 */
	public JLabel getFaxNumberLabel() {
		return faxNumberLabel;
	}

	/**
	 * @param faxNumberLabel   the faxNumberLabel to set
	 * @uml.property  name="faxNumberLabel"
	 */
	public void setFaxNumberLabel(JLabel faxNumberLabel) {
		this.faxNumberLabel = faxNumberLabel;
	}

	/**
	 * @return   the faxNumberTextField
	 * @uml.property  name="faxNumberTextField"
	 */
	public JTextField getFaxNumberTextField() {
		return faxNumberTextField;
	}

	/**
	 * @param faxNumberTextField   the faxNumberTextField to set
	 * @uml.property  name="faxNumberTextField"
	 */
	public void setFaxNumberTextField(JTextField faxNumberTextField) {
		this.faxNumberTextField = faxNumberTextField;
	}

	/**
	 * @return   the emailTextField
	 * @uml.property  name="emailTextField"
	 */
	public JTextField getEmailTextField() {
		return emailTextField;
	}

	/**
	 * @param emailTextField   the emailTextField to set
	 * @uml.property  name="emailTextField"
	 */
	public void setEmailTextField(JTextField emailTextField) {
		this.emailTextField = emailTextField;
	}

	/**
	 * @return   the websiteTextField
	 * @uml.property  name="websiteTextField"
	 */
	public JTextField getWebsiteTextField() {
		return websiteTextField;
	}

	/**
	 * @param websiteTextField   the websiteTextField to set
	 * @uml.property  name="websiteTextField"
	 */
	public void setWebsiteTextField(JTextField websiteTextField) {
		this.websiteTextField = websiteTextField;
	}

	/**
	 * @return the customerUpdateByIdButton
	 */
	public JToggleButton getCustomerUpdateButton() {
		return customerUpdateByIdButton;
	}

	/**
	 * @param customerUpdateByIdButton the customerUpdateByIdButton to set
	 */
	public void setCustomerUpdateButton(JToggleButton customerUpdateButton) {
		this.customerUpdateByIdButton = customerUpdateButton;
	}
	
	/**
	 * @return   the customerQueryButton
	 * @uml.property  name="customerQueryButton"
	 */
	public JButton getCustomerQueryButton() {
		return customerQueryButton;
	}

	/**
	 * @param customerQueryButton   the customerQueryButton to set
	 * @uml.property  name="customerQueryButton"
	 */
	public void setCustomerQueryButton(JButton customerQueryButton) {
		this.customerQueryButton = customerQueryButton;
	}

	/**
	 * @return   the customerInsertButton
	 * @uml.property  name="customerInsertButton"
	 */
	public JButton getCustomerInsertButton() {
		return customerInsertButton;
	}

	/**
	 * @param customerInsertButton   the customerInsertButton to set
	 * @uml.property  name="customerInsertButton"
	 */
	public void setCustomerInsertButton(JButton customerInsertButton) {
		this.customerInsertButton = customerInsertButton;
	}

	/**
	 * @return   the customerClearButton
	 * @uml.property  name="customerClearButton"
	 */
	public JButton getCustomerClearButton() {
		return customerClearButton;
	}

	/**
	 * @param customerClearButton   the customerClearButton to set
	 * @uml.property  name="customerClearButton"
	 */
	public void setCustomerClearButton(JButton customerClearButton) {
		this.customerClearButton = customerClearButton;
	}

	/**
	 * @return   the customerResetButton
	 * @uml.property  name="customerResetButton"
	 */
	public JButton getCustomerResetButton() {
		return customerResetButton;
	}

	/**
	 * @param customerResetButton   the customerResetButton to set
	 * @uml.property  name="customerResetButton"
	 */
	public void setCustomerResetButton(JButton customerResetButton) {
		this.customerResetButton = customerResetButton;
	}

}
