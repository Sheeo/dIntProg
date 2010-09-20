/**
 * Another mouse-controlled world. Lots of small balls that follow the mouse.
 */
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
		b.setWallDampen(null);
		b.setBallDampen(null);
		addObject(b, x, y);
		return b;
	}
}
