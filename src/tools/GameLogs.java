package tools;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public final class GameLogs {

	private static volatile GameLogs instance = null;

	private ObservableList<String> logs = FXCollections.observableArrayList();

	/**
	 * Constructor.
	 */
	private GameLogs() {
		super();
	}

	/**
	 * Method to get the singleton instance
	 *
	 * @return GameLogs
	 */
	public static GameLogs getInstance() {
		if (GameLogs.instance == null) {
			synchronized (GameLogs.class) {
				if (GameLogs.instance == null) {
					GameLogs.instance = new GameLogs();
				}
			}
		}
		return GameLogs.instance;
	}

	/**
	 * Return logs list
	 *
	 * @return ObservableList<String>
	 */
	public ObservableList<String> getLogs() {
		return logs;
	}

	/**
	 * Add log to stack
	 *
	 * @param log String
	 */
	public void addLog(String log) {
		logs.add(0, log);
	}
}
