package engine;

import tools.Action;
import tools.GameLogs;
import tools.HardCodedParameters;
import tools.Resolver;
import specifications.DataService;
import specifications.EngineService;
import specifications.InfoService;
import specifications.LabyrinthFactoryService;
import specifications.RequireDataService;
import specifications.RequireLabyrinthFactoryService;
import specifications.RequireResolverFactoryService;
import specifications.ResolverFactoryService;
import specifications.ResolverService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import data.Bot;
import data.Position;

public class Engine implements EngineService, RequireDataService, RequireResolverFactoryService, RequireLabyrinthFactoryService, InfoService {
	private Timer engineClock;
	private int currentIndexBot;
	private DataService data;
	private ResolverFactoryService resolverFactory;
	private LabyrinthFactoryService labyrinthFactory;
	private ArrayList<ResolverService> resolvers;
	private boolean isFinish;

	public Engine() {
		this.currentIndexBot = 0;
		this.isFinish = false;
		this.resolvers = new ArrayList<ResolverService>();
	}

	@Override
	public void init() {
		engineClock = new Timer();
		this.data.setLabyrinth(this.labyrinthFactory.getLabyrinth());
	}

	@Override
	public void reset() {
		this.isFinish = false;
		this.resolvers.removeAll(this.resolvers);
		this.data.reset();
		this.data.setLabyrinth(this.labyrinthFactory.getLabyrinth());		
	}

	@Override
	public void start() {
		engineClock.schedule(new TimerTask() {
			public void run() {
				data.addStep();
				currentIndexBot = 0;

				for (ResolverService resolver : resolvers) {
					int currentCaseId = data.getCaseId(currentIndexBot);

					Action action = resolver.step(currentCaseId);
					
					log(action);

					if (data.canMove(currentIndexBot, action)) {
						data.moveBot(currentIndexBot, action);
					}

					currentIndexBot++;
				}
				
				determineIfIsFinish();
			}
		}, 0, HardCodedParameters.enginePaceMillis);
	}
	
	private void log(Action action) {
		
		String str = "";
		
		switch(action) {
		case UP:
			str = "up";
			break;
			
		case DOWN:
			str = "down";
			break;
			
		case LEFT:
			str = "left";
			break;
			
		case RIGHT:
			str = "right";
			break;
			
		case BACK:
			str = "back";
			break;
		}

		GameLogs.getInstance().addLog(
			"Bot #" + Integer.toString(this.currentIndexBot) + " moving to " + str
		);
	}
	
	private void determineIfIsFinish() {
		
		int idCaseEnd = this.data.getLabyrinth().getCase(this.data.getLabyrinth().getSize() - 1).getId();
		
		for (int i = 0; i < resolvers.size(); i++) {
			Position position = this.data.getBots().get(i).getPosition();
			
			if (this.data.getLabyrinth().getCaseId(position) == idCaseEnd) {
				this.isFinish = true;
				this.stop();
				break;
			}
		}
	}

	@Override
	public void stop() {
		engineClock.cancel();
	}
	
	@Override
	public void bindDataService(DataService service) {
		this.data = service;
	}

	@Override
	public boolean canMove(Action action) {
		return this.data.canMove(this.currentIndexBot, action);
	}

	@Override
	public ArrayList<Action> getHistories() {
		return this.data.getHistories(this.currentIndexBot);
	}

	@Override
	public void bindResolverFactoryService(ResolverFactoryService service) {
		this.resolverFactory = service;
	}

	@Override
	public void bindLabyrinthFactoryService(LabyrinthFactoryService service) {
		this.labyrinthFactory = service;
	}

	@Override
	public void addResolver(Resolver resolver) {
		this.resolvers.add(
				this.resolverFactory.getResolver(resolver, this.data.getLabyrinth(), this)
		);
		Bot bot = new Bot(0, 0);
		data.addBot(bot);
	}
	
	public boolean isFinish() {
		return this.isFinish;
	}
}
