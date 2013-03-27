package jea;

import java.util.Vector;

public class Generation<T extends GenPool> {

	int permutationCount;
	Vector<Permutation<T>> permutations;

	public Generation(int permutationCount) {
		this.permutationCount = permutationCount;
		permutations = new Vector<Permutation<T>>();
	}

	/**
	 * fillInitialGeneration füllt Generation mit zufälligen Permuationen auf
	 * (siehe Schritt 3)
	 * 
	 */
	public void fillInitialGeneration() {
		for (int i = permutations.size(); i < permutationCount; i++) {
			// TODO: Vector füllen
		}
	}
	
	/**
	 * calcFitness berechnet die Fitness aller Permutationen 
	 * 
	 */
	public void calcFitness() {
		for (Permutation<T> permutation : permutations) {
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
	public Generation<T> getNextGeneration(Generation<T> children) {
		Generation<T> newGeneration = new Generation<T>(permutationCount);
		Vector<Permutation<T>> newPermutations = new Vector<Permutation<T>>();
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
	public Permutation<T> getBestPermutation() {
		Permutation<T> bestPermutation = null;
		int bestFitness = Integer.MIN_VALUE;
		for (Permutation<T> permutation : permutations) {
			if (bestFitness < permutation.getFitness()) {
				bestPermutation = permutation;
				bestFitness = permutation.getFitness();
			}
		}
		return bestPermutation;
	}
}
