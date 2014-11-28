/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-10-04
 * Project: Opus Dei
 * Package: com.opusdei.validation
 * File: CustomerValidator.java
 * Description: Validates the mandatory fields
 *
 */
package com.opusdei.validation;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;
import com.opusdei.transfer.Customer;

public class CustomerValidator implements Validator<Customer>{

	@Override
	public ValidationResult validate(Customer customer) {
	   PropertyValidationSupport support = new PropertyValidationSupport(customer, "Customer");
		
		if(ValidationUtils.isBlank(customer.getCustomerName()) || 
		   ValidationUtils.isBlank(customer.getFirstName()) ||
		   ValidationUtils.isBlank(customer.getLastName()) ||
		   ValidationUtils.isBlank(customer.getPrimaryPhoneNumber()) ||
		   ValidationUtils.isBlank(customer.getEmail())) {
			  support.clearResult();
		      support.addError("Customer Insert Panel", " - Missing mandatory fields");
		   }
	    return support.getResult();
	}

}
