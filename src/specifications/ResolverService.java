package specifications;

import tools.Action;

public interface ResolverService {
	public String getName();
	
	public void init();
	
	public Action step(int currentCaseId);
}
