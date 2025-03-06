package invadersGame;


import graphics.CenteredSquareGraphicElement;
import graphics.Color;

/**
 * Class representing a projectile, it is a square that is centered around its center
 * coordinate. When exploding, it changes its appearance in an animation.
 *
 */
public class Projectile extends CenteredSquareGraphicElement {

    private enum ExplodingState{START,WEG,GROSSFARBIG1,KLEINBLAU1,GROSSFARBIG2,KLEINBLAU2,END}

    private ExplodingState explodingState;
    private boolean destroyed;
    private boolean explode;
    private Color explodingColor;

    public Projectile(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
        setSpeedY(-1); // moves upwards
        explodingState = ExplodingState.START;
        boolean explode = false;
        boolean destroyed = false;
        explodingColor = new Color(255,0,0);
    }

    @Override
    public void update () {

        if (explode && !destroyed) {

            //animation for the explosion
            switch (explodingState) {
                case START:
                    setRadius(0);
                    explodingState = ExplodingState.WEG;
                    break;
                case WEG:
                    setRadius(2);
                    setColor(explodingColor);
                    explodingState = ExplodingState.GROSSFARBIG1;
                    break;
                case GROSSFARBIG1:
                    setRadius(1);
                    setColor(Color.BLUE);
                    explodingState=ExplodingState.KLEINBLAU1;
                    break;
                case KLEINBLAU1:
                    setRadius(2);
                    setColor(explodingColor);
                    explodingState=ExplodingState.GROSSFARBIG2;
                    break;
                case GROSSFARBIG2:
                    setRadius(1);
                    setColor(Color.BLUE);
                    explodingState=ExplodingState.KLEINBLAU2;
                    break;
                case KLEINBLAU2:
                    explodingState=ExplodingState.END;
                    destroyed = true;
            }
        } else {
            move();
        }
    }


    /**
     * Starts the explosion. Can not be stopped any more.
     */
    public void explode() {
        explode = true;
    }

    /**
     * Tells whether the projecile is already destroyed.
     * @return destroyed or not
     */
    public boolean isDestroyed() {
        return destroyed;
    }

}
