package algorithm;

import java.util.Random;

import tools.Action;

public class DepthFirstResolver extends Resolver {

	private Random random;
	
	@Override
	public void init() {
		this.random = new Random();
	}

	@Override
	public Action step(int currentCaseId) {
		
		this.caseVisited.add(currentCaseId);

		if (this.allDirectionIsVisited()) {
			return Action.BACK;
		}

		Action action = Action.UP;
		
		do {
			int rand = this.random.nextInt(4);

			switch(rand) {
			case 0:
				action = Action.UP;
				break;
				
			case 1:
				action = Action.DOWN;
				break;
				
			case 2:
				action = Action.LEFT;
				break;

			case 3:
				action = Action.RIGHT;
				break;
			}
			
		} while (!this.info.canMove(action) || this.isVisitedCase(this.info.getCaseIdNeighbor(action)));

		return action;
	}	
}
