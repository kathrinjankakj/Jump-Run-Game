package lumenaer;

import graphics.Color;

/**
 * Class representing the pixel matrix. Can be displayed on various output media,
 * e.g. either a real HW pixel matrix, or in a software window on a computer screen.
 * This is completely abstracted from the output format.
 */
public class PixelMatrix {

	/* The actual storage of the color pixels*/
	private Color[][] pixelArray;

	/* The background color for the pixel matrix*/
	private Color backgroundColor;

	/**
	 * Constructs a pixel matrix with a given width and height (in pixel)
	 * Default background color is white
	 *
	 * @param height of the matrix in pixels
	 * @param width of the matrix in pixels
	 */
	public PixelMatrix(int height, int width) {
		this(height,width,Color.WHITE);
	}

	/**
	 * Constructs a pixel matrix with a given width and height (in pixel)
	 *
	 * @param height of the matrix in pixels
	 * @param width of the matrix in pixels
	 * @param backgroundColor of the matrix
	 */
	public PixelMatrix(int height, int width, Color backgroundColor) {
		pixelArray = new Color[height][width];
		this.backgroundColor = backgroundColor;
		drawBackground();
	}

	/**
	 * @return the height of the pixel matrix
	 */
	public int getHeight() {
		return pixelArray.length;
	}

	/**
	 * @return the width of the pixel matrix
	 */
	public int getWidth() {
		return pixelArray[0].length;
	}

	/**
	 * get the Color of a particular pixel, might throw a NullPointerException
	 * if the index is out of bounds
	 * @param y the y-coordinate of the pixel
	 * @param x the x-coordinate of the pixel
	 * @return the color of the particular pixel
	 */
	public Color getPixel(int y, int x) {
		return pixelArray[y][x]; //TD index could be checked and ArrayIndexOutOfBoundsExpetion thrown
	}

	/**
	 * Returns the the integer value representing the color at a given location in the matrix
	 * @param y y-position of the pixel
	 * @param x x-position of the pixel
	 * @return the integer value representing the color
	 */
	public int getIntValue(int y, int x) {
		return pixelArray[y][x].getIntValue();
	}

	/**
	 * sets a pixel at a specified location to a given value
	 * if the given value is Color.TRANSPARENT, the pixel retains its value
	 *
	 * @param y y-position of the pixel
	 * @param x x-position of the pixel
	 * @param color the color of the pixel
	 */
	public void setPixel(int y, int x, Color color) {
		// only set a pixelArray if possible and not transparent
		if (y >= 0 && y < pixelArray.length &&
				x >= 0 && x < pixelArray[0].length &&
				color != Color.TRANSPARENT) {
			pixelArray[y][x] = color;
		}
	}

	/**
	 * Sets the color values of all pixels to the background color
	 */
	public void drawBackground() {
		for (int y=0; y < getHeight(); y++) {
			for (int x=0; x < getWidth(); x++) {
				setPixel(y, x, backgroundColor);
			}
		}		
	}

	/**
	 * Linearizes the 2D Pixel Matrix in a 1D integer array used for serial
	 * communication with the HW-Matrix. The individual pixels represent
	 * a color value each, the position in the 1D array codes the exact position
	 * in the HW matrix (can be decoded there).
	 *
	 * @return linearized pixel information
	 */
	int[] getPixels() {
		int[] linearPixels = new int[pixelArray[0].length * pixelArray.length];
		int idx = 0;
		for (int y=0; y<getHeight(); y++) {
			for (int x=0; x<getHeight(); x++) {
				linearPixels[idx] = pixelArray[y][x].getIntValue();
				idx++;
			}
		}
		return linearPixels;
	}

	/**
	 * @return the background color
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * sets the background color of the pixel matrix
	 * @param backgroundColor the background color
	 */
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
