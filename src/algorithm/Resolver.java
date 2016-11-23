package algorithm;

import java.util.ArrayList;

import specifications.ResolverService;

public abstract class Resolver implements ResolverService {
	ArrayList<Integer> caseVisited;
	private String name;
	
	private static int countResolver = 0;
	
	public Resolver() {
		this.caseVisited = new ArrayList<Integer>();
		Resolver.countResolver++;

		this.name = "Resolver #" + Integer.toString(Resolver.countResolver);
	}

	protected boolean isVisitedCase(int caseId) {
		for (Integer id : caseVisited) {
			if (id == caseId) {
				return true;
			}
		}
		
		return false;
	}
	
	public String getName() {
		return this.name;
	}
}
