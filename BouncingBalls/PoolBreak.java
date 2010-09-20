import java.util.*;
/**
 * A BallWorld that emulates a break in a game of 8-ball Pool (the cue game).
 * You may move the white ball with the mouse to break.
 * Call autoOn() to enable automatic breaking.
 */
public class PoolBreak extends BallWorld {
	/**
	 * States in automatic mode.
	 */
	enum State {
		START, /* Waiting to break. */
		ROLL, /* Breaking, and continuously checking the sum of ball speeds. */
		END /* Post-break, balls are mostly lying still, waiting to restart. */
	};
	
	/**
	 * The white ball (note: may not graphically be white).
	 */
	private Ball white;

	/**
	 * In automatic mode, the number of ticks until the next action.
	 */
	private int acttick;

	/**
	 * Current state in automatic mode.
	 */
	private State state;

	/**
	 * Whether we're in automatic mode.
	 */
	private boolean autoMode;
	
	public PoolBreak() {
		super(800, 442);
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

	/**
	 * (Re-)set the field. Remove all balls, add the white ball, add more balls,
	 * reset state variables.
	 */
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
		/* Do what an automatic game of Pool does: Mostly waiting. */
		switch (state) {
			case START:
				++acttick;
				if (acttick == 80) {
					/* It's time to break. Apply a force to the white ball, and increment
					 * the state. */
					white.setVelocity(new Vector(r.nextGaussian()*4.0+12.0, r.nextDouble()*0.2-0.1));
					state = State.ROLL;
					acttick = 0;
				}
				break;
			case ROLL:
				++acttick;
				if (acttick % 100 == 0) {
					/* Check if balls are still rolling. */
					double sum = velocitySum();
					if (sum < 0.8) {
						/* If not, increment the state. */
						state = State.END;
						acttick = 0;
					}
				}
				break;
			case END:
				++acttick;
				if (acttick == 300) {
					/* It's getting dull, so we'd better reset. */
					reset();
				}
				break;
		}
	}

	/**
	 * Find the sum of the ball speeds.
	 */
	private double velocitySum() {
		List objs = getObjects(Ball.class);
		double sum = 0.0;
		for (Object o : objs) 
			sum += ((Ball) o).getVelocity().length();
		return sum;
	}

	/**
	 * Add the white ball and store it in a property.
	 */
	public void addFirstBall() {
		Vector pos = getSize().scale(new Vector(1.0/16.0, 0.5));
		white = addBall((int) Math.round(pos.x()), (int) Math.round(pos.y()), 0.0, 0.0);
		white.setImage("poolballs/16.png");
		if (!autoMode) {white.enableMouse();}
	}

	/**
	 * Add the 15 balls in a pre-break position.
	 */
	public void addMoreBalls() {
		Vector base = getSize().scale(new Vector(0.6, 0.5));
		Vector dx = Vector.fromPolar(-Math.PI/6, 2*ballRadius);
		Vector dy = new Vector(0, 2*ballRadius);
		int bc = 1;
		for (int i = 0; i < 5; ++i) {
			for (int j = 0; j <= i; ++j) {
				Ball b = addBall(base.add(dx.scale(i)).add(dy.scale(j)));
				b.setImage(String.format("poolballs/%d.png", bc ));
				bc++;
			}
		}
	}
}
