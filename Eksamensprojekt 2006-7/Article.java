import java.util.*;
public class Article {
	private String author;
	private String title;
	private int score;

	public Article(String a, String t, int s) {
		author = a;
		title = t;
		score = s;
	}

	public String getAuthor() {return author;}
	public String getTitle() {return title;}
	public int getScore() {return score;}

	public String toString() {
		return getAuthor()+": "+getTitle()+" (score "+getScore()+")";
	}
}
