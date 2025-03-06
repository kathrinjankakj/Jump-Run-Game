package graphics;

import lumenaer.PixelMatrix;

/**
 * Square graphic element with internal reference point at the center of the object
 * Side length of the square is
 *    1 for "radius" 0,
 *    3 for "radius" 1,
 *    5 for "radius" 2 and so on..
 *    i.e. in general 2*radius+1
 */
public class CenteredSquareGraphicElement extends GraphicElement {

    /* the "radius" of the square*/
    protected int radius;

    /**
     * Constructs a centered square graphic element
     * @param x x-coordinate of the square
     * @param y y-coordinate of the square
     * @param radius "radius" of element, plus the center pixel makes a side length of 2*radius+1
     * @param color color of the element
     */
    public CenteredSquareGraphicElement(int x, int y, int radius, Color color) {
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    public void render(PixelMatrix matrix) {
        int drawRadius = radius -1;
        for (int i = -drawRadius; i <= drawRadius; ++i) {
            for (int j=-drawRadius; j<= drawRadius; ++j) {
                matrix.setPixel(y+i, x+j, color);
            }
        }
    }

    /**
     * @return the "radius"
     */
    public int getRadius() {
        return radius;
    }

    /**
     * sets the "radius"
     * Side length of the square is 2*radius+1
     * @param radius the radius
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

}
