package DegnRun;

import graphics.Color;
import graphics.RectangleGraphicElement;

/**
 * Obstacle that needs to be passed by the player to continue
 */
public class Obstacle extends RectangleGraphicElement {

    /**
     * Constructs an obstacle with the given parameters
     *
     * @param x      X coordinate
     * @param y      Y coordinate
     * @param width  Width of the obstacle
     * @param height Height of the obstacle
     * @param color Color of the obstacle
     */
    public Obstacle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    /**
     * updates position of the obstacle
     */
    @Override
    public void update() {
        this.x -= 2;
        if (this.x <= -2) {
            int randomX = (int) (Math.random() * 25 + 24);
            int randomY = (int) (Math.random() * 6 + 12);
            x = randomX;
            y = randomY;
        }
    }

    /**
     * set x coordinate
     *
     * @param newX new x coordinate
     */
    @Override
    public void setX(int newX) {
        this.x = newX;
    }
}

