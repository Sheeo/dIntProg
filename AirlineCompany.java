import java.util.*;

public class AirlineCompany {
	public String name;
	
	private ArrayList<Plane> planes;
	
	public AirlineCompany() {
		planes = new ArrayList<Plane>();
	}

	public void add(Plane p) {
		planes.add(p);
	}

	public void remove(Plane p) {
		planes.remove(p);
	}

	public List<Plane> select(int low, int high) {
		List<Plane> res = new ArrayList<Plane>();
		for(Plane p : planes) {
			if(p.getCapacity() > low && p.getCapacity() < high) {
				res.add(p);
			}
		}
		return res;
	}

	public Plane widestRange() {
		int maxRange = 0;
		Plane outplane = new Plane("",0,0);
		for(Plane p : planes) {
			if(p.getRange() > maxRange) {
				outplane = p;
				maxRange = p.getRange();
			}
		}
		return outplane;
	}

	public void printPlanes() {
		ArrayList<Plane> copy = planes.clone();
		// Todo: implement comparator
		for(Plane p : copy) {
			System.out.println(p.toString());
		}
	}
}
