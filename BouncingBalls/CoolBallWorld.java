/**
 * A showcase of BallWorld displaying the exciting properties of elastic
 * collision.
 */
public class CoolBallWorld extends BallWorld {
	/**
	 * The ID of the next ball spawned by addBall().
	 */
	private int ballID;

	/**
	 * Create a cool BallWorld, and add some balls.
	 */
	public CoolBallWorld() {
		super();
		addBalls(10);
	}

	/**
	 * Add a ball.
	 */
	public Ball addBall() {
		int vX = 0;
		int x = (int) Math.round((2*ballRadius)*(ballID+3));
		if (ballID == 0) {
			/* The first ball added has a velocity. (The rest are still.) */
			vX = 3;
			x = 1+(int) Math.round(ballRadius);
		}
		++ballID;
		return addBall(x, 100, vX, 0);
	}
}
