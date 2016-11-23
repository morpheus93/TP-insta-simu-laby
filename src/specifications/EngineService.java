package specifications;

import tools.Resolver;

public interface EngineService {
	public void init();

	public void start();

	public void stop();
	
	public void reset();
	
	public void addResolver(Resolver resolver);
	
	public boolean isFinish();
}
