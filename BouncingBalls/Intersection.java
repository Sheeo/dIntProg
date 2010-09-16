public class Intersection {
	private static Intersection noIntersection;
	public static Intersection NoIntersection() {return noIntersection;}
	public final boolean intersects;
	public final Vector normal;
	public final double amount;
	public Intersection(Vector normal, double amount) {
		this.normal = normal;
		this.amount = amount;
		this.intersects = true;
	}
	private Intersection(boolean none) {
		normal = null;
		amount = 0.0;
		intersects = false;
	}
	static {
		noIntersection = new Intersection(true);
	}
}
