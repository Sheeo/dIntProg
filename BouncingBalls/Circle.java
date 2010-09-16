public class Circle extends Shape {
	double r_; double r() {return r_;}
	public Circle(Vector loc, double r) {
		this(loc.x(), loc.y(), r);
	}
	public Circle(double x, double y, double r) {
		super(x, y, 2*r, 2*r);
		r_ = r;
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
	public boolean intersects(Circle c) {
		return c.pos().subtract(pos()).length() < c.r()+r();
	}
}
