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
		Position position = this.getSafePosition(indexBot);

		if (null == position) {
			return; // TODO throw Exception
		}

		switch (action) {
		case UP:
			position.setY(position.getY() + 1);
			break;

		case LEFT:
			position.setX(position.getX() + 1);
			break;

		case DOWN:
			position.setY(position.getY() - 1);
			break;

		case RIGHT:
			position.setX(position.getX() - 1);
			break;
		}

		this.bots.get(indexBot).setPosition(position);
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

		return this.labyrinth.getCaseId(position);
	}

	private Position getSafePosition(int indexBot) {
		if (indexBot < 0 || this.bots.size() <= indexBot) {
			return null;
		}

		return this.bots.get(indexBot).getPosition();
	}
}
