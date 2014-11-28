/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-06
 * Project: Opus Dei
 * Package: com.opusdei.view
 * File: CustomerQueryView.java
 * Description: UI for querying data
 */
package com.opusdei.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.opusdei.common.CoreConstants;
import com.opusdei.common.StringTools;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.table.model.CustomerTableModel;

import net.miginfocom.swing.MigLayout;

/**
 * @uml.dependency   supplier="com.opusdei.table.model.CustomerTableModel"
 */
public class CustomerQueryView {
	
	private JPanel customerQueryPanel;
	private JLabel customerQueryLabel;	
	private JLabel customerValueLabel;
	private JComboBox customerComboBox;
	private JTextField customerTextField;
	private JButton customerQueryButton;
	private JButton customerClearButton;
	private JButton customerResetButton;
	private JTable customerTable;
	private TableColumnModel customerTableColumModel;
	private JTableHeader customerTableHeader;
	private JScrollPane customerScrollPane;	
	private JPopupMenu customerPopupMenu;
	private JMenuItem customerUpdateMenuItem;
	private JMenuItem customerDeleteMenuItem;
	protected int row;
	protected int column;
	
	protected CustomerTableModel customerTableModel;
	
	/**
	 * Constructor for CustomerView()
	 */
	public CustomerQueryView() {
		
		// UI components
		customerQueryLabel = new JLabel(CoreConstants.QUERY_LABEL);
		customerComboBox = new JComboBox(CustomerConstants.CUSTOMER_QUERY_FIELDS);
		customerValueLabel = new JLabel(CoreConstants.VALUE_LABEL);
		customerTextField = new JTextField(CoreConstants.TEXTFIELD_SIZE);
		customerQueryButton = new JButton(CoreConstants.QUERY_BUTTON);
		customerClearButton = new JButton(CoreConstants.CLEAR_BUTTON);
		customerResetButton = new JButton(CoreConstants.RESET_BUTTON);
		
		// Customer table model
		customerTableModel = new CustomerTableModel();
		
		// Table model
		customerTableColumModel = createTableColumnModel(customerTableModel);
		
		// Table header
		customerTableHeader = new JTableHeader();
		customerTableHeader.setColumnModel(customerTableColumModel);
		customerTableHeader.setReorderingAllowed(false);
		
		// Table
		customerTable = new JTable(customerTableModel, customerTableColumModel);
		customerTable.setTableHeader(customerTableHeader);
		customerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		/**
		 * Sets whether or not this table is always made large enough to fill the height of an enclosing viewport. 
		 * If the preferred height of the table is smaller than the viewport, then the table will be stretched to fill the viewport. 
		 * In other words, this ensures the table is never smaller than the viewport. 
		 * The default for this property is false. 
		 */
		customerTable.setFillsViewportHeight(false);
		customerTable.getColumnModel().getSelectionModel().addListSelectionListener(new RowListener());
		customerTable.getSelectionModel().addListSelectionListener(new ColumnListener());
		
		// Scroll pane
		customerScrollPane = new JScrollPane(customerTable,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Panel
		customerQueryPanel = new JPanel(new MigLayout("", "[grow]", "[][grow][]"));
		customerQueryPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createEmptyBorder(10, 10, 10, 10), BorderFactory.createCompoundBorder(null,
						BorderFactory.createEmptyBorder(0, 5, 5, 5))));
		customerQueryPanel.add(customerQueryLabel, "split 6, gapright 5");
		customerQueryPanel.add(customerComboBox, "gapright 15");
		customerQueryPanel.add(customerValueLabel, "gapright 5");
		customerQueryPanel.add(customerTextField, "gapright 15");
		customerQueryPanel.add(customerQueryButton, "gapright 15");
		customerQueryPanel.add(customerClearButton, "wrap");
		customerQueryPanel.add(customerScrollPane, "grow, span, gaptop 10");	
		customerQueryPanel.add(customerResetButton, "wrap");
		
		// Pop up menu
		customerPopupMenu = new JPopupMenu();
		customerUpdateMenuItem = new JMenuItem("Update");
		customerPopupMenu.add(customerUpdateMenuItem);
		customerDeleteMenuItem = new JMenuItem("Delete");
		customerPopupMenu.add(customerDeleteMenuItem);
	}
	
	/**
	 * Handler for buttons
	 * @param actionListener
	 */
	public void buttonHandler(ActionListener actionListener) {
		customerQueryButton.addActionListener(actionListener);
		customerClearButton.addActionListener(actionListener); 
		customerResetButton.addActionListener(actionListener);
	}
	
	/**
	 * Handler for key actions
	 */
	public void keyHandler(KeyListener keyListener) {
		customerTextField.addKeyListener(keyListener);
	}
	
	/**
	 * Handler for comboBox
	 * @param actionListener
	 */
	public void comboBoxHandler(ActionListener actionListener) {
		customerComboBox.addActionListener(actionListener);
	}
		
	/**
	 * Handler for table event
	 */
	public void customerTableListener(MouseListener mouseListener) {
		customerTable.addMouseListener(mouseListener);
	}

	/**
	 * Handler for pop up menu
	 * @param popupListener
	 */
	public void customerPopupMenuListener(MouseListener mouseListener) {
		customerPopupMenu.addMouseListener(mouseListener);
	}
	
	/**
	 * Handler for menu item actions
	 * @param actionListener
	 */
	public void customerMenuItemActionListener(ActionListener actionListener) {
		customerUpdateMenuItem.addActionListener(actionListener);
		customerDeleteMenuItem.addActionListener(actionListener);
	}
	
	public void customerMenuItemMouseListener(MouseListener mouseListener) {
		customerUpdateMenuItem.addMouseListener(mouseListener);
		customerDeleteMenuItem.addMouseListener(mouseListener);
	}
	
	/**
	 * Create the table column model
	 * @param customerModel
	 * @return defaultTableColumnModel
	 */
	protected TableColumnModel createTableColumnModel(final CustomerTableModel customerModel) {
		DefaultTableColumnModel defaultTableColumnModel = new DefaultTableColumnModel();
		defaultTableColumnModel.setColumnSelectionAllowed(false);
		defaultTableColumnModel.setColumnMargin(CoreConstants.SPACING_SMALL);
		 
		TableColumn col = new TableColumn(0);
		col.setMinWidth(CoreConstants.COLUMN_CHECKBOX_MIN_SIZE);
		col.setPreferredWidth(CoreConstants.COLUMN_CHECKBOX_PREF_SIZE);
		col.setMaxWidth(CoreConstants.COLUMN_CHECKBOX_MAX_SIXE);
		col.setResizable(false);
		col.setHeaderValue(customerModel.getColumnName(0));
		defaultTableColumnModel.addColumn(col);

        for(int i = 1; i < customerModel.getColumnCount(); i++) {
        	col = new TableColumn(i);
        	col.setPreferredWidth(CoreConstants.COLUMN_PREF_SIZE);
        	col.setResizable(true);
    	    col.setHeaderValue(customerModel.getColumnName(i));
    	    defaultTableColumnModel.addColumn(col);
        }
        		
		return defaultTableColumnModel;
	}
	
	public JPopupMenu getPopupMenu() {
		return customerPopupMenu;
	}
		
	/**
	 * Inner class for row listening
	 */
	private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if(event.getValueIsAdjusting()) {
            	return;
            }
        	row = customerTable.getSelectedRow();
        	column = customerTable.getSelectedColumn();   
        }
    }
	
	/**
	 * Inner class for row listening
	 */
	private class ColumnListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			if(event.getValueIsAdjusting()) {
				return;
	        }
			row = customerTable.getSelectedRow();
        	column = customerTable.getSelectedColumn();      
	    }
	}

	/**
	 * @return   the customerPanel
	 * @uml.property  name="customerQueryPanel"
	 */
	public JPanel getCustomerQueryPanel() {
		return customerQueryPanel;
	}

	/**
	 * @param customerPanel   the customerPanel to set
	 * @uml.property  name="customerQueryPanel"
	 */
	public void setCustomerQueryPanel(JPanel customerPanel) {
		this.customerQueryPanel = customerPanel;
	}
	
	/**
	 * @return   the customerQueryLabel
	 * @uml.property  name="customerQueryLabel"
	 */
	public JLabel getCustomerQueryLabel() {
		return customerQueryLabel;
	}

	/**
	 * @param customerQueryLabel   the customerQueryLabel to set
	 * @uml.property  name="customerQueryLabel"
	 */
	public void setCustomerQueryLabel(JLabel customerQueryLabel) {
		this.customerQueryLabel = customerQueryLabel;
	}

	/**
	 * @return   the customerValueLabel
	 * @uml.property  name="customerValueLabel"
	 */
	public JLabel getCustomerValueLabel() {
		return customerValueLabel;
	}

	/**
	 * @param customerValueLabel   the customerValueLabel to set
	 * @uml.property  name="customerValueLabel"
	 */
	public void setCustomerValueLabel(JLabel customerValueLabel) {
		this.customerValueLabel = customerValueLabel;
	}

	/**
	 * @return   the customerTextField
	 * @uml.property  name="customerTextField"
	 */
	public JTextField getCustomerTextField() {
		return customerTextField;
	}

	/**
	 * @param customerTextField   the customerTextField to set
	 * @uml.property  name="customerTextField"
	 */
	public void setCustomerTextField(JTextField customerTextField) {
		this.customerTextField = customerTextField;
	}
	
	/**
	 * clear the text field
	 */
	public void clearValueTextField() {
		this.customerTextField.setText(StringTools.EMPTY_STRING);
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

	/**
	 * @return   the customerScrollPane
	 * @uml.property  name="customerScrollPane"
	 */
	public JScrollPane getCustomerScrollPane() {
		return customerScrollPane;
	}
	
	/**
	 * @param customerScrollPane   the customerScrollPane to set
	 * @uml.property  name="customerScrollPane"
	 */
	public void setCustomerScrollPane(JScrollPane customerScrollPane) {
		this.customerScrollPane = customerScrollPane;
	}

	/**
	 * @return   the row
	 * @uml.property  name="row"
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return   the column
	 * @uml.property  name="column"
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @return   the customerUpdateMenuItem
	 * @uml.property  name="customerUpdateMenuItem"
	 */
	public JMenuItem getCustomerUpdateMenuItem() {
		return customerUpdateMenuItem;
	}

	/**
	 * @param customerUpdateMenuItem   the customerUpdateMenuItem to set
	 * @uml.property  name="customerUpdateMenuItem"
	 */
	public void setCustomerUpdateMenuItem(JMenuItem customerUpdateMenuItem) {
		this.customerUpdateMenuItem = customerUpdateMenuItem;
	}

	/**
	 * @return   the customerDeleteMenuItem
	 * @uml.property  name="customerDeleteMenuItem"
	 */
	public JMenuItem getCustomerDeleteMenuItem() {
		return customerDeleteMenuItem;
	}

	/**
	 * @param customerDeleteMenuItem   the customerDeleteMenuItem to set
	 * @uml.property  name="customerDeleteMenuItem"
	 */
	public void setCustomerDeleteMenuItem(JMenuItem customerDeleteMenuItem) {
		this.customerDeleteMenuItem = customerDeleteMenuItem;
	}

	public CustomerTableModel getCustomerTableModel() {
		return customerTableModel;
	}

	public void setCustomerTableModel(CustomerTableModel customerTableModel) {
		this.customerTableModel = customerTableModel;
	}
	
	public JTable getCustomerTable() {
		return customerTable;
	}
	
}
