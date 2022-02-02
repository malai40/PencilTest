package pencilTest;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
import javax.imageio.ImageIO;

/**
 * <h1>AlignPictures</h1>
 * <br>A class with methods to align pictures based on registration holes. 
 * @author malai40
 */
public class AlignPictures {
	/**
	 * Get the number of pixels to move a picture to align it with registration holes in a base picture.
	 * @param desiredTemplateLocPixels The (x,y) location in the base picture where the 
	 * 		top-left corner of the template is located.
	 * @param actualTemplateLocPixels The (x,y) location in the new picture where the 
	 * 		top-left corner of the template is located.
	 * @return An array representing the (x,y) amount by which the new picture needs to 
	 * 		move to align with the base picture.
	 */
	private static int[] getNumberOfPixelsToMove(int[] desiredTemplateLocPixels, int[] actualTemplateLocPixels) {
		int[] amtToMovePic = new int[2];
		//If below if negative, pic must move left/up to match
		//If below is positive, pic must move right/down to match
		//So just add the below to the actual loc to move it to the desired loc
		amtToMovePic[0] = desiredTemplateLocPixels[0] - actualTemplateLocPixels[0]; //x coord
		amtToMovePic[1] = desiredTemplateLocPixels[1] - actualTemplateLocPixels[1]; //y coord

		return amtToMovePic;
	}
	
	/**
	 * Use the sum of absolute differences (SAD) to find the (x,y) location of the 
	 * 		top-left corner within an image that most closely matches the input template.
	 * @param searchImg The image to be searched, in {@link Matrix#Matrix(int, int) Matrix} format.
	 * @param templateImg The image to search for, in {@code Matrix} format.
	 * @return An array representing the (x,y) location of the top-left corner most closely 
	 * 		matching the template.
	 */
	private static int[] findTemplateInImgSADMethod(Matrix searchImg, Matrix templateImg) {
		int[] minSADLoc = new int[2];
		int minSAD = 0;
		int diff = 0;
		
		// loop thru Search
		for (int i = 0; i < (searchImg.getRows() - templateImg.getRows()); i++) { //getting y coord 
			for (int j = 0; j < (searchImg.getCols() - templateImg.getCols()); j++) { //getting x coord
				// loop thru Template
				for (int k = 0; k < templateImg.getRows(); k++) {
					for (int l = 0; l < templateImg.getCols(); l++) {
						diff += Math.abs(searchImg.getValueAt(k+i, l+j) - templateImg.getValueAt(k, l));
					}
				}
				
				if (minSAD == 0) { //minSAD hasn't been analyzed yet
					minSAD = diff;
				} else {
					if (minSAD > diff) {
						minSAD = diff;
						minSADLoc[0] = j; //x coord in search img
						minSADLoc[1] = i; //y coord in search img
					}
				}
				/*
				System.out.print("I'm thinking (");
				System.out.print(minSADLoc[0]);
				System.out.print(",");
				System.out.print(minSADLoc[1]);
				System.out.print("). ");
				System.out.print(minSAD);
				System.out.print(" is compared to ");
				System.out.print(diff);
				System.out.print("\n");
				*/
				
				diff = 0;
			}
		}
		return minSADLoc;
	}
	
	/**
	 * Align a picture within its canvas so that it more closely matches a base image 
	 * based on location of registration holes in both images.
	 * @param pic The picture to align.
	 * @param templateMatrix The registration hole template to use for alignment, 
	 * 		in {@link Matrix#Matrix(int, int) Matrix} format.
	 * @param desiredXYLoc The (x,y) location where the top-left corner of the 
	 * 		template should be found in the picture to align.
	 * @param bgColor The color to fill in any blank canvas left over after 
	 * 		moving the picture around.
	 * @return The aligned image in {@link BufferedImage#BufferedImage() BufferedImage} format.
	 */
	private static BufferedImage alignPicture(BufferedImage pic, Matrix templateMatrix, int[] desiredXYLoc, Color bgColor) {
		
		//If this is the first image in the sequence, save where the template is located.
		//For the second or later image, find the template in the image, then move the image to match the first image's coords.
		
		Matrix searchMatrix = ConvertPicToMatrix.convertPicToGrayscaleMatrix(pic);
		
		int[] loc = findTemplateInImgSADMethod(searchMatrix, templateMatrix);
		int[] diff = getNumberOfPixelsToMove(desiredXYLoc, loc);
		
		BufferedImage editedPic = EditPicture.movePictureHoriz(pic, diff[0], bgColor);
		editedPic = EditPicture.movePictureVert(editedPic, diff[1], bgColor);
		
		return editedPic;
	}
	
	//Take a folder of images
	//Use the first to get the template. Align all others to the first, then crop to match first one's dimensions.
	/**
	 * Take a folder of images.
	 * Use the first to get the template. 
	 * Align all others to the first, then move around on canvas to match first one's dimensions.
	 * @param inputDrawingsFolder The folder of images to align.
	 * @param templateLoc The String denoting the template image's location.
	 * @param bgColor The color to use to fill in blank space left after moving around on the canvas.
	 */
	public static void fixImages(String inputDrawingsFolder, String templateLoc, Color bgColor) {
		
		File folder = new File(inputDrawingsFolder);
		File[] listOfFiles = folder.listFiles();
		
		//Load template
		BufferedImage template = null;
		try {
			template = ImageIO.read(new File(templateLoc));
		} catch (IOException e) {
		}
		
		//convert template to matrix
		Matrix templateMatrix = ConvertPicToMatrix.convertPicToGrayscaleMatrix(template);
		
		// Load the first pic
		// TODO Read in EXIF rotation information and modify the picture to 
		// // match what user sees on computer normally.
		BufferedImage firstImg = null;
		try {
			firstImg = ImageIO.read(new File(listOfFiles[0].getAbsolutePath()));
		} catch (IOException e) {
		}
				
		System.out.println(listOfFiles[0].getAbsolutePath());
		
		//get the final width and height of all pics
		int finalWidth = firstImg.getWidth();
		int finalHeight = firstImg.getHeight();
		
		// convert img to matrix
		Matrix picMatrix = ConvertPicToMatrix.convertPicToGrayscaleMatrix(firstImg);
		
		//Get the template location in the first pic
		int[] loc = findTemplateInImgSADMethod(picMatrix, templateMatrix);
		
		// Load all other pics and do the following:
		// 1. Align based on template
		// 2. Crop to match first pic dimensions
		for (int i = 1; i < listOfFiles.length; i++) {
			BufferedImage nextImg = null;
			try {
				nextImg = ImageIO.read(new File(listOfFiles[i].getAbsolutePath()));
			} catch (IOException e) {
			}
			nextImg = alignPicture(nextImg, templateMatrix, loc, bgColor); //keep
			
			//Crop the image
			nextImg = EditPicture.resizePicCanvas(nextImg, finalWidth, finalHeight, bgColor); //keep
			
			//Save the new image if anything changed
			File outputFile = new File(listOfFiles[i].getAbsolutePath());
			String imgType = GetInfoBasedOnFileNames.getFileType(listOfFiles[i].getAbsolutePath());
			try {
				ImageIO.write(nextImg, imgType, outputFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
