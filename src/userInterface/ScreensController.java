package userInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import specifications.EngineService;
import specifications.ReadService;
import specifications.RequireEngineService;
import specifications.RequireReadService;
import tools.GameLogs;

/**
 * Class ScreensController
 */

public class ScreensController extends StackPane implements RequireReadService, RequireEngineService {

	private HashMap<String, String> screensURL = new HashMap<>();
	private HashMap<String, Node> screens = new HashMap<>();
	private HashMap<String, ControlledScreen> screensControllers = new HashMap<>();
	private String currentView = null;

	private EngineService engineService;

	private ReadService readService;

	private final static String SCREEN_1_FILE = "/resources/menu.fxml";
	private final static String SCREEN_2_FILE = "/resources/game.fxml";

	public final static String SCREEN_1_ID = "menu";
	public final static String SCREEN_2_ID = "game";

	/**
	 * Constructor
	 */
	public ScreensController() {
		super();
		screensURL.put(SCREEN_1_ID, SCREEN_1_FILE);
		screensURL.put(SCREEN_2_ID, SCREEN_2_FILE);
	}

	/**
	 * Loads all fxml files, add the screen to the screens collection and
	 * finally injects the screenPane to the controller.
	 */
	public void loadViews() {
		for (Map.Entry<String, String> viewURL : screensURL.entrySet()) {
			String name = viewURL.getKey();
			String url = viewURL.getValue();
			if (screens.containsKey(name)) continue;
			try {
				FXMLLoader myLoader = new FXMLLoader(this.getClass().getResource(url));
				Parent loadScreen = myLoader.load();
				ControlledScreen myScreenController = myLoader.getController();
				screensControllers.put(name, myScreenController);
				myScreenController.setScreenParent(this);
				screens.put(name, loadScreen);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	/**
	 * Reload view
	 * @param viewName String
	 */
	public void reloadView(String viewName) {
		this.unloadScreen(viewName);
		this.loadViews();
		this.bindReadService(this.readService);
		this.bindEngineService(this.engineService);
	}

	/**
	 * This method tries to displayed the screen with a predefined name.
	 * First it makes sure the screen has been already loaded.  Then if there is more than
	 * one screen the new screen is been added second, and then the current screen is removed.
	 * If there isn't any screen being displayed, the new screen is just added to the root.
	 *
	 * @param name String
	 * @return Boolean
	 */
	public boolean setScreen(final String name) {
		if (screens.get(name) != null) {
			final DoubleProperty opacity = opacityProperty();
			this.currentView = name;
			ControlledScreen controller = screensControllers.get(name);
			controller.init();
			if (!getChildren().isEmpty()) {
				Timeline fade = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
						new KeyFrame(new Duration(500), t -> {
							getChildren().remove(0);
							getChildren().add(0, screens.get(name));
							Timeline fadeIn = new Timeline(
									new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
									new KeyFrame(new Duration(600), new KeyValue(opacity, 1.0)));
							fadeIn.play();
						}, new KeyValue(opacity, 0.0)));
				fade.play();
			} else {
				setOpacity(0.0);
				getChildren().add(screens.get(name));
				Timeline fadeIn = new Timeline(
						new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
						new KeyFrame(new Duration(1500), new KeyValue(opacity, 1.0)));
				fadeIn.play();
			}
			GameLogs.getInstance().addLog("Displaying " + name);

			return true;
		} else {
			System.out.println("Screen " + name + " hasn't been loaded!!! \n");
			return false;
		}
	}

	/**
	 * This method will remove the screen with the given name from the collection of screens
	 */
	public boolean unloadScreen(String name) {
		if (screens.remove(name) == null) {
			System.out.println("Screen didn't exist");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void bindReadService(ReadService service) {
		this.readService = service;
		if (screensControllers.get(SCREEN_2_ID) != null) {
			GameInterface controller = (GameInterface) screensControllers.get(SCREEN_2_ID);
			controller.bindReadService(service);
		}
	}

	@Override
	public void bindEngineService(EngineService service) {
		this.engineService = service;
		if (screensControllers.get(SCREEN_1_ID) != null) {
			MenuInterface controller = (MenuInterface) screensControllers.get(SCREEN_1_ID);
			controller.bindEngineService(service);
		}

		if (screensControllers.get(SCREEN_2_ID) != null) {
			GameInterface controller = (GameInterface) screensControllers.get(SCREEN_2_ID);
			controller.bindEngineService(service);
		}
	}

	/**
	 * Return current interface name
	 *
	 * @return String
	 */
	public String getCurrentView() {
		return currentView;
	}

	/**
	 * Return current view controller
	 *
	 * @return ControlledScreen
	 */
	public ControlledScreen getCurrentViewController() {
		return screensControllers.get(currentView);
	}
}
