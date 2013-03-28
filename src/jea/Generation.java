package jea;

import java.util.Vector;

public class Generation {

	private int permutationCount;
	private Vector<Permutation> permutations;

	public Generation(int permutationCount) {
		this.permutationCount = permutationCount;
		permutations = new Vector<Permutation>();
	}
	
	public int getPermutationCount() {
		return permutationCount;
	}

	public void addPermutation(Permutation permutation) {
		if(permutations.size() < permutationCount)
			permutations.add(permutation);
	}
	
	public Permutation getPermutation(int position) {
		return permutations.get(position);
	}
	
	public Permutation getRandomPermutation() {
		return permutations.get((int) (Math.random() * permutationCount));
	}

	/**
	 * fillInitialGeneration füllt Generation mit zufälligen Permuationen auf
	 * (siehe Schritt 3)
	 * 
	 */
	public void fillInitialGeneration() {
		for (int i = permutations.size(); i < permutationCount; i++) {
			permutations.add(Permutation.getRandomPermutation(GenePoolSingleton.getInstance().getGenePool().geneCount()));
		}
	}
	
	/**
	 * calcFitness berechnet die Fitness aller Permutationen 
	 * 
	 */
	public void calcFitness() {
		for (Permutation permutation : permutations) {
			permutation.calcFitness();
		}
	}

	/**
	 * getNextGeneration gibt neue Generation,
	 * mit den besten Permutationen aus alter Generation und Kindern, zurück
	 * (siehe Schritt 15)
	 * 
	 * @param children
	 * @return
	 */
	public Generation getNextGeneration(Generation children) {
		Generation newGeneration = new Generation(permutationCount);
		Vector<Permutation> newPermutations = new Vector<Permutation>();
		newPermutations.addAll(permutations);
		newPermutations.addAll(children.permutations);
		while (permutationCount < newPermutations.size()) {
			int worstFitness = Integer.MAX_VALUE;
			int worstPermutation = 0;
			for(int i = 0; i < newPermutations.size(); i++) {
				if(worstFitness > newPermutations.get(i).getFitness()) {
					worstFitness = newPermutations.get(i).getFitness();
					worstPermutation = i;
				}
			}
			newPermutations.remove(worstPermutation);
		}
		newGeneration.permutations = newPermutations;
		return newGeneration;
	}

	/**
	 * getBestPermutation gibt die Permutation mit der höchsten Fitness zurück
	 * 
	 * @return
	 */
	public Permutation getBestPermutation() {
		Permutation bestPermutation = null;
		int bestFitness = Integer.MIN_VALUE;
		for (Permutation permutation : permutations) {
			if (bestFitness < permutation.getFitness()) {
				bestPermutation = permutation;
				bestFitness = permutation.getFitness();
			}
		}
		return bestPermutation;
	}
}
