/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-12-08
 * Project: Opus Dei
 * Package: com.opusdei.view
 * File: ActionsMenu.java
 * Description: Creates a menu bar with actions
 *
 */
package com.opusdei.view;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ActionsMenu {
	
	/**
	 * @uml.property  name="menuBar"
	 */
	protected JMenuBar menuBar;
	/**
	 * @uml.property  name="menu"
	 */
	protected JMenu menu;
	/**
	 * @uml.property  name="configurationMenu"
	 */
	protected JMenu configurationMenu;
	/**
	 * @uml.property  name="queryMenuItem"
	 */
	protected JMenuItem queryMenuItem;
	/**
	 * @uml.property  name="saveMenuItem"
	 */
	protected JMenuItem saveMenuItem;
	/**
	 * @uml.property  name="deleteMenuItem"
	 */
	protected JMenuItem deleteMenuItem;
	/**
	 * @uml.property  name="clearMenuItem"
	 */
	protected JMenuItem clearMenuItem;
	/**
	 * @uml.property  name="resetMenuItem"
	 */
	protected JMenuItem resetMenuItem;
    /**
	 * @uml.property  name="exitMenuItem"
	 */
    protected JMenuItem exitMenuItem;
    /**
	 * @uml.property  name="databaseMenuItem"
	 */
    protected JMenuItem databaseMenuItem;
    
    public ActionsMenu() {
    	 
        // Create the menu bar.
        menuBar = new JMenuBar();

        // Actions Menu
        menu = new JMenu("Actions");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription(
        "The only menu in this program that has menu items");
        menuBar.add(menu);

        queryMenuItem = new JMenuItem("Query");
        queryMenuItem.setMnemonic(KeyEvent.VK_Q);
        menu.add(queryMenuItem);
        
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setMnemonic(KeyEvent.VK_S);
        menu.add(saveMenuItem);
        
        deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.setMnemonic(KeyEvent.VK_D);
        menu.add(deleteMenuItem);

        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.setMnemonic(KeyEvent.VK_C);
        menu.add(clearMenuItem);
        
        resetMenuItem = new JMenuItem("Reset");
        resetMenuItem.setMnemonic(KeyEvent.VK_R);
        menu.add(resetMenuItem);
                
        menu.addSeparator();
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        menu.add(exitMenuItem);
        
        // Configurations Menu
        configurationMenu = new JMenu("Configurations");
        configurationMenu.setMnemonic(KeyEvent.VK_C);
        menuBar.add(configurationMenu);
        
        databaseMenuItem = new JMenuItem("Database");
        databaseMenuItem.setMnemonic(KeyEvent.VK_D);
        configurationMenu.add(databaseMenuItem);
    }
    
    /**
     * @param actionListener
     */
    public void menuBarListener(ActionListener actionListener) {
	   	queryMenuItem.addActionListener(actionListener);
	   	saveMenuItem.addActionListener(actionListener);
	   	deleteMenuItem.addActionListener(actionListener);
	   	clearMenuItem.addActionListener(actionListener);
	   	resetMenuItem.addActionListener(actionListener);
    }
    
    /**
     * @param actionListener
     */
    public void menuBarExitListener(ActionListener actionListener) {
	   	exitMenuItem.addActionListener(actionListener);
    }
    
    /**
     * @param actionListener
     */
    public void databaseConfigurationListener(ActionListener actionListener) {
    	databaseMenuItem.addActionListener(actionListener);
    	
    }

	/**
	 * @return   the menuBar
	 * @uml.property  name="menuBar"
	 */
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	/**
	 * @param menuBar   the menuBar to set
	 * @uml.property  name="menuBar"
	 */
	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
	 * @return   the menu
	 * @uml.property  name="menu"
	 */
	public JMenu getMenu() {
		return menu;
	}

	/**
	 * @param menu   the menu to set
	 * @uml.property  name="menu"
	 */
	public void setMenu(JMenu menu) {
		this.menu = menu;
	}

	/**
	 * @return   the queryMenuItem
	 * @uml.property  name="queryMenuItem"
	 */
	public JMenuItem getQueryMenuItem() {
		return queryMenuItem;
	}

	/**
	 * @param queryMenuItem   the queryMenuItem to set
	 * @uml.property  name="queryMenuItem"
	 */
	public void setQueryMenuItem(JMenuItem queryMenuItem) {
		this.queryMenuItem = queryMenuItem;
	}

	/**
	 * @return   the saveMenuItem
	 * @uml.property  name="saveMenuItem"
	 */
	public JMenuItem getSaveMenuItem() {
		return saveMenuItem;
	}

	/**
	 * @param saveMenuItem   the saveMenuItem to set
	 * @uml.property  name="saveMenuItem"
	 */
	public void setSaveMenuItem(JMenuItem saveMenuItem) {
		this.saveMenuItem = saveMenuItem;
	}

	/**
	 * @return   the deleteMenuItem
	 * @uml.property  name="deleteMenuItem"
	 */
	public JMenuItem getDeleteMenuItem() {
		return deleteMenuItem;
	}

	/**
	 * @param deleteMenuItem   the deleteMenuItem to set
	 * @uml.property  name="deleteMenuItem"
	 */
	public void setDeleteMenuItem(JMenuItem deleteMenuItem) {
		this.deleteMenuItem = deleteMenuItem;
	}

	/**
	 * @return   the clearMenuItem
	 * @uml.property  name="clearMenuItem"
	 */
	public JMenuItem getClearMenuItem() {
		return clearMenuItem;
	}

	/**
	 * @param clearMenuItem   the clearMenuItem to set
	 * @uml.property  name="clearMenuItem"
	 */
	public void setClearMenuItem(JMenuItem clearMenuItem) {
		this.clearMenuItem = clearMenuItem;
	}

	/**
	 * @return   the resetMenuItem
	 * @uml.property  name="resetMenuItem"
	 */
	public JMenuItem getResetMenuItem() {
		return resetMenuItem;
	}

	/**
	 * @param resetMenuItem   the resetMenuItem to set
	 * @uml.property  name="resetMenuItem"
	 */
	public void setResetMenuItem(JMenuItem resetMenuItem) {
		this.resetMenuItem = resetMenuItem;
	}

	/**
	 * @return   the exitMenuItem
	 * @uml.property  name="exitMenuItem"
	 */
	public JMenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	/**
	 * @param exitMenuItem   the exitMenuItem to set
	 * @uml.property  name="exitMenuItem"
	 */
	public void setExitMenuItem(JMenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}

	/**
	 * @return   the configurationMenu
	 * @uml.property  name="configurationMenu"
	 */
	public JMenu getConfigurationMenu() {
		return configurationMenu;
	}

	/**
	 * @param configurationMenu   the configurationMenu to set
	 * @uml.property  name="configurationMenu"
	 */
	public void setConfigurationMenu(JMenu configurationMenu) {
		this.configurationMenu = configurationMenu;
	}

	/**
	 * @return   the databaseMenuItem
	 * @uml.property  name="databaseMenuItem"
	 */
	public JMenuItem getDatabaseMenuItem() {
		return databaseMenuItem;
	}

	/**
	 * @param databaseMenuItem   the databaseMenuItem to set
	 * @uml.property  name="databaseMenuItem"
	 */
	public void setDatabaseMenuItem(JMenuItem databaseMenuItem) {
		this.databaseMenuItem = databaseMenuItem;
	}
    
}
