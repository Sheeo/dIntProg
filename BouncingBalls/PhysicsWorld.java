import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

/**
 * A world of highly realistic physics.
 * 
 * @author Michael Søndergaard
 * @author Mathias Rav
 */
public class PhysicsWorld extends World
{
	protected Vector gravity;

	public PhysicsWorld() {	
		this(540, 480);
	}

	public PhysicsWorld(int width, int height) {
		super(width, height, 1);
		setGravity(new Vector(0.0, 0.3));
	}

	public void setGravity(Vector v) {
		gravity = v;
	}
	public void setDownwardsGravity(double g) {
		gravity = new Vector(0, g);
	}
	public Vector getGravity() {
		return gravity;
	}
	public void act() {
		List objs = getObjects(ShapeActor.class);
		for (Object o : objs) {
			//ShapeActor a = (ShapeActor) o;
			Ball a = (Ball) o;
			a.checkCollisions();
			addGravity(a);
		}
		for (Object o : objs) {
			//ShapeActor a = (ShapeActor) o;
			Ball a = (Ball) o;
			a.move();
		}
	}
	protected void addGravity(DynamicActor a) {
		a.setVelocity(a.getVelocity().add(getGravity()));
	}
	public Vector getSize() {
		return new Vector(getWidth(), getHeight());
	}
}
