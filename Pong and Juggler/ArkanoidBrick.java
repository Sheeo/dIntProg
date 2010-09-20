/**
 * ArkanoidBrick. A colored ShapeActor that removes itself when hit.
 */
public class ArkanoidBrick extends ShapeActor {
	/**
	 * Default size of the brick.
	 */
	public static final int width = 32;
	public static final int height = 16;

	/**
	 * Size of the brick.
	 */
	private Vector size;

	/**
	 * How many hits the brick can take before it dies.
	 */
	private int hitCount;

	/**
	 * The brick's color.
	 */
	private String color;
	
	/**
	 * Create a default brick: Default size, default hit count, default color.
	 */
	public ArkanoidBrick() {
		this(new Vector(width, height), 1);
		color = "red";
	}

	/**
	 * Create a brick of the given color.
	 */
	public ArkanoidBrick(String color) {
		this();
		setColor(color);
	}

	/**
	 * Create a brick that can take a specific amount of hits.
	 */
	public ArkanoidBrick(int maxhits) {
		this();
		this.hitCount = maxhits;
	}

	/**
	 * Create a brick of the given size. Private since the size is fixed.
	 */
	private ArkanoidBrick(Vector size, int maxhits) {
		super();
		this.hitCount = maxhits;
		this.size = size;
	}
	
	/**
	 * Change the color of the brick. Must match a file arkanoid_COLOR.png in
	 * images/.
	 */
	public void setColor(String color) {
		this.color = color;
		updateColor();
	}

	/**
	 * Update the GreenfootImage, when the color property has changed.
	 */
	protected void updateColor() {
		setImage("arkanoid_"+color+".png");
	}

	/**
	 * Called when a Ball hits this brick.
	 */
	public void hit() {
		this.hitCount--;
		if(hitCount <= 0) {
			getWorld().removeObject(this);
		}
	}

	/**
	 * A brick is a rectangle.
	 */
	public Shape getShape() {
		return new Rectangle(getLocation(), size);
	}
}
