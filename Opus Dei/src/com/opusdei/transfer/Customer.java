/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-05
 * Project: Opus Dei
 * Package: com.opusdei.transfer
 * File: Customer.java
 * Description: Customer
 *
 */
package com.opusdei.transfer;

import java.io.Serializable;

public class Customer implements Serializable {
	
	/**
	 * @uml.property  name="customerId"
	 */
	private String customerId;
	/**
	 * @uml.property  name="customerName"
	 */
	private String customerName;
	/**
	 * @uml.property  name="firstName"
	 */
	private String firstName;
	/**
	 * @uml.property  name="middleName"
	 */
	private String middleName;
	/**
	 * @uml.property  name="lastName"
	 */
	private String lastName;
	/**
	 * @uml.property  name="street"
	 */
	private String street;
	/**
	 * @uml.property  name="postalCode"
	 */
	private String postalCode;
	/**
	 * @uml.property  name="city"
	 */
	private String city;
	/**
	 * @uml.property  name="state"
	 */
	private String state;
	/**
	 * @uml.property  name="country"
	 */
	private String country;
	/**
	 * @uml.property  name="primaryPhoneNumber"
	 */
	private String primaryPhoneNumber;
	/**
	 * @uml.property  name="secondaryPhoneNumber"
	 */
	private String secondaryPhoneNumber;
	/**
	 * @uml.property  name="faxNumber"
	 */
	private String faxNumber;
	/**
	 * @uml.property  name="email"
	 */
	private String email;
	/**
	 * @uml.property  name="website"
	 */
	private String website;
	/**
	 * @uml.property  name="dateOfCreation"
	 */
	private String dateOfCreation;
	/**
	 * @uml.property  name="selected"
	 */
	private boolean selected;
	
	/**
	 * Default constructor
	 */
	public Customer() {
		// Do nothing
	}
	
	/**
	 * @param customerId
	 * @param customerName
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param street
	 * @param postalCode
	 * @param city
	 * @param state
	 * @param country
	 * @param primaryPhoneNumber
	 * @param secondaryPhoneNumber
	 * @param faxNumber
	 * @param email
	 * @param website
	 * @param dateOfCreation
	 */
	public Customer(String customerId, String customerName, String firstName,
			String middleName, String lastName, String street,
			String postalCode, String city, String state, String country,
			String primaryPhoneNumber, String secondaryPhoneNumber,
			String faxNumber, String email, String website,
			String dateOfCreation) {
		this.customerId = customerId;
		this.customerName = customerName;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.state = state;
		this.country = country;
		this.primaryPhoneNumber = primaryPhoneNumber;
		this.secondaryPhoneNumber = secondaryPhoneNumber;
		this.faxNumber = faxNumber;
		this.email = email;
		this.website = website;
		this.dateOfCreation = dateOfCreation;
	}

	/**
	 * @return   the customerId
	 * @uml.property  name="customerId"
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * @param customerId   the customerId to set
	 * @uml.property  name="customerId"
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	/**
	 * @return   the customerName
	 * @uml.property  name="customerName"
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	/**
	 * @param customerName   the customerName to set
	 * @uml.property  name="customerName"
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	/**
	 * @return   the firstName
	 * @uml.property  name="firstName"
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * @param firstName   the firstName to set
	 * @uml.property  name="firstName"
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * @return   the middleName
	 * @uml.property  name="middleName"
	 */
	public String getMiddleName() {
		return middleName;
	}
	
	/**
	 * @param middleName   the middleName to set
	 * @uml.property  name="middleName"
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	/**
	 * @return   the lastName
	 * @uml.property  name="lastName"
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * @param lastName   the lastName to set
	 * @uml.property  name="lastName"
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * @return   the street
	 * @uml.property  name="street"
	 */
	public String getStreet() {
		return street;
	}
	
	/**
	 * @param street   the street to set
	 * @uml.property  name="street"
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	
	/**
	 * @return   the postalCode
	 * @uml.property  name="postalCode"
	 */
	public String getPostalCode() {
		return postalCode;
	}
	
	/**
	 * @param postalCode   the postalCode to set
	 * @uml.property  name="postalCode"
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	/**
	 * @return   the city
	 * @uml.property  name="city"
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city   the city to set
	 * @uml.property  name="city"
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return   the state
	 * @uml.property  name="state"
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state   the state to set
	 * @uml.property  name="state"
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return   the country
	 * @uml.property  name="country"
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param country   the country to set
	 * @uml.property  name="country"
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return   the primaryPhoneNumber
	 * @uml.property  name="primaryPhoneNumber"
	 */
	public String getPrimaryPhoneNumber() {
		return primaryPhoneNumber;
	}
	
	/**
	 * @param primaryPhoneNumber   the primaryPhoneNumber to set
	 * @uml.property  name="primaryPhoneNumber"
	 */
	public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
		this.primaryPhoneNumber = primaryPhoneNumber;
	}
	
	/**
	 * @return   the secondaryPhoneNumber
	 * @uml.property  name="secondaryPhoneNumber"
	 */
	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}
	
	/**
	 * @param secondaryPhoneNumber   the secondaryPhoneNumber to set
	 * @uml.property  name="secondaryPhoneNumber"
	 */
	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
	
	/**
	 * @return   the faxNumber
	 * @uml.property  name="faxNumber"
	 */
	public String getFaxNumber() {
		return faxNumber;
	}
	
	/**
	 * @param faxNumber   the faxNumber to set
	 * @uml.property  name="faxNumber"
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	
	/**
	 * @return   the email
	 * @uml.property  name="email"
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * @param email   the email to set
	 * @uml.property  name="email"
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return   the website
	 * @uml.property  name="website"
	 */
	public String getWebsite() {
		return website;
	}
	
	/**
	 * @param website   the website to set
	 * @uml.property  name="website"
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * @return   the dateOfCreation
	 * @uml.property  name="dateOfCreation"
	 */
	public String getDateOfCreation() {
		return dateOfCreation;
	}
	
	/**
	 * @param dateOfCreation   the dateOfCreation to set
	 * @uml.property  name="dateOfCreation"
	 */
	public void setDateOfCreation(String dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}
	
	/**
	 * @return   the selected
	 * @uml.property  name="selected"
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * @param selected   the selected to set
	 * @uml.property  name="selected"
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	

	/**
	 * Makes a field-for-field copy of instances of the class
	 */
	public Object clone() {
		return deepCopy();
	}
	
	/**
	 * Creates a field-for-field object
	 */
	protected synchronized Customer deepCopy() {
		return new Customer(getCustomerId(), getCustomerName(), getFirstName(),
				getMiddleName(), getLastName(), getStreet(), getPostalCode(),
				getCity(), getState(), getCountry(), getPrimaryPhoneNumber(),
				getSecondaryPhoneNumber(), getFaxNumber(), getEmail(),
				getWebsite(), getDateOfCreation());
	}
	
	/**
	 * Compares the String values of this object to those of the argument object
	 */
	public boolean equals(Object obj) {
		
		if((obj != null) && (obj instanceof Customer)) {
			if(((Customer) obj).getCustomerId().equals(getCustomerId())) {				
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Retrieves the column value according to the column index
	 */
	public Object getColumnValue(final int columnIndex) {
				
		switch(columnIndex) {
		
			case 0:
				return String.valueOf(isSelected());

			case 1:
				return getCustomerId();

			case 2:
				return getCustomerName();

			case 3:
				return getFirstName();
				
			case 4:
				return getMiddleName();
				
			case 5:
				return getLastName();
				
			case 6:
				return getStreet();
				
			case 7:
				return getPostalCode();
				
			case 8:
				return getCity();
				
			case 9:
				return getState();
				
			case 10:
				return getCountry();
				
			case 11:
				return getPrimaryPhoneNumber();
				
			case 12:
				return getSecondaryPhoneNumber();
				
			case 13:
				return getFaxNumber();
				
			case 14:
				return getEmail();
				
			case 15:
				return getWebsite();
				
			case 16:
				return getDateOfCreation();

			default:
				return null;
		}
	}
	
}
