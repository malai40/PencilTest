package pencilTest;

/**
 * <h1>Pencil Test</h1>
 * <br> <i>Pencil Test</i> is a program that compiles drawings into an animated movie to 
 * preview an animation, known as a <i>pencil test</i>. 
 * <br>
 * <br> The program is still in development, and as such should not yet be considered stable. 
 * Make backups of any input images before using this program.
 * <br>
 * <br> This program is intended for previewing animations before re-creating them in more 
 * robust video editing programs for final products. 
 * Results when using videos created with this program as final movies cannot be guaranteed.
 * <br>
 * <br> This program does not currently support sound. 
 * The expectation is that the movie file created will be loaded in to a more robust video editing 
 * program if it needs to be paired with sound. 
 * <br>Future versions of this program may include sound capability on operating 
 * systems for which the Java Media Framework (JMF) supports sound, such as Windows and Solaris SPARC.
 * This program does not, and will probably never, support sound on operating systems for which JMF 
 * does not support sound, such as Linux, macOS, and Mac OS X. 
 * <br>
 * <br> Written using Java 1.8 in Eclipse IDE.
 * <br>
 * @author malai40
 * @version Version 0.1.0
 */
public class Main {
/** The main method to execute the entire program.
 * <br>The only correct way to run the entire program.
 * <br>This is the method accessed when the .jar executable is run.
 * @param String[] args Normal convention for the main method of a Java program.

 */
	public static void main(String[] args) {
		try {
			/*//Use the lines below for automatically feeding in inputs for testing.
			 * String inputFolderLoc = "(Frame Input Folder Location)";
			 * String templateLoc = "(Template Location)";
			 * int fps = 24;
			 * int holdDrawingHowManyFrames = 1;
			 * int repeatAnimationHowManyTimes = 5;
			 * String outputLocationMovie = "(Desired Movie Location)";
			 * boolean willCleanseNumbering = true;
			 * boolean willAlignHoles = true;
			 * Color bgColor = Color.WHITE;
			 */

			GetInputVariablesFromUser.getAllInputs();
			String outputLocationMovie = 
					GetInputVariablesFromUser.outputMovieLoc 
					+ "/"
					+ GetInputVariablesFromUser.outputMovieName;
			
			String tempFolder = 
					BuildMovieWithParameters.makeVideo(
							GetInputVariablesFromUser.inputFolderLoc, 
							GetInputVariablesFromUser.fps,
							GetInputVariablesFromUser.holdDrawingHowManyFrames, 
							GetInputVariablesFromUser.repeatAnimationHowManyTimes, 
							outputLocationMovie,
							GetInputVariablesFromUser.templateLoc,
							GetInputVariablesFromUser.willCleanseNumbering,
							GetInputVariablesFromUser.willAlignHoles,
							GetInputVariablesFromUser.bgColor);
			
			// When program closes, delete everything in the temp folder
			FileFolderCleanup.deleteFolderAndFiles(tempFolder); 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
