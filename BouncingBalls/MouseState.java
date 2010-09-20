public class MouseState {
	public final boolean held;
	public final boolean down;
	public final boolean up;
	public final boolean moved;
	public final Vector position;
	public MouseState(boolean held, boolean down, boolean up, boolean moved, Vector position) {
		this.held = held;
		this.down = down;
		this.up = up;
		this.moved = moved;
		this.position = position;
	}
}
