abstract class DynamicActor extends ShapeActor {
	private Vector vel;
	private Vector lastVel;
	DynamicActor() {
		vel = Vector.zero();
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
}
