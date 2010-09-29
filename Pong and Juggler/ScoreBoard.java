import greenfoot.*;

/**
 * A board used to display scores
 * TODO: Make scoreboard display graphically
 **/
public class ScoreBoard extends Actor
{
	private int[] scores;

	public ScoreBoard(int players) {
		scores = new int[players];
	}

	public void score(int player) {
		scores[player-1]++;
		System.out.println(toString());
	}

	public String toString() {
		String out = "Scoreboard:\n";
		for(int p = 0; p < scores.length; p++) {
			out += String.format("Player #%d score: %d\n", p, scores[p]);
		}
		return out;
	}
}
