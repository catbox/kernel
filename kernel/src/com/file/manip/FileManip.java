package com.file.manip;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import com.constants.FileManipConstants;

public class FileManip {

	// Property file location
	private final static File PROPERTY_FILE_LOCATION = new File(FileManipConstants.USER_DATA_FILE_LOCATION);
	// Directory root path
	private static String rootDirectoryPath;
	// Property file
	private static Properties properties;
	// Root Directory
	private static File rootDirectory;
	// User Directory
	private static File userDirectory;
	// User Directory containing a wish list directory
	private static File userWishListDirectory;
	// Array of valid file extension (suffix)
	public static String[] fileExtensionArray = {"BMP", "GIF", "ICO", "JFIF", "JPEG", "JPG", "PNG", "TIF", "TIFF"};
	// List of valid file extension (suffix)
	public static List<String> fileExtensionList = new ArrayList<String>(Arrays.asList(fileExtensionArray));
	
	/**
	 * Creates the root directory for all users
	 */
	public static void createRootDirectory() {
		
		try {
			InputStream rootPathStream = new FileInputStream(PROPERTY_FILE_LOCATION);
			properties = new Properties();
			properties.load(rootPathStream);
			rootDirectoryPath = properties.getProperty(FileManipConstants.ROOT);
			
			rootDirectory = new File(rootDirectoryPath);
			
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
	
	/**
	 * Create the user's directory
	 * @param userRootDirectory
	 */
	public static void createUserDirectory(String userRootDirectory) {
		
		createRootDirectory();
		
		userDirectory = new File(rootDirectoryPath + File.separator + userRootDirectory);
		
		if(!userDirectory.exists()) {
			userDirectory.mkdir();
			System.out.println("User directory was created at: " + userDirectory.getAbsolutePath());
		}
		else {
			System.out.println("User directory already exists at: " + userDirectory.getAbsolutePath() + " *** Directory not created!");
		}
	}
	
	/**
	 * Verify if file extension is valid
	 * @param extension
	 * @return true if file extension is valid else false
	 */
	private static boolean isExtensionValid(String extension) {
	
		if(fileExtensionList.contains(extension.toUpperCase())) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Upload user's profile image
	 * @param user
	 * @param userProfileImagePath
	 */
	public static void uploadUserProfileImage(String user, String userProfileImagePath) {
		
		createUserDirectory(user);
		
		BufferedImage image = null;
				
		File imageFile = new File(userProfileImagePath);

		try {
			image = ImageIO.read(imageFile);
			
			String fileName = FilenameUtils.getName(userProfileImagePath);
			System.out.println("File Name: " + fileName);

			String fileSuffix = FilenameUtils.getExtension(userProfileImagePath);	
			System.out.println("File Extension: " + fileSuffix);
			
			if(isExtensionValid(fileSuffix)) {
				File userProfileLocation = new File(rootDirectoryPath + File.separator + user + File.separator + fileName);
				boolean result = ImageIO.write(image, fileSuffix, userProfileLocation);
				if(result) {
					System.out.println("The profile of " + user + " was succefully loaded at: " + userProfileLocation);
				}
				else {
					System.out.println("The profile of " + user + " was not loaded at: " + userProfileLocation);
				}
			}
			else {
				System.err.println("File extension is not supported");
			}
		} 
		catch(IOException ioException) {
			System.err.println("Error loading the user profile image: " + ioException.getMessage());
		}
		catch(Exception exception) {
			System.err.println("Error loading the user profile image: " + exception.getMessage());
		}
	}
	
	/**
	 * Delete the user's directory
	 * @param userDirectory
	 * @return true if deletion of the user directory was successful else false
	 */
	public static boolean deleteUserDirectory(String userDirectory) {
		 return deleteUserDirectoryContent(new File(rootDirectoryPath + File.separator + userDirectory));
	}
	
	/**
	 * Delete the user's directory content
	 * @param directoryPath
	 * @return true if deletion of the user directory was successful else false
	 */
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
	
	/**
	 * Create the user's wish list directory
	 * @param userDirectory
	 * @param wishlist
	 */
	public static void createUserWishListDirectory(String userDirectory, String wishlist) {
		
		userWishListDirectory = new File(rootDirectoryPath + File.separator + userDirectory + File.separator + wishlist);
		
		if(!userWishListDirectory.exists()) {
			userWishListDirectory.mkdir();
			System.out.println("Wish List was created at: " + userWishListDirectory.getAbsolutePath());
		}
		else {
			System.out.println("Wish List already exists at: " + userWishListDirectory.getAbsolutePath() + " *** Directory not created!");
		}
	}

	/**
	 * Delete the user's wish list directory
	 * @param userDirectory
	 * @param true if deletion of the user wish list directory was successful else false
	 */
	public static boolean deleteUserWishListDirectory(String userDirectory, String wishlist) {
		return deleteUserWishListDirectoryContent(new File(rootDirectoryPath + File.separator + userDirectory + File.separator + wishlist));
	}
	
	/**
	 * Delete the user's wish list directory content
	 * @param directoryPath
	 * @return true if deletion of the user wish list directory was successful else false
	 */
	private static boolean deleteUserWishListDirectoryContent(File directoryPath) {
		if(directoryPath.exists()) {
	      File[] userDirectoryContent = directoryPath.listFiles();
	      for(int i=0; i<userDirectoryContent.length; i++) {
	           userDirectoryContent[i].delete();
	         }
	      }
	    return(directoryPath.delete());
	}
	
	/**
	 * The Driver
	 */
	public static void main(String[] args) {
		/*
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
		*/
		
		// Create root directory
		FileManip.createRootDirectory();	
		// User1 home directory
		FileManip.createUserDirectory("User1");
		// User1 creates a wish list - spring break
		FileManip.createUserWishListDirectory("User1", "Spring Break - Cuba");
		// User1 creates a wish list - easter
		FileManip.createUserWishListDirectory("User1", "Easter");
		// Upload user profile image
		uploadUserProfileImage("User1", "C:\\HiveImages\\Format\\Uppercase\\dsotm.JPG");		
	}

}
