public class Driver {
	public static void main(String[] args) {
		Exam();
	}
	public static void Exam() {
		Politician p1 = new Politician("Verner Valgflæsk", "Venstre", 300);

		Politician p2 = new Politician("Helle Thorning", "Soc. Dem", 1);

		System.out.println(p1.toString());
		System.out.println(p2.toString());


		Parliament par = new Parliament();
		par.add(p1);
		par.add(p2);
		
		System.out.println("Antallet af politikere i Venstre");
		System.out.println(par.noOfPoliticians("Venstre"));

		System.out.println("Politikere med over 50 stemmer:");
		System.out.println(par.find(50));
	}
}
