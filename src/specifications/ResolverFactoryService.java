package specifications;

import data.Labyrinth;
import engine.Engine;
import tools.Resolver;

public interface ResolverFactoryService {
	public ResolverService getResolver(Resolver resolver, Labyrinth labyrinth, Engine engine);
}
