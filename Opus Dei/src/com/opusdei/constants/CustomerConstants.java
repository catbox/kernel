/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-06
 * Project: Opus Dei
 * Package: com.opusdei.constants
 * File: CustomerConstants.java
 * Description: Customer constant variables.
 *
 */
package com.opusdei.constants;

public class CustomerConstants {
	
	// Customer Column Name
	public static final String CUSTOMER_ID = "CUSTOMER ID";
	
	public static final String CUSTOMER_NAME = "CUSTOMER NAME";
	
	public static final String CUSTOMER_FIRSTNAME = "FIRST NAME";
	
	public static final String CUSTOMER_MIDDLENAME = "MIDDLE NAME";
	
	public static final String CUSTOMER_LASTNAME = "LAST NAME";
	
	public static final String CUSTOMER_STREET = "STREET";
	
	public static final String CUSTOMER_POSTAL_CODE = "POSTAL CODE";
	
	public static final String CUSTOMER_CITY = "CITY";
	
	public static final String CUSTOMER_STATE = "STATE";
	
	public static final String CUSTOMER_COUNTRY = "COUNTRY";
	
	public static final String CUSTOMER_PRIMARY_PHONE_NUMBER = "PRIMARY PHONE NUMBER";
	
	public static final String CUSTOMER_SECONDARY_PHONE_NUMBER = "SECONDARY PHONE NUMBER";
	
	public static final String CUSTOMER_FAX_NUMBER = "FAX NUMBER";
	
	public static final String CUSTOMER_EMAIL = "EMAIL";
	
	public static final String CUSTOMER_WEBSITE = "WEBSITE";
	
	public static final String CUSTOMER_DATE_OF_CREATION = "DATE OF CREATION";
	
	public static final String CUSTOMER_SELECTED = "selected";
	
	// Customer default table data
	public final static Object[][] CUSTOMER_DEFAULT_DATA = new Object[0][0];
	
	// Customer default table header
	public final static Object[] CUSTOMER_DEFAULT_HEADER = {CUSTOMER_ID, CUSTOMER_NAME, CUSTOMER_FIRSTNAME, CUSTOMER_MIDDLENAME,
	CUSTOMER_LASTNAME, CUSTOMER_STREET, CUSTOMER_POSTAL_CODE, CUSTOMER_CITY, CUSTOMER_STATE, CUSTOMER_COUNTRY, CUSTOMER_PRIMARY_PHONE_NUMBER,
	CUSTOMER_SECONDARY_PHONE_NUMBER, CUSTOMER_FAX_NUMBER, CUSTOMER_EMAIL, CUSTOMER_WEBSITE, CUSTOMER_DATE_OF_CREATION};
	
	// Customer Common Swing Components
	public final static String CUSTOMER_MANAGER_LABEL = "OPUS DEI";
	
	public final static String CUSTOMER_MANAGER_ICON_FILENAME = "CustomerIcon.jpeg";
	
	public final static String CUSTOMER_MANAGER_ICON_DESCRIPTION = "Customer Manager Icon";
	
	public final static String CUSTOMER_BUTTON_GAP_LEFT = "gapleft 5";
	public final static String CUSTOMER_BUTTON_GAP_RIGHT = "gapright 10";
	
	// Customer Query UI Swing Components
	public final static String[] CUSTOMER_QUERY_FIELDS = {"Customer ID", "Customer Name", "Last Name"};
	
	// Customer Insert UI Swing Components
	public final static String CUSTOMER_UI_BORDER_LABEL = "CUSTOMER INFORMATION";
	public final static String CUSTOMER_UI_BORDER_LABEL_ADDRESS = "ADDRESS";
	public final static String CUSTOMER_UI_BORDER_LABEL_CONTACT = "CONTACT";
	
	// Customer Manager Application Title
	public final static String CUSTOMER_MANAGER_TITLE = "Customer Manager";
	
	// Customer Insert Title
	public final static String CUSTOMER_INSERT_TITLE = "Customer Insert Operation";
	
	// Customer Query Title
	public final static String CUSTOMER_QUERY_TITLE = "Customer Query Operation";
		
	// Customer Update Title
	public final static String CUSTOMER_UPDATE_TITLE = "Customer Update Operation";
	
	// Customer Clear Title
	public final static String CUSTOMER_CLEAR_TITLE = "Customer Clear Operation";
	
	// Customer Clear Title
	public final static String CUSTOMER_RESET_TITLE = "Customer Reset Operation";
	
}
