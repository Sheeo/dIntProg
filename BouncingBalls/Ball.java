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
	protected Double wallDampen;
	protected Double ballDampen;
	/**
	 * The bounds of the ball's position vector.
	 */
	public Ball(String image, double velX, double velY) {
		super();
		if (image != null) {
			setImage(image);
		}
		wallDampen = ballDampen = 1.0;
		setVelocity(new Vector(velX, velY));
		size = new Vector(getImage().getWidth());
		radius = size.x()/2.0;
	}
	public Ball(String image) {
		this(image,3,2);
	}
	public Ball(double velX, double velY) {
		this(null, velX, velY);
	}
	public Ball() {
		this(null);
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
		handleIntersection(s);
		if (other instanceof ArkanoidBrick) {
			((ArkanoidBrick)other).hit();
		}
	}
	public void deflectOnBoundingBox(ShapeActor other) {
		Circle us = (Circle) getShape();
		Shape them = other.getShape();
		Vector normal = us.bboxIntersection(them).normal;
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
		Vector bounce;
		if (ballDampen == null) {
			bounce = tangent.scale(v1t).subtract(v1);
		} else {
			bounce = normal.scale(v1np*ballDampen).add(tangent.scale(v1t)).subtract(v1);
		}
		addVelocity(bounce);
	}

	public void handleIntersection(Shape s) {
		Vector normal = s.intersectionNormal(getShape());
		if (null != normal) {
			mirrorVelocity(normal.orthogonal());
		}
	}

	public void collidedWithWall(PhysicsWorld.Walls wall) {
		if (wallDampen == null) {
			setVelocity(Vector.zero());
			return;
		}
		Vector newvel;
		if (wall == PhysicsWorld.Walls.NORTH || wall == PhysicsWorld.Walls.SOUTH) {
			newvel = getVelocity().scale(new Vector(1.0, -1.0));
		} else {
			newvel = getVelocity().scale(new Vector(-1.0, 1.0));
		}
		newvel = newvel.scale(wallDampen);
		setVelocity(newvel);
	}

	public void setWallDampen(Double d) {
		wallDampen = d;
	}

	public void setBallDampen(Double d) {
		ballDampen = d;
	}
}
