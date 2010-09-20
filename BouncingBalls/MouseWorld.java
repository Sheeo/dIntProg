/**
 * A world containing a ball which can be manipulated act-time using the mouse.
 */
public class MouseWorld extends PhysicsWorld {
	public MouseWorld() {
		super(640, 480);
		Ball b = new Ball(0, 0);
		addObject(b, 320, 240);
		b.enableMouse();
	}
}
