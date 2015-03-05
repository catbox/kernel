package com.file.manip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.constants.FileManipConstants;

public class FileManip {

	private final static File ROOT_PATH = new File(FileManipConstants.USER_DATA_FILE_LOCATION);
	private static String rootPathStr;
	private static Properties properties;
	private static File rootDirectory;
	private static File userDirectory;
	private static File userWishListDirectory;
	
	// Creates the root directory for all users
	public static void createRootDirectory() {
		
		try {
			InputStream rootPathStream = new FileInputStream(ROOT_PATH);
			properties = new Properties();
			properties.load(rootPathStream);
			rootPathStr = properties.getProperty(FileManipConstants.ROOT);
			
			rootDirectory = new File(rootPathStr);
			
			if(!rootDirectory.exists()) {
				rootDirectory.mkdir();
				System.out.println("Directory was created at: " + rootDirectory.getAbsolutePath());
			}
			else {
				System.out.println("Directory already exists at: " + rootDirectory.getAbsolutePath() + " *** Directory not created!");
			}
		} 
		catch(FileNotFoundException fileNotFoundException) {
			System.out.println("FileNotFoundException: " + fileNotFoundException.getMessage());
		} 
		catch(IOException ioException) {
			System.out.println("IOException: " + ioException.getMessage());
		}
		catch(Exception exception) {
			System.out.println("IOException: " + exception.getMessage());
		}		
	}
	
	// Create the user's directory
	public static void createUserDirectory(String userRootDirectory) {
		
		userDirectory = new File(rootPathStr + File.separator + userRootDirectory);
		
		if(!userDirectory.exists()) {
			userDirectory.mkdir();
			System.out.println("User directory was created at: " + userDirectory.getAbsolutePath());
		}
		else {
			System.out.println("User directory already exists at: " + userDirectory.getAbsolutePath() + " *** Directory not created!");
		}
	}
	
	// Delete the user's directory
	public static boolean deleteUserDirectory(String userDirectory) {
		 return deleteUserDirectoryContent(new File(rootPathStr + File.separator + userDirectory));
	}
	
	// Delete the user's directory content
	private static boolean deleteUserDirectoryContent(File directoryPath) {
		if(directoryPath.exists()) {
	      File[] userDirectoryContent = directoryPath.listFiles();
	      for(int i=0; i<userDirectoryContent.length; i++) {
	    	 // Found a directory - Call back the method recursively
	         if(userDirectoryContent[i].isDirectory()) {
	           deleteUserDirectoryContent(userDirectoryContent[i]);
	         }
	         // Found a file - Delete it
	         else {
	           userDirectoryContent[i].delete();
	         }
	      }
	    }
	    return(directoryPath.delete());
	}
	
	// Create the user's wish list directory
	public static void createUserWishListDirectory(String userDirectory, String wishlist) {
		
		userWishListDirectory = new File(rootPathStr + File.separator + userDirectory + File.separator + wishlist);
		
		if(!userWishListDirectory.exists()) {
			userWishListDirectory.mkdir();
			System.out.println("Wish List was created at: " + userWishListDirectory.getAbsolutePath());
		}
		else {
			System.out.println("Wish List already exists at: " + userWishListDirectory.getAbsolutePath() + " *** Directory not created!");
		}
	}
	
	// Delete the user's wish list directory
	public static void deleteUserWishListDirectory(String userDirectory, String wishlist) {
		deleteUserWishListDirectoryContent(new File(rootPathStr + File.separator + userDirectory + File.separator + wishlist));
	}
	
	// Delete the user's wish list directory content
	private static boolean deleteUserWishListDirectoryContent(File directoryPath) {
		if(directoryPath.exists()) {
	      File[] userDirectoryContent = directoryPath.listFiles();
	      for(int i=0; i<userDirectoryContent.length; i++) {
	           userDirectoryContent[i].delete();
	         }
	      }
	    return(directoryPath.delete());
	}
	
	// The Driver
	public static void main(String[] args) {
		
		FileManip.createRootDirectory();
	
		// User1 home directory
		FileManip.createUserDirectory("User1");
		// User1 creates a wish list - spring break
		FileManip.createUserWishListDirectory("User1", "Spring Break - Cuba");
		// User1 creates a wish list - easter
		FileManip.createUserWishListDirectory("User1", "Easter");
		
		// User2 home directory
		FileManip.createUserDirectory("User2");
		// User2 creates a wish list - spring break
		FileManip.createUserWishListDirectory("User2", "Spring Break - The Real Cancun");
		// User2 creates a wish list - easter
		FileManip.createUserWishListDirectory("User2", "Easter Bunnies");
		
		// User3 home directory
		FileManip.createUserDirectory("User3");
		// User3 creates a wish list - spring break
		FileManip.createUserWishListDirectory("User3", "Spring Break - Burn Valadero");
		// User3 creates a wish list - easter
		FileManip.createUserWishListDirectory("User3", "Easter Pink Bunnies");

		// Delete User1
		FileManip.deleteUserDirectory("User1");
		
		// Delete User2 - Wish List: Easter Bunnies
		FileManip.deleteUserWishListDirectory("User3", "Easter Pink Bunnies");

	}

}
