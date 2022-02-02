package pencilTest;

import java.io.File;

/**
 * <h1>Get Info Based on File Names</h1>
 * <br>Get information on a file based on its path.
 * @author malai40
 */
public class GetInfoBasedOnFileNames {
	/**
	 * Get an array of files from a folder.
	 * @param inputPicsFolderPath The path to the folder in {@link String String} format.
	 * @return An array of {@link File Files} representing all files within the folder.
	 */
	public static File[] getFilesInInputPicsFolderPath(String inputPicsFolderPath) {
		File folder = new File(inputPicsFolderPath);
		File[] listOfFiles = folder.listFiles();
		return listOfFiles;
	}
	
	/**
	 * Get the file type of a given file.
	 * @param fileName The path to the file in {@link String String} format.
	 * @return The file type in {@code String} format.
	 */
	public static String getFileType(String fileName) {
		String fileType = fileName.substring((fileName.lastIndexOf(".")+1), fileName.length());
		return fileType;
	}
	
	/**
	 * Get the file name and extension from an absolute path.
	 * @param absolutePath The absolute path to a file in {@link String String} format.
	 * @return The file name and extension in {@code String} format.
	 */
	public static String getFileNameFromAbsolutePath(String absolutePath) {
		String fileName = absolutePath.substring((absolutePath.lastIndexOf("/")+1), absolutePath.length());
		return fileName;
	}
	
	/**
	 * Get the file name, without extension, from an absolute path.
	 * @param fileName The relative path to the file in {@link String String} format. 
	 * @return The file name in {@code String} format without the extension.
	 */
	public static String getFileNameNoFileTypeEnding(String fileName) {
		// fileName must have no path, just the file name
		String fileNameNoEnding = fileName.substring(0, fileName.lastIndexOf("."));
		return fileNameNoEnding;
	}
	
	/**
	 * Get the absolute path, without extension.
	 * @param absolutePath The absolute path to the file in {@link String String} format.
	 * @return The absolute path, without extension, in {@code String} format.
	 */
	public static String getAbsolutePathBeforeFileName(String absolutePath) {
		String absolutePathNoFileName = absolutePath.substring(0, (absolutePath.lastIndexOf("/")+1));
		return absolutePathNoFileName;
	}
	
	/**
	 * Check if a file is a hidden file (i.e. if a file name starts with a ".").
	 * @param fileName The file name, without any of the path before the file name.
	 * @return True if the file is hidden, False if not.
	 */
	public static boolean isHiddenFile(String fileName) {
		if (fileName.startsWith(".")) {
			return true;
		}
		return false;
	}
}
