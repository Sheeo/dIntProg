public class Plane {
	private String name;
	private int capacity;
	private int range;

	public Plane(String name, int capacity, int range) {
		this.name = name;
		this.capacity = capacity;
		this.range = range;
	}
	
	public int getCapacity() {
		return capacity;
	}

	public int getRange() {
		return range;
	}

	public String toString() {
		return String.format("%s, %d, %d", name, capacity, range);
	}

	public String getName() {
		return name;
	}

}
