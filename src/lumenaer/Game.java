package lumenaer;

import graphics.Color;
import graphics.GraphicElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Base class for all games. Own games must extend this class.
 *
 */
public abstract class Game {

    /* A pixel matrix, where to displayPixelMatrix the game.*/
    protected PixelMatrix pixelMatrix; // just for convenience

    /* Array for storing all individual graphic objects to be drawn */
    protected List<GraphicElement> graphicElements;

    /**
     * Constructs a game with a given pixel matrix to draw upon
     *
     * @param matrix the pixel matrix where the game should be drawn upon
     */
    public Game(PixelMatrix matrix) {
        pixelMatrix = matrix;
        pixelMatrix.setBackgroundColor(Color.WHITE);
        graphicElements = new LinkedList<>();
    }

    /**
     * The next game step. By default, just draws everything.
     * Game logic should be implemented in the derived classes.
     */
    public void nextGameStep() {

        // draw the background
        pixelMatrix.drawBackground();

        //draw every GraphicElement in the list on the pixelMatrix
        for (GraphicElement element : graphicElements) {
            element.update();
            element.render(pixelMatrix);
        }

    }

    /**
     * Abstract method that has to be implemented to handle
     * a click on the buzzer (i.e. the left button of a mouse is pressed)
     */
    public abstract void buzzered();

    /**
     * Abstract method that has to be implemented to handle
     * when the buzzer is released (i.e.the left button of a mouse is released)
     */
    public abstract void buzzerReleased();

    /**
     * Abstract method that determines what should happen when the
     * metal wheel is turned forward/backwards (i.e. the mouse wheel is turned)
     * @param rotationValue the rotaion value of the mouse wheel, semantic is:
     *                      negative values if the mouse wheel was rotated up/away from the user, and
     *                      positive values if the mouse wheel was rotated down/ towards the user
     */
    public abstract void wheelRotation(int rotationValue);
}
