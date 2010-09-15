/**
 * This class models a Vector in a right-turned coordinate system.
 * @author Jeppe B
 * @author Mathias Rav
 */
public class Vector  
{
	public static Vector zero() {return new Vector(true);}
	public static Vector ones() {return new Vector(1.0);}
	private Double _x;
	private Double _y;
	private Double _angle;
	private Double _length;
	public final boolean zeroes;
	
	public Vector(double s){
		this(s,s);
	}
	public Vector(double x, double y){
		_x = x;
		_y = y;
		zeroes = false;
	}
	private Vector(double angle, double length, boolean polar){
		_angle = (angle+180.0)%360.0-180.0;
		_length = length;
		zeroes = false;
	}
	private Vector(boolean allZeroes){
		_x = _y = _length = _angle = 0.0;
		zeroes = true;
	}
	public static Vector fromPolar(double angle, double length){
		return new Vector(angle, length, true);
	}
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
	public String toString(){
		if (zeroes) return "(0)";
		return "(" + x() + ", " + y() + " ; "+(180.0*angle()/Math.PI)+")";
	}
	
	public Vector scale(double f){
		if (zeroes) return this;
		if (_x == null)
			return new Vector(angle(), f*length(), true);
		return new Vector(x()*f, y()*f);
	}

	public Vector scale(Vector v2) {
		if (zeroes) return this;
		return new Vector(x()*v2.x(), y()*v2.y());
	}

	public Vector add(Vector v2) {
		if (zeroes) return v2;
		return new Vector(x()+v2.x(), y()+v2.y());
	}

	public Vector subtract(Vector v2) {
		return add(v2.scale(-1));
	}

	public Vector sign() {
		return new Vector((x() < 0) ? -1 : 1, (y() < 0) ? -1 : 1);
	}

	public Vector transform(double t11, double t12, double t21, double t22) {
		if (zeroes) return this;
		return new Vector(x()*t11+y()*t12, x()*t21+y()*t22);
	}

	public Vector mirror(Vector v2) {
		// sadly, this method isn't needed anyway, so neither is transform()
		if (zeroes) return this;
		double angle = v2.angle();
		double sin = Math.sin(2*angle);
		double cos = Math.cos(2*angle);
		Vector res = transform(cos, sin, sin, -cos);
		//System.out.println(v2+" mirroring "+this+" to "+res);
		return res;
	}

	public Vector orthogonal() {
		if (_x == null)
			return new Vector(angle()+Math.PI/2, length(), true);
		return new Vector(-y(), x());
	}

	public Vector clamp(Vector lower, Vector upper) {
		return new Vector(Math.min(Math.max(x(), lower.x()), upper.x()), Math.min(Math.max(y(), lower.y()), upper.y()));
	}

	public double dotP(Vector v) {
		return x()*v.x()+y()*v.y();
	}
}
