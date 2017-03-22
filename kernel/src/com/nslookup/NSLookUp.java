package com.nslookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookUp {
	
public void performNSLookup() {
        
        try {
            String emailAddress = "sombody@facebook.com";
            int emailAddressLength = emailAddress.length();
            int atPosition = emailAddress.indexOf("@");
            String domain = emailAddress.substring((atPosition+1), emailAddressLength);
            InetAddress inetHost = InetAddress.getByName(domain);
            String hostName = inetHost.getHostName();
            System.out.println("The host name was: " + hostName);
            System.out.println("The hosts IP address is: " + inetHost.getHostAddress());            
        } 
        catch(UnknownHostException ex) {           
            System.out.println("Unrecognized host");
        }
    }

	public static void main(String[] args) {
		new NSLookUp().performNSLookup();
	}

}
