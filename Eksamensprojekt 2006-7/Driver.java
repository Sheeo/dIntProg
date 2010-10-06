public class Driver {
	public static void exam() {
		Article a1 = new Article("William H. Gates", "Pancake Sorting", 2);
		Article a2 = new Article("Bob Newbie", "My First Article", 2);
		Article a3 = new Article("Ivan Damgaard", "On the Post-Modern Musical Genres in the 2010's", 4);
		System.out.println("a1:");
		System.out.println(a1);
		System.out.println("a2:");
		System.out.println(a2);
		System.out.println("a3:");
		System.out.println(a3);
		Journal myJournal = new Journal("My exciting journal");
		myJournal.add(a1);
		myJournal.add(a2);
		myJournal.add(a3);
		myJournal.printArticles();
		System.out.println("The best article is clearly "+myJournal.highScoreArticle());
	}

	public static void main(String[] args) {
		exam();
	}
}
