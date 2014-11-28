/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-12-10
 * Project: Opus Dei
 * Package: com.opusdei.observer
 * File: CustomerMenuObserver.java
 * Description: Observer for the menu bar and the customer panels
 *
 */
package com.opusdei.observer;

public class CustomerMenuObserver {
	
	 /**
	 * @uml.property  name="insertCustomerPanelFlag"
	 */
	private boolean insertCustomerPanelFlag;
	 /**
	 * @uml.property  name="queryCustomerPanelFlag"
	 */
	private boolean queryCustomerPanelFlag;
	private boolean modifyCustomerPanelFlage;
	 
	public CustomerMenuObserver() {
		insertCustomerPanelFlag = false;
		queryCustomerPanelFlag = false;
		modifyCustomerPanelFlage = false;
	}
	 
	public void SetCustomerTab() {
		 // Do nothing
	}

	/**
	 * @return   the insertCustomerPanelFlag
	 * @uml.property  name="insertCustomerPanelFlag"
	 */
	public boolean isInsertCustomerPanelFlag() {
		return insertCustomerPanelFlag;
	}

	/**
	 * @param insertCustomerPanelFlag the insertCustomerPanelFlag to set
	 */
	public void setOnlyInsertCustomerPanelFlag(boolean insertCustomerPanelFlag) {
		this.insertCustomerPanelFlag = insertCustomerPanelFlag;
	}
	
	/**
	 * @param insertCustomerPanelFlag   the insertCustomerPanelFlag to set
	 * @uml.property  name="insertCustomerPanelFlag"
	 */
	public void setInsertCustomerPanelFlag(boolean insertCustomerPanelFlag) {
		this.insertCustomerPanelFlag = insertCustomerPanelFlag;
		this.queryCustomerPanelFlag = !insertCustomerPanelFlag;
		this.modifyCustomerPanelFlage = !insertCustomerPanelFlag;
	}

	/**
	 * @return   the queryCustomerPanelFlag
	 * @uml.property  name="queryCustomerPanelFlag"
	 */
	public boolean isQueryCustomerPanelFlag() {
		return queryCustomerPanelFlag;
	}

	/**
	 * @param queryCustomerPanelFlag the queryCustomerPanelFlag to set
	 */
	public void setOnlyQueryCustomerPanelFlag(boolean queryCustomerPanelFlag) {
		this.queryCustomerPanelFlag = queryCustomerPanelFlag;
	}
	
	/**
	 * @param queryCustomerPanelFlag   the queryCustomerPanelFlag to set
	 * @uml.property  name="queryCustomerPanelFlag"
	 */
	public void setQueryCustomerPanelFlag(boolean queryCustomerPanelFlag) {
		this.insertCustomerPanelFlag = !queryCustomerPanelFlag;
		this.queryCustomerPanelFlag = queryCustomerPanelFlag;
		this.modifyCustomerPanelFlage = !queryCustomerPanelFlag;
	}

	/**
	 * @return the modifyCustomerPanelFlage
	 */
	public boolean isModifyCustomerPanelFlag() {
		return modifyCustomerPanelFlage;
	}

	/**
	 * @param modifyCustomerPanelFlage the modifyCustomerPanelFlage to set
	 */
	public void setOnlyModifyCustomerPanelFlage(boolean modifyCustomerPanelFlage) {
		this.modifyCustomerPanelFlage = modifyCustomerPanelFlage;
	}
	
	/**
	 * @param modifyCustomerPanelFlage   the modifyCustomerPanelFlage to set
	 * @uml.property  name="modifyCustomerPanelFlage"
	 */
	public void setModifyCustomerPanelFlage(boolean modifyCustomerPanelFlage) {
		this.insertCustomerPanelFlag = !modifyCustomerPanelFlage;
		this.queryCustomerPanelFlag = !modifyCustomerPanelFlage;
		this.modifyCustomerPanelFlage = modifyCustomerPanelFlage;
	}
	 
}
