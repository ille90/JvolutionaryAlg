package jea;

import java.util.Vector;

/**
 * Klasse, welche Methoden f�r Umweltselektion enth�lt
 * @author Franziska Staake
 *
 */
public class environmentalSelection {
	
	/**
	 * Methode f�r deterministische Selektion
	 * @param generation	Generation, deren Permutationen selektiert werden sollen
	 * @return Generation 	mit selektierten Individuen (Permutationen)
	 */
	public static Generation determSelection(Generation generation){
		
		//neue Generation mit gleicher Anzahl an Permutationen
		//wie urspr�ngliche Generation
		Generation newGeneration = new Generation(generation.getPermutationCount());
		
		//Container f�r selektierte Permutationen
		Vector<Permutation> newPermutations = new Vector<Permutation>();
		
		//urspr�ngliche Perms werden dem Container �bergeben f�r Selektion
		newPermutations.addAll(generation.getPermutations());
		
		//Selektion
		//solange Anzahl an maximalen Permutationen/Generation nicht erreicht ist:
		while (generation.getPermutationCount() < newPermutations.size()) {
			
			Double worstFitness = Double.MAX_VALUE;
			int worstPermutation = 0;
			
			//alle Permutationen miteinander vergleichen
			for(int i = 0; i < newPermutations.size(); i++) {
				
				if(worstFitness > newPermutations.get(i).getFitness()) {
					
					worstFitness = newPermutations.get(i).getFitness();
					worstPermutation = i;
				}
			}
			
			//Permutation mit schlechtester Fitness entfernen
			newPermutations.remove(worstPermutation);
		}
		
		//Permutaionen der neuen Generation zuweisen und zur�ckgeben 
		newGeneration.setPermutations(newPermutations);
		return newGeneration;
	}	
}
