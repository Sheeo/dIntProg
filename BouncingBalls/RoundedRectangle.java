public class RoundedRectangle extends Shape {
	double r_; double r() {return r_;}
	public RoundedRectangle(Vector loc, Vector size) {
		this(loc.x(), loc.y(), size.x(), size.y());
	}
	public RoundedRectangle(double x, double y, double w, double h) {
		super(x, y, w, h);
		r_ = h/2.0;
	}
	public Shape getPrimitive(Shape s) {
		if (s instanceof Circle) {return getPrimitive((Circle) s);}
		return this;
	}
	public Shape getPrimitive(Circle s) {
		double x1 = x()-w()/2.0+r();
		System.out.println("Does "+this+" intersect "+s+"?");
		if (s.x() < x1) {
			System.out.println("Try left circle");
			return new Circle(x1, y(), r());
		}
		double x2 = x()+w()/2.0-r();
		if (s.x() > x2) {
			System.out.println("Try right circle");
			return new Circle(x2, y(), r());
		}
		System.out.println("Try rectangle");
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
			return null;
		}
		return getPrimitive(s).intersectionNormal(this);
	}
}
