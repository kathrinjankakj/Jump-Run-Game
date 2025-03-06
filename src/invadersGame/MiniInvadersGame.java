package invadersGame;

import graphics.Color;
import lumenaer.Game;
import lumenaer.PixelMatrix;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MiniInvadersGame extends Game {

	private Invader invader;
	private Defender defender;
	private List<Projectile> projectiles;
	private int counter;

	public MiniInvadersGame(PixelMatrix matrix) {

		super(matrix);
		invader = new Invader(0, 3);
		defender = new Defender(15,20,3,1, Color.BLUE);

		graphicElements.add(invader);
		graphicElements.add(defender);

		projectiles = new LinkedList<>();

		pixelMatrix.setBackgroundColor(new Color(220,230,47));
		counter = 0;
	}

	@Override
	public void nextGameStep() {

		//check collision
		for (Projectile p : projectiles) {
			if (invader.intersects(p)) {
				p.explode();
				counter ++;
			}
		}

		// select references that can be removed (projectiles that are destroyed or flew
		// out of the screen...)
		// Be careful! Here an iterator must be used when traversing over the
		// projectiles array and deleting at the same time, otherwise an
		// ConcurrentModificationException might occur!
		Iterator<Projectile> projectileIterator = projectiles.iterator();
		while (projectileIterator.hasNext()) {
			Projectile p = projectileIterator.next();
			if (p.isDestroyed() || p.getY() < 0) {
				// this Element can be removed from the projectiles array,
				// but only via the iterator, because modifying the array
				// directly in the projectiles-Array would confuse the iterator
				// and java would quit with a ConcurrentModificationException
				projectileIterator.remove();
				// However, the element can be removed from the graphicElements-Array,
				// directly as we do not use an iterator there...
				graphicElements.remove(p);

			}
		}

		super.nextGameStep();
	}

	@Override
	public void buzzered() {

		// generate a new Projectile
		Projectile p = new Projectile(defender.getX()+1, defender.getY(),1,Color.BLACK);

		//"doppelte Buchhaltung": projectiles are in graphicElements to be rendered,
		// and in the projectiles-List to be managed better.
		graphicElements.add(p);
		projectiles.add(p);

	}

	@Override
	public void buzzerReleased() {
	}

	@Override
	public void wheelRotation(int rotationValue) {


		int newXValue = defender.getX() - rotationValue;

		if (newXValue < 0 ) {
			newXValue = 0;
		}

		if (newXValue + defender.getWidth() > 23) {
			newXValue = 24 - defender.getWidth();
		}
		defender.moveTo(newXValue, defender.getY()); // y-value stays the same
	}
}
