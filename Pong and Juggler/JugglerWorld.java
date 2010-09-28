public class JugglerWorld extends BallWorld {
	public JugglerWorld() {
		super();
		addBall(getWidth()*2/5, getHeight()/2, 1.0, -2.0);
		setDownwardsGravity(0.4);
		setFriction(1.0);
		addObject(new PlayerPaddle("left", "right"), getWidth()/2, 9*getHeight()/10);
	}

	public Ball addBall(int x, int y, double vX, double vY) {
		Ball b = new PongBall(vX, vY);
		addObject(b, x, y);
		return b;
	}
}
