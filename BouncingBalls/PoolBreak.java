class PoolBreak extends BallWorld {
	public PoolBreak() {
		super();
		addFirstBall();
		addMoreBalls();
	}
	public void addFirstBall() {
		Vector pos = getSize().scale(new Vector(1.0/16.0, 0.5));
		addBall((int) Math.round(pos.x()), (int) Math.round(pos.y()), r.nextGaussian()*4.0+12.0, r.nextDouble()*0.2-0.1);
	}
	public void addMoreBalls() {
		Vector base = getSize().scale(new Vector(0.6, 0.5));
		Vector dx = Vector.fromPolar(-Math.PI/6, 2*ballRadius);
		Vector dy = new Vector(0, 2*ballRadius);
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j <= i; ++j) {
				addBall(base.add(dx.scale(i)).add(dy.scale(j)));
			}
		}
	}
}
