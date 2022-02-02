package pencilTest;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Methods to get information on a picture.
 * @author malai40
 */
public class GetPictureInformation {
	/**
	 * Get the width and height of the image at the {@link String String} location supplied.
	 * @param picFileLoc The {@code String} of the absolute path of the image to analyze.
	 * @return A 1x2 {@code int} array of the width and height of the image.
	 * @throws IOException
	 */
	public static int[] getWidthHeightOfPic(String picFileLoc) throws IOException {
		BufferedImage img = ImageIO.read(new File(picFileLoc));
		
		int[] widthHeightArray = new int[2];
		//System.out.println(picFileLoc);
		widthHeightArray[0] = img.getWidth();
		widthHeightArray[1] = img.getHeight();
		
		return widthHeightArray;
	}
	
	/**
	 * Get the first image in an array of {@link File Files} that's ordered by name (numbers in the names).
	 * @param listOfFiles An array of {@code Files} of all of the images to put into the final movie.
	 * @return The absolute path in {@link String String} format to the first file in the input array.
	 */
	public static String getFirstPicInInputPicsFolderPathAsString(File[] listOfFiles) {
		return listOfFiles[0].getAbsolutePath();
	}
}
