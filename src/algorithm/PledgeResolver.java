package algorithm;

import java.util.ArrayList;
import java.util.Random;

import tools.Action;

public class PledgeResolver extends Resolver {

	private Random random;
	
	@Override
	public void init() {
		this.random = new Random();
	}

	@Override
	public Action step(int currentCaseId) {
		this.caseVisited.add(currentCaseId);

		for (Action direction : this.getOrderTryDirection()) {

			if (this.info.canMove(direction)) {
				return direction;
			}
		}

		return Action.BACK;
	}	
	
	protected ArrayList<Action> getOrderTryDirection() {
		
		ArrayList<Action> lastActions = this.info.getHistories();
		ArrayList<Action> orders = new ArrayList<Action>();

		// start point
		if (lastActions.isEmpty()) {
			orders.add(Action.DOWN);
			orders.add(Action.RIGHT);
			//orders.add(Action.UP);
			//orders.add(Action.LEFT);

			return orders;
		}
		
		Action lastDirection = lastActions.get(lastActions.size() - 1);
		
		if (lastDirection == Action.UP) {
			orders.add(Action.RIGHT);
			orders.add(Action.UP);
			orders.add(Action.LEFT);
			orders.add(Action.DOWN);
		} else if (lastDirection == Action.RIGHT) {
			orders.add(Action.DOWN);
			orders.add(Action.RIGHT);
			orders.add(Action.UP);
			orders.add(Action.LEFT);
		} else if (lastDirection == Action.DOWN) {
			orders.add(Action.LEFT);
			orders.add(Action.DOWN);
			orders.add(Action.RIGHT);
			orders.add(Action.UP);
		} else {
			orders.add(Action.UP);
			orders.add(Action.LEFT);
			orders.add(Action.DOWN);
			orders.add(Action.RIGHT);
		}

		return orders;
	}
}
