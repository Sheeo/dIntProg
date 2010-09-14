interface DynamicActor {
	void hasCollided(ShapeActor other);
	void hasCollidedWithWall();
	Vector getVelocity();
	void setVelocity(Vector vel);
}
