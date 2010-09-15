import greenfoot.*;
abstract class ShapeActor extends Actor {
	abstract boolean intersects(ShapeActor other);
	protected Vector size;
	Vector getSize() {return size;}
	int getWidth() {return (int) Math.round(size.x());}
	int getHeight() {return (int) Math.round(size.y());}
	public Vector getLocation() {
		return new Vector(getX(), getY());
	}
	public void setLocation(Vector vector) {
		setLocation((int) Math.round(vector.x()), (int) Math.round(vector.y()));
	}
	public void moveBy(Vector vector) {
		setLocation(getLocation().add(vector));
	}
}
