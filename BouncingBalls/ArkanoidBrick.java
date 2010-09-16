public class ArkanoidBrick extends ShapeActor {
	private Vector size;
	public static final double width = 80.0;
	public static final double height = 20.0;
	private int hitCount;
	
	public ArkanoidBrick() {
		this(new Vector(width, height), 1);
	}
	public ArkanoidBrick(int maxhits) {
		this();
		this.hitCount = maxhits;
	}
	private ArkanoidBrick(Vector size, int maxhits) {
		super();
		this.hitCount = maxhits;
		this.size = size;
	}
	
	public void hit() {
		this.hitCount--;
		if(hitCount <= 0) {
			getWorld().removeObject(this);
		}
	}
	
	public Shape getShape() {
		return new Rectangle(getLocation(), size);
	}
}
