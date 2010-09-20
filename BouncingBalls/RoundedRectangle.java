/**
 * A RoundedRectangle that can be modelled with a circle of diameter h, a
 * rectangle of dimensions w*h, and another circle of diameter h. This is not
 * an arbitrary rounded rectangle!
 */
public class RoundedRectangle extends Shape {
	/**
	 * Circle radius.
	 */
	double r_; double r() {return r_;}

	/**
	 * Constructor.
	 */
	public RoundedRectangle(Vector loc, Vector size) {
		this(loc.x(), loc.y(), size.x(), size.y());
	}
	public RoundedRectangle(double x, double y, double w, double h) {
		super(x, y, w, h);
		r_ = h/2.0;
	}

	/**
	 * Reduce the RoundedRectangle to a simpler primitive (a Circle or a
	 * Rectangle), as applicable when colliding with a given Shape.
	 */
	public Shape getPrimitive(Shape s) {
		if (s instanceof Circle) {return getPrimitive((Circle) s);}
		return this;
	}
	public Shape getPrimitive(Circle s) {
		double x1 = x()-w()/2.0+r();
		if (s.x() < x1) {
			/* Return the Circle contained in our left side. */
			return new Circle(x1, y(), r());
		}
		double x2 = x()+w()/2.0-r();
		if (s.x() > x2) {
			/* Return the Circle contained in our right side. */
			return new Circle(x2, y(), r());
		}
		/* Return our bounding box. */
		return new Rectangle(pos(), size());
	}

	public boolean intersects(Shape s) {
		if (!super.intersects(s)) {
			return false;
		}
		if (s instanceof Circle) {
			return intersects((Circle) s);
		}
		return true;
	}
	public boolean intersects(Circle s) {
		return s.intersects(getPrimitive(s));
	}
	public Vector intersectionNormal(Shape s) {
		if (s instanceof RoundedRectangle) {
			System.out.println("A RoundedRectangle can't have an intersectionNormal with a RoundedRectangle!");
			return null;
		}
		return getPrimitive(s).intersectionNormal(s);
	}
}
