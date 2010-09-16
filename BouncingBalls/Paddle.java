public class Paddle extends DynamicActor {
	private Vector size;
	public Paddle() {
		this(new Vector(200.0, 30.0));
	}
	public Paddle(Vector size) {
		this.size = size;
	}
	public Shape getShape() {
		return new RoundedRectangle(getLocation(), size);
	}
}
