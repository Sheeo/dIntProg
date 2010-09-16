public class PaddleWorld extends BallWorld {
	public PaddleWorld() {
		super();
		Paddle paddle = new PlayerPaddle("a", "d");
		addObject(paddle, getWidth()/2, 4*getHeight()/5);
		for (double x = ballRadius; x < getWidth()-ballRadius; x += 2*ballRadius) {
			addBall((int) Math.round(x), 50, 0.0, 6.0);
		}
	}
}
