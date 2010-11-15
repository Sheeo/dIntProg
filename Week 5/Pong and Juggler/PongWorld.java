/**
 * Pong!
 */
public class PongWorld extends BallWorld {


	private ScoreBoard score;

	/**
	 * Constructor
	 *
	 * Initializes, by default, a pong game with two players
	 * Player 1 = south
	 * Player 2 = north
	 **/
	public PongWorld() {
		super();
		score = new ScoreBoard(2);
		Paddle paddle1 = new PlayerPaddle("left", "right");
		addObject(paddle1, getWidth()/2, 9*getHeight()/10);
		Paddle paddle2 = new PlayerPaddle("a", "d");
		addObject(paddle2, getWidth()/2, getHeight()/10);
		addBall(getWidth()/2, getHeight()/2, 0.5, 6.0);
	}
	
	/**
	 * Add a pong-ball
	 *
	 **/
	public Ball addBall(int x, int y, double vX, double vY) {
		Ball b = new PongBall(vX, vY);
		addObject(b, x, y);
		return b;
	}
	
	/**
	 * This method is called by pongBall when it hits north/south walls
	 */
	public void pongScore(PhysicsWorld.Walls wall) {
		if(wall == PhysicsWorld.Walls.NORTH) {
			// Player 1 scores
			score.score(1);
			addBall(getWidth()/2, getHeight()/2, 0, -6.0);
		}
		else if(wall == PhysicsWorld.Walls.SOUTH) {
			// Player 2 scores
			score.score(2);
			addBall(getWidth()/2, getHeight()/2, 0, 6.0);
		}
	}
}
