package algorithm;

import java.util.ArrayList;
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
			System.out.println("BACK");
			return Action.BACK;
		}

		Action action = Action.UP;
		
		do {
			
			System.out.println("TRY DIRECTION");
			
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
	
	protected boolean isIntersection() {
		
		int openDirection = 0;

		if (this.info.canMove(Action.UP)) {
			openDirection++;
		}
		
		if (this.info.canMove(Action.DOWN)) {
			openDirection++;
		}
		
		if (this.info.canMove(Action.LEFT)) {
			openDirection++;
		}
		
		if (this.info.canMove(Action.RIGHT)) {
			openDirection++;
		}

		return openDirection > 2;
	}
	
	protected boolean allDirectionIsVisited() {
		
		ArrayList<Action> directions = new ArrayList<Action>();
		
		directions.add(Action.UP);
		directions.add(Action.DOWN);
		directions.add(Action.LEFT);
		directions.add(Action.RIGHT);

		for (Action direction : directions) {
			if (!this.isVisitedCase(this.info.getCaseIdNeighbor(direction)) && this.info.canMove(direction)) {
				return false;
			}			
		}

		return true;
	}

	
}

/*
 * if intersection
 * - if unvisited direction
 * - - go random unvisited direction
 * - else
 * - - back
 * else
 * - if all visited
 * - - back
 * - else
 * - - continue way
 */
