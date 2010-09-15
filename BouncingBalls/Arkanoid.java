class Arkanoid extends PhysicsWorld {
	public Arkanoid() {
		super(800, 500);
		for (double y = ArkanoidBrick.height; y < 250; y += ArkanoidBrick.height*1.2) {
			for (double x = ArkanoidBrick.width; x < 800.0-ArkanoidBrick.width/2.0; x += ArkanoidBrick.width*1.2) {
				addObject(new ArkanoidBrick(), (int) Math.round(x), (int) Math.round(y));
			}
		}
		addObject(new Ball(1, -6), 350, 450);
	}
}
