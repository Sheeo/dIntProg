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
	/**
	 * The four walls of the world.
	 */
	public enum Walls {
		NORTH, EAST, SOUTH, WEST // CSS order
	};

	/**
	 * The gravity. A vector that is added to each DynamicActor's velocity on
	 * each act().
	 */
	protected Vector gravity;

	/**
	 * The frictional constant. Each DynamicActor's velocity is scaled by this
	 * constant on each act().  If 1.0 or null, the world is frictionless.
	 * Friction in the range (0.0, 1.0) is a frictional world. Friction greater
	 * than 1 or less than 0 is highly unstable.
	 */
	protected Double friction;

	/**
	 * A mouse listener.
	 */
	protected Mouse mouse;

	/**
	 * Amount of attraction to the mouse pointer. If null or 0.0, no attraction
	 * to the mouse is applied. Greater than zero is attraction towards the mouse
	 * pointer. Less than zero is attraction away from the mouse pointer (or is
	 * that disgust?).
	 */
	protected Double mouseAttraction;

	/**
	 * Initialise a world with the default size.
	 */
	public PhysicsWorld() {	
		this(540, 480);
	}

	/**
	 * Initialise a world of the given size.
	 */
	public PhysicsWorld(int width, int height) {
		super(width, height, 1);
		setGravity(Vector.zero());
		mouse = new Mouse();
		mouseAttraction = null;
	}

	public void setGravity(Vector v) {
		gravity = v;
	}

	/**
	 * Set gravity to a vertical vector. (For easier manipulation in Greenfoot.)
	 */
	public void setDownwardsGravity(double g) {
		gravity = new Vector(0, g);
	}

	public Vector getGravity() {
		return gravity;
	}

	/**
	 * Friction getter and setters.
	 */
	public void setFriction(double fric) {
		friction = fric;
	}
	public Double getFriction() {
		return friction;
	}
	public void clearFriction() {
		friction = null;
	}

	/**
	 * Set mouse attraction to an amount or null.
	 */
	public void setMouseAttraction(Double attraction) {
		mouseAttraction = attraction;
	}

	/**
	 * The act() method. Iterate through all DynamicActors a couple of times,
	 * each time doing some stuff to each.
	 */
	public void act() {
		/* Allow the mouse listener to update. */
		mouse.act();

		/* Iterate through all objects, apply acceleration and make them move. */
		List objs = getObjects(DynamicActor.class);
		for (Object o : objs) {
			DynamicActor a = (DynamicActor) o;
			addGravity(a);
			addMouseAttraction(a);
			addFriction(a);
			a.move();
		}

		/* Allow each object to react on collisions. */
		for (Object o : objs) {
			DynamicActor a = (DynamicActor) o;
			checkCollisions(a);
		}

		/* Unstick the objects from each other and from the walls. */
		for (Object o : objs) {
			DynamicActor a = (DynamicActor) o;
			fixCollisions(a);
		}
	}

	/**
	 * Apply gravity to a DynamicActor.
	 */
	protected void addGravity(DynamicActor a) {
		a.setVelocity(a.getVelocity().add(getGravity()));
	}

	/**
	 * Apply mouse attraction to a DynamicActor.
	 */
	protected void addMouseAttraction(DynamicActor a) {
		if (mouseAttraction == null) {
			return;
		}
		Vector mousePos = mouse.getState().position;
		if (mousePos == null) {
			return;
		}
		a.addVelocity(mousePos.subtract(a.getLocation()).scale(mouseAttraction).setLength(mouseAttraction));
	}

	/**
	 * Apply friction to a DynamicActor.
	 */
	protected void addFriction(DynamicActor a) {
		if (friction == null) {return;}
		a.setVelocity(a.getVelocity().scale(friction));
	}

	/**
	 * Return the size of the world.
	 */
	public Vector getSize() {
		return new Vector(getWidth(), getHeight());
	}

	/**
	 * Allow a DynamicActor to act on various kinds of collisions.
	 */
	protected void checkCollisions(DynamicActor a) {
		checkWallCollisions(a);
		checkActorCollisions(a);
	}

	/**
	 * Allow a DynamicActor to act on collisions with the walls, if applicable.
	 */
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

	/**
	 * Allow a DynamicActor to act on collisions with other ShapeActors.
	 */
	private void checkActorCollisions(DynamicActor a) {
		List<ShapeActor> intersecting = a.getIntersectingActors();
		if (intersecting.isEmpty()) {
			return;
		}
		for (ShapeActor b : intersecting) {
			a.handleIntersection(b);
		}
	}

	/**
	 * Unstick a DynamicActor from the walls and other ShapeActors.
	 */
	private void fixCollisions(DynamicActor a) {
		fixActorCollisions(a);
		fixWallCollisions(a);
	}

	/**
	 * Unstick a DynamicActor from other ShapeActors.
	 */
	private void fixActorCollisions(DynamicActor a) {
		List<ShapeActor> intersecting = a.getIntersectingActors();

		for (ShapeActor b : intersecting) {
			Intersection i = a.getShape().intersection(b.getShape());
			if (!i.intersects) {
				continue;
			}

			if (b instanceof DynamicActor) {
				/* Both actors are movable, so move each equally (assume equal masses) */
				double dist = i.amount/2.0;
				Vector d = b.getLocation().subtract(a.getLocation());
				d = d.scale(dist/d.length());
				a.setLocation(a.getLocation().add(d));
				b.setLocation(b.getLocation().subtract(d));
			} else {
				/* The other actor is static, so move us all the way out */
				double dist = i.amount;
				System.out.println(dist);
				Vector d = b.getLocation().subtract(a.getLocation());
				d = d.scale(dist/d.length());
				a.setLocation(a.getLocation().add(d));
			}
		}
	}

	/**
	 * Unstick a DynamicActor from the walls, if applicable.
	 */
	private void fixWallCollisions(DynamicActor a) {
		Shape shape = a.getShape();
		Vector v1 = shape.size().scale(0.5);
		Vector v2 = getSize().subtract(v1);
		a.setLocation(a.getLocation().clamp(v1, v2));
	}
}
