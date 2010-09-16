import java.util.*;
import greenfoot.*;
public class PlayerPaddle extends Paddle {
	public enum Controls {LEFT, RIGHT};
	private EnumMap<Controls, String> controls;
	public PlayerPaddle() {
		controls = new EnumMap<Controls, String>(Controls.class);
	}
	public PlayerPaddle(String left, String right) {
		this();
		setKey(Controls.LEFT, left);
		setKey(Controls.RIGHT, right);
	}
	public void setKey(Controls control, String key) {
		controls.put(control, key);
	}
	public void act() {
		super.act();
		String keyleft = controls.get(Controls.LEFT);
		String keyright = controls.get(Controls.RIGHT);
		boolean left = (keyleft != null && Greenfoot.isKeyDown(keyleft));
		boolean right = (keyright != null && Greenfoot.isKeyDown(keyright));
		if (left && !right) {
			setVelocity(new Vector(-2.0, 0.0));
		} else if (right && !left) {
			setVelocity(new Vector(2.0, 0.0));
		} else {
			setVelocity(Vector.zero());
		}
	}
}