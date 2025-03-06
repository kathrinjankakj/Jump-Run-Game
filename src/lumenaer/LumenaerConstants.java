package lumenaer;

/**
 * Global constants for configuring the hardware matrix
 */
public class LumenaerConstants {

	/////////////////////////
	// Versionnumber of Lumenaer
	////////////////////////
	public final static String VERSIONNUMBER = "4.0";

	/////////////////////////
	// real, physical screens set up
	////////////////////////

	public final int SINGLESCREENWIDTH = 8;
	public final int SINGLESCREENHEIGHT = 8;

	public final int NUMBEROFSCREENS_X = 3;
	public final int NUMBEROFSCREENS_Y = 3;

	public final int REALSCREENWIDTH = SINGLESCREENWIDTH * NUMBEROFSCREENS_X;
	public final int REALSCREENHEIGHT = SINGLESCREENHEIGHT * NUMBEROFSCREENS_Y;

	public final int PIXELCOUNT = REALSCREENHEIGHT * REALSCREENWIDTH;
	public final int SCREENCOUNT = NUMBEROFSCREENS_X * NUMBEROFSCREENS_Y;

	//////////////////////////////////////////
	// communication with the screen
	/////////////////////////////////////////
	public final int MESSAGESIZE = SINGLESCREENWIDTH * REALSCREENHEIGHT;

	/////////////////////////
	// FrameRate
	////////////////////////
	public final static int FPS = 10; //Frames per second
	public final static long MS_PER_FRAME = 1000 / FPS;

}
