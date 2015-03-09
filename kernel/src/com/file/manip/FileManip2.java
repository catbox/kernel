package com.file.manip;

public class FileManip2 {

	public static String fileName = "dsofm.jpg";
	
	public static void main(String[] args) {
		
		System.out.println("FileName: " + fileName);
		
		int fileLength = fileName.length();
		
		int indexOfDot = fileName.lastIndexOf(".");
		
		String prefix = fileName.substring(0, indexOfDot-1);
		
		String suffix = fileName.substring(indexOfDot+1, fileLength);
		
		System.out.println("Prefix: " + prefix);
		System.out.println("Suffix: " + suffix);
	}

}
