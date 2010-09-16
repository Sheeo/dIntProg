public class Arkanoid extends PhysicsWorld {
	private Ball ball;
	public Arkanoid() {
		super(416, 512);
		addBricks();
		addBall();
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
	private void addBall() {
		ball = new Ball(1, -6);
		ball.setImage("arkanoidball.png");
		addObject(ball, 200, 470);
	}
}
