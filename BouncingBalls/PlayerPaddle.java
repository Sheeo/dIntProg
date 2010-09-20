import java.util.*;
import greenfoot.*;
/**
 * A Paddle controlled with the keyboard.
 */
public class PlayerPaddle extends Paddle {
	/**
	 * The commands we bind to keys.
	 */
	public enum Controls {LEFT, RIGHT};

	/**
	 * Each command may be mapped to at most one key.
	 */
	private EnumMap<Controls, String> controls;

	/**
	 * Create a player-controlled paddle and initialise the command map.
	 */
	public PlayerPaddle() {
		controls = new EnumMap<Controls, String>(Controls.class);
	}

	/**
	 * Create a PlayerPaddle with the given controls.
	 */
	public PlayerPaddle(String left, String right) {
		this();
		setKey(Controls.LEFT, left);
		setKey(Controls.RIGHT, right);
	}

	/**
	 * Set a control.
	 */
	public void setKey(Controls control, String key) {
		controls.put(control, key);
	}

	/**
	 * Move if a key is held down.
	 */
	public void act() {
		super.act();
		String keyleft = controls.get(Controls.LEFT);
		String keyright = controls.get(Controls.RIGHT);
		boolean left = (keyleft != null && Greenfoot.isKeyDown(keyleft));
		boolean right = (keyright != null && Greenfoot.isKeyDown(keyright));
		if (left && !right) {
			moveBy(-2.0);
		} else if (right && !left) {
			moveBy(2.0);
		}
	}

	/**
	 * Move by the specified amount along the x-axis.
	 */
	private void moveBy(double dX) {
		setLocation(getLocation().add(new Vector(dX, 0)));
	}
}
