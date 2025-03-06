package graphics;

import lumenaer.PixelMatrix;

/**
 * Rectangle Graphic Element
 */
public class RectangleGraphicElement extends GraphicElement {

    /** the width of the rectangle*/
    protected int width;

    /** the height of the rectangle*/
    protected int height;

    /**
     * Constructs a graphic rectangle with a given width, height and color
     * The default position is (0|0)
     *
     * @param width the width
     * @param height the height
     * @param color the color
     */
    public RectangleGraphicElement(int width, int height, Color color) {
       this(0,0,width,height,color);
    }

    /**
     * Constructs a graphic rectangle with a given width, height and color
     *
     * @param x the x-coordinte
     * @param y the y-coordinate
     * @param width the width
     * @param height the height
     * @param color the color
     */
    public RectangleGraphicElement(int x, int y, int width, int height, Color color) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void render(PixelMatrix matrix) {
        for (int i = 0; i< height; ++i) {
            for (int j=0; j<width; ++j) {
                matrix.setPixel(y+i, x+j, color);
            }
        }
    }

    /**
     * @return the width of the rectangle
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width of the rectangle
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets the height of the rectangle
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

}
