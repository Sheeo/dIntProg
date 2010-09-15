import java.util.*;
abstract class Shape {
	private double x_; double x() {return x_;}
	private double y_; double y() {return y_;}
	private double w_; double w() {return w_;}
	private double h_; double h() {return h_;}
	private Vector upperLeftBound_;
	private Vector lowerRightBound_;
	protected Shape(double x, double y, double w, double h) {
		x_ = x; y_ = y; w_ = w; h_ = h;
	}
	public Vector pos() {return new Vector(x(), y());}
	public Vector size() {return new Vector(w(), h());}
	public boolean intersects(Shape s) {
		return (2*(x()-s.x()) < w()+s.w())
		&& (2*(y()-s.y()) < h()+s.h());
	}
	public EnumSet<PhysicsWorld.Walls> wallIntersection(Vector size) {
		EnumSet<PhysicsWorld.Walls> res = EnumSet.noneOf(PhysicsWorld.Walls.class);
		if (x()-w()/2 < 0) res.add(PhysicsWorld.Walls.WEST);
		if (y()-h()/2 < 0) res.add(PhysicsWorld.Walls.NORTH);
		if (x()+w()/2 > size.x()) res.add(PhysicsWorld.Walls.EAST);
		if (y()+h()/2 > size.y()) res.add(PhysicsWorld.Walls.SOUTH);
		return res;
	}
}