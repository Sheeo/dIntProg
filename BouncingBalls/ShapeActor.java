abstract class ShapeActor extends VectorActor {
	abstract boolean intersects(ShapeActor other);
	protected Vector size;
	Vector getSize() {return size;}
	int getWidth() {return (int) Math.round(size.x());}
	int getHeight() {return (int) Math.round(size.y());}
}
