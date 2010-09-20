/**
 * Test cases for Circle collisions
 */
public class ShapeTest {
	static boolean circleCollide(double x1, double y1, double r1, double x2, double y2, double r2) {
		Circle a = new Circle(x1, y1, r1);
		Circle b = new Circle(x2, y2, r2);
		boolean col = a.intersects(b);
		boolean col2 = b.intersects(a);
		if (col != col2) {
			System.out.println(a+" and "+b+" inconsistent in intersection!");
		}
		return col;
	}
	static void assertCollides(double x1, double y1, double r1, double x2, double y2, double r2) {
		System.out.println("Asserting that ("+x1+","+y1+","+r1+") collides with ("+x2+","+y2+","+r2+"):");
		if (circleCollide(x1,y1,r1,x2,y2,r2)) {
			System.out.println("They collide.");
		} else {
			System.out.println("They don't collide!");
		}
	}
	static void assertCollidesNot(double x1, double y1, double r1, double x2, double y2, double r2) {
		System.out.println("Asserting that ("+x1+","+y1+","+r1+") doesn't collide with ("+x2+","+y2+","+r2+"):");
		if (circleCollide(x1,y1,r1,x2,y2,r2)) {
			System.out.println("They collide!");
		} else {
			System.out.println("They don't collide.");
		}
	}
	public static void main(String[] args) {
		assertCollides(0,0,1,0,0,1);
		assertCollides(0,0,1,0.5,0.5,1);
		assertCollides(0,0,10,10.5,0.5,1);
		assertCollidesNot(0,0,5,10,0,1);
	}
}
