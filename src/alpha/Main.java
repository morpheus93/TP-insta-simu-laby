package alpha;

import factory.ResolverFactory;
import specifications.ResolverFactoryService;
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
import javafx.animation.AnimationTimer;
import javafx.scene.Group;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private AnimationTimer guiTimer;

	private ScreensController mainContainer = new ScreensController();
	private EngineService engine;

	/**
	 * Start method
	 *
	 * @param primaryStage Stage
	 */
	@Override
	public void start(Stage primaryStage) {
		DataService data = new Data();
		this.engine = new Engine();
		LabyrinthFactoryService labyrinthFactory = new LabyrinthFactory();
		ResolverFactoryService resolverFactory = new ResolverFactory();
		((Engine) engine).bindDataService(data);
		((Engine) engine).bindLabyrinthFactoryService(labyrinthFactory);
		((Engine) engine).bindResolverFactoryService(resolverFactory);

		data.init();
		engine.init();

		mainContainer.loadViews();
		mainContainer.setScreen(ScreensController.SCREEN_1_ID);
		mainContainer.bindReadService(data);
		mainContainer.bindEngineService(engine);
		mainContainer.bindMainInstance(this);

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
	}

	public void stopAnimationTimer(){
		this.guiTimer.stop();
	}

	public void startAnimationTimer() {
		this.guiTimer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				ControlledScreen controller = mainContainer.getCurrentViewController();
				switch (mainContainer.getCurrentView()) {
					case ScreensController.SCREEN_1_ID:
						break;
					case ScreensController.SCREEN_2_ID:
						((GameInterface) controller).drawBots();
						((GameInterface) controller).updateLogs();
						((GameInterface) controller).updateStats();
						if (((GameInterface) controller).isFinish()) {
							stopAnimationTimer();
						}
						break;
					default:
						GameLogs.getInstance().addLog("Unknown view");
						break;
				}
			}
		};
		this.guiTimer.start();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
	}

}
