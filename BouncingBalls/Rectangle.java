/**
 * A rectangular shape.
 */
public class Rectangle extends Shape {
	public Rectangle(Vector pos, Vector size) {
		super(pos.x(), pos.y(), size.x(), size.y());
	}

	/**
	 * Circles handle Circle-Rectangle intersection. Otherwise, use the bbox
	 * intersection in Shape.
	 */
	public Vector intersectionNormal(Shape other) {
		if (other instanceof Circle) {
			return intersectionNormal((Circle) other);
		}
		return null;
	}
	public Vector intersectionNormal(Circle other) {
		return other.intersectionNormal(this);
	}
}
