package factory;

import java.util.ArrayList;
import java.util.Random;

import data.Case;
import data.Labyrinth;
import specifications.LabyrinthFactoryService;
import tools.Action;
import tools.HardCodedParameters;
import tools.Type;

public class LabyrinthFactory implements LabyrinthFactoryService {

	private Random random;
	private Labyrinth labyrinth;

	public LabyrinthFactory() {
		this.random = new Random();
	}

	@Override
	public Labyrinth getLabyrinth() {		
		int height = HardCodedParameters.heightLabyrinth;
		int width = HardCodedParameters.widthLabyrinth;
		
		this.labyrinth = new Labyrinth(height, width);
		
		int nbCells = height * width;

		final int countDoorToOpen = nbCells - 1;

		for (int i = 0; i < countDoorToOpen;) {
			// get random cell
			int indexCase = this.random.nextInt(nbCells - 1);

			// get random direction
			Action direction = this.getRandomDirection();

			// try to open door
			if (this.tryOpenDoor(indexCase, direction)) {
				i++;
			}
		}

		this.labyrinth.getCase(0).setStatus(Type.START);
		this.labyrinth.getCase(this.labyrinth.getSize()).setStatus(Type.END);

		return labyrinth;
	}

	private Action getRandomDirection() {
		int direction = this.random.nextInt(3);

		switch (direction) {
			case 0:
				return Action.UP;
	
			case 1:
				return Action.DOWN;
	
			case 2:
				return Action.LEFT;
	
			case 3:
				return Action.RIGHT;
	
			default:
				return Action.UP;
		}
	}

	private boolean tryOpenDoor(int indexCaseFrom, Action direction) {
		Case caseFrom = this.labyrinth.getCase(indexCaseFrom);

		int indexCaseTo = this.labyrinth.getIndexNeighbor(indexCaseFrom, direction);

		if (indexCaseTo < 0 || indexCaseTo >= this.labyrinth.getSize()) {
			return false;
		}

		Case caseTo = this.labyrinth.getCase(indexCaseTo);

		if (caseFrom.getIdWay() == caseTo.getIdWay()) {
			return false;
		}

		this.labyrinth.openDoor(indexCaseFrom, direction);

		return true;
	}
}
