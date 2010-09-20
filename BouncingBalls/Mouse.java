import greenfoot.*;
public class Mouse {
	private MouseState lastState;
	private Actor concerning;
	public Mouse() {
		this(null);
	}
	public Mouse(Actor concerning) {
		lastState = new MouseState(false, false, false, false, null);
		this.concerning = concerning;
	}
	public MouseState getState() {
		return lastState;
	}
	public void act() {
		MouseInfo mouseinfo = Greenfoot.getMouseInfo();
		boolean held = lastState.held;
		boolean down = false;
		boolean up = false;
		boolean moved = false;
		Vector position;
		if (mouseinfo != null) {
			position = new Vector(mouseinfo.getX(), mouseinfo.getY());
			moved = lastState.position == null || position.subtract(lastState.position).length() > 0.5;
		} else {
			position = lastState.position;
		}
		if (lastState.held) {
			if (System.getProperty("os.name").equals("Mac OS X")) {
				if (Greenfoot.mouseClicked(null) || mouseinfo != null && (mouseinfo.getActor() != concerning || mouseinfo.getButton() != 1)) {
					up = true;
					held = false;
				}
			} else {
				if (Greenfoot.mouseClicked(concerning) || mouseinfo != null && (mouseinfo.getActor() != concerning || mouseinfo.getButton() != 0)) {
					up = true;
					held = false;
				}
			}
		} else {
			if (Greenfoot.mousePressed(concerning)) {
				down = true;
				held = true;
			}
		}
		lastState = new MouseState(held, down, up, moved, position);
	}
}
