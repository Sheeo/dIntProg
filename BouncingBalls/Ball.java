import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends CircularActor implements DynamicActor
{
	private Vector canvasSize;
	private Vector vel;
	protected boolean hasMoved;
	/**
	 * The bounds of the ball's position vector.
	 */
	private Vector upperLeftBound;
	private Vector lowerRightBound;
	
	public Ball() {
		this(3,2);
	}
	public Ball(double velX, double velY) {
		vel = new Vector(velX, velY);
		upperLeftBound = new Vector(radius);
	}
	public void addedToWorld(World w) {
		canvasSize = new Vector(w.getWidth(), w.getHeight());
		lowerRightBound = canvasSize.subtract(new Vector(radius));
	}
	public void move() {
		moveBy(vel);
		setLocation(getLocation().clamp(upperLeftBound, lowerRightBound));
	}
	public void checkCollisions() {
		checkWallCollisions();
		checkBallCollisions();
	}
	/**
	 * Reflect the velocity when the ball is out of bounds.
	 * Multiply the velocity by the sign of the difference between the position
	 * vector and the upper left bound vector, and multiply the velocity vector
	 * by the sign of the difference between the lower right bound vector and the
	 * position vector.
	 * The sign of a vector is the vector whose coordinates are -1 or 1 depending
	 * on the signs of the coordinates of the vector. See Vector.sign().
	 */
	private Vector wallCollision() {
		// god I miss operator overloading
		return upperLeftBound.subtract(getLocation()).sign()
		.scale(getLocation().subtract(lowerRightBound).sign());
	}
	public void hasCollidedWithWall() {
		vel = vel.scale(wallCollision());
	}
	public void checkWallCollisions() {
		hasCollidedWithWall();
	}
	public void hasCollided(ShapeActor other) {
	}

	private ArrayList<Ball> getIntersectingBalls() {
		List intersecting = getIntersectingObjects(Ball.class);
		ArrayList<Ball> balls = new ArrayList<Ball>();
		for (Object o : intersecting) {
			Ball b = (Ball) o;
			double dist = b.getLocation().subtract(getLocation()).length();
			if (dist > radius*2) {
				continue;
			}
			double nextdist = b.getLocation().subtract(getLocation().add(vel)).length();
			if (nextdist > dist) {
				// we're moving away from the ball
				// pretend we're not intersecting it
				continue;
			}
			balls.add(b);
		}
		return balls;
	}

	private void checkBallCollisions() {
		ArrayList<Ball> balls = getIntersectingBalls();
		if (0 == balls.size()) {
			return;
		}
		// it's gay, balls are touching
		Ball other = balls.get(0); // only bounce off one
		double normal = other.getLocation().subtract(getLocation()).orthogonal().angle();
		Vector mirrored = Vector.fromPolar(2*normal-vel.angle(), other.vel.length());
		//System.out.println(this+" intersects "+other);
		//System.out.println(Math.round(180.0*vel.angle()/Math.PI)+" mirrored by "+Math.round(180.0*normal/Math.PI)+" to "+Math.round(180.0*mirrored.angle()/Math.PI));
		vel = mirrored;
	}

	public Vector getVelocity() {
		return vel;
	}
	public void setVelocity(Vector vel) {
		this.vel = vel;
	}
}
