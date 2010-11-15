public class Driver
{
    public static void Exam() {
		System.out.println("Examinating standard flight");
		Flight f = new Flight("SK 945", "CPH", "AMD", 4500);
        System.out.println(f.toString());

		System.out.println("Examinating flight with load");
		f = new Flight("SK 946", "AMD", "CPH", 3500);
		Luggage l = new Luggage();
		l.setWeight(1250);
		f.addLuggage(l);
		System.out.println("Added 1250kr of luggage");

		System.out.println(f.toString());

		System.out.println("Attempting to add 4.000kg of luggage");
		l = new Luggage();
		l.setWeight(4000);
		f.addLuggage(l);
		System.out.println(f.toString());
    }
}
