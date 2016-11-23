package specifications;

import java.util.ArrayList;

import data.Bot;
import data.Labyrinth;
import tools.Action;

public interface ReadService {
	public Labyrinth getLabyrinth();
	
	public ArrayList<Bot> getBots();
	
	public int getCaseId(int indexBot);
	
	public ArrayList<Action> getHistories(int indexBot);
	
	public int getCountBot();
	
	public boolean canMove(int indexBot, Action action);

	int getStepCount();

	int getCountBackForBor(int index);
}
