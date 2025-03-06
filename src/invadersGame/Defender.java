package invadersGame;

import graphics.Color;
import graphics.RectangleGraphicElement;

/**
 * Class represents a Defender space ship (currently only a flat bar...)
 * could probably use some more logic to make it somehow different from an ordinary bar...
 */
public class Defender extends RectangleGraphicElement {

    /**
     * Constructs a Defender space ship
     *
     * @param x the x-position
     * @param y the y-position
     * @param width the width of this space ship
     * @param height the height of this space ship
     * @param color the color of this space ship
     */
    public Defender(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

}
