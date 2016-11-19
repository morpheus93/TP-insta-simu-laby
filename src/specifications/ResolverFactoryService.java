package specifications;

import data.Labyrinth;
import tools.Resolver;

public interface ResolverFactoryService {
	public ResolverService getResolver(Resolver resolver, Labyrinth labyrinth);
}
