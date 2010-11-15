import java.util.ArrayList;

public class Flight
{
	String flightNumber;
	String departure;
	String destination;
	int totalLoadCapacity;
	ArrayList<Luggage> luggage;

	public Flight(String flightNumber, String departure, String destination, int totalLoadCapacity)
	{
		this.flightNumber = flightNumber;
		this.departure = departure;
		this.destination = destination;
		this.totalLoadCapacity = totalLoadCapacity;
		this.luggage = new ArrayList<Luggage>();
	}
	
	public void addLuggage(Luggage l)
	{
		if(availableLoadCapacity() > l.getWeight())
		{
			luggage.add(l);
		}
	}
	
	public void removeLuggage(Luggage l)
	{
		luggage.remove(l);
	}
	
	int availableLoadCapacity() {
		return totalLoadCapacity - totalLoad();
	}
	
	int totalLoad() {
		int s = 0;
		for(Luggage l : luggage)
		{
			s += l.getWeight();
		}
		return s;
	}

	public String toString()
	{
		String format = "%s: %s - %s (C: %d kg; A: %d kg)";
		return String.format(format, flightNumber, departure, destination, totalLoadCapacity, availableLoadCapacity());
	}
}
