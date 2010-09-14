import java.util.ArrayList;
class CircularActor extends ShapeActor {
	final double radius;
	public CircularActor() {
		radius = getImage().getWidth()/2.0;
	}
	boolean intersects(ShapeActor other) {
		if (other instanceof CircularActor) {
			return intersects((CircularActor) other);
		} else {
			throw new RuntimeException("Can't collide a CircularActor with a "+other);
		}
	}
	boolean intersects(CircularActor other) {
		double dist = other.getLocation().subtract(getLocation()).length();
		return dist <= (other.getWidth()+getWidth())/2.0;
	}
}
