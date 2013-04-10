package jea;

import java.util.Vector;

public class parentSelection {
	
	private static Double[] fitnessPropSelection(Generation generation){
		int size = generation.getPermutationCount();
		Double[] presumptions = new Double[size];
		Double  generationFitness = generation.calcFitness();
		int i = 0;
		
		for (Permutation permutation : generation.getPermutations()) {	
			permutation.calcFitness();
			presumptions[i] = permutation.getFitness()/generationFitness;
			i++;
		}
		
		return presumptions;
	}
	
	private static Double[] rankingPropSelection(Generation generation){
		int size = generation.getPermutationCount();
		Double[] presumptions = new Double[size];
		int r = generation.getPermutationCount();
		
		//Wahrscheinlichkeitswerte bestimmen
		for (int i = 0; i < size; i++) {			
			presumptions[i] = (double) 2/r * (1- ((i-1) / (r-1)));
		}
		
		return presumptions;
	}
	
	public static Generation generationOrder(Generation generation){
		int size = generation.getPermutationCount();
		Double[] presumptions = new Double[size];
		int i = 0;		
			 
		//Fitness der einzelnen Individuen bestimmen
		for (Permutation permutation : generation.getPermutations()) {	
			permutation.calcFitness();
			presumptions[i] = permutation.getFitness();
			i++;
		}
		
		//Generation neu ordnen
		for(int y=0; y<presumptions.length-1; y++) {
	    	
	        for(int x=0; x<presumptions.length-1; x++) {
	        	
	            if(presumptions[x] > presumptions[x+1]) {
	            	
	                Double t = presumptions[x];
	                Permutation u = generation.getPermutation(x);
	                
	                presumptions[x] = presumptions[x+1];
	                generation.setPermutation(generation.getPermutation(x+1), x);
	                
	                presumptions[x+1] = t;
	                generation.setPermutation(u, x+1);
	            }
	        }
	    }
		
		return generation;
	}
	
	//rangabsierte, für das Minimierungsprinzip
	public static Permutation rouletteSelection(Generation generation, presumptionType type){
		Double[] presumptions = new Double[generation.getPermutationCount()];
		
		double z =  Math.random();		
		double currentValue = 0;
		
		//Wahrscheinlichkeitswerte für Permutationen bestimmen
		if(type == presumptionType.ranking){
			//1. Permutationen einer Generation in entsprechende RF bringen
			Generation newGeneration = generationOrder(generation);
			//2. Array mit Wahrscheinlichkeitswerten
			presumptions = rankingPropSelection(newGeneration);
			generation = newGeneration;
		} else {
			presumptions = fitnessPropSelection(generation);
		}		
		
		for(int i = 0; i < presumptions.length; i++) {
			currentValue += presumptions[i];
			if(currentValue > z)
				return generation.getPermutation(i);
		}
		
		return generation.getPermutation(presumptions.length - 1);
	}
	
	public static Generation qSelection(Generation generation){
		
		return generation;
	}
	
	public static Generation multibleQSelection(Generation generation){
		
		return generation;
	}

}
