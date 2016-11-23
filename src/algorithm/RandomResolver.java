package algorithm;

import java.util.Random;

import tools.Action;

public class RandomResolver extends Resolver {

	private Random random;
	
	@Override
	public void init() {
		this.random = new Random();
	}

	@Override
	public Action step(int currentCaseId) {

		int action = this.random.nextInt(4);

		switch(action) {
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
}
