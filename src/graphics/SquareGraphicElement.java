package graphics;

import lumenaer.PixelMatrix;

/**
 * Square graphic element with internal reference point at the upper left corner
 */
public class SquareGraphicElement extends GraphicElement {

    protected int sideLength;

    public SquareGraphicElement(int x, int y, int sideLength, Color color) {
        super(x, y);
        this.sideLength = sideLength;
        this.color = color;
    }

    public void render(PixelMatrix matrix) {
        for (int i = 0; i < sideLength; ++i) {
            for (int j = 0; j < sideLength; ++j) {
                matrix.setPixel(y+i, x+j, color);
            }
        }
    }


    public int getSideLength() {
        return sideLength;
    }

    public void setSideLength(int sideLength) {
        this.sideLength = sideLength;
    }

}
