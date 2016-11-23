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
		int sizeCell = ((int) canvas1.getWidth() / this.laby.getWidth() - 20);
		int x = 0;
		int y = 0;

		gc1.strokeLine(0, 0, sizeCell * this.laby.getWidth(), 0);
		gc2.strokeLine(0, 0, sizeCell * this.laby.getWidth(), 0);
		gc1.strokeLine(0, 0, 0, sizeCell * this.laby.getHeight());
		gc2.strokeLine(0, 0, 0, sizeCell * this.laby.getHeight());

		for (int i = 0; i < this.laby.getSize(); i++) {
			Case cell = this.laby.getCase(i);
			x += sizeCell;

			if (!cell.isRightIsOpen()) {
				gc1.strokeLine(x, y, x, y + sizeCell);
				gc2.strokeLine(x, y, x, y + sizeCell);
			}
			if (!cell.isDownIsOpen()) {
				gc1.strokeLine(x - sizeCell, y + sizeCell, x, y + sizeCell);
				gc2.strokeLine(x - sizeCell, y + sizeCell, x, y + sizeCell);
			}
			if (x == sizeCell * this.laby.getWidth()) {
				x = 0;
				y += sizeCell;
			}
		}
	}

	public void drawBots() {
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		Position bot1Position = readService.getBots().get(0).getPosition();
		Position bot2Position = readService.getBots().get(1).getPosition();
		int sizeCell = ((int) canvas1.getWidth() / this.laby.getWidth() - 20);
		int bot1X = bot1Position.getX() * sizeCell + sizeCell / 2;
		int bot1Y = bot1Position.getY() * sizeCell + sizeCell / 2;
		int bot2X = bot2Position.getX() * sizeCell + sizeCell / 2;
		int bot2Y = bot2Position.getY() * sizeCell + sizeCell / 2;
		gc1.fillOval(bot1X, bot1Y, 10, 10);
		GameLogs.getInstance().addLog("Bot 1 moving");
		gc2.fillOval(bot2X, bot2Y, 10, 10);
		GameLogs.getInstance().addLog("Bot 2 moving");
	}
}
