public class ArkanoidBrick extends ShapeActor {
	private Vector size;
	public static final int width = 32;
	public static final int height = 16;
	private int hitCount;
	private String color;
	
	public ArkanoidBrick() {
		this(new Vector(width, height), 1);
		color = "red";
	}
	public ArkanoidBrick(String color) {
		this();
		setColor(color);
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
	
	public void setColor(String color) {
		this.color = color;
		updateColor();
	}
	protected void updateColor() {
		setImage("arkanoid_"+color+".png");
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
