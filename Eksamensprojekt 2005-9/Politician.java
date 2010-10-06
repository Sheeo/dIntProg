import java.util.*;

public class Politician {
	public String name;
	public String party;
	public int votes;
	
	public Politician(String name, String party, int votes) {
		this.name = name;
		this.party = party;
		this.votes = votes;
	}

	public String toString() {
		return String.format("%s, %s, %d stemmer", name, party, votes);
	}

	public String getName() {
		return name;
	}
	public String getParty() {
		return party;
	}

	public int getVotes() {
		return votes;
	}
}
