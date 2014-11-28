/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.table.model
 * File: CustomerTableModel.java
 * Description: Table model to represent the persisted data
 */
package com.opusdei.table.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;

import com.opusdei.common.BooleanProperty;
import com.opusdei.common.StringTools;
import com.opusdei.constants.CustomerConstants;
import com.opusdei.transfer.Customer;

public class CustomerTableModel extends AbstractTableModel {
	
	protected final int COLUMN_COUNT = 17;
	protected List<Customer> rows;
	protected ChangeListener listener;

	public static final int SORT_ASCENDING = 0;
	public static final int SORT_DESCENDING = 1;

	/**
	 * Constructor
	 */
	public CustomerTableModel() {
		rows = new ArrayList<Customer>();
	}

	/**
	 * Get the type associated with the column
	 */
	public Class getColumnClass(int columnIndex) {		
		switch(columnIndex) {
			case 0:
				return Boolean.class;
			default:
				return String.class;
		}
	}

	/**
	 * Column labels.
	 *
	 */
	public String getColumnName(final int column) {		
		switch(column) {
		
			case 0:
				return StringTools.SINGLE_SPACE;
	
			case 1:
				return CustomerConstants.CUSTOMER_ID;
				
			case 2:
				return CustomerConstants.CUSTOMER_NAME;
	
			case 3:
				return CustomerConstants.CUSTOMER_FIRSTNAME; 
	
			case 4:
				return CustomerConstants.CUSTOMER_MIDDLENAME;
			
			case 5:
				return CustomerConstants.CUSTOMER_LASTNAME;
			
			case 6:
				return CustomerConstants.CUSTOMER_STREET;
				
			case 7:
				return CustomerConstants.CUSTOMER_POSTAL_CODE;
			
			case 8:
				return CustomerConstants.CUSTOMER_CITY;
				
			case 9:
				return CustomerConstants.CUSTOMER_STATE;
				
			case 10:
				return CustomerConstants.CUSTOMER_COUNTRY;
				
			case 11:
				return CustomerConstants.CUSTOMER_PRIMARY_PHONE_NUMBER;
				
			case 12:
				return CustomerConstants.CUSTOMER_SECONDARY_PHONE_NUMBER;
				
			case 13:
				return CustomerConstants.CUSTOMER_FAX_NUMBER;
				
			case 14:
				return CustomerConstants.CUSTOMER_EMAIL;
				
			case 15:
				return CustomerConstants.CUSTOMER_WEBSITE;
				
			case 16:
				return CustomerConstants.CUSTOMER_DATE_OF_CREATION;
				
			default:
				return StringTools.SINGLE_SPACE;
			}	
	}

	/**
	 * This listener is only called whenever the value in the
	 * selected column changes from true to false, or false to true.
	 * @param l
	 */
	public void setSelectionListener(ChangeListener l) {
		listener = l;
	}

	/**
	 * Insert a row to the table
	 *
	 * @param customer
	 */
	public synchronized void insertRow(final Customer customer) {
		rows.add(customer);
		fireTableRowsInserted(rows.size() - 1, rows.size() - 1);
	}

	/**
	 * Insert a row into the table at the specified location.
	 *
	 * @param row index, customer
	 */
	public synchronized void insertRow(final int row, final Customer customer) {
		rows.add(row, customer);
		fireTableRowsInserted(row, row);
	}
	
	/**
	 * Update a row into the table at the specified location.
	 *
	 * @param row index, customer
	 */
	public synchronized void updateRow(final Customer customer, final int row) {
		Object object = null;
		try {
			object = rows.remove(row);
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		if(object != null) {
			rows.add(row, customer);
			fireTableRowsUpdated(row, row);
		}
	}

	/**
	 * Removes the row at the specified location. If the row does not exist then no action is taken.
	 *
	 * @param row
	 */
	public synchronized void removeRow(final int row) {
		rows.remove(row);
		fireTableRowsDeleted(row, row);
	}

	/**
	 * Gets the row at the specified location. If the row does not exist then return null.
	 *
	 * @param row
	 * @return Customer
	 */
	public synchronized Customer getRow(final int row) {		
		if((row >= 0) && (row < rows.size())) {
			return rows.get(row);
		}
		return null;
	}

	/**
	 * Appends all the rows in the specified collection to the table in the order
	 * given by the collections iterator. If the collection is null or empty then no action is taken.
	 *
	 * @param c
	 * @return void
	 */
	public synchronized void addRows(final Collection c) {
		if(c != null) {
			for(Iterator i = c.iterator(); i.hasNext();) {
				Object obj = i.next();
				if(obj instanceof Customer) {
					insertRow((Customer) obj);
				}
			}
		}
	}

	/**
	 * Sorts the table model by the specified column.
	 *
	 * @param columnName
	 * @param direction
	 * @return void
	 */
	public void sortBy(final String columnName, final int direction) {
		sortBy(getColumnIndex(columnName), direction);
	}

	/**
	 * Sorts the table model by the specified column.
	 *
	 * @param columnIdx
	 * @param direction
	 */
	public synchronized void sortBy(final int columnIndex, final int direction) {
		if(direction == SORT_DESCENDING) {
			Collections.sort(rows, new Comparator() {
				public int compare(Object o1, Object o2) {					
					if((o1 == null) || (o2 == null)) {
						return -1;
					}

					if(!(o1 instanceof Customer) ||!(o2 instanceof Customer)) {
						return -1;
					}

					int result = -1 * compareTo(((Customer) o1).getColumnValue(columnIndex), ((Customer) o2).getColumnValue(columnIndex));
					
					if((result == 0) && (getColumnIndex(CustomerConstants.CUSTOMER_ID) != columnIndex)) {
						return compareTo(((Customer) o1).getCustomerId(), ((Customer) o2).getCustomerId());
					}

					return result;
				}
			});
		}
		else {
			Collections.sort(rows, new Comparator() {
				public int compare(Object o1, Object o2) {
					if((o1 == null) || (o2 == null)) {
						return -1;
					}

					if(!(o1 instanceof Customer) ||!(o2 instanceof Customer)) {
						return -1;
					}

					int result = compareTo(((Customer) o1).getColumnValue(columnIndex), ((Customer) o2).getColumnValue(columnIndex));
					
					if((result == 0) && (getColumnIndex(CustomerConstants.CUSTOMER_ID) != columnIndex)) {
						return compareTo(((Customer) o1).getCustomerId(), ((Customer) o2).getCustomerId());
					}

					return result;
				}

			});
		}
		fireTableDataChanged();
	}

	/**
	 * Compare two objects.
	 */
	private int compareTo(Object o1, Object o2) {
		
		if((o1 == null) || (o2 == null)) {
			return -1;
		}

		if((o1 instanceof Double) && (o2 instanceof Double)) {
			return ((Double) o1).compareTo((Double) o2);
		}

		return (o1.toString().toLowerCase()).compareTo(o2.toString().toLowerCase());
	}

	/**
	 * Removes all rows from the table.
	 */
	public synchronized void clear() {
		rows.clear();
		fireTableDataChanged();
	}

	/**
	 * Verifies that the Selected cell is editable in this model
	 */
	public boolean isCellEditable(final int row, final int column) {
		if(column == 0) {
			return true;
		}
		return false;
	}

	/**
	 * The number of columns.
	 */
	public int getColumnCount() {
		return COLUMN_COUNT;
	}

	/**
	 * The number of rows.
	 */
	public synchronized int getRowCount() {
		return rows.size();
	}

	/**
	 * Get the value at the specified row and column index.
	 */
	public synchronized Object getValueAt(final int rowIndex, final int columnIndex) {
		
		if((rowIndex >= 0) && (rowIndex < rows.size()) && (columnIndex >= 0) && (columnIndex < COLUMN_COUNT)) {
			
			Customer customer = rows.get(rowIndex);
			
			switch(columnIndex) {
			
				case 0:
					return new Boolean(customer.isSelected());

				case 1:
					return customer.getCustomerId();

				case 2:
					return customer.getCustomerName();

				case 3:
					return customer.getFirstName();
					
				case 4:
					return customer.getMiddleName();
					
				case 5:
					return customer.getLastName();
					
				case 6:
					return customer.getStreet();
					
				case 7:
					return customer.getPostalCode();
					
				case 8:
					return customer.getCity();
				
				case 9:
					return customer.getState();
					
				case 10:
					return customer.getCountry();
					
				case 11:
					return customer.getPrimaryPhoneNumber();
					
				case 12:
					return customer.getSecondaryPhoneNumber();
					
				case 13:
					return customer.getFaxNumber();
					
				case 14:
					return customer.getEmail();
					
				case 15:
					return customer.getWebsite();
					
				case 16:
					return customer.getDateOfCreation();

				default:
					return null;
			}
		}

		return null;
	}
	
	/**
	 * Retrieves the column index according to the column name.
	 */
	public int getColumnIndex(final String columnName) {
		
		if(columnName.equals(CustomerConstants.CUSTOMER_SELECTED)) {
			return 0;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_ID)) {
			return 1;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_NAME)) {
			return 2;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_FIRSTNAME)) {
			return 3;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_MIDDLENAME)) {
			return 4;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_LASTNAME)) {
			return 5;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_STREET)) {
			return 6;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_POSTAL_CODE)) {
			return 7;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_CITY)) {
			return 8;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_STATE)) {
			return 9;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_COUNTRY)) {
			return 10;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_PRIMARY_PHONE_NUMBER)) {
			return 11;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_SECONDARY_PHONE_NUMBER)) {
			return 12;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_FAX_NUMBER)) {
			return 13;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_EMAIL)) {
			return 14;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_WEBSITE)) {
			return 15;
		}
		else if(columnName.equals(CustomerConstants.CUSTOMER_DATE_OF_CREATION)) {
			return 16;
		}
		else
			return 0;
	}
	

	/**
	 * Set the value at the specified row and column index.
	 *
	 */
	public synchronized void setValueAt(Object aValue, final int rowIndex, final int columnIndex) {
		if((rowIndex >= 0) && (rowIndex < rows.size()) && (columnIndex == 0)) {			
			
			Customer customer = rows.get(rowIndex);
			boolean originalValue = customer.isSelected();
			customer.setSelected(BooleanProperty.examine(aValue, false));
			
			if((listener != null) && (originalValue != customer.isSelected())){
				listener.stateChanged(new ChangeEvent(new Boolean(customer.isSelected())));
			}
			
			fireTableRowsUpdated(rowIndex, rowIndex);
		}
	}
}
