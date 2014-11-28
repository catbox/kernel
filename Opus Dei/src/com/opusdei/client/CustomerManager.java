/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-09-27
 * Project: Opus Dei
 * Package: com.opusdei.client
 * File: CustomerManager.java
 * Description: handles the customer insert, query, update and delete UI
 */
package com.opusdei.client;

import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

import com.opusdei.common.Messages;
import com.opusdei.common.Utilities;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.controller.ActionsMenuController;
import com.opusdei.controller.CustomerQueryController;
import com.opusdei.controller.CustomerInsertController;
import com.opusdei.controller.CustomerUpdateByIdController;
import com.opusdei.dao.CustomerDAO;
import com.opusdei.factory.ConnectionFactory;
import com.opusdei.factory.DAOFactory;
import com.opusdei.observer.ConnectionObserver;
import com.opusdei.observer.CustomerMenuObserver;
import com.opusdei.view.ActionsMenu;
import com.opusdei.view.CustomerInsertView;
import com.opusdei.view.CustomerQueryView;
import com.opusdei.view.CustomerUpdateByIdView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class CustomerManager {
	
	private DAOFactory factory;
	private CustomerDAO customerDAO;
	private JMenuBar menubar;
	private JPanel tabbedPanePanel;
	private JTabbedPane tabbedPane;
	private ActionsMenu actionsMenu;
	   
    private JComponent insertCustomerPanel;
    private JComponent queryCustomerPanel;
    private JComponent modifyCustomerPanel;   
    
    private CustomerMenuObserver customerMenuObserver;
    private ConnectionObserver connectionObserver;
   
    public CustomerManager() {    	
    	JFrame frame = new JFrame("Customer Manager"); 
    	
    	// Create a connection observer to watch for the connection setting update
		connectionObserver = new ConnectionObserver();	
    	
		// Connection Factory
		try {   		
    		ConnectionFactory.createConnection();
    		ConnectionFactory.registerConnectionObserver(connectionObserver);
    		
		}
    	catch(SQLException sqlException) {
    		JOptionPane.showMessageDialog(null,
					Messages.DB_CON_ERROR_001 + " " + sqlException.getMessage(),
					Messages.DB_CON_TITLE,
					JOptionPane.ERROR_MESSAGE);
		}
    	catch(Exception exception) {
    		JOptionPane.showMessageDialog(null,
					exception.getMessage(),
					Messages.DB_CON_TITLE,
					JOptionPane.ERROR_MESSAGE);
		}	
    	   	
    	// DAO Factory
		factory = new DAOFactory();
		
		try {
			// Customer DAO
			customerDAO = factory.getCustomerDAO();			
			// Register the DAOs
			connectionObserver.registerCustomerDAO(customerDAO);
		} 
		catch(SQLException sqlException) {
			JOptionPane.showMessageDialog(null, Messages.BD_QUERY_ERROR + " " + sqlException.getMessage(),
			Messages.DB_CON_TITLE, JOptionPane.ERROR_MESSAGE);
		}	
    	
    	// Title bar icon
    	ImageIcon frameIcon = Utilities.createImageIcon(CustomerConstants.CUSTOMER_MANAGER_ICON_FILENAME, CustomerConstants.CUSTOMER_MANAGER_ICON_DESCRIPTION);
        if(frameIcon != null) {
        	Image image = frameIcon.getImage();
        	frame.setIconImage(image);
        }
        
        // Set up the menu bar
    	actionsMenu = new ActionsMenu();
    	// The frame owner parent (this) is passed in order to know where to position the Database UI relative to the former
    	new ActionsMenuController(actionsMenu, frame);
    	menubar = actionsMenu.getMenuBar();
    	frame.setJMenuBar(menubar);
    	
    	// Menu bar observer
    	customerMenuObserver = new CustomerMenuObserver();
    	customerMenuObserver.setInsertCustomerPanelFlag(true);
    	
    	// Default settings for menu bar
    	actionsMenu.getSaveMenuItem().setEnabled(true);
	    actionsMenu.getQueryMenuItem().setEnabled(false);
	    actionsMenu.getDeleteMenuItem().setEnabled(false);
	    actionsMenu.getClearMenuItem().setEnabled(true);
	    actionsMenu.getResetMenuItem().setEnabled(false);
	    
    	tabbedPanePanel = new JPanel(new MigLayout("", "[grow]", "[grow]"));

        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.LIGHT_GRAY);
        
        // Insert UI
        insertCustomerPanel = createInsertCustomerPanel();
        tabbedPane.addTab("New", null, insertCustomerPanel, "Create a new customer - key tip: Alt+N");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_N);

        // Query UI
        queryCustomerPanel = createQueryCustomerPanel();
        tabbedPane.addTab("Find", null, queryCustomerPanel, "Find a customer - key tip: Alt+F");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_F);
        
        // Update UI
        modifyCustomerPanel = createModifyCustomerPanel();
        tabbedPane.addTab("Modify", null, modifyCustomerPanel, "Modify a customer - key tip: Alt+M");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_M);
             
        // Enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
        // Track the change of tabs
        tabbedPane.addChangeListener(new TabHandler());
        
        // Add the tabbed pane to this panel.
        tabbedPanePanel.add(tabbedPane, "grow");
        tabbedPanePanel.setAutoscrolls(true);
        new JScrollPane(tabbedPanePanel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
					    ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add the UI components to the container
        frame.setContentPane(tabbedPane);
      
        // Window size setting
        frame.setMinimumSize(new Dimension(560, 275));
        frame.setSize(new Dimension(600, 850));
        //frame.pack();
        frame.setVisible(true);
    }
   
    // Create Insert UI
    protected JComponent createInsertCustomerPanel() {  	
        CustomerInsertView customerInsertView = new CustomerInsertView();           
		new CustomerInsertController(customerInsertView, customerDAO, actionsMenu, customerMenuObserver);
        JPanel panel = new JPanel();
        panel.add(customerInsertView.getCustomerInsertScrollPane());    
        panel.setLayout(new GridLayout(1, 1));
        return panel;
    }
    
    // Create Query UI
    protected JComponent createQueryCustomerPanel() {  	
        CustomerQueryView customerQueryView = new CustomerQueryView();           
		new CustomerQueryController(customerQueryView, customerDAO, actionsMenu, customerMenuObserver);
        JPanel panel = new JPanel();
        panel.add(customerQueryView.getCustomerQueryPanel());    
        panel.setLayout(new GridLayout(1, 1));
        return panel;
    }
    
    // Create Update UI
    protected JComponent createModifyCustomerPanel() {  	
        CustomerUpdateByIdView customerUpdateByIdView = new CustomerUpdateByIdView();           
		new CustomerUpdateByIdController(customerUpdateByIdView, customerDAO, actionsMenu, customerMenuObserver);
        JPanel panel = new JPanel();
        panel.add(customerUpdateByIdView.getCustomerUpdateScrollPane());    
        panel.setLayout(new GridLayout(1, 1));
        return panel;
    }
    
    /**
	 * @return
	 * @uml.property  name="tabbedPane"
	 */
    public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	/**
	 * @param  tabbedPane
	 * @uml.property  name="tabbedPane"
	 */
	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}
	
	private class TabHandler implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent event) {
			if(event.getSource() == tabbedPane) {
				JComponent tab = (JComponent)tabbedPane.getComponent((tabbedPane.getSelectedIndex()));				
				if(tab == insertCustomerPanel) {
				    customerMenuObserver.setInsertCustomerPanelFlag(true);				    
				    actionsMenu.getSaveMenuItem().setEnabled(true);
				    actionsMenu.getQueryMenuItem().setEnabled(false);
				    actionsMenu.getDeleteMenuItem().setEnabled(false);
				    actionsMenu.getClearMenuItem().setEnabled(true);
				    actionsMenu.getResetMenuItem().setEnabled(false);
				}
				else if(tab == queryCustomerPanel) {
				    customerMenuObserver.setQueryCustomerPanelFlag(true);
				    actionsMenu.getSaveMenuItem().setEnabled(false);
				    actionsMenu.getQueryMenuItem().setEnabled(true);
				    actionsMenu.getDeleteMenuItem().setEnabled(false);
				    actionsMenu.getClearMenuItem().setEnabled(true);
				    actionsMenu.getResetMenuItem().setEnabled(true);
				}
				else if(tab == modifyCustomerPanel) {
				    customerMenuObserver.setModifyCustomerPanelFlage(true);
				    actionsMenu.getSaveMenuItem().setEnabled(true);
				    actionsMenu.getQueryMenuItem().setEnabled(true);
				    actionsMenu.getDeleteMenuItem().setEnabled(true);
				    actionsMenu.getClearMenuItem().setEnabled(true);
				    actionsMenu.getResetMenuItem().setEnabled(true);
				}
			}			
		}		
	}
	
    public static void main(String[] args) {
        //Schedule a job for the event dispatch thread; creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					new CustomerManager();
				} 
				catch(ClassNotFoundException exception) {
					JOptionPane.showMessageDialog(null,
							exception.toString(),
							Messages.UI_MANAGER_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}
				catch(InstantiationException exception) {
					JOptionPane.showMessageDialog(null,
							exception.toString(),
							Messages.UI_MANAGER_TITLE,
							JOptionPane.ERROR_MESSAGE);
				} 
				catch(IllegalAccessException exception) {
					JOptionPane.showMessageDialog(null,
							exception.toString(),
							Messages.UI_MANAGER_TITLE,
							JOptionPane.ERROR_MESSAGE);
				} 
				catch(UnsupportedLookAndFeelException exception) {
					JOptionPane.showMessageDialog(null,
							exception.toString(),
							Messages.UI_MANAGER_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}
            }
        });
    }
}