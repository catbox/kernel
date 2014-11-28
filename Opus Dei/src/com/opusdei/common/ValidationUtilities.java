/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-09-27
 * Project: Opus Dei
 * Package: com.opusdei.common
 * File: ValidationUtilities.java
 * Description: Validation Utilities
 */
package com.opusdei.common;

import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.jgoodies.validation.ValidationResult;

public class ValidationUtilities {
	
	public static void showValidationMessage(ActionEvent e, String headerText, ValidationResult validationResult) {
        if(validationResult.isEmpty()) {
            throw new IllegalArgumentException("The validation result must not be empty.");
        }
        Object eventSource = e.getSource();
        Component parent = null;
        if(eventSource instanceof Component) {
        	parent = SwingUtilities.windowForComponent((Component) eventSource);
        }
        boolean error = validationResult.hasErrors();
        String messageText = headerText + "\n\n"  + "Missing mandatory fields"+ "\n\n";
        String titleText = "Validation " + (error ? "Error" : "Warning");
        int messageType = error ? JOptionPane.ERROR_MESSAGE : JOptionPane.WARNING_MESSAGE;
        JOptionPane.showMessageDialog(parent, messageText, titleText, messageType);
    }

}
