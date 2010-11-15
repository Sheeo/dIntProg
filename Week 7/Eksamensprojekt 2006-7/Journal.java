import java.util.*;
public class Journal {
	private String name;
	private List<Article> articles;

	public Journal(String n) {
		articles = new ArrayList<Article>();
		name = n;
	}

	public void add(Article a) {
		articles.add(a);
	}

	public void remove(Article a) {
		articles.remove(a);
	}

	public List<Article> selectByAuthor(String s) {
		List<Article> res = new ArrayList<Article>();
		for (Article a : articles) {
			if (a.getAuthor().equals(s)) {
				res.add(a);
			}
		}
		return res;
	}

	public Article highScoreArticle() {
		return Collections.max(articles, new Comparator<Article>() {
			public int compare(Article o1, Article o2) {
				return o1.getScore()-o2.getScore();
			}
		});
	}

	public void printArticles() {
		Collections.sort(articles, new Comparator<Article>() {
			public int compare(Article o1, Article o2) {
				int cmp = o2.getScore()-o1.getScore();
				if (cmp == 0) {
					cmp = o1.getAuthor().compareTo(o2.getAuthor());
				}
				return cmp;
			}
		});
		System.out.println("Journal "+name);
		for (Article a : articles) {
			System.out.println("\t"+a);
		}
	}
}
