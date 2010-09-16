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
		if (s instanceof RoundedRectangle) {
			return intersects((RoundedRectangle) s);
		}
		return true;
	}
	public boolean intersects(Circle c) {
		return c.pos().subtract(pos()).length() < c.r()+r();
	}
	public boolean intersects(RoundedRectangle s) {
		return s.intersects(this);
	}
	public Vector intersectionNormal(Shape other) {
		if (other instanceof Circle) {
			return other.pos().subtract(pos());
		}
		return bboxDeflectionNormal(other);
	}
	public Vector bboxDeflectionNormal(Shape them) {
		Vector tl = them.bbox_tl();
		Vector br = them.bbox_br();
		if (tl.x() <= x() && x() <= br.x()) {
			return new Vector(1.0, 0.0);
		} else if (tl.y() <= y() && y() <= br.y()) {
			return new Vector(0.0, 1.0);
		} else {
			Vector tr = them.bbox_tr();
			Vector bl = them.bbox_bl();
			Vector point = tl;
			double dist = tl.subtract(pos()).length();
			double d2 = tr.subtract(pos()).length();
			if (d2 < dist) {dist = d2; point = tr;}
			d2 = br.subtract(pos()).length();
			if (d2 < dist) {dist = d2; point = br;}
			d2 = bl.subtract(pos()).length();
			if (d2 < dist) {dist = d2; point = bl;}
			return point.subtract(pos()).orthogonal();
		}
	}
}
