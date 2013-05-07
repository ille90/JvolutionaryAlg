package jea.alg;

import java.util.Random;
import java.util.Vector;

import jea.alg.selection.FitnessSelectionType;

public class Generation {

	private int permutationCount;
	private Vector<Permutation> permutations;

	public Generation(int permutationCount) {
		this.permutationCount = permutationCount;
		permutations = new Vector<Permutation>();
	}

	public int getPermutationCount() {
		return permutations.size();
	}
	
	public int getMaxPermutationCount() {
		return permutationCount;
	}

	public void addPermutation(Permutation permutation) {
		if (permutations.size() < permutationCount)
			permutations.add(permutation);
	}

	public Permutation getPermutation(int position) {
		return permutations.get(position);
	}

	public Vector<Permutation> getPermutations() {
		return permutations;
	}

	public Permutation getRandomPermutation() {
		return permutations.get((int) (new Random().nextDouble() * permutationCount));
	}

	/**
	 * fillInitialGeneration füllt Generation mit zufälligen Permuationen auf
	 * (siehe Schritt 3)
	 * 
	 */
	public void fillInitialGeneration() {
		for (int i = permutations.size(); i < permutationCount; i++) {
			permutations.add(Permutation.getRandomPermutation());
		}
	}

	/**
	 * calcFitness berechnet die Fitness aller Permutationen
	 * 
	 */
	public double calcFitness() {
		double fitness = 0.0;
		for (Permutation permutation : permutations) {
			permutation.calcFitness();
			fitness = fitness + permutation.getFitness();
		}

		return fitness;
	}

	/**
	 * getNextGeneration gibt neue Generation, mit den besten Permutationen aus
	 * alter Generation und Kindern, zurück (siehe Schritt 15)
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
			double worstFitness = Double.MAX_VALUE;
			int worstPermutation = 0;

			for (int i = 0; i < newPermutations.size(); i++) {
				if (EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest) {
					if (worstFitness > newPermutations.get(i).getFitness()) {
						worstFitness = newPermutations.get(i).getFitness();
						worstPermutation = i;
					}
				} else {
					if (worstFitness < newPermutations.get(i).getFitness()) {
						worstFitness = newPermutations.get(i).getFitness();
						worstPermutation = i;
					}
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
		double bestFitness = Double.MAX_VALUE;
		if (EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest)
			bestFitness = -1 * Double.MAX_VALUE;
		for (Permutation permutation : permutations) {
			if (EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest) {
				if (bestFitness < permutation.getFitness()) {
					bestPermutation = permutation;
					bestFitness = permutation.getFitness();
				}
			} else {
				if (bestFitness > permutation.getFitness()) {
					bestPermutation = permutation;
					bestFitness = permutation.getFitness();
				}
			}
		}
		return bestPermutation;
	}

	public double getHighestFitness() {
		double heighestFitness = -1 * Double.MAX_VALUE;
		for (Permutation permutation : permutations) {
			if (heighestFitness < permutation.getFitness())
				heighestFitness = permutation.getFitness();
		}
		return heighestFitness;
	}
	
	public double getLowestFitness() {
		double lowestFitness = Double.MAX_VALUE;
		for (Permutation permutation : permutations) {
			if (lowestFitness > permutation.getFitness())
				lowestFitness = permutation.getFitness();
		}
		return lowestFitness;
	}

	public int getBestPermutationByIndex() {
		int bestPermutation = -1;
		double bestFitness = Double.MAX_VALUE;
		if (EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest)
			bestFitness = -1 * Double.MAX_VALUE;
		for (int i = 0; i < permutations.size(); i++) {
			if (bestFitness < permutations.get(i).getFitness()) {
				bestPermutation = i;
				bestFitness = permutations.get(i).getFitness();
			}
			if (EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest) {
				if (bestFitness < permutations.get(i).getFitness()) {
					bestPermutation = i;
					bestFitness = permutations.get(i).getFitness();
				}
			} else {
				if (bestFitness > permutations.get(i).getFitness()) {
					bestPermutation = i;
					bestFitness = permutations.get(i).getFitness();
				}
			}
		}
		return bestPermutation;
	}

	public void setPermutations(Vector<Permutation> newPermutations) {
		this.permutations = newPermutations;
	}
	
	public void addGeneration(Generation generation) {
		this.permutations.addAll(generation.permutations);
	}

	public void setPermutation(Permutation newPermutation, int index) {
		this.permutations.set(index, newPermutation);
	}

	public void printGeneration() {
		int geneCount = EvolutionSingleton.getInstance().getGeneCount();
		for (Permutation permutation : permutations) {
			System.out.print("\t[");
			for (int i = 0; i < geneCount; i++) {
				System.out.print(permutation.getGene(i).getValue());
				if (i != geneCount - 1)
					System.out.print(", ");
			}
			System.out.println("] " + permutation.getFitness());
		}
	}

}
