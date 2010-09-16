import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * A world of highly realistic physics.
 * 
 * @author Michael Søndergaard
 * @author Mathias Rav
 */
public class PhysicsWorld extends World
{
	protected Vector gravity;
	protected Double friction;
	public enum Walls {
		NORTH, EAST, SOUTH, WEST // CSS order
	};

	public PhysicsWorld() {	
		this(540, 480);
	}

	public PhysicsWorld(int width, int height) {
		super(width, height, 1);
		setGravity(Vector.zero());
	}

	public void setGravity(Vector v) {
		gravity = v;
	}
	public void setDownwardsGravity(double g) {
		gravity = new Vector(0, g);
	}
	public Vector getGravity() {
		return gravity;
	}
	public void setFriction(double fric) {
		friction = fric;
	}
	public Double getFriction() {
		return friction;
	}
	public void clearFriction() {
		friction = null;
	}
	public void act() {
		List objs = getObjects(DynamicActor.class);
		for (Object o : objs) {
			DynamicActor a = (DynamicActor) o;
			a.move();
		}
		for (Object o : objs) {
			DynamicActor a = (DynamicActor) o;
			checkCollisions(a);
			addGravity(a);
			addFriction(a);
			fixCollisions(a);
		}
	}
	protected void addGravity(DynamicActor a) {
		a.setVelocity(a.getVelocity().add(getGravity()));
	}
	protected void addFriction(DynamicActor a) {
		if (friction == null) {return;}
		a.setVelocity(a.getVelocity().scale(friction));
	}
	public Vector getSize() {
		return new Vector(getWidth(), getHeight());
	}
	protected void checkCollisions(DynamicActor a) {
		checkWallCollisions(a);
		checkActorCollisions(a);
	}
	private void checkWallCollisions(DynamicActor a) {
		Vector size = getSize();
		Shape shape = a.getShape();
		EnumSet<PhysicsWorld.Walls> intersection = shape.wallIntersection(size);
		if (intersection.isEmpty()) {
			return;
		}
		for (PhysicsWorld.Walls w : intersection) {
			a.collidedWithWall(w);
		}
	}
	private void checkActorCollisions(DynamicActor a) {
		List<ShapeActor> intersecting = a.getIntersectingActors();
		if (intersecting.isEmpty()) {
			return;
		}
		for (ShapeActor b : intersecting) {
			if (b instanceof DynamicActor) {
				DynamicActor d = (DynamicActor) b;
				double dist = a.getLocation().subtract(d.getLocation()).length();
				double nextdist = a.getLocation().add(a.getLastVelocity()).subtract(d.getLocation().add(d.getLastVelocity())).length();
				if (nextdist > dist) {
					continue;
				}
			}
			a.handleIntersection(b);
		}
	}
	private void fixCollisions(DynamicActor a) {
		fixActorCollisions(a);
		fixWallCollisions(a);
	}
	private void fixActorCollisions(DynamicActor a) {
		// ...
	}
	private void fixWallCollisions(DynamicActor a) {
		Shape shape = a.getShape();
		Vector v1 = shape.size().scale(0.5);
		Vector v2 = getSize().subtract(v1);
		a.setLocation(a.getLocation().clamp(v1, v2));
	}
}
