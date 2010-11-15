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
		ArrayList<Plane> copy = (ArrayList)planes.clone();
		Collections.sort(copy, new Comparator<Plane>() {
			public int compare(Plane o1, Plane o2) {
				int cmp = o1.getCapacity()-o2.getCapacity();
				if(cmp == 0)
				{
					cmp = o1.getName().compareTo(o2.getName());
				}
				return cmp;
			}
		});
		for(Plane p : copy) {
			System.out.println(p.toString());
		}
	}
}
