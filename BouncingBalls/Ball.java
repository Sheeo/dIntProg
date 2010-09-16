import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Write a description of class Ball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ball extends DynamicActor
{
	final double radius;
	/**
	 * The bounds of the ball's position vector.
	 */
	public Ball(double velX, double velY) {
		super();
		setVelocity(new Vector(velX, velY));
		size = new Vector(getImage().getWidth());
		radius = size.x()/2.0;
	}
	public Ball() {
		this(3,2);
	}
	Shape getShape() {
		return new Circle(getLocation(), radius);
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
			double nextdist = b.getLocation().add(b.getLastVelocity()).subtract(getLocation().add(getLastVelocity())).length();
			if (nextdist > dist) {
				// pretend we don't intersect with the ball
				continue;
			}
			balls.add(b);
		}
		return balls;
	}

	public void handleIntersection(ShapeActor other) {
		if (other instanceof Ball) {
			handleIntersection((Ball) other);
			return;
		}
		Shape s = other.getShape();
		if (s instanceof RoundedRectangle) {
			handleIntersection((RoundedRectangle) s);
		}
		deflectOnBoundingBox(other);
		if (other instanceof ArkanoidBrick) {
			((ArkanoidBrick)other).hit();
		}
	}
	public void deflectOnBoundingBox(ShapeActor other) {
		Circle us = (Circle) getShape();
		Shape them = other.getShape();
		Vector normal = us.bboxDeflectionNormal(them);
		mirrorVelocity(normal);
	}
	public void mirrorVelocity(Vector normal) {
		setVelocity(getVelocity().mirror(normal));
	}
	public void handleIntersection(Ball other) {
		Ball b = (Ball) other;
		Vector normal = other.getShape().intersectionNormal(getShape()).unit();
		normal = normal.scale(1.0/normal.length());
		Vector tangent = normal.orthogonal();
		Vector v1 = getLastVelocity();
		Vector v2 = b.getLastVelocity();
		//double m1 = getMass();
		//double m2 = other.getMass();
		double v1n = v1.dotP(normal);
		double v2n = v2.dotP(normal);
		double v1t = v1.dotP(tangent);
		//double v2t = v2.dotP(tangent);

		//double v1np = v1n*(m1-m2)/(m1+m2)+2.0*m2*v2n/(m1+m2);
		double v1np = v2n; // for m1 = m2
		addVelocity(normal.scale(v1np).add(tangent.scale(v1t)).subtract(v1));
	}

	public void handleIntersection(Shape s) {
		Vector normal = s.intersectionNormal(getShape());
		if (null != normal) {
			mirrorVelocity(normal.orthogonal());
		}
	}

	public void collidedWithWall(PhysicsWorld.Walls wall) {
		if (wall == PhysicsWorld.Walls.NORTH || wall == PhysicsWorld.Walls.SOUTH) {
			setVelocity(getVelocity().scale(new Vector(1.0, -1.0)));
		} else {
			setVelocity(getVelocity().scale(new Vector(-1.0, 1.0)));
		}
	}
}
