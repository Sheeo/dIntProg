import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * A world of balls.
 * 
 * @author Michael Søndergaard
 * @author Mathias Rav
 */
public class BallWorld extends PhysicsWorld
{
	protected Random r;
	protected final double ballRadius;

	/**
	 * Constructor for objects of class BallWorld.
	 */
	public BallWorld()
	{	
		super(640, 480);
		ballRadius = findRadius();
		r = new Random();
	}

	private double findRadius() {
		Ball b = new Ball();
		return b.radius;
	}

	public void addBalls(int amount) {
		for (int i = 0; i < amount; ++i) {
			addBall();
		}
	}

	public Ball addBall() {
		final int lowerX = (int) Math.round(ballRadius);
		final int lowerY = (int) Math.round(ballRadius);
		final int upperX = getWidth()-(int) Math.round(ballRadius);
		final int upperY = getHeight()-(int) Math.round(ballRadius);
		double direction = 2.0*Math.PI*r.nextDouble();
		double speed = 3.5+r.nextGaussian();
		return addBall(lowerX+r.nextInt(upperX-lowerX), lowerY+r.nextInt(upperY-lowerY), speed*Math.cos(direction), speed*Math.sin(direction));
	}
	public Ball addBall(int x, int y, double vX, double vY) {
		Ball b = new Ball(vX, vY);
		addObject(b, x, y);
		return b;
	}
	public Ball addBall(Vector position) {
		return addBall((int) Math.round(position.x()), (int) Math.round(position.y()), 0.0, 0.0);
	}
}
