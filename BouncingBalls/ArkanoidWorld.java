/**
 * A world that emulates a game of Arkanoid.
 */
public class ArkanoidWorld extends PhysicsWorld {
	/**
	 * There is only one Ball in this Arkanoid game, and we need it for
	 * reference.
	 */
	private Ball ball;

	/**
	 * Create a game of Arkanoid. The field has a fixed size, a fixed number of
	 * bricks and a fixed number of balls.
	 */
	public ArkanoidWorld() {
		super(416, 512);
		addBricks();
		addBall();
		addPlayerPaddle();
	}

	/**
	 * Add rows of bricks of various colors.
	 */
	private void addBricks() {
		/* The colors of bricks, given by the brick_COLOR.jpg files in images/. */
		String[] colors = {"lime", "purple", "blue", "yellow", "red", "gray"};

		/* Initial y position. This is incremented for each row of bricks that is
		 * added. */
		int y = 4*ArkanoidBrick.height+ArkanoidBrick.height/2;

		/* Number of bricks in a row. Easy since an ArkanoidBrick has a fixed
		 * width. */
		int bricks = getWidth()/ArkanoidBrick.width;

		for (int j = colors.length; j-- > 0;) {
			for (int i = 0; i < bricks; ++i) {
				ArkanoidBrick brick = new ArkanoidBrick(colors[j]);
				addObject(brick, i*ArkanoidBrick.width+ArkanoidBrick.width/2, y);
			}
			y += ArkanoidBrick.height;
		}
	}

	private void addPlayerPaddle() {
		PlayerPaddle paddle = new PlayerPaddle("a", "d");
		paddle.setImage("paddle_200x30.png");
		addObject(paddle, 416/2, 480);
	}

	/**
	 * Add the Arkanoid ball, and store it in the ball property.
	 */
	private void addBall() {
		ball = new Ball(1, -6);
		ball.setImage("arkanoidball.png");
		addObject(ball, 200, 450);
	}
}
