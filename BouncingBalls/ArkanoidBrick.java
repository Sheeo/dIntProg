class ArkanoidBrick extends ShapeActor {
	private Vector size;
	public static final double width = 80.0;
	public static final double height = 20.0;
	public ArkanoidBrick() {
		this(new Vector(width, height));
	}
	private ArkanoidBrick(Vector size) {
		super();
		this.size = size;
	}
	public Shape getShape() {
		return new Rectangle(getLocation(), size);
	}
}
