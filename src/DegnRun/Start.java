package DegnRun;

import graphics.Color;
import lumenaer.Game;
import lumenaer.PixelMatrix;

/**
 * Start screen, starts the game
 */
public class Start extends Game {

    /**
     * creates new character
     */
    private final Degen degen = new Degen();
    /**
     * boolean if game started
     */
    private boolean started = false;
    /**
     * boolean if game ended is set to false
     */
    private boolean dead = false;
    /**
     * creates the background element door
     */
    private final BackgroundElements door = new BackgroundElements(11, 6, "door");
    /**
     * creates the background element posters
     */
    private final BackgroundElements posters = new BackgroundElements(13, 15, "poster");
    /**
     * creates the background element wall
     */
    private final BackgroundElements wall = new BackgroundElements(0, 0, "wall");
    /**
     * creates the background element title
     */
    private final BackgroundElements title = new BackgroundElements(0, 0, "title");
    /**
     * creates the background element start
     */
    private final BackgroundElements start = new BackgroundElements(14, 9, "start");
    /**
     * creates the background element button
     */
    private final BackgroundElements button = new BackgroundElements(20, 13, "button");
    /**
     * creates the background element text if the character is dead
     */
    private final BackgroundElements textDead = new BackgroundElements(0, 0, "textDead");
    /**
     * creates the background element start if the character is dead
     */
    private final BackgroundElements startDead = new BackgroundElements(14, 9, "startDead");
    /**
     * creates the background element wall if the character is dead
     */
    private final BackgroundElements wallDead = new BackgroundElements(0, 0, "wallDead");
    /**
     * creates an obstacle
     */
    private final Obstacle obstacle = new Obstacle(30, 18, 2, 2, Color.BLACK);
    /**
     * creates another obstacle
     */
    private final Obstacle obstacle2 = new Obstacle(50, 15, 2, 2, Color.RED);

    /**
     * Constructs a game with a given pixel matrix to draw upon
     *
     * @param matrix the pixel matrix where the game should be drawn upon
     */
    public Start(PixelMatrix matrix) {
        super(matrix);
        pixelMatrix.setBackgroundColor(Color.WHITE);

        BackgroundElements floor = new BackgroundElements(0, 0, "floor");
        graphicElements.add(floor);
        graphicElements.add(wall);
        graphicElements.add(start);
        graphicElements.add(title);
        graphicElements.add(button);
        graphicElements.add(degen);
    }

    /**
     * start the game, restart it or jump when pressing the button, depending on the current state
     */
    @Override
    public void buzzered() {
        if (!started && dead) {
            dead = false;
            started = true;
            this.removeElementEndScreen();
            this.addElementGamePlay();
        } else if (!started) {
            started = true;
            this.removeElementStartScreen();
            this.addElementGamePlay();
        } else if (degen.getY() == 16) {
            degen.setToJump();
        }
        // Double Jump
        if (degen.getJumpCount() == 1 && started && degen.getY() <= 12) {
            degen.setDoubleJump();
        }
    }

    /**
     * unused method
     */
    @Override
    public void buzzerReleased() {
    }

    /**
     * character sliding when spinning the button
     */
    @Override
    public void wheelRotation(int rotationValue) {
        if (started) degen.setToSlide();
    }

    /**
     * character dies when hitting an obstacle, game is over
     */
    @Override
    public void nextGameStep() {
        if (degen.hits(obstacle) || degen.hits(obstacle2)) {
            dead = true;
            started = false;
            this.removeElementGamePlay();
            this.addElementEndScreen();
            obstacle.setX(30);
            obstacle2.setX(50);
        } else super.nextGameStep();
    }

    /**
     * adds background elements and obstacles when the game starts
     */
    public void addElementGamePlay() {
        graphicElements.add(posters);
        graphicElements.add(door);
        graphicElements.add(degen);
        graphicElements.add(obstacle);
        graphicElements.add(obstacle2);
    }

    /**
     * removes the background elements and obstacles when game ends
     */
    public void removeElementGamePlay() {
        graphicElements.remove(posters);
        graphicElements.remove(door);
        graphicElements.remove(degen);
        graphicElements.remove(obstacle);
        graphicElements.remove(obstacle2);
    }

    /**
     * removes the background elements of the start screen when the game starts
     */
    public void removeElementStartScreen() {
        graphicElements.remove(wall);
        graphicElements.remove(start);
        graphicElements.remove(title);
        graphicElements.remove(button);
        graphicElements.remove(degen);
    }

    /**
     * adds background elements when the game ends
     */
    public void addElementEndScreen() {
        graphicElements.add(wallDead);
        graphicElements.add(textDead);
        graphicElements.add(startDead);
        graphicElements.add(button);
        graphicElements.add(degen);
    }

    /**
     * removes the background elements of the end screen when game starts
     */
    public void removeElementEndScreen() {
        graphicElements.remove(degen);
        graphicElements.remove(wallDead);
        graphicElements.remove(textDead);
        graphicElements.remove(startDead);
        graphicElements.remove(button);
    }
}