class CoolBallWorld extends BallWorld {
	private int ballID;
	public CoolBallWorld() {
		super();
	}
	public void addBall() {
		int vX = 0;
		int x = (int) Math.round((2*ballRadius)*(ballID+3));
		if (ballID == 0) {
			vX = 3;
			x = 1+(int) Math.round(ballRadius);
		}
		addBall(x, 100, vX, 0);
		++ballID;
	}
}