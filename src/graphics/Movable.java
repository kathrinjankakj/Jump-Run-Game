package graphics;

public interface Movable {

    /**
     * This function uses the object intrinsic parameters for moving the object
     */
    void move();

    /**
     * Moves the objects to specific coordinates (x/y)
     *
     * @param x the new x coordinate of the object
     * @param y the new y coordinate of the object
     */
    void moveTo(int x, int y);

}
