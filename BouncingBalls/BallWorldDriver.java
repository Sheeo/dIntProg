public class BallWorldDriver extends BallWorld {
	public BallWorldDriver() {
		super();
		addBalls(10);
		setGravity(new Vector(0.0, 0.3));
	}
}
