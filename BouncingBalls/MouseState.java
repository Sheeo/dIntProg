/**
 * A dumb data store that contains info about the mouse pointer in some point
 * of time.
 */
public class MouseState {
	/**
	 * Is the mouse being held down?
	 */
	public final boolean held;

	/**
	 * Was the mouse pointer just pressed down?
	 */
	public final boolean down;

	/**
	 * Was the mouse pointer just released?
	 */
	public final boolean up;

	/**
	 * Was the mouse pointer moved while being held?
	 */
	public final boolean moved;

	/**
	 * Where is the mouse pointer now?
	 */
	public final Vector position;

	/**
	 * Instantiate a MouseState.
	 */
	public MouseState(boolean held, boolean down, boolean up, boolean moved, Vector position) {
		this.held = held;
		this.down = down;
		this.up = up;
		this.moved = moved;
		this.position = position;
	}
}
