/**
 * This class models an immutable two-dimensional Vector.  All manipulating
 * methods return a new Vector rather than modifying the current one.
 * @author Jeppe B
 * @author Mathias Rav
 */
public class Vector  
{
	/**
	 * Return a Vector of all zeroes. (We do a lot of short-circuiting when
	 * calculating with these special babies.)
	 */
	public static Vector zero() {return new Vector(true);}

	/**
	 * Return a Vector of all ones.
	 */
	public static Vector ones() {return new Vector(1.0);}

	/**
	 * Either _x and _y are non-null, or _angle and _length are non-null. Calculated from one another when needed.
	 */
	private Double _x;
	private Double _y;
	private Double _angle;
	private Double _length;

	/**
	 * Do we consist of all zeroes?
	 */
	public final boolean zeroes;
	
	public Vector(double s) {
		this(s,s);
	}
	public Vector(double x, double y) {
		_x = x;
		_y = y;
		zeroes = false;
	}

	/**
	 * Create a Vector from polar coordinates. Accessed through the public static
	 * Vector.fromPolar().
	 */
	private Vector(double angle, double length, boolean polar){
		_angle = (angle+180.0)%360.0-180.0;
		_length = length;
		zeroes = false;
	}

	/**
	 * Create a Vector of all zeroes. Accessed through the public static
	 * Vector.zero().
	 */
	private Vector(boolean allZeroes) {
		_x = _y = _length = _angle = 0.0;
		zeroes = true;
	}

	/**
	 * Create a Vector from polar coordinates.
	 */
	public static Vector fromPolar(double angle, double length) {
		return new Vector(angle, length, true);
	}

	/**
	 * Get position, or figure it out from length and angle if it hasn't been
	 * calculated.
	 */
	public double x() {
		if (_x == null)
			_x = _length*Math.cos(_angle);
		return _x;
	}

	public double y() {
		if (_y == null)
			_y = _length*Math.sin(_angle);
		return _y;
	}

	/**
	 * Get polar coordinates, or figure them out from x and y if they haven't
	 * been calculated.
	 */
	public double angle() {
		if (_angle == null)
			_angle = Math.atan2(_y, _x);
		return _angle;
	}
	public double length() {
		if (_length == null)
			_length = Math.sqrt(_x*_x+_y*_y);
		return _length;
	}

	public String toString() {
		if (zeroes) return "(0)";
		return "(" + x() + ", " + y() + " ; "+(180.0*angle()/Math.PI)+")";
	}

	/**
	 * Scale by scalar.
	 */
	public Vector scale(double f){
		if (zeroes) return this;
		if (_x == null)
			return new Vector(angle(), f*length(), true);
		return new Vector(x()*f, y()*f);
	}

	/**
	 * Scale each entry by the corresponding entry in v2.
	 */
	public Vector scale(Vector v2) {
		if (zeroes) return this;
		return new Vector(x()*v2.x(), y()*v2.y());
	}

	/**
	 * Add two vectors.
	 */
	public Vector add(Vector v2) {
		if (zeroes) return v2;
		return new Vector(x()+v2.x(), y()+v2.y());
	}

	/**
	 * Subtract two vectors.
	 */
	public Vector subtract(Vector v2) {
		return add(v2.scale(-1));
	}

	/**
	 * Find the sign of each entry (-1 if negative, 1 if non-negative).
	 */
	public Vector sign() {
		return new Vector((x() < 0) ? -1 : 1, (y() < 0) ? -1 : 1);
	}

	/**
	 * Transform a Vector by the given 2-by-2 transformation matrix.
	 */
	public Vector transform(double t11, double t12, double t21, double t22) {
		if (zeroes) return this;
		return new Vector(x()*t11+y()*t12, x()*t21+y()*t22);
	}

	/**
	 * Mirror a Vector in another Vector using the relevant transformation
	 * matrix.
	 */
	public Vector mirror(Vector v2) {
		if (zeroes) return this;
		double angle = v2.angle();
		double sin = Math.sin(2*angle);
		double cos = Math.cos(2*angle);
		Vector res = transform(cos, sin, sin, -cos);
		//System.out.println(v2+" mirroring "+this+" to "+res);
		return res;
	}

	/**
	 * Find a vector orthogonal to this one.
	 */
	public Vector orthogonal() {
		if (_x == null)
			return new Vector(angle()+Math.PI/2, length(), true);
		return new Vector(-y(), x());
	}

	/**
	 * Restrict a Vector to a lower and upper bound.
	 */
	public Vector clamp(Vector lower, Vector upper) {
		return new Vector(Math.min(Math.max(x(), lower.x()), upper.x()), Math.min(Math.max(y(), lower.y()), upper.y()));
	}

	/**
	 * Find the dot product of two vectors (and incidentally the length of the
	 * projection of one vector on another.)
	 */
	public double dotP(Vector v) {
		return x()*v.x()+y()*v.y();
	}

	/**
	 * Find the corresponding unit vector.
	 */
	public Vector unit() {
		return scale(1.0/length());
	}

	/**
	 * Find the vector with the same angle and a specified length.
	 */
	public Vector setLength(double length) {
		return Vector.fromPolar(angle(), length);
	}
}
