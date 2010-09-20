import java.util.*;
/**
 * A Shape that has a bounding box and a center and can intersect other Shapes.
 */
public abstract class Shape {
	/**
	 * Position.
	 */
	private double x_; double x() {return x_;}
	private double y_; double y() {return y_;}

	/**
	 * Size.
	 */
	private double w_; double w() {return w_;}
	private double h_; double h() {return h_;}

	private Vector upperLeftBound_;
	private Vector lowerRightBound_;

	protected Shape(double x, double y, double w, double h) {
		x_ = x; y_ = y; w_ = w; h_ = h;
	}

	/**
	 * Getters. Short, simple names, since these are used all the time.
	 */
	public Vector pos() {return new Vector(x(), y());}
	public Vector size() {return new Vector(w(), h());}

	/**
	 * By default, do bounding box intersection detection.
	 */
	public boolean intersects(Shape s) {
		return (2*(x()-s.x()) < w()+s.w())
		&& (2*(y()-s.y()) < h()+s.h());
	}

	/**
	 * Find out if we intersect the walls of the PhysicsWorld given by some size.
	 * By default, do bounding box intersection detection.
	 */
	public EnumSet<PhysicsWorld.Walls> wallIntersection(Vector size) {
		EnumSet<PhysicsWorld.Walls> res = EnumSet.noneOf(PhysicsWorld.Walls.class);
		if (x()-w()/2 < 0) res.add(PhysicsWorld.Walls.WEST);
		if (y()-h()/2 < 0) res.add(PhysicsWorld.Walls.NORTH);
		if (x()+w()/2 > size.x()) res.add(PhysicsWorld.Walls.EAST);
		if (y()+h()/2 > size.y()) res.add(PhysicsWorld.Walls.SOUTH);
		return res;
	}

	/**
	 * Return our bounding box corners.
	 */
	public Vector bbox_tl() {
		return pos().add(size().scale(-0.5));
	}
	public Vector bbox_br() {
		return pos().add(size().scale(0.5));
	}
	public Vector bbox_tr() {
		return pos().add(size().scale(new Vector(0.5,-0.5)));
	}
	public Vector bbox_bl() {
		return pos().add(size().scale(new Vector(-0.5,0.5)));
	}

	/**
	 * If we're a complex shape, we might be able to reduce ourselves to a
	 * simpler shape for purposes of collision detection with some other given
	 * shape. Otherwise, return ourselves, as we are our own primitive.
	 */
	public Shape getPrimitive(Shape other) {
		return this;
	}

	/**
	 * Figure out if we intersect some other Shape. If this method is not
	 * overridden, throw up a warning to stdout and let the objects pass through
	 * each other.
	 */
	public Intersection intersection(Shape other) {
		System.out.println(this+" can't collide with "+other);
		return Intersection.NoIntersection();
	}

	public Vector intersectionNormal(Shape other) {
		return intersection(other).normal;
	}
}
