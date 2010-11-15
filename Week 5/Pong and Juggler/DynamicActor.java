import greenfoot.*;
/**
 * A base class for Actors that have a Shape, collide with other ShapeActors,
 * and move by adding a velocity to their position.
 *
 * They may be controlled by the mouse and can handle collisions with walls and
 * ShapeActors.
 */
public abstract class DynamicActor extends ShapeActor {
	/**
	 * Velocity to use in the next move().
	 */
	private Vector vel;
	/**
	 * Velocity used in the previous move(). The distinction is important since
	 * collisions must happen syncronously.
	 */
	private Vector lastVel;

	/**
	 * Whether we'll pick up on mouse events and set our velocity accordingly.
	 */
	private boolean acceptMouse;

	/**
	 * A mouse listener.
	 */
	protected Mouse mouse;

	/**
	 * During mouse control, the offset from our center at which the mouse
	 * pointer is holding us.
	 */
	protected Vector heldOffset;

	/**
	 * Create a dynamic actor and set the velocity to zero.
	 */
	public DynamicActor() {
		mouse = new Mouse(this);
		vel = Vector.zero();
	}

	/**
	 * Getter and setters for acceptMouse.
	 */
	public boolean getMouseEnabled() {return acceptMouse;}
	public void enableMouse() {acceptMouse = true;}
	public void disableMouse() {acceptMouse = false;}

	public void act() {
		if (acceptMouse) {handleMouse();}
	}

	/**
	 * Getters and setters.
	 */
	public Vector getVelocity() {
		return vel;
	}
	public Vector getLastVelocity() {
		return (lastVel == null) ? vel : lastVel;
	}
	public void setVelocity(Vector vel) {
		this.vel = vel;
	}
	public void addVelocity(Vector vel) {
		setVelocity(getVelocity().add(vel));
	}

	/**
	 * Called by PhysicsWorld when it is our turn to move.
	 */
	public void move() {
		moveBy(vel);
		lastVel = vel;
	}

	/**
	 * Called by PhysicsWorld when it detects that we have collided with a wall
	 * or with a ShapeActor.
	 */
	public void collidedWithWall(PhysicsWorld.Walls wall) {
	}
	public void handleIntersection(ShapeActor other) {
	}

	/**
	 * Check if the mouse has done anything interesting.
	 */
	protected void handleMouse() {
		mouse.act();
		MouseState state = mouse.getState();
		Vector mouse = state.position;
		if (state.down) {
			onMousePressed(mouse);
		} else if (state.up) {
			onMouseReleased();
		} else {
			onMouseMoved(mouse);
		}
	}

	/**
	 * Mouse has been pressed, so grab the offset at which we're being held.
	 */
	protected void onMousePressed(Vector mouse) {
		heldOffset = mouse.subtract(getLocation());
		System.out.println("Set held offset to "+heldOffset);
	}

	/**
	 * Mouse has been released, so forget we were ever held.
	 */
	protected void onMouseReleased() {
		heldOffset = null;
	}

	/**
	 * Mouse is down and has moved to some position (possibly the same position
	 * as previous frame.)
	 */
	protected void onMouseMoved(Vector mouse) {
		if (heldOffset == null) {
			return;
		}
		/* Find the velocity that will bring us to the desired position on next
		 * frame. */
		Vector moveTo = mouse.subtract(heldOffset);
		Vector newVel = moveTo.subtract(getLocation());
		setVelocity(newVel);
	}
}
