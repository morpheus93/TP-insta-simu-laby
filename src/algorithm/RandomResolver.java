package algorithm;

import java.util.ArrayList;
import java.util.Random;

import specifications.ResolverService;
import tools.Action;

public class RandomResolver extends Resolver {

	private Random random;
	
	@Override
	public void init() {
		this.random = new Random();
	}

	@Override
	public Action step(int currentCaseId) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
