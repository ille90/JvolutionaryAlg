package jea;

import jea.GenPool;

public class Permutation<T extends GenPool> {
	
	private T genPool;
	private int fitness;
	
	public void calcFitness() {
		genPool.calcFitness(this);
	}
	
	public int getFitness() {
		return fitness;
	}
}
