package data;

import java.util.ArrayList;

import tools.Action;
import tools.Type;

public class Labyrinth {
	private ArrayList<Case> cases;
	private int height;
	private int width;

	public Labyrinth(int height, int width) {
		this.cases = new ArrayList<Case>();

		this.height = height;
		this.width 	= width;

		for (int i = 0; i < height * width; i++) {
			Case cell = new Case(i, false, false, Type.NOTHING);
			this.cases.add(cell);
		}
	}

	public void addCase(final Case cell) {
		this.cases.add(cell);
	}

	public int getCaseId(Position position) {
		
		int index = position.getY() * this.width + position.getX();
		
		if (this.cases.size() <= index) {
			return -1;
		}
		
		return this.cases.get(index).getId();
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}
	
	public boolean canMove(int caseId, Action action) {
		switch(action) {
		case UP:
			return this.getCaseById(caseId - this.width).isDownIsOpen();

		case DOWN:
			return this.getCaseById(caseId).isDownIsOpen();
			
		case LEFT:
			return this.getCaseById(caseId - 1).isRightIsOpen();

		case RIGHT:
			return this.getCaseById(caseId).isRightIsOpen();
			
		case BACK:
			return true;

		default:
			return false;
		}
	}
	
	private Case getCaseById(int caseId) {
		for (Case cell: this.cases) {
			if (caseId == cell.getId()) {
				return cell;
			}
		}

		return new Case(-1, false, false, Type.NOTHING);
	}
	
	public int getIndexByPosition(Position position)
	{
		return position.getY() * this.height + position.getX();
	}

	public int getIndexNeighbor(int indexCase, Action direction) {
		switch (direction) {
			case DOWN:
				return this.getBottom(indexCase);
				
			case LEFT:
				return this.getLeft(indexCase);

			case RIGHT:
				return this.getRight(indexCase);

			case UP:
				return this.getTop(indexCase);

			default:
				return -1;
		}
	}


	private int getTop(int indexFrom) {
		if (indexFrom < this.height) {
			return -1;
		}
		
		return indexFrom - this.width;
	}
	
	private int getBottom(int indexFrom) {
		if (indexFrom >= this.height * (this.width - 1)) {
			return -1;
		}

		return indexFrom + this.width;
	}
	
	private int getLeft(int indexFrom) {
		if (0 == indexFrom % this.width) {
			return -1;
		}
		
		return indexFrom - 1;
	}
	
	private int getRight(int indexFrom) {
		if (this.width -1 == indexFrom % this.width) {
			return -1;
		}
		
		return indexFrom + 1;
	}
	
	private void updateIdWay(int from, int to) {
		for (Case cell : this.cases) {
			if (from == cell.getIdWay()) {
				cell.setIdWay(to);
			}
		}
	}
	
	public void openDoor(int indexFrom, Action direction) {
		
		Case caseFrom = this.cases.get(indexFrom);
		Case caseTo = this.cases.get(this.getIndexNeighbor(indexFrom, direction));
		
		switch (direction) {
			case DOWN:
				caseFrom.setDownIsOpen(true);
				break;
	
			case LEFT:
				caseTo.setRightIsOpen(true);
				break;
				
			case RIGHT:
				caseFrom.setRightIsOpen(true);
				break;
	
			case UP:
				caseTo.setDownIsOpen(true);
				break;
		}
		
		this.updateIdWay(caseFrom.getIdWay(), caseTo.getIdWay());
	}
	
	public Case getCase(int index) {
		return this.cases.get(index);
	}
	
	public int getSize() {
		return this.cases.size();
	}
}
