import greenfoot.*;
public abstract class DynamicActor extends ShapeActor {
	private Vector vel;
	private Vector lastVel;
	private boolean acceptMouse;
	public DynamicActor() {
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

	protected Vector heldOffset;
	protected Vector lastKnownMouse;
	protected void handleMouse() {
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
	protected void onMousePressed(Vector mouse) {
		heldOffset = mouse.subtract(getLocation());
		System.out.println("Set held offset to "+heldOffset);
	}
	protected void onMouseReleased() {
		heldOffset = null;
	}
	protected void onMouseMoved(Vector mouse) {
		Vector moveTo = mouse.subtract(heldOffset);
		Vector newVel = moveTo.subtract(getLocation());
		//double len = newVel.length();
		//if (len > 2*radius) {
		//	newVel = newVel.scale(2.0*radius/len);
		//}
		setVelocity(newVel);
	}
}
