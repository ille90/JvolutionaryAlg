package jea;

import java.util.Vector;

public class Population {
	
	int currentPopulation;
	int maxGeneration;
	Vector<Generation> generations;
	int children;
	int limit;
	int geneCount;
	int[] genes;
	
	public Population() {
		// TODO Auto-generated constructor stub
	}
	
	void start() {
		currentPopulation = 0;
	}
	
	void initFirstGeneration() {
	}
}
