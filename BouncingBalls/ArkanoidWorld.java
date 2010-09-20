public class ArkanoidWorld extends PhysicsWorld {
	private Ball ball;
	public ArkanoidWorld() {
		super(416, 512);
		addBricks();
		addBall();
		addPlayerPaddle();
	}
	private void addBricks() {
		String[] colors = {"lime", "purple", "blue", "yellow", "red", "gray"};
		int y = 4*ArkanoidBrick.height+ArkanoidBrick.height/2;
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
	private void addBall() {
		ball = new Ball(1, -6);
		ball.setImage("arkanoidball.png");
		addObject(ball, 200, 450);
	}
}
