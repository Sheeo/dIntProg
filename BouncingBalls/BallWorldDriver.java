/**
 * A simple BallWorld driver, since the default BallWorld doesn't do so much.
 * Add some balls, set some gravity, set some friction.
 */
public class BallWorldDriver extends BallWorld {
	public BallWorldDriver() {
		super();
		addBalls(10);
		setGravity(new Vector(0.0, 0.3));
		setFriction(0.998);
	}
}
