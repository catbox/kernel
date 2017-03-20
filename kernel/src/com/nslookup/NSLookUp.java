package com.nslookup;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NSLookUp {
	
public void performNSLookup() {
        
        try {
            
            InetAddress inetHost = InetAddress.getByName("yahoo.ca");
            String hostName = inetHost.getHostName();
            System.out.println("The host name was: " + hostName);
            System.out.println("The hosts IP address is: " + inetHost.getHostAddress());
            
        } catch(UnknownHostException ex) {
            
            System.out.println("Unrecognized host");
        }
    }

	public static void main(String[] args) {
		new NSLookUp().performNSLookup();
	}

}
