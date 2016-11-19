package specifications;

import java.util.ArrayList;

import tools.Action;

public interface EngineService {
	public void init();

	public void start();

	public void stop();
	
	public boolean canMove(Action action);
	
	public ArrayList<Action> getHistories();
}
