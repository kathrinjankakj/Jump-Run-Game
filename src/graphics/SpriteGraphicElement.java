package graphics;

import lumenaer.PixelMatrix;

/**
 * Abstract base class for all sprite based graphic elements. A pixel matrix
 * (matrix of color objects) must be created (and probably initialized)
 * in the constructor.
 */
public abstract class SpriteGraphicElement extends GraphicElement {

	/*pixel matix where the spite information can be stored*/
	protected Color[][] sprite;

	/**
	 * Constructs a sprite graphic element a specified position, i.e. the spites
	 * origin (0|0) is at this position
	 *
	 * @param posX the x position
	 * @param posY the y position
	 */
	public SpriteGraphicElement(int posX, int posY) {
		super(posX,posY);
	}

	/**
	 * Copies the pixel of this sprite to the display matrix
	 * @param matrix the pixel Matrix
	 */
	public void render(PixelMatrix matrix) {

		// just copy the sprite to the current Array
		// only the ones that are not transparent
		if (sprite != null) {
			for (int i=0; i < sprite.length; i++ ) {
				for (int j=0; j < sprite[0].length; j++ ) {
					if (sprite[i][j] != Color.TRANSPARENT) {
						matrix.setPixel(y+i, x+j, sprite[i][j]);
					}
				}
			}
		}
	}

	/**
	 * @return the width of this sprite
	 */
	public int getWidth() {
		return sprite[0].length;
	}

	/**
	 * @return the height of this sprite
	 */
	public int getHeight() {
		return sprite.length;
	}

}
