import java.util.*;

public class Parliament {
	public String name;

	private ArrayList<Politician> politicians;

	public Parliament() {
		politicians = new ArrayList<Politician>();
	}

	public void add(Politician p) {
		politicians.add(p);
	}
	public void remove(Politician p) {
		politicians.remove(p);
	}

	public int noOfPoliticians(String party) {
		int n = 0;
		for(Politician p : politicians) {
			if(p.getParty() == party) {
				n++;
			}
		}
		return n;
	}

	public List<Politician> find(int threshold) {
		ArrayList<Politician> res = new ArrayList<Politician>();
		for(Politician p : politicians) {
			if(p.getVotes() > threshold) {
				res.add(p);
			}
		}
		return res;
	}

	public void printPoliticians() {
		Collections.sort(politicians, new Comparator<Politician>(){
			public int compare(Politician p1, Politician p2) {
				int cmp = p1.getParty().compareTo(p2.getParty());
				if(cmp == 0) {
					cmp = p1.getName().compareTo(p2.getName());
					if(cmp == 0) {
						cmp = p1.getVotes() - p2.getVotes();
					}
				}
				return cmp;
			}
		});
		for(Politician p : politicians) {
			System.out.println(p);
		}
	}
}
