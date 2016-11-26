package algorithm;

import java.util.ArrayList;

import specifications.InfoService;
import specifications.RequireInfoService;
import specifications.ResolverService;
import tools.Action;

public abstract class Resolver implements ResolverService, RequireInfoService {
	protected ArrayList<Integer> caseVisited;
	protected String name;
	protected InfoService info;
	
	private static int countResolver = 0;
	
	public Resolver() {
		this.caseVisited = new ArrayList<Integer>();
		Resolver.countResolver++;

		this.name = "Resolver #" + Integer.toString(Resolver.countResolver);
	}

	protected boolean isVisitedCase(int caseId) {
		for (Integer id : caseVisited) {
			if (id == caseId) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public void bindInfoService(InfoService info) {
		this.info = info;
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
