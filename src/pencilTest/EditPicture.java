package pencilTest;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * <h1>Edit Picture</h1>
 * <br> A class of methods to edit a picture.
 * @author malai40
 */
public class EditPicture {
	/**
	 * Decrease an image's width by shaving equal amounts from each side. 
	 * If the total amount to shave off is an odd number of pixels, one less pixel will
	 * be removed from the left side than the right, making the width decreasing one pixel
	 * less than perfectly even.
	 * @param pic The {@link BufferedImage BufferedImage} to make narrower.
	 * @param desiredWidth The desired width of the image. Should be less than the 
	 * current width.
	 * @return The narrower image.
	 */
	public static BufferedImage shrinkPicWidth(BufferedImage pic, int desiredWidth) {
		// find the x and y where the subimage should start

		// if diff btwn desiredWidth and actual width is even, then diff/2 and chop off each side
		// if diff btwn desiredWidth and actual width is odd, then diff/2 and round down to nearest integer
		int startX = (pic.getWidth() - desiredWidth) / 2; //rounds down
		
		BufferedImage croppedPic = null;
		croppedPic = pic.getSubimage(startX, 0, desiredWidth, pic.getHeight());
		
		return croppedPic;
	}
	
	/**
	 * Decrease an image's height by shaving equal amounts from top and bottom. 
	 * If the total amount to shave off is an odd number of pixels, one less pixel will
	 * be removed from the top than the bottom, making the height decreasing one pixel
	 * less than perfectly even.
	 * @param pic The {@link BufferedImage BufferedImage} to make shorter.
	 * @param desiredHeight The desired height of the image. Should be less than the 
	 * current height.
	 * @return The shorter image.
	 */
	public static BufferedImage shrinkPicHeight(BufferedImage pic, int desiredHeight) {
		// find the x and y where the subimage should start

		// if diff btwn desiredWidth and actual width is even, then diff/2 and chop off each side
		// if diff btwn desiredWidth and actual width is odd, then diff/2 and round down to nearest integer
		int startY = (pic.getWidth() - desiredHeight) / 2;
		
		BufferedImage croppedPic = null;
		croppedPic = pic.getSubimage(0, startY, pic.getWidth(), desiredHeight);
		
		return croppedPic;
	}
	
	/**
	 * Increase an image's width by adding equal amounts from each side. 
	 * If the total amount to add on is an odd number of pixels, one less pixel will
	 * be added to the left side than the right, making the width increasing one pixel
	 * less than perfectly even.
	 * @param pic The {@link BufferedImage BufferedImage} to make wider.
	 * @param desiredWidth The desired width of the image. Should be greater than the 
	 * current width.
	 * @param bgColor The fill-in color for canvas expansions.
	 * @return The wider image.
	 */
	public static BufferedImage expandPicWidth(BufferedImage pic, int desiredWidth, Color bgColor) {
		int amtToAddLeft;

		amtToAddLeft = (desiredWidth - pic.getWidth()) / 2; //rounds down
		BufferedImage expandedPic = new BufferedImage(desiredWidth, pic.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics graphics = expandedPic.createGraphics();
		graphics.drawImage(pic, amtToAddLeft, 0, bgColor, null);
		graphics.dispose();
		
		return expandedPic;
	}
	
	/**
	 * Increase an image's height by adding equal amounts to each side. 
	 * If the total amount to add on is an odd number of pixels, one less pixel will
	 * be added to the top than the bottom, making the height increasing one pixel
	 * less than perfectly even.
	 * @param pic The {@link BufferedImage BufferedImage} to make taller.
	 * @param desiredWidth The desired height of the image. Should be greater than the 
	 * current height.
	 * @param bgColor The fill-in color for canvas expansions.
	 * @return The taller image.
	 */
	public static BufferedImage expandPicHeight(BufferedImage pic, int desiredHeight, Color bgColor) {
		int amtToAddTop;

		amtToAddTop = (desiredHeight - pic.getHeight()) / 2; //rounds down
		BufferedImage expandedPic = new BufferedImage(pic.getWidth(), desiredHeight, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = expandedPic.createGraphics();
		graphics.drawImage(pic, 0, amtToAddTop, bgColor, null);
		graphics.dispose();
		
		return expandedPic;
	}
	
	/**
	 * Resize an image's canvas.
	 * @param pic The {@link BufferedImage#BufferedImage(int, int, int) BufferedImage} whose canvas
	 * will be resized.
	 * @param desiredWidth The new width of the canvas. Will crop the image if smaller than actual
	 * width.
	 * @param desiredHeight The new height of the canvas. Will crop the image if smaller than actual
	 * height.
	 * @param bgColor The fill-in {@link Color Color} for canvas expansions.
	 * @return The {@code BufferedImage} with the new canvas size.
	 */
	public static BufferedImage resizePicCanvas(BufferedImage pic, int desiredWidth, int desiredHeight, Color bgColor) {
		// Change pic canvas to the given width and height
		BufferedImage resizedPic = null;
		
		if (pic.getWidth() == desiredWidth & pic.getHeight() == desiredHeight) {
			return pic;
		}
		
		if (pic.getWidth() < desiredWidth) {
			//Crop it down
			resizedPic = shrinkPicWidth(pic, desiredWidth);
		} else if (pic.getWidth() > desiredWidth) {
			//Expand it
			resizedPic = expandPicWidth(pic, desiredWidth, bgColor);
		}
		
		if (pic.getHeight() < desiredHeight) {
			//Crop it down
			resizedPic = shrinkPicHeight(pic, desiredHeight);
		} else if (pic.getHeight() > desiredHeight) {
			//Expand it
			resizedPic = expandPicHeight(pic, desiredHeight, bgColor);
		}
		
		return resizedPic;
	}
	
	/**
	 * Move an image horizontally within its canvas.
	 * @param pic The {@link BufferedImage BufferedImage} to move horizontally.
	 * @param amtMoveX The amount of pixels to move left or right.
	 * @param bgColor The fill-in {@link Color Color} for newly exposed areas of the canvas.
	 * @return The image moved left or right within its canvas.
	 */
	public static BufferedImage movePictureHoriz(BufferedImage pic, int amtMoveX, Color bgColor) {
		BufferedImage editedPic = new BufferedImage(pic.getWidth(), pic.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics graphics = editedPic.createGraphics();
		graphics.drawImage(pic, amtMoveX, 0, bgColor, null);
		graphics.dispose();
		
		return editedPic;
	}
	
	/**
	 * Move an image vertically within its canvas.
	 * @param pic The {@link BufferedImage BufferedImage} to move vertically.
	 * @param amtMoveX The amount of pixels to move up or down.
	 * @param bgColor The fill-in {@link Color Color} for newly exposed areas of the canvas.
	 * @return The image moved up or down within its canvas.
	 */
	public static BufferedImage movePictureVert(BufferedImage pic, int amtMoveY, Color bgColor) {
		BufferedImage editedPic = new BufferedImage(pic.getWidth(), pic.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics graphics = editedPic.createGraphics();
		graphics.drawImage(pic, 0, amtMoveY, bgColor, null);
		graphics.dispose();
		
		return editedPic;
	}
	
}
