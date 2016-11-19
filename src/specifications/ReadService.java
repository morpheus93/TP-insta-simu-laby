package specifications;

import java.util.ArrayList;

import data.Bot;
import data.Labyrinth;

public interface ReadService {
	public Labyrinth getLabyrinth();
	
	public ArrayList<Bot> getBots();
	
	public int getCaseId(int indexBot);
}
