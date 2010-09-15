abstract class DynamicActor extends ShapeActor {
	private Vector vel;
	private Vector lastVel;
	private Vector upperLeftBound_;
	private Vector lowerRightBound_;
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
	private Vector upperLeftBound() {
		if (null == upperLeftBound_) {
			upperLeftBound_ = new Vector(getWidth()/2.0, getHeight()/2.0);
		}
		return upperLeftBound_;
	}
	private Vector lowerRightBound() {
		if (null == lowerRightBound_) {
			lowerRightBound_ = ((PhysicsWorld) getWorld()).getSize().subtract(upperLeftBound());
		}
		return lowerRightBound_;
	}
	public void move() {
		moveBy(vel);
		lastVel = vel;
		setLocation(getLocation().clamp(upperLeftBound(), lowerRightBound()));
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
		return upperLeftBound().subtract(getLocation()).sign()
		.scale(getLocation().subtract(lowerRightBound()).sign());
	}
	public void hasCollidedWithWall() {
		vel = vel.scale(wallCollision());
	}
	public void checkWallCollisions() {
		hasCollidedWithWall();
	}
}
