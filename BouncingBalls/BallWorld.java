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

	public void addBall() {
		final int lowerX = (int) Math.round(ballRadius);
		final int lowerY = (int) Math.round(ballRadius);
		final int upperX = getWidth()-(int) Math.round(ballRadius);
		final int upperY = getHeight()-(int) Math.round(ballRadius);
		double direction = 2.0*Math.PI*r.nextDouble();
		double speed = 3.5+r.nextGaussian();
		addBall(lowerX+r.nextInt(upperX-lowerX), lowerY+r.nextInt(upperY-lowerY), speed*Math.cos(direction), speed*Math.sin(direction));
	}
	public void addBall(int x, int y, double vX, double vY) {
		addObject(new Ball(vX, vY), x, y);
	}
	public void addBall(Vector position) {
		addObject(new Ball(0.0, 0.0), (int) Math.round(position.x()), (int) Math.round(position.y()));
	}
}
