import greenfoot.*;
public abstract class DynamicActor extends ShapeActor {
	private Vector vel;
	private Vector lastVel;
	private boolean acceptMouse;
	protected Mouse mouse;
	protected Vector heldOffset;
	public DynamicActor() {
		mouse = new Mouse(this);
		vel = Vector.zero();
	}
	public boolean getMouseEnabled() {return acceptMouse;}
	public void enableMouse() {acceptMouse = true;}
	public void disableMouse() {acceptMouse = false;}

	public void act() {
		if (acceptMouse) {handleMouse();}
	}
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
	public void move() {
		moveBy(vel);
		lastVel = vel;
	}
	public void collidedWithWall(PhysicsWorld.Walls wall) {
	}
	public void handleIntersection(ShapeActor other) {
	}

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
	protected void onMousePressed(Vector mouse) {
		heldOffset = mouse.subtract(getLocation());
		System.out.println("Set held offset to "+heldOffset);
	}
	protected void onMouseReleased() {
		heldOffset = null;
	}
	protected void onMouseMoved(Vector mouse) {
		if (heldOffset == null) {
			return;
		}
		Vector moveTo = mouse.subtract(heldOffset);
		Vector newVel = moveTo.subtract(getLocation());
		//double len = newVel.length();
		//if (len > 2*radius) {
		//	newVel = newVel.scale(2.0*radius/len);
		//}
		setVelocity(newVel);
	}
}
