package com.profile;

public class ZIPCodeDriver {

    public static void main(String[] args) {
        
        String zipCode9 = "123456789";
        
        String zipCodeFirst5Nums = zipCode9.substring(0, 5);
        String zipCodeLast4Nums = zipCode9.substring(5, 9);
                
        System.out.println("ZIP Code 9: " + zipCode9);
        System.out.println("ZIP Code - First 5 Numbers: " + zipCodeFirst5Nums);
        System.out.println("ZIP Code - Last 4 Numbers: " + zipCodeLast4Nums);
    }

}
