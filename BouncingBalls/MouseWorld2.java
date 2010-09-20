public class MouseWorld2 extends BallWorld {
	public MouseWorld2() {
		super();
		setMouseAttraction(0.5);
		setGravity(Vector.zero());
		setFriction(0.95);
		addBalls(200);
	}
	public Ball addBall(int x, int y, double vX, double vY) {
		Ball b = new Ball("ball_8px.png", vX, vY);
		addObject(b, x, y);
		return b;
	}
}
