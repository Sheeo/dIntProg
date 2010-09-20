/**
 * Dumb data container that contains information about an intersection.
 */
public class Intersection {
	/**
	 * The singleton Intersection that is used for a non-intersection.
	 */
	private static Intersection noIntersection;

	/**
	 * Return the non-intersection singleton.
	 */
	public static Intersection NoIntersection() {return noIntersection;}

	/**
	 * False for the non-intersection; true otherwise.
	 */
	public final boolean intersects;

	/**
	 * The normal of intersection.
	 */
	public final Vector normal;

	/**
	 * The length of penetration. May not be exact.
	 */
	public final double amount;

	public Intersection(Vector normal, double amount) {
		this.normal = normal;
		this.amount = amount;
		this.intersects = true;
	}

	/**
	 * Create a non-intersection to be used for the noIntersection singleton.
	 */
	private Intersection(boolean none) {
		normal = null;
		amount = 0.0;
		intersects = false;
	}
	static {
		noIntersection = new Intersection(true);
	}
}
