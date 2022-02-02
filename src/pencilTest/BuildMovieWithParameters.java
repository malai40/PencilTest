package pencilTest;

import java.awt.Color;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Vector;
import javax.media.MediaLocator;

/**
 * <h1>BuildMovieWithParameters</h1>
 * <br>A class with methods to create the final movie based on inputs. 
 * @author malai40
 */
public class BuildMovieWithParameters {
	public static int vidWidth;
	public static int vidHeight;
	
	/**
	 * Make the video.
	 * @param inputDrawingsFolder Folder with drawings to compile into the final movie.
	 * @param framesPerSecond The desired frame ate in frames per second (fps).
	 * @param onWhats The number of frames that each drawing will be held for.
	 * @param TimesToRepeat The number of times to repeat the animation.
	 * @param VideoOutputPath The absolute path where the video will be saved, including the video name.
	 * @param templateLoc The absolute path to an image of the registration hole template.
	 * @param willCleanseNumbering Whether or not to cleanse numbering of input files.
	 * @param willAlignHoles Whether or not to align images based on registration holes.
	 * @param bgColor The background color to use as a fill on blank areas left over after moving an image around on a canvas.
	 * @return The location of the temp folder where temp files are stored.
	 * @throws IOException
	 */
	public static String makeVideo(
			String inputDrawingsFolder, 
			int framesPerSecond,
			int onWhats, 
			int TimesToRepeat, 
			String VideoOutputPath, 
			String templateLoc, 
			boolean willCleanseNumbering,
			boolean willAlignHoles,
			Color bgColor) 
					throws IOException {
		//Copy files over to temp folder
		//Only copy files that match the file type of the first picture
		String tempFolder = FileFolderCleanup.copyFilesToTemp(inputDrawingsFolder, "PencilTest");
		// System.out.println(tempFolder); //Keep
		
		FileFilter isNotHiddenFilter = new FileFilter() {
		      public boolean accept(File file) 
		      {
		    	  	if (file.getName().startsWith(".")) 
		    	  	{
		    	  		return false;
				}
				return true;
		      }
		};
		
		//Load in pictures as files
		File folder = new File(tempFolder);
		File[] listOfFiles = folder.listFiles(isNotHiddenFilter);
		
		if (willCleanseNumbering) {
			listOfFiles = FileFolderCleanup.cleanseInputPicNumbering(listOfFiles);
		}
		
		if (willAlignHoles) {
			AlignPictures.fixImages(tempFolder, templateLoc, bgColor);
		}
		
		Vector<String> imgLst = new Vector<String>();
		
		//Get the file type of the first image
		String fileType = GetInfoBasedOnFileNames.getFileType(listOfFiles[0].getAbsolutePath());
	
		for (int j = 0; j < TimesToRepeat; j++) {
			for (int i = 0; i < listOfFiles.length; i++) {
				// TODO Get the file type of the first image file found, then only take in imgs of same file type
				if (listOfFiles[i].getAbsolutePath().endsWith(fileType)) {
					for (int k = 0; k < onWhats; k++) {
						imgLst.add(listOfFiles[i].getAbsolutePath());
					}
				}
			}
		}
		
		//get vid width and height
		int[] vidWidthHeight = GetPictureInformation.getWidthHeightOfPic(listOfFiles[0].getAbsolutePath());
		vidWidth = vidWidthHeight[0];
		vidHeight = vidWidthHeight[1];
	
	    JpegImagesToMovie imagesToMovie = new JpegImagesToMovie();
	    MediaLocator oml;
	    
	    if ((oml = JpegImagesToMovie.createMediaLocator(VideoOutputPath)) == null) 
	    {
	        System.err.println("Cannot build media locator from: " + VideoOutputPath);
	        System.exit(0);
	    }
	    
	    imagesToMovie.doIt(vidWidth, vidHeight, framesPerSecond, imgLst, oml);
	
	    return tempFolder;
	    
	}
}
