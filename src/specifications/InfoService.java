package specifications;

import java.util.ArrayList;

import tools.Action;

public interface InfoService {
	public boolean canMove(Action action);
	
	public ArrayList<Action> getHistories();
	
	int getCaseIdNeighbor(Action action);
}
