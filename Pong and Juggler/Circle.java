/**
 * A circular shape.
 */
public class Circle extends Shape {
	/**
	 * Radius.
	 */
	double r_; double r() {return r_;}

	/**
	 * Create a circle given a location and a radius.
	 */
	public Circle(Vector loc, double r) {
		this(loc.x(), loc.y(), r);
	}
	public Circle(double x, double y, double r) {
		super(x, y, 2*r, 2*r);
		r_ = r;
	}

	/**
	 * Figure out if we intersect some other Shape. First check bounding box
	 * intersection (Shape.intersects), then check the special cases of
	 * intersections with other Circles and RoundedRectangles.
	 */
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

	/**
	 * A Circle is intersected if the distance between the centers is smaller
	 * than the sum of the radii.
	 */
	public boolean intersects(Circle c) {
		return c.pos().subtract(pos()).length() < c.r()+r();
	}

	/**
	 * A RoundedRectangle will know if we intersect it.
	 */
	public boolean intersects(RoundedRectangle s) {
		return s.intersects(this);
	}

	/**
	 * Return intersection data for the intersection with some other shape.
	 */
	public Intersection intersection(Shape other) {
		if (other instanceof Circle) {
			Vector normal = other.pos().subtract(pos());
			double amount = normal.length()-r()-((Circle) other).r();
			return new Intersection(normal, amount);
		}
		return bboxIntersection(other);
	}

	/**
	 * Return intersection data for the intersection with some other shape's
	 * bounding box.
	 */
	public Intersection bboxIntersection(Shape them) {
		/* The other shape's bounding box's corners. */
		Vector tl = them.bbox_tl();
		Vector br = them.bbox_br();

		if (tl.x() <= x() && x() <= br.x()) {
			/* We've hit the bounding box on a horizontal side. */
			Vector normal = new Vector(0.0, 1.0);
			double dist;
			if (them.y() >= y()) {
				/* Above the bounding box. */
				dist = tl.y()-y()-r();
			} else {
				/* Below it. */
				dist = y()-r()-br.y();
			}
			return new Intersection(normal, dist);

		} else if (tl.y() <= y() && y() <= br.y()) {
			/* We're hit the bounding box on a vertical side. */
			Vector normal = new Vector(1.0, 0.0);
			double dist;
			if (them.x() >= x()) {
				/* Left of the bounding box. */
				dist = tl.x()-x()-r();
			} else {
				/* Starboard. */
				dist = x()-r()-br.x();
			}
			return new Intersection(normal, dist);

		} else {
			/* We've hit a corner. Determine which. */
			Vector tr = them.bbox_tr();
			Vector bl = them.bbox_bl();

			/* First, assume the top left corner is the closest. Find the distance
			 * from our center to the corner, and iterate through the other corners,
			 * checking if they are closer. */
			Vector point = tl;
			double dist = tl.subtract(pos()).length();
			double d2 = tr.subtract(pos()).length();
			if (d2 < dist) {dist = d2; point = tr;}
			d2 = br.subtract(pos()).length();
			if (d2 < dist) {dist = d2; point = br;}
			d2 = bl.subtract(pos()).length();
			if (d2 < dist) {dist = d2; point = bl;}

			/* We hit the bounding box on the corner 'point'. Find the normal of
			 * collision and the distance between us and the corner. */
			Vector vec = point.subtract(pos());
			return new Intersection(vec, vec.length()-r());
		}
	}
}
