package data;

import java.util.ArrayList;

import tools.Action;

public class Bot {
	private static int countBot = 0;

	private Position position;
	private int nbStep;
	private String name;
	private ArrayList<Action> histories;

	public Bot(final int x, final int y) {
		this.nbStep = 0;
		this.position = new Position(x, y);
		this.name = "Bot #" + Integer.toString(Bot.countBot);
		this.histories = new ArrayList<Action>();

		Bot.countBot++;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public int getNbStep() {
		return nbStep;
	}

	public void setNbStep(final int nbStep) {
		this.nbStep = nbStep;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ArrayList<Action> getHistories() {
		return this.histories;
	}

	private void removeAction() {
		int index = this.histories.size() - 1;

		if (index < 0) {
			return;
		}

		this.histories.remove(index);
	}

	public void move(Action action) {
		if (Action.BACK != action) {
			this.histories.add(action);
		}

		switch (action) {
		case UP:
			position.setY(position.getY() - 1);
			break;

		case LEFT:
			position.setX(position.getX() + 1);
			break;

		case DOWN:
			position.setY(position.getY() + 1);
			break;

		case RIGHT:
			position.setX(position.getX() - 1);
			break;

		case BACK:
			this.back();
			break;
		}
	}

	private void back() {

		int indexLastAction = this.getHistories().size() - 1;

		// no action, so no back
		if (indexLastAction < 0) {
			return;
		}

		Action lastAction = this.getHistories().get(indexLastAction);

		switch (lastAction) {
		case UP:
			position.setY(position.getY() - 1);
			break;

		case LEFT:
			position.setX(position.getX() - 1);
			break;

		case DOWN:
			position.setY(position.getY() + 1);
			break;

		case RIGHT:
			position.setX(position.getX() + 1);
			break;
			
		default:
			// TODO throw exception (Should be never reached)
		}

		this.removeAction();
	}
}
