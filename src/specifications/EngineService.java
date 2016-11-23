package specifications;

import java.util.ArrayList;

import tools.Action;
import tools.Resolver;

public interface EngineService {
	public void init();

	public void start();

	public void stop();
	
	public boolean canMove(Action action);
	
	public ArrayList<Action> getHistories();
	
	public void addResolver(Resolver resolver);
}
