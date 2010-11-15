import java.util.*;

public class Driver {
	public static void main(String[] args) {
		Exam();
	}
	
	public static void Exam() {
		Plane p1 = new Plane("Airbus 340-300", 295, 14800);
		System.out.println(p1.toString());
		Plane p2 = new Plane("Boeing 747-200", 305, 12500);
		System.out.println(p2.toString());
		Plane p3 = new Plane("Tupolev 2", 72, 9000);
		System.out.println(p3.toString());
		Plane p4 = new Plane("Airbus 380-200", 305, 18000);
		System.out.println(p4.toString());

		AirlineCompany company = new AirlineCompany();
		company.add(p1);
		company.add(p2);
		company.add(p3);
		company.add(p4);

		List<Plane> planesBetweenCriterium = company.select(200, 300);
		System.out.println("Planes with capacity between 200 and 300");
		for(Plane p : planesBetweenCriterium) {
			System.out.println(p.toString());
		}
		
		System.out.println("Planes sorted by capacity, then range alfabetically");
		company.printPlanes();
	}


}
