/**
 * Pong!
 */
public class PongWorld extends BallWorld {
	public PongWorld() {
		super();
		Paddle paddle1 = new PlayerPaddle("left", "right");
		addObject(paddle1, getWidth()/2, 9*getHeight()/10);
		Paddle paddle2 = new PlayerPaddle("a", "d");
		addObject(paddle2, getWidth()/2, getHeight()/10);
		addBall(getWidth()/2, getHeight()/2, 0.5, 6.0);
	}

	public Ball addBall(int x, int y, double vX, double vY) {
		Ball b = new PongBall(vX, vY);
		addObject(b, x, y);
		return b;
	}
}
