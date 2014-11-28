/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-09-27
 * Project: Opus Dei
 * Package: com.opusdei.common
 * File: Utilities.java
 * Description: Utilities
 */
package com.opusdei.common;

import javax.swing.ImageIcon;

import com.opusdei.client.CustomerManager;

public class Utilities {
	
	 /** Returns an ImageIcon, or null if the path was invalid. */
    public static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CustomerManager.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } 
        else {         
            return null;
        }
    }
    
    /** Returns an ImageIcon, or null if the path was invalid. */
	public static ImageIcon createImageIcon(String path, String description) {
       java.net.URL imgURL = CustomerManager.class.getResource(path);
       if (imgURL != null) {
           return new ImageIcon(imgURL, description);
       } 
       else {
           return null;
       }
    }

}
