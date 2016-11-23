package userInterface;

import data.Case;
import data.Labyrinth;
import data.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import specifications.ReadService;
import specifications.RequireReadService;
import tools.GameLogs;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInterface implements Initializable, ControlledScreen, RequireReadService {

	@FXML
	public Canvas canvas1;

	@FXML
	public LineChart statsContainer;

	@FXML
	public Canvas canvas2;

	@FXML
	public ListView<String> logContainer;

	private ObservableList<String> logs = FXCollections.observableArrayList();

	private ReadService readService;

	private Labyrinth laby;

	private ScreensController screensController;

	private Position latestPositionBot1 = new Position(0, 0);

	private Position latestPositionBot2 = new Position(0, 0);

	private int sizeCell = 0;


	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	@Override
	public void init() {
		this.drawLabyrinth();
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		// screensController = screenParent;
	}

	@Override
	public void bindReadService(ReadService service) {
		this.readService = service;
	}

	/**
	 * Get logs from Logger instance and display it
	 */
	public void updateLogs() {
		logContainer.setItems(GameLogs.getInstance().getLogs());
		logContainer.scrollTo(logs.size());
	}

	/**
	 * Draw Labyrinth
	 */
	private void drawLabyrinth() {
		GameLogs.getInstance().addLog("Drawing labyrinth");
		this.laby = readService.getLabyrinth();
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		int x = 0;
		int y = 0;
		this.sizeCell = ((int) canvas1.getWidth() / this.laby.getWidth() - 20);

		gc1.strokeLine(0, 0, this.sizeCell * this.laby.getWidth(), 0);
		gc2.strokeLine(0, 0, this.sizeCell * this.laby.getWidth(), 0);
		gc1.strokeLine(0, 0, 0, this.sizeCell * this.laby.getHeight());
		gc2.strokeLine(0, 0, 0, this.sizeCell * this.laby.getHeight());

		for (int i = 0; i < this.laby.getSize(); i++) {
			Case cell = this.laby.getCase(i);
			x += this.sizeCell;

			if (!cell.isRightIsOpen()) {
				gc1.strokeLine(x, y, x, y + this.sizeCell);
				gc2.strokeLine(x, y, x, y + this.sizeCell);
			}
			if (!cell.isDownIsOpen()) {
				gc1.strokeLine(x - this.sizeCell, y + this.sizeCell, x, y + this.sizeCell);
				gc2.strokeLine(x - this.sizeCell, y + this.sizeCell, x, y + this.sizeCell);
			}
			if (x == this.sizeCell * this.laby.getWidth()) {
				x = 0;
				y += this.sizeCell;
			}
		}
	}

	public void drawBots() {
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		Position bot1Position = readService.getBots().get(0).getPosition();
		Position bot2Position = readService.getBots().get(1).getPosition();

		int bot1X = bot1Position.getX() * this.sizeCell + this.sizeCell / 2;
		int bot1Y = bot1Position.getY() * this.sizeCell + this.sizeCell / 2;
		int bot2X = bot2Position.getX() * this.sizeCell + this.sizeCell / 2;
		int bot2Y = bot2Position.getY() * this.sizeCell + this.sizeCell / 2;

		gc1.setFill(Color.WHITE);
		gc1.fillOval(latestPositionBot1.getX(), latestPositionBot1.getY(), 10, 10);

		gc1.setFill(Color.BLACK);
		gc1.fillOval(bot1X, bot1Y, 10, 10);

		gc2.setFill(Color.WHITE);
		gc2.fillOval(latestPositionBot2.getX(), latestPositionBot2.getY(), 10, 10);

		gc2.setFill(Color.BLACK);
		gc2.fillOval(bot2X, bot2Y, 10, 10);

		this.latestPositionBot1 = new Position(bot1X, bot1Y);
		this.latestPositionBot2 = new Position(bot2X, bot2Y);
	}
}
