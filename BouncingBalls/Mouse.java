import greenfoot.*;
/**
 * Glue to the Greenfoot mouse event API. A stateful mouse listener, possibly
 * only concerned with a certain Greenfoot Actor. Used to get around
 * limitations and bugs in Greenfoot's Greenfoot.getMouseInfo():
 *
 * - Greenfoot.getMouseInfo() is sometimes null
 *
 * - Greenfoot.getButton() is platform-inconsistent
 *
 * - It is hard to get the mouse position unless you store it explicitly when
 *   you *can* read it
 */
public class Mouse {
	/**
	 * The state of the mouse pointer when act() was last called.
	 */
	private MouseState lastState;

	/**
	 * The Actor we're concerned about. null for the entire world, as per the
	 * argument to most of Greenfoot's mouse functions.
	 */
	private Actor concerning;

	/**
	 * Create a mouse listener concerned with the entire world.
	 */
	public Mouse() {
		this(null);
	}

	/**
	 * Create a mouse listener concerned with the specified actor, and initialise
	 * the lastState variable to a null state.
	 */
	public Mouse(Actor concerning) {
		lastState = new MouseState(false, false, false, false, null);
		this.concerning = concerning;
	}

	/**
	 * Getter for lastState
	 */
	public MouseState getState() {
		return lastState;
	}

	/**
	 * Update the lastState property. Should ideally be called on each World.act(), or at least before getState() in a frame where mouse info is wanted.
	 */
	public void act() {
		MouseInfo mouseinfo = Greenfoot.getMouseInfo();

		/* To-be properties of MouseState */
		boolean held = lastState.held;
		boolean down = false;
		boolean up = false;
		boolean moved;
		Vector position;

		if (mouseinfo != null) {
			/* Get the new mouse position */
			position = new Vector(mouseinfo.getX(), mouseinfo.getY());
			/* We have moved if there is a difference between this mouse position and
			 * the previous mouse position. */
			moved = lastState.position == null || position.subtract(lastState.position).length() > 0.5;

		} else {
			/* mouseinfo == null */

			/* Use the previous mouse position */
			position = lastState.position;
			/* We haven't moved the mouse pointer. */
			moved = false;
		}

		if (lastState.held) {
			/* Figure out if the mouse has been released. This has to be done
			 * differently on Mac OS X, as MouseInfo contains different info in case
			 * of mouse release on that platform. */
			if (System.getProperty("os.name").equals("Mac OS X")) {
				if (Greenfoot.mouseClicked(null) || mouseinfo != null && (mouseinfo.getActor() != concerning || mouseinfo.getButton() != 1)) {
					up = true;
					held = false;
				}
			} else {
				/* TODO: Check if this behaves correctly in Windows */
				if (Greenfoot.mouseClicked(concerning) || mouseinfo != null && (mouseinfo.getActor() != concerning || mouseinfo.getButton() != 0)) {
					up = true;
					held = false;
				}
			}

		} else {
			/* !lastState.held */

			/* Figure out if mouse has been pressed. This is thankfully
			 * cross-platform consistent. */
			if (Greenfoot.mousePressed(concerning)) {
				down = true;
				held = true;
			}
		}

		/* Store the state in the lastState property. */
		lastState = new MouseState(held, down, up, moved, position);
	}
}
