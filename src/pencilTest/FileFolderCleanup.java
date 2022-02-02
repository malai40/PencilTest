package pencilTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
//import java.util.Arrays;
import java.util.Vector;

/**
 * <h1>File Folder Cleanup</h1>
 * <br>Methods to manipulate files, folders, and their names.
 * @author malai40
 */
public class FileFolderCleanup {
	/**
	 * Delete all files within a given folder.
	 * <br>Will not delete the folder.
	 * @param folderLoc The absolute path of the folder to delete.
	 */
	protected static void deleteAllFilesInFolder(String folderLoc) {
		// delete all files in folder
		File folder = new File(folderLoc);
		File[] listOfFiles = folder.listFiles();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			listOfFiles[i].delete();
		}
	}
	
	/**
	 * Delete all files within a given folder, then delete the folder.
	 * <br>Will not delete folders within the folder. Only use with 
	 * folders that are leaves (do not have any sub-folders).
	 * @param folderLoc The absolute path of the folder to delete.
	 */
	protected static void deleteFolderAndFiles(String folderLoc) {
		// delete all files in folder, then delete the folder
		deleteAllFilesInFolder(folderLoc);
		
		File folder = new File(folderLoc);
		folder.delete();
	}
	
	/**
	 * Delete an empty folder.
	 * <br>Will not delete files within the folder.
	 * @param folderLoc The absolute path of the folder to delete.
	 */
	protected static void deleteEmptyFolder(String folderLoc) {
		// delete an empty folder
		
		File folder = new File(folderLoc);
		folder.delete();
	}
	
	/**
	 * Cleanse file name numbering for easier reading.
	 * <br>e.g. If there are 250 files in a folder, "3.jpg" should be renames to "003.jpg",
	 * or else the file will be read in after "250.jpg".
	 * @param listImgPaths An array of {@link File Files} to re-number.
	 * @return An array of {@link File Files} that have been re-numbered.
	 */
	protected static File[] cleanseInputPicNumbering(File[] listImgPaths) {
		/* Count the number of files in this list */
		
		//String[] newListImgPaths = new String[listImgPaths.length];
		
		int numberOfDesiredDigits = NumberAnalysis.getNumberOfDigitsInInteger(listImgPaths.length);
		
		String oldFileName;
		String pathNoFileName;
		String newAbsoluteFileName;
		String fullOldPath;
		int numberOfChars;
		
		/* All images must be named just a number, and numbers must all have same number of integers */
		
		for (int i = 0; i < listImgPaths.length; i++) {
			fullOldPath = listImgPaths[i].getAbsolutePath();
			pathNoFileName = GetInfoBasedOnFileNames.getAbsolutePathBeforeFileName(fullOldPath);
			/* Get file name without the ending */
			oldFileName = GetInfoBasedOnFileNames.getFileNameNoFileTypeEnding(GetInfoBasedOnFileNames.getFileNameFromAbsolutePath(fullOldPath));
			
			/* TODO Get number at the end of the file name */
			
			numberOfChars = oldFileName.length();
			/* If number of characters is less than numberOfDesiredDigits, add 0s to beginning */
			while (numberOfChars < numberOfDesiredDigits) {
				oldFileName = "0" + oldFileName;
				numberOfChars++;
			}
		
			newAbsoluteFileName = pathNoFileName + oldFileName + "." + GetInfoBasedOnFileNames.getFileType(fullOldPath);
			File newPicFile = new File(newAbsoluteFileName);
			listImgPaths[i].renameTo(newPicFile);
			//newListImgPaths[i] = listImgPaths;
			//System.out.println(newListImgPaths);
			
		}
		
		/* Order listImgPaths based on clean numbering */
		/* TODO make Comparator that sorts by uncleansed numbering, and feed that into below */
		//Arrays.sort(newListImgPaths);
		
		//return newListImgPaths;
		return listImgPaths;
		
	}
	
	/**
	 * Only pick the files in an array of {@link File Files} that are of the desired file type.
	 * @param listOfFiles An array of {@link File Files}.
	 * @return The list of files to use in {@link Vector Vector<String>} format.
	 */
	public static Vector<String> cleanseFilesByFileType(File[] listOfFiles) {
		/* Get the file type to work with. Only one allowed */
		String thePicTypeAllowed = GetInputVariablesFromUser.getDesiredFileType(GetPictureInformation.getFirstPicInInputPicsFolderPathAsString(listOfFiles));
		
		Vector<String> imgLst = new Vector<String>();
		for (int i=0; i < listOfFiles.length; i++) {
			String fileType = GetInfoBasedOnFileNames.getFileType(listOfFiles[i].getName());
			if (fileType == thePicTypeAllowed) {
				imgLst.add(listOfFiles[i].getAbsolutePath());
			}
		}
		return imgLst;
	}
	
	/**
	 * Copy all files in a given folder to a temp folder just for this program.
	 * @param inputDrawingsFolder The absolute path in {@link String String} format to 
	 * the input folder of files to be copied. Must not contain any sub-folders.
	 * @param prgmName The name of the program running in {@code String} format. 
	 * This will be within name of the temp folder.
	 * @return The absolute path to the new temp folder in {@code String} format.
	 */
	public static String copyFilesToTemp(String inputDrawingsFolder, String prgmName) {
		//will also cleanse the files so that only things of the first file type get copied
		//Load in pictures as files
		File folder = new File(inputDrawingsFolder);
		File[] listOfFiles = folder.listFiles();
		
		String tempFolder = System.getProperty("java.io.tmpdir") + prgmName + "/inputPics/";
		//Path tempFolder = Files.createTempDirectory(null);
		new File(tempFolder).mkdirs();
		
		for (int i = 0; i < listOfFiles.length; i++) {
			if (!GetInfoBasedOnFileNames.isHiddenFile(listOfFiles[i].getName())) {
				Path source = Paths.get(listOfFiles[i].getAbsolutePath());
				Path dest = Paths.get(tempFolder + listOfFiles[i].getName());
				
				try {
				    Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e) {
				    e.printStackTrace();
				}
			}
		}
		return tempFolder;
	}
}
