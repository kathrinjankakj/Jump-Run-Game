package invadersGame;

import graphics.Color;
import graphics.SpriteGraphicElement;

/**
 * Class representing an invading space ship
 */
public class Invader extends SpriteGraphicElement {

	/**
	 * determines the maximum x position the object can move to,
	 * so that it does not move out of the screen
	 */
	private int maxX;

	/**
	 * Constructs an invading space ship at position posX, posY on the pixel
	 * matrix. The graphic representation is stored inside the sprite matrix
	 * (has to be initialized here in this constructor!)
	 * @param posX the x position
	 * @param posY the y position
	 */
	public Invader(int posX, int posY) {
		super(posX, posY);
		speedX = 1;  //move right
		sprite = new Color[2][3]; // sprite matrix must be created here
		maxX = 21; //outermost right position

		Color bodyColor = new Color (30,30,100);

		sprite[0][0] = Color.TRANSPARENT;
		sprite[0][1] = bodyColor;
		sprite[0][2] = Color.TRANSPARENT;

		sprite[1][0] = bodyColor;
		sprite[1][1] = bodyColor;
		sprite[1][2] = bodyColor;

	}

	public void update() {
		move();
	}

	public void move() {
		int nx = x + speedX;

		if (nx < 0 ) {
			nx = 0;
			speedX *= -1;
		} else if (nx > maxX) {
			nx = maxX;
			speedX *= -1;
		}
		setX(nx);
	}

	/**
	 * Function determines whether the invading space ship intersects with
	 * a projectile
	 *
	 * @param projectile an projectile instance
	 * @return whether an intersection takes place
	 */
	public boolean intersects(Projectile projectile) {
		int py = projectile.getY();
		int px = projectile.getX();

		if (py < y || py > y + getHeight() || px < x || px > x + getWidth()) {
			return false;
		} else {
			return true;
		}
	}

}
