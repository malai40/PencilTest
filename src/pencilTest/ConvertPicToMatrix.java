package pencilTest;

import java.awt.image.BufferedImage;

/**
 * <h1>ConvertPicToMatrix</h1>
 * <br>A class with methods to convert a picture to a matrix of color values. 
 * @author malai40
 */
public class ConvertPicToMatrix {
	/**
	 * Convert a picture to a {@link Matrix#Matrix(int, int) Matrix} of grayscale values.
	 * @param pic The picture in {@link BufferedImage#BufferedImage(int, int, int) BufferedImage} 
	 * format to convert to a {@code Matrix} of grayscale values. The input picture can be in RGB (will
	 * be converted to grayscale) or in grayscale.
	 * @return Matrix A {@code Matrix} of values corresponding to the input picture converted to grayscale.
	 */
	public static Matrix convertPicToGrayscaleMatrix(BufferedImage pic) {
		int rgb, r, g, b, gray;
		/*
		int r;
		int g;
		int b;
		int gray;
		*/
		
		Matrix picMatrix = new Matrix(pic.getHeight(), pic.getWidth());
		
		for (int i = 0; i < picMatrix.getRows(); i++) {
			for (int j = 0; j < picMatrix.getCols(); j++) {
				//To convert from RGB to grayscale:
				
				rgb = pic.getRGB(j, i);
				r = (rgb >> 16) & 0xFF;
				g = (rgb >> 8) & 0xFF;
				b = (rgb) & 0xFF;
				gray = (r + g + b) / 3;
				picMatrix.setValueAt(i, j, gray);
				
				//If input image is already grayscale:
				//search.setValueAt(i, j, searchImg.getRGB(j, i)& 0xFF);
			}
		}
		
		return picMatrix;
		
	}
}
