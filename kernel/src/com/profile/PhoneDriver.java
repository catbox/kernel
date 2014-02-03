package com.profile;

public class PhoneDriver {

	public static void main(String[] args) {
		String phoneNumber = "5142811100";
		
		String phone1 = phoneNumber.substring(0, 3);
		String phone2 = phoneNumber.substring(3, 6);
		String phone3 = phoneNumber.substring(6, 10);
		
		System.out.println("Phone1: " + phone1);
		System.out.println("Phone2: " + phone2);
		System.out.println("Phone3: " + phone3);
		
		//String phoneNumber2 = null;
		//String phoneNumberTrimmed = phoneNumber2.trim();
		//System.out.println("phoneNumber2: " + phoneNumberTrimmed);
		
		String phoneNumber3 = "";
		if(isEmpty(phoneNumber3)) {
			System.out.println("Phone Number 3 is empty");
		}
		else {
			System.out.println("Phone Number 3 is not empty");
		}
		
	}

	public static boolean isEmpty(String str) {
		return (str == null) || (str.length() == 0);
	}
}
