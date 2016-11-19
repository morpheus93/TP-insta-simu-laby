package data;

import java.util.ArrayList;

public class Labyrinth {
	private ArrayList<Case> cases;
	private int height;
	private int width;

	public Labyrinth() {
		this.cases = new ArrayList<Case>();
		
		// TODO should be dynamic
		this.height = 10;
		this.width 	= 10;
	}

	public void addCase(final Case cell) {
		this.cases.add(cell);
	}

	public int getCaseId(Position position) {
		
		int index = position.getY() * this.width + position.getX();
		
		if (this.cases.size() <= index) {
			return -1; // TODO throw exception
		}
		
		return this.cases.get(index).getId();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
}
