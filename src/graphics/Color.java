package graphics;

/**
 * Class representing Colors. Currently only in R-G-B format.
 */
public class Color {

    private int red;
    private int green;
    private int blue;

    //Color constants for convenience
    public static final Color RED;
    public static final Color GREEN;
    public static final Color BLUE;
    public static final Color BLACK;
    public static final Color WHITE;
    public static final Color CYAN;
    public static final Color MAGENTA;
    public static final Color YELLOW;
    public static final Color TRANSPARENT;

    /**
     * Constructs a color with given component values
     * @param red the intensity of the red channel (0-255)
     * @param green the intensity of the green channel (0-255)
     * @param blue the intensity of the blue channel (0-255)
     */
    public Color(int red, int green, int blue) {
        this.red = red;       //TD: could be checked whether values are in range
        this.green = green;
        this.blue = blue;
    }

    /**
     * Transform the Red-Green-Blue Compontens of the color to a single color value (int)
     * This is possible since the value range used for each channel fits in a byte (however,
     * this data type is not available in java)
     *
     * @return the integer value representing the Color
     */
    public int getIntValue() {
        int rgb = red;
        rgb = (rgb << 8) + green;
        rgb = (rgb << 8) + blue;
        return rgb;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    static {
        RED = new Color(255,0,0);
        GREEN = new Color(0,255,0);
        BLUE = new Color(0,0,255);
        MAGENTA = new Color(255,0,255);
        CYAN = new Color(0,255,255);
        YELLOW = new Color(255,255,0);
        WHITE = new Color(255,255,255);
        BLACK = new Color(0,0,0);
        TRANSPARENT = new Color(-1,-1,-1);
    }
}
