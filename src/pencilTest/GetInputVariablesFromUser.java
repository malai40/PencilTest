package pencilTest;

import java.awt.Color;
import java.util.Scanner;

/**
 * <h1>Get Input Variables from User</h1>
 * <br> Ask the user for information for how to put the movie together.
 * <br> Currently gets inputs from the console.
 * @author malai40
 */
public class GetInputVariablesFromUser {
	 /*
	 * Questions to ask:
	 * 1) Where is the folder to use as input pictures?
	 * 2) Frame rate (fps)
	 * 2) Do you want to cleanse numbering? Y/N
	 * 3) Do you want to align by holes? Y/N
	 * 3a) If so, give me the location of the template holes.
	 * 3b) And give me the color you want any changed space to become. White or Black.
	 * 4a) Location for output movie
	 * 4b) Movie name
	 */
	private static Scanner scanner = new Scanner(System.in);
	
	static String inputFolderLoc;
	static int fps;
	static int holdDrawingHowManyFrames;
	static int repeatAnimationHowManyTimes;
	static boolean willCleanseNumbering;
	static boolean willAlignHoles;
	static String templateLoc = null;
	static Color bgColor = null;
	public static String outputMovieLoc;
	protected static String outputMovieName;
	
	/**
	 * Get the folder containing the images to compile into a movie.
	 * @return A {@link String String} representing the absolute path where the folder will be.
	 */
	private static String getInputFolder() {
		System.out.println("Input folder name:");
		
		inputFolderLoc = scanner.nextLine();
	    System.out.println(inputFolderLoc);
	    
	    return inputFolderLoc;
	}
	
	/**
	 * Get how many times the animation should repeat in the movie.
	 * <br>This must be 1 or greater. Put "1" if you don't want the animation to loop in the movie.
	 * @return An integer representing how many times the animation should repeat in the movie.
	 */
	private static int getRepeatAnimationHowManyTimes() {
		System.out.println("How many times should the animation repeat? Integers only:");
		
		repeatAnimationHowManyTimes = scanner.nextInt();
	    System.out.print(repeatAnimationHowManyTimes);
	    System.out.print(" times");
	    System.out.print("\n");
	    
	    return repeatAnimationHowManyTimes;
	}
	
	/**
	 * Get how many frames each drawing should be held for.
	 * <br>The input must take the form of "on ones", "on twos", or "on threes".
	 * <br>"On ones" means that each drawing should be held for one frame each.
	 * This is for very fluid animations and quick movements that aren't using smearing instead.
	 * This is good to use for detailed or complex animations in feature film-quality works.
	 * <br>"On twos" means that each drawing should be held for two frames each.
	 * This can be used for less complex animations in feature film-quality works, 
	 * and for normal animations in television-quality works.
	 * <br>"On threes" means that each drawing should be held for three frames each.
	 * This can be used for less complex animations in television-quality works.
	 * <br>Animating on fours, on fives, or lower should be used sparingly and only as necessary.
	 * <br>
	 * <br>The hold rate will be applied to all input drawings. 
	 * Further tweaking of each drawing's hold rate may be necessary.
	 * @return An integer representing how many frames each drawing will be held for.
	 */
	private static int getHoldFramesHowMany() {
		String animateHoldColloq = "N/A";
		while (!(animateHoldColloq.equals("on ones") 
				| animateHoldColloq.equals("on twos") 
				| animateHoldColloq.equals("on threes"))) {
			System.out.println("Animate on what? ('on ones', 'on twos', or 'on threes'):");
			animateHoldColloq = scanner.nextLine();
			if (animateHoldColloq.equals("on ones")) {
				holdDrawingHowManyFrames = 1;
			} else if (animateHoldColloq.equals("on twos")) {
				holdDrawingHowManyFrames = 2;
			} else if (animateHoldColloq.equals("on threes")) {
				holdDrawingHowManyFrames = 3;
			} else {
				System.out.println("Invalid answer provided.");
			}
		}
		System.out.println(animateHoldColloq);
	    
	    return holdDrawingHowManyFrames;
	}

	/**
	 * Get the desired frames per second (FPS) for the movie.
	 * <br>The default rate for hand-drawn animation is 24 FPS.
	 * <br>Other common FPS include 30, 60, and 120.
	 * @return An integer representing the frames to be played per second.
	 */
	private static int getFPS() {
		System.out.println("Input FPS (frames per second):");
		
		fps = scanner.nextInt();
		scanner.nextLine();
	    System.out.print(fps);
	    System.out.print(" frames per second");
	    System.out.print("\n");
	    
	    return fps;
	}
	
	/**
	 * Get whether or not the file name numbering should be cleansed so that images 
	 * are read in in the correct order.
	 * <br>For example, if there are 250 images in the input folder, "4.jpg" should be 
	 * renamed to "004.jpg", or else the image will be read in after "250.jpg".
	 * @return True if the program should cleanse the file numbering, and False if not.
	 */
	private static boolean getCleanseNumbering() {
		String ynAnswer = "?";
		while (!(ynAnswer.equals("Y") | ynAnswer.equals("N"))) {
			System.out.println("Would you like to cleanse the numbering of input files? Y/N:");
			ynAnswer = scanner.nextLine();
			if (ynAnswer.equals("Y")) {
				willCleanseNumbering = true;
			} else if (ynAnswer.equals("N")) {
				willCleanseNumbering = false;
			} else {
				System.out.println("Invalid answer provided.");
			}
		}
	    System.out.println(ynAnswer);
	    
	    return willCleanseNumbering;
	}
	
	/**
	 * Get whether or not to align images according to a registration hole template.
	 * @return True if the program should align images, and False if not.
	 */
	private static boolean getAlignByHoles() {
		String ynAnswer = "?";
		while (!(ynAnswer.equals("Y") | ynAnswer.equals("N"))) {
			System.out.println("Would you like to align each image using registration holes? Y/N:");
			ynAnswer = scanner.nextLine();
			if (ynAnswer.equals("Y")) {
				willAlignHoles = true;
			} else if (ynAnswer.equals("N")) {
				willAlignHoles = false;
			} else {
				System.out.println("Invalid answer provided.");
			}
		}
	    System.out.println(ynAnswer);
	    
	    return willAlignHoles;
	}
	
	/**
	 * Get the registration hole template image location if the program will 
	 * align based on registration holes.
	 * @return A {@link String String} representing the absolute path to an image of the registration hole template.
	 */
	private static String getTemplateLoc() {
		System.out.println("Input template image location:");
		
		templateLoc = scanner.nextLine();
	    System.out.println(templateLoc);
	    
	    return templateLoc;
	}
	
	/**
	 * Get the background, fill-in color to use when moving an image around on its canvas.
	 * <br> This can only be black or white.
	 * @return The {@link Color Color} to use for filling in leftover canvas after 
	 * canvas manipulation.
	 */
	private static Color getBgColor() {
		String bgColorAnswer = "?";
		while (!(bgColorAnswer.equals("Black") 
				| bgColorAnswer.equals("black") 
				| bgColorAnswer.equals("BLACK") 
				| bgColorAnswer.equals("White") 
				| bgColorAnswer.equals("white") 
				| bgColorAnswer.equals("WHITE"))) {
			System.out.println("Should the background color used for realignment be Black or White?");
			bgColorAnswer = scanner.nextLine();
			if (bgColorAnswer.equals("Black") | bgColorAnswer.equals("black") | bgColorAnswer.equals("BLACK")) {
				bgColor = Color.BLACK;
			} else if (bgColorAnswer.equals("White") | bgColorAnswer.equals("white") | bgColorAnswer.equals("WHITE")) {
				bgColor = Color.WHITE;
			} else {
				System.out.println("Invalid answer provided.");
			}
		}
	    System.out.println(bgColorAnswer);
	    
	    return bgColor;
	}

	/**
	 * Get the folder where the final movie should be saved.
	 * @return A {@link String String} representing the folder to save the final movie in.
	 */
	private static String getOutputMovieLoc() {
		System.out.println("Output folder to save final movie in:");
		
		outputMovieLoc = scanner.nextLine();
	    System.out.println(outputMovieLoc);
	    
	    return outputMovieLoc;
	}
	
	/**
	 * Get the desired name of the final movie, including the file type. 
	 * This will not include the path to the movie, just the name and file type, 
	 * e.g. "FinalMovie.mov".
	 * @return A {@link String String} representing the name and file type of the final movie.
	 */
	private static String getOutputMovieName() {
		System.out.println("Input movie name and desired format (e.g. 'movie.mov'):");
		
		outputMovieName = scanner.nextLine();
	    System.out.println(outputMovieName);
	    
	    return outputMovieName;
	}
	
	/**
	 * Get the file type of images to use. 
	 * <br>The program will only take in images in the same file format as the 
	 * supplied image in the folder.
	 * @param firstPicName The absolute path to the picture whose file type should be emulated. 
	 * Ideally this will be the first image to appear in the animation, and the lowest-numbered 
	 * image in the folder of input images.
	 * @return A {@link String String} representing the desired file type.
	 */
	public static String getDesiredFileType(String firstPicName) {
		/* User will declare the file type to look at */
		/* Guess that the file type to use is the one of the first file in the folder */
		/* User may not mix file types for one movie */
		String desiredInputPicFileType = GetInfoBasedOnFileNames.getFileType(firstPicName);
		/* TODO Ask user if they want to pick different file type to use */
		
		return desiredInputPicFileType;
	}
	
	/**
	 * Get all inputs to run the program. The user will supply these inputs in the console.
	 * @return Nothing.
	 */
	protected static void getAllInputs() {
		getInputFolder();
		getFPS();
		getHoldFramesHowMany();
		getRepeatAnimationHowManyTimes();
		getCleanseNumbering();
		if (getAlignByHoles()) {
			getTemplateLoc();
			getBgColor();
		}
		getOutputMovieLoc();
		getOutputMovieName();
	
		scanner.close();
	}
	
}
