public class Rectangle extends Shape {
	public Rectangle(Vector pos, Vector size) {
		super(pos.x(), pos.y(), size.x(), size.y());
	}
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
