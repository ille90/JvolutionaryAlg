package jea.alg.selection;

import jea.alg.EvolutionSingleton;
import jea.alg.Generation;

/**
 * Klasse, welche Methoden für Umweltselektion enthält
 * @author Franziska Staake
 *
 */
public class EnvironmentalSelection {
	
	/**
	 * Methode für deterministische Selektion
	 * @param generation	Generation, deren Permutationen selektiert werden sollen
	 * @return Generation 	mit selektierten Individuen (Permutationen)
	 */
	public static void determSelection(Generation generation){

		double stdWorstFitness = Double.MAX_VALUE;
		if(EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Lowest)
			stdWorstFitness = -1 * Double.MAX_VALUE;
		double worstFitness;
		int worstPermutation;
		while (generation.getMaxPermutationCount() < generation.getPermutationCount()) {
			worstFitness = stdWorstFitness;
			worstPermutation = -1;
			for(int i = 0; i < generation.getPermutationCount(); i++) {
				
				if(EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest) {
					if(worstFitness > generation.getPermutation(i).getFitness()) {
						worstFitness = generation.getPermutation(i).getFitness();
						worstPermutation = i;
					}
				} else {
					if(worstFitness < generation.getPermutation(i).getFitness()) {
						worstFitness = generation.getPermutation(i).getFitness();
						worstPermutation = i;
					}
					
				}
			}
			
			//Permutation mit schlechtester Fitness entfernen
			generation.getPermutations().remove(worstPermutation);
		}
	}	
}
