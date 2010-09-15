import greenfoot.*;
abstract class ShapeActor extends Actor {
	abstract Shape getShape();
	protected Vector size;
	private Vector location;
	boolean intersects(ShapeActor other) {
		return getShape().intersects(other.getShape());
	}
	Vector getSize() {
		return size;
	}
	int getWidth() {
		return (int) Math.round(size.x());
	}
	int getHeight() {
		return (int) Math.round(size.y());
	}
	public Vector getLocation() {
		if (location == null || getX() != (int) Math.round(location.x()) || getY() != (int) Math.round(location.y())) {
			location = new Vector(getX(), getY());
		}
		return location;
	}
	public void setLocation(Vector vector) {
		location = vector;
		setLocation((int) Math.round(vector.x()), (int) Math.round(vector.y()));
	}
	public void moveBy(Vector vector) {
		setLocation(getLocation().add(vector));
	}
}
