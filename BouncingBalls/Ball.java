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
	private boolean acceptMouse;
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

	boolean getMouseEnabled() {return acceptMouse;}
	public void enableMouse() {acceptMouse = true;}
	void disableMouse() {acceptMouse = false;}

	public void act() {
		if (acceptMouse) {handleMouse();}
	}

	private Vector heldOffset;
	private Vector lastKnownMouse;
	private void handleMouse() {
		MouseInfo mouseinfo = Greenfoot.getMouseInfo();
		Vector mouse = (mouseinfo == null) ? ((lastKnownMouse == null) ? Vector.zero() : lastKnownMouse) : new Vector(mouseinfo.getX(), mouseinfo.getY());
		lastKnownMouse = mouse;
		if (heldOffset == null) {
			if (Greenfoot.mousePressed(this)) {
				onMousePressed(mouse);
			}
			return;
		}
		if(System.getProperty("os.name").equals("Mac OS X"))
		{
			if (Greenfoot.mouseClicked(null) || mouseinfo != null && (mouseinfo.getActor() != this || mouseinfo.getButton() != 1)) {
				onMouseReleased();
				return;
			}
		}
		else
		{
			if (Greenfoot.mouseClicked(this) || mouseinfo != null && (mouseinfo.getActor() != this || mouseinfo.getButton() != 0)) {
				onMouseReleased();
				return;
			}
		}
		onMouseMoved(mouse);
	}
	private void onMousePressed(Vector mouse) {
		heldOffset = mouse.subtract(getLocation());
		System.out.println("Set held offset to "+heldOffset);
	}
	private void onMouseReleased() {
		heldOffset = null;
	}
	private void onMouseMoved(Vector mouse) {
		Vector moveTo = mouse.subtract(heldOffset);
		Vector newVel = moveTo.subtract(getLocation());
		double len = newVel.length();
		if (len > 2*radius) {
			newVel = newVel.scale(2.0*radius/len);
		}
		setVelocity(newVel);
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
		deflectOnBoundingBox(other);
		if (other instanceof ArkanoidBrick) {
			((ArkanoidBrick)other).hit();
		}
	}
	public void deflectOnBoundingBox(ShapeActor other) {
		Shape us = getShape();
		Shape them = other.getShape();
		Vector tl = them.bbox_tl();
		Vector br = them.bbox_br();
		Vector normal;
		//System.out.println(tl+" "+us.pos()+" "+br);
		if (tl.x() <= us.x() && us.x() <= br.x()) {
			normal = new Vector(1.0, 0.0);
		} else if (tl.y() <= us.y() && us.y() <= br.y()) {
			normal = new Vector(0.0, 1.0);
		} else {
			Vector tr = them.bbox_tr();
			Vector bl = them.bbox_bl();
			Vector point = tl;
			double dist = tl.subtract(us.pos()).length();
			double d2 = tr.subtract(us.pos()).length();
			if (d2 < dist) {dist = d2; point = tr;}
			d2 = br.subtract(us.pos()).length();
			if (d2 < dist) {dist = d2; point = br;}
			d2 = bl.subtract(us.pos()).length();
			if (d2 < dist) {dist = d2; point = bl;}
			normal = point.subtract(us.pos()).orthogonal();
			//System.out.println("We hit corner "+point+" (dist "+dist+")");
		}
		//System.out.println("Deflecting on "+normal);
		setVelocity(getVelocity().mirror(normal));
	}
	public void handleIntersection(Ball other) {
		Ball b = (Ball) other;
		Vector normal = b.getLocation().subtract(getLocation());
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

	public void collidedWithWall(PhysicsWorld.Walls wall) {
		if (wall == PhysicsWorld.Walls.NORTH || wall == PhysicsWorld.Walls.SOUTH) {
			setVelocity(getVelocity().scale(new Vector(1.0, -1.0)));
		} else {
			setVelocity(getVelocity().scale(new Vector(-1.0, 1.0)));
		}
	}
}
