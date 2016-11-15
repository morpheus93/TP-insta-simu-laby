package data;

public class Bot {
	private static int countBot = 0;
	
	private Position position;
	private int nbStep;
	private String name;
	
	public Bot(final int x, final int y) {
		this.nbStep = 0;
		this.position = new Position(x, y);
		this.name = "Bot #" + Integer.toString(Bot.countBot);
		
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
}
