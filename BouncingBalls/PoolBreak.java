import java.util.*;
public class PoolBreak extends BallWorld {
	private Ball white;
	private int acttick;

	enum State {START, ROLL, END};
	
	private State state;

	private boolean autoMode;
	
	public PoolBreak() {
		super();
		setFriction(0.995);
		reset();
	}
	public void autoOn() {
		autoMode = true;
		if (null != white) {
			white.disableMouse();
		}
	}
	public void autoOff() {
		autoMode = false;
		if (null != white) {
			white.enableMouse();
		}
	}
	public void reset() {
		removeObjects(getObjects(Ball.class));
		addFirstBall();
		addMoreBalls();
		state = State.START;
		acttick = 0;
	}
	public void act() {
		super.act();
		if (!autoMode) {return;}
		switch (state) {
			case START:
				++acttick;
				if (acttick == 80) {
					white.setVelocity(new Vector(r.nextGaussian()*4.0+12.0, r.nextDouble()*0.2-0.1));
					state = State.ROLL;
					acttick = 0;
				}
				break;
			case ROLL:
				++acttick;
				if (acttick % 100 == 0) {
					double sum = velocitySum();
					System.out.println("Sum of velocities: "+sum);
					if (sum < 0.8) {
						state = State.END;
						acttick = 0;
					}
				}
				break;
			case END:
				++acttick;
				if (acttick == 300) {
					reset();
				}
				break;
		}
	}
	private double velocitySum() {
		List objs = getObjects(Ball.class);
		double sum = 0.0;
		for (Object o : objs) 
			sum += ((Ball) o).getVelocity().length();
		return sum;
	}
	public void addFirstBall() {
		Vector pos = getSize().scale(new Vector(1.0/16.0, 0.5));
		white = addBall((int) Math.round(pos.x()), (int) Math.round(pos.y()), 0.0, 0.0);
		if (!autoMode) {white.enableMouse();}
	}
	public void addMoreBalls() {
		Vector base = getSize().scale(new Vector(0.6, 0.5));
		Vector dx = Vector.fromPolar(-Math.PI/6, 2*ballRadius);
		Vector dy = new Vector(0, 2*ballRadius);
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j <= i; ++j) {
				addBall(base.add(dx.scale(i)).add(dy.scale(j)));
			}
		}
	}
}
