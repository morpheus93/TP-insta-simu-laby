package engine;

import tools.HardCodedParameters;
import specifications.EngineService;
import java.util.Timer;
import java.util.TimerTask;

public class Engine implements EngineService {
	private Timer engineClock;

	public Engine() {
	}

	@Override
	public void init() {
		engineClock = new Timer();
	}

	@Override
	public void start() {
		engineClock.schedule(new TimerTask() {
			public void run() {
				// core
			}
		}, 0, HardCodedParameters.enginePaceMillis);
	}

	@Override
	public void stop() {
		engineClock.cancel();
	}
}
