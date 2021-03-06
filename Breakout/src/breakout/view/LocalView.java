package breakout.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import breakout.model.Ball;
import breakout.model.GameWorld;
import breakout.model.Paddle;

/**
 * View class for the local window.
 * 
 * @author armin
 *
 */
public class LocalView extends JPanel {

	private static final long serialVersionUID = 1L;

	private final GameWorld gw;
	private final double x;
	private final double y;

	// Scale we use to scale the world up.
	private static double scale = 40;

	/**
	 * Constructor for LocalView.
	 * 
	 * @param gw
	 *            GameWorld to show.
	 */
	public LocalView(GameWorld gw) {
		this.gw = gw;
		x = ((gw.getWidth()) * scale);
		y = ((gw.getHeight()) * scale);
		
		this.setPreferredSize(new Dimension((int) x, (int) y));
	}

	@Override
	/**
	 * Paint the components.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Draw a rectangle as border
		g.drawRect(0, 0, (int) x, (int) y);

		// Draw the ball
		Ball b = gw.getBall();
		g.setColor(Color.RED);
		g.fillOval((int) (b.getX() * scale), (int) (b.getY() * scale), (int) (b.getR() * 2 * scale),
				(int) (b.getR() * 2 * scale));

		// Draw paddle
		Paddle p = gw.getPaddle();
		g.setColor(Color.BLUE);
		g.fillRect((int) (p.getX() * scale), (int) (p.getY() * scale), (int) (p.getPw() * scale),
				(int) (p.getPh() * scale));

		// Draw the bricks
		for (int i = 0; i < gw.brickList.size(); i++) {
			// Draw brick border
			g.setColor(Color.BLACK);
			g.drawRect((int) (gw.brickList.get(i).getX() * scale), (int) (gw.brickList.get(i).getY() * scale),
					(int) (gw.brickList.get(i).getXw() * scale), (int) (gw.brickList.get(i).getYh() * scale));
			
			// Draw filled brick
			g.setColor(gw.brickList.get(i).getColour());
			g.fillRect((int) (gw.brickList.get(i).getX() * scale + 1), (int) (gw.brickList.get(i).getY() * scale + 1),
					(int) (gw.brickList.get(i).getXw() * scale - 1), (int) (gw.brickList.get(i).getYh() * scale - 1));
		}
	}

	/**
	 * Getter for the scale value.
	 * 
	 * @return scale
	 */
	public double getScale() {
		return scale;
	}

}
