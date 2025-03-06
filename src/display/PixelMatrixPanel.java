package display;

import lumenaer.PixelMatrix;
import java.awt.*;
import javax.swing.JPanel;

/**
 * Panel that holds a pixel matrix and adds some "decorations" around it,
 * so that it looks like the hardware lumenaer matrix
 * Important is that each pixel is scaled, otherwise the entire matrix would be
 * very small.
 */
public class PixelMatrixPanel extends JPanel {

	/* scale factor*/
	int scale;

	/* the pixel matrix to be displayed*/
	PixelMatrix matrix;

	/* double buffering */
	private Image offImg;

	/* for drawing everything*/
	private Graphics2D graphics2D;

	/**
	 * Constructs a panel that displays a pixel matrix with some decorations
	 *
	 * @param matrix the matix
	 * @param scale scale factor
	 */
	public PixelMatrixPanel(PixelMatrix matrix, int scale) {
		setDoubleBuffered(true);

		this.matrix = matrix;
		this.scale = scale;
	}

	public Dimension getPreferredSize() {
		return new Dimension(matrix.getHeight() * scale, matrix.getWidth() * scale);
	}

	/**
	 * double buffering of the Graphics instance
	 * @param g graphic object
	 */
	private void offscreenPaint( Graphics g ) {

		int width = matrix.getWidth();
		int height = matrix.getHeight();

		//draw Pixel
		for (int y = 0; y < height; y++) {
			int ycoord = y * scale;

			for (int x = 0; x < width; x++) {
				int xcoord = x * scale;
				g.setColor(new Color(matrix.getIntValue(y, x)));
				g.fillRect(xcoord, ycoord, scale, scale);
			}
		}

		//drawGrid
		g.setColor(Color.black);
		for (int i = 0; i < width * scale ; i+= scale) {
			g.drawLine(i,0,i, height * scale);
		}
		for (int i = 0; i < height * scale ; i+= scale) {
			g.drawLine(0,i, width * scale,i);
		}

	}

	public void paint(Graphics g) {

		if (offImg == null) {
			offImg = createImage(getSize().width, getSize().height);
			graphics2D = (Graphics2D) offImg.getGraphics();
		}

		offscreenPaint(graphics2D);
		g.drawImage(offImg, 0, 0, this);

	}

	public void update(Graphics g) {
		paint(g);
	}
}
