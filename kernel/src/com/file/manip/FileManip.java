package com.file.manip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.constants.FileManipConstants;

public class FileManip {

	private final File ROOT_PATH;
	private String rootPathStr;
	private Properties properties;
	private File rootDirectory;
	private File userDirectory;
	private File userWishListDirectory;
	
	// Constructor - Creates the root directory for all users
	public FileManip() {
		
		ROOT_PATH = new File(FileManipConstants.USER_DATA_FILE_LOCATION);

		try {
			InputStream rootPathStream = new FileInputStream(ROOT_PATH);
			properties = new Properties();
			properties.load(rootPathStream);
		} 
		catch(FileNotFoundException fileNotFoundException) {
			System.out.println("FileNotFoundException: " + fileNotFoundException.getMessage());
		} 
		catch(IOException ioException) {
			System.out.println("IOException: " + ioException);
		}

		rootPathStr = properties.getProperty(FileManipConstants.ROOT);
		
		rootDirectory = new File(rootPathStr);
		if(!rootDirectory.exists()) {
			rootDirectory.mkdir();
			System.out.println("Directory was created at: " + rootDirectory.getAbsolutePath());
		}
		else {
			System.out.println("Directory already exists at: " + rootDirectory.getAbsolutePath());
		}
	}
	
	// Create the user's directory
	public void createUserDirectory(String userDirectory) {
		this.userDirectory = new File(rootPathStr + File.separator + userDirectory);
		if(!this.userDirectory.exists()) {
			this.userDirectory.mkdir();
			System.out.println("User directory was created at: " + this.userDirectory.getAbsolutePath());
		}
		else {
			System.out.println("User directory already exists at: " + this.userDirectory.getAbsolutePath());
		}
	}
	
	// Create the user's wish list directory
	public void createUserWishListDirectory(String userDirectory, String wishlist) {
		this.userWishListDirectory = new File(rootPathStr + File.separator + userDirectory + File.separator + wishlist);
		if(!this.userWishListDirectory.exists()) {
			this.userWishListDirectory.mkdir();
			System.out.println("Wish List was created at: " + this.userWishListDirectory.getAbsolutePath());
		}
		else {
			System.out.println("Wish List already exists at: " + this.userWishListDirectory.getAbsolutePath());
		}
	}
		
	public static void main(String[] args) {
		
		FileManip fileManip = new FileManip();
		
		// User1 home directory
		fileManip.createUserDirectory("User1");
		// User1 creates a wish list - spring break
		fileManip.createUserWishListDirectory("User1", "Spring Break - Cuba 2015");
		// User1 creates a wish list - easter
		fileManip.createUserWishListDirectory("User1", "Easter");
		
		// User2 home directory
		fileManip.createUserDirectory("User2");
		// User1 creates a wish list - spring break
		fileManip.createUserWishListDirectory("User2", "Spring Break - The Real Cancun 2015");
		// User1 creates a wish list - easter
		fileManip.createUserWishListDirectory("User2", "Easter Bunnies");
		
		// User2 home directory
		fileManip.createUserDirectory("User3");
		// User1 creates a wish list - spring break
		fileManip.createUserWishListDirectory("User3", "Spring Break - Burn Valadero 2015");
		// User1 creates a wish list - easter
		fileManip.createUserWishListDirectory("User3", "Easter Pink Bunnies");

	}

}
