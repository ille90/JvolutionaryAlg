package jea.alg.selection;

import java.util.Vector;

import jea.alg.EvolutionSingleton;
import jea.alg.Generation;
import jea.alg.Permutation;

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
	public static Generation determSelection(Generation generation){
		
		//neue Generation mit gleicher Anzahl an Permutationen
		//wie ursprüngliche Generation
		Generation newGeneration = new Generation(generation.getMaxPermutationCount());
		
		//Container für selektierte Permutationen
		Vector<Permutation> newPermutations = new Vector<Permutation>();
		
		//ursprüngliche Perms werden dem Container übergeben für Selektion
		newPermutations.addAll(generation.getPermutations());
		
		//Selektion
		//solange Anzahl an maximalen Permutationen/Generation nicht erreicht ist:
		while (generation.getMaxPermutationCount() < newPermutations.size()) {
			
			double worstFitness = Double.MAX_VALUE;
			if(EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Lowest)
				worstFitness = -1 * Double.MAX_VALUE;
			int worstPermutation = 0;
			
			//alle Permutationen miteinander vergleichen
			for(int i = 0; i < newPermutations.size(); i++) {
				
				if(EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest) {
					if(worstFitness > newPermutations.get(i).getFitness()) {
						worstFitness = newPermutations.get(i).getFitness();
						worstPermutation = i;
					}
				} else {
					if(worstFitness < newPermutations.get(i).getFitness()) {
						worstFitness = newPermutations.get(i).getFitness();
						worstPermutation = i;
					}
					
				}
			}
			
			//Permutation mit schlechtester Fitness entfernen
			newPermutations.remove(worstPermutation);
		}
		
		//Permutaionen der neuen Generation zuweisen und zurückgeben 
		newGeneration.setPermutations(newPermutations);
		return newGeneration;
	}	
}
