package specifications;

import data.Bot;
import data.Labyrinth;
import tools.Action;

public interface WriteService {
	public void setLabyrinth(Labyrinth labyrinth);
	
	public void addBot(Bot bot);
	
	public void moveBot(int indexBot, Action action);
}
