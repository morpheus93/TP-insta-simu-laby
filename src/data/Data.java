package data;

import java.util.ArrayList;

import specifications.DataService;
import tools.Action;

public class Data implements DataService {

	private Labyrinth labyrinth;
	private ArrayList<Bot> bots;

	public Data() {
		this.bots = new ArrayList<Bot>();
	}

	@Override
	public void init() {
	}

	@Override
	public void setLabyrinth(Labyrinth labyrinth) {
		this.labyrinth = labyrinth;
	}

	@Override
	public void addBot(Bot bot) {
		this.bots.add(bot);
	}

	@Override
	public void moveBot(int indexBot, Action action) {
		Bot bot = this.getSafeBot(indexBot);
		
		if (null == bot) {
			// TODO throw exception
		}
		
		bot.move(action);
	}

	@Override
	public Labyrinth getLabyrinth() {
		return this.labyrinth;
	}

	@Override
	public ArrayList<Bot> getBots() {
		return this.bots;
	}

	@Override
	public int getCaseId(int indexBot) {
		Position position = this.getSafePosition(indexBot);

		if (null == position) {
			return -1; // TODO throw exception
		}
		
		return this.labyrinth.getCaseId(position);
	}

	private Position getSafePosition(int indexBot) {
		Bot bot = this.getSafeBot(indexBot);
		
		if (null == bot) {
			return null; // TODO throw exception
		}

		return this.bots.get(indexBot).getPosition();
	}
	
	private Bot getSafeBot(int indexBot) {
		if (indexBot < 0 || this.bots.size() <= indexBot) {
			return null; // TODO throw exception
		}

		return this.bots.get(indexBot);
	}

	@Override
	public ArrayList<Action> getHistories(int indexBot) {
		Bot bot = this.getSafeBot(indexBot);

		if (null == bot) {
			return null; // TODO throw exception
		}
		
		return bot.getHistories();
	}

	@Override
	public int getCountBot() {
		return this.bots.size();
	}
}
