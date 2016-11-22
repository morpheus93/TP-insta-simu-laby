package alpha;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import tools.GameLogs;
import tools.HardCodedParameters;

import specifications.DataService;
import specifications.EngineService;
import specifications.LabyrinthFactoryService;

import data.Data;
import engine.Engine;
import factory.LabyrinthFactory;
import userInterface.*;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

	private static DataService data = new Data();

	public static void main(String[] args) {

		EngineService engine = new Engine();
		LabyrinthFactoryService labyrinthFactory = new LabyrinthFactory();

		((Engine) engine).bindDataService(data);
		((Engine) engine).bindLabyrinthFactoryService(labyrinthFactory);

		data.init();
		engine.init();
		launch(args);
	}

	/**
	 * Start method
	 *
	 * @param primaryStage Stage
	 */
	@Override
	public void start(Stage primaryStage) {

		ScreensController mainContainer = new ScreensController();
		mainContainer.loadViews();
		mainContainer.setScreen(ScreensController.SCREEN_1_ID);
		mainContainer.bindReadService(data);
		GameLogs.getInstance().addLog("Displaying menu");
		Group root = new Group();
		root.getChildren().addAll(mainContainer);
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Simulateur de Labyrinthe");
		primaryStage.setMinWidth(HardCodedParameters.defaultWidth);
		primaryStage.setMinHeight(HardCodedParameters.defaultHeight);
		primaryStage.setMaxHeight(HardCodedParameters.defaultHeight);
		primaryStage.setMaxWidth(HardCodedParameters.defaultWidth);
		primaryStage.show();
		GameLogs.getInstance().addLog("Displaying window");

		AnimationTimer guiTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				ControlledScreen controller = mainContainer.getCurrentViewController();
				switch (mainContainer.getCurrentView()) {
					case ScreensController.SCREEN_1_ID:
						break;
					case ScreensController.SCREEN_2_ID:
						((GameInterface) controller).updateLogs();
						break;
					case ScreensController.SCREEN_3_ID:
						break;
					default:
						GameLogs.getInstance().addLog("Unknown view");
						break;
				}
			}
		};
		guiTimer.start();
	}
}
