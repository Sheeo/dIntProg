import greenfoot.*;
import java.util.*;
/**
 * An Actor that has a Shape and knows its position and size as Vectors.
 */
public abstract class ShapeActor extends Actor {

	abstract Shape getShape();

	protected Vector size;

	private Vector location;

	/**
	 * We intersect another ShapeActor if our Shape intersects their Shape.
	 */
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

	/**
	 * Mainly because getIntersectingObjects() is declared protected in Actor
	 * (for reasons unknown). Find all intersecting ShapeActors.
	 */
	public List<ShapeActor> getIntersectingActors() {
		/* First, let Greenfoot do bounding box intersection detection. */
		List intersecting = getIntersectingObjects(ShapeActor.class);

		/* Then, select the ShapeActors that actually intersect us, as decided by
		 * intersects(). */
		ArrayList<ShapeActor> res = new ArrayList<ShapeActor>();
		for (Object o : intersecting) {
			ShapeActor a = (ShapeActor) o;
			if (intersects(a)) {
				res.add(a);
			}
		}
		return res;
	}
}
