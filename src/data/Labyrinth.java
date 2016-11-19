package data;

import java.util.ArrayList;

public class Labyrinth {
	private ArrayList<Case> cases;
	
	public Labyrinth() {
		this.cases = new ArrayList<Case>();
	}
	
	public void addCase(final Case cell) {
		this.cases.add(cell);
	}
}
