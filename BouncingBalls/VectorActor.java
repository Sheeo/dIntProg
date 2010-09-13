import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class VectorActor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VectorActor extends Actor
{
	public Vector getLocation() {
		return new Vector(getX(), getY());
	}
	public void setLocation(Vector vector) {
		setLocation((int) Math.round(vector.x()), (int) Math.round(vector.y()));
	}
	public void moveBy(Vector vector) {
		setLocation(getLocation().add(vector));
	}
}
