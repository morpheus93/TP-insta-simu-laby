package userInterface;

import alpha.Main;
import data.Case;
import data.Labyrinth;
import data.Position;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import specifications.EngineService;
import specifications.ReadService;
import specifications.RequireEngineService;
import specifications.RequireReadService;
import tools.Action;
import tools.GameLogs;

import java.net.URL;
import java.util.ResourceBundle;

public class GameInterface implements Initializable, ControlledScreen, RequireReadService, RequireEngineService {

	@FXML
	public Canvas canvas1;

	@FXML
	public Canvas canvas2;

	@FXML
	public ListView<String> logContainer;

	@FXML
	public Button quit_btn;

	@FXML
	public Label label_down;

	@FXML
	public Label label_up;

	@FXML
	public Label label_right;

	@FXML
	public Label label_left;

	@FXML
	public Label label_back;

	@FXML
	public Label label_lap;

	private ObservableList<String> logs = FXCollections.observableArrayList();

	private ReadService readService;

	private EngineService engineService;

	private ScreensController screensController;

	private Position latestPositionBot1 = new Position(0, 0);

	private Position latestPositionBot2 = new Position(0, 0);

	private int sizeCell = 0;

	private Labyrinth laby;


	private Boolean engineIsFinish = false;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	@Override
	public void init() {
		this.drawLabyrinth();
	}

	@Override
	public void setScreenParent(ScreensController screenParent) {
		screensController = screenParent;
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
	 *
	 */
	public void updateStats() {
		int upBot1 = this.readService.getCountMoveForBot(0, Action.UP);
		int upBot2 = this.readService.getCountMoveForBot(1, Action.UP);

		int downBot1 = this.readService.getCountMoveForBot(0, Action.DOWN);
		int downBot2 = this.readService.getCountMoveForBot(1, Action.DOWN);

		int leftBot1 = this.readService.getCountMoveForBot(0, Action.LEFT);
		int leftBot2 = this.readService.getCountMoveForBot(1, Action.LEFT);

		int rightBot1 = this.readService.getCountMoveForBot(0, Action.RIGHT);
		int rightBot2 = this.readService.getCountMoveForBot(1, Action.RIGHT);

		int backBot1 = this.readService.getCountMoveForBot(0, Action.BACK);
		int backBot2 = this.readService.getCountMoveForBot(1, Action.BACK);

		int countLaps = this.readService.getStepCount();

		this.label_up.setText("UP Action Bot 1 : " + upBot1 + " Bot 2 : " + upBot2);
		this.label_down.setText("DOWN Action Bot 1 : " + downBot1 + " Bot 2 : " + downBot2);
		this.label_left.setText("LEFT Action Bot 1 : " + leftBot1 + " Bot 2 : " + leftBot2);
		this.label_right.setText("RIGHT Action Bot 1 : " + rightBot1 + " Bot 2 : " + rightBot2);
		this.label_back.setText("BACK Action Bot 1 : " + backBot1 + " Bot 2 : " + backBot2);
		this.label_lap.setText("Lap N° : " + countLaps);
	}

	/**
	 * Draw Labyrinth
	 */
	private void drawLabyrinth() {

		GameLogs.getInstance().addLog("Drawing labyrinth");
		this.laby = this.readService.getLabyrinth();
		GraphicsContext gc1 = canvas1.getGraphicsContext2D();
		GraphicsContext gc2 = canvas2.getGraphicsContext2D();
		int x = 0;
		int y = 0;
		this.sizeCell = ((int) canvas1.getWidth() / laby.getWidth() - 20);

		gc1.strokeLine(0, 0, this.sizeCell * laby.getWidth(), 0);
		gc2.strokeLine(0, 0, this.sizeCell * laby.getWidth(), 0);
		gc1.strokeLine(0, 0, 0, this.sizeCell * laby.getHeight());
		gc2.strokeLine(0, 0, 0, this.sizeCell * laby.getHeight());

		for (int i = 0; i < laby.getSize(); i++) {
			Case cell = laby.getCase(i);
			x += this.sizeCell;

			if (!cell.isRightIsOpen()) {
				gc1.strokeLine(x, y, x, y + this.sizeCell);
				gc2.strokeLine(x, y, x, y + this.sizeCell);
			}
			if (!cell.isDownIsOpen()) {
				gc1.strokeLine(x - this.sizeCell, y + this.sizeCell, x, y + this.sizeCell);
				gc2.strokeLine(x - this.sizeCell, y + this.sizeCell, x, y + this.sizeCell);
			}
			if (x == this.sizeCell * laby.getWidth()) {
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

		int bot1X = bot1Position.getX() * this.sizeCell + this.sizeCell / 2 - 5;
		int bot1Y = bot1Position.getY() * this.sizeCell + this.sizeCell / 2 - 5;
		int bot2X = bot2Position.getX() * this.sizeCell + this.sizeCell / 2 - 5;
		int bot2Y = bot2Position.getY() * this.sizeCell + this.sizeCell / 2 - 5;

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

		if (this.engineService.isFinish()) {

			if (bot1Position.getX() == this.laby.getWidth() - 1 && bot1Position.getY() == this.laby.getHeight() - 1) {
				GameLogs.getInstance().addLog("Bot #1 won");
			}

			if (bot2Position.getX() == this.laby.getWidth() - 1 && bot2Position.getY() == this.laby.getHeight() - 1) {
				GameLogs.getInstance().addLog("Bot #2 won");
			}
			this.engineIsFinish = true;
			GameLogs.getInstance().addLog("Replay ?");
		}
	}

	@Override
	public void bindEngineService(EngineService service) {
		this.engineService = service;
	}

	public void quitGame(ActionEvent actionEvent) {
		System.exit(0);
	}

	public void replayGame(ActionEvent actionEvent) {
		this.screensController.setScreen("menu");
		this.engineService.reset();
	}

	public boolean isFinish(){
		return this.engineIsFinish;
	}
}
