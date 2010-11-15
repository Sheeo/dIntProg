public class PongBall extends Ball {
	public PongBall(double vX, double vY) {
		super(vX, vY);
	}

	public void handleIntersection(Shape s) {
		Vector normal = s.intersectionNormal(getShape());
		if (null != normal) {
			mirrorVelocity(normal.orthogonal());
			setVelocity(getVelocity());
		}
	}

	public void collidedWithWall(PhysicsWorld.Walls wall) {
		if (wall == PhysicsWorld.Walls.NORTH || wall == PhysicsWorld.Walls.SOUTH) {
			((PongWorld)getWorld()).pongScore(wall);
			getWorld().removeObject(this);
			return;
		}
		super.collidedWithWall(wall);
	}
}
