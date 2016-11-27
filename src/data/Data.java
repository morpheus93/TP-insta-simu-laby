package data;

import java.util.ArrayList;

import specifications.DataService;
import tools.Action;

public class Data implements DataService {

	private Labyrinth labyrinth;
	private ArrayList<Bot> bots;
	private int countStep;

	public Data() {
		this.bots = new ArrayList<Bot>();
		this.countStep = 0;
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
			return;
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
			return -1;
		}
		
		return this.labyrinth.getCaseId(position);
	}

	private Position getSafePosition(int indexBot) {
		Bot bot = this.getSafeBot(indexBot);
		
		if (null == bot) {
			return null;
		}

		return this.bots.get(indexBot).getPosition();
	}
	
	private Bot getSafeBot(int indexBot) {
		if (indexBot < 0 || this.bots.size() <= indexBot) {
			return null;
		}

		return this.bots.get(indexBot);
	}

	@Override
	public ArrayList<Action> getHistories(int indexBot) {
		Bot bot = this.getSafeBot(indexBot);
		
		if (null == bot) {
			return null;
		}
		
		return bot.getHistories();
	}

	@Override
	public int getCountBot() {
		return this.bots.size();
	}

	@Override
	public boolean canMove(int indexBot, Action action) {
		int caseId = this.getCaseId(indexBot);

		if (-1 == caseId) {
			return false;
		}
		
		return this.labyrinth.canMove(caseId, action);
	}

	@Override
	public void removeBot() {
		this.bots.removeAll(this.bots);
	}
	
	@Override
	public void reset() {
		this.labyrinth = null;
		this.removeBot();
		this.countStep = 0;
	}
	
	@Override
	public void addStep() {
		this.countStep++;
	}
	
	@Override
	public int getStepCount() {
		return this.countStep;
	}
	
	@Override
	public int getCountBackForBot(int index) {
		Bot bot = this.getSafeBot(index);
		
		if (null == bot) {
			return 0;
		}
		
		return bot.getCountBack();
	}

	@Override
	public int getCaseIdNeighbor(int indexBot, Action action) {
		Position position = this.getSafePosition(indexBot);

		if (null == position) {
			return -1;
		}

		int indexCurrentCase = this.labyrinth.getIndexByPosition(position);
		
		return this.labyrinth.getIndexNeighbor(indexCurrentCase, action);
	}
}
