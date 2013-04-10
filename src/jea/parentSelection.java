package jea;

import java.util.Vector;

/**
 * Klasse, welche Methoden für Elternselektion enthält
 * @author Franziska Staake, Tim Illner
 *
 */
public class parentSelection {
	
	/**
	 * fitnessprobalistische Selektion
	 * Methode für Bestimmung von Wahrscheinlichkeitswerte
	 * für die Permutationen
	 * Ausgangspunkt für probalistische Auswahlverfahren
	 * @param generation 	ursprüngliche Generation
	 * @return Double[]		Wahrscheinlichkeitswerte der Permutationen
	 */
	private static Double[] fitnessPropSelection(Generation generation) {
		
		//Container f�r die Wahrscheinlichkeitswerte der Permutationen
		Double[] presumptions = new Double[generation.getPermutations().size()];
		
		//Fitness der urspr�nglichen Generation
		Double  generationFitness = generation.calcFitness();
		int i = 0;
		
		//Bestimmung des Wahrscheinlichkeitswerts einer jeden Permutation
		for (Permutation permutation : generation.getPermutations()) {
			
			//Fitness der Permutation bestimmen
			permutation.calcFitness();
			
			//W-Wert bestimmen
			//Verh�ltnis der Fitness des Individuums zur Fitness aller
			presumptions[i] = permutation.getFitness()/generationFitness;
			
			i++;
		}
		
		//R�ckgabe des Containers der W-Werte
		return presumptions;
	}
	
	/**
	 * rangbasierte Selektion
	 * Methode für Bestimmung von Wahrscheinlichkeitswerte
	 * für die Permutationen
	 * Ausgangspunkt für probalistische Auswahlverfahren
	 * @param generation 	ursprüngliche Generation
	 * @return Double[]		Wahrscheinlichkeitswerte der Permutationen
	 */
	private static Double[] rankingPropSelection(Generation generation) {
		
		//Container f�r die Wahrscheinlichkeitswerte der Permutationen
		Double[] presumptions = new Double[generation.getPermutations().size()];
		
		//Anzahl der Individuen der Generation
		int r = generation.getPermutations().size();
		
		//Wahrscheinlichkeitswerte bestimmen
		for (int i = 0; i < generation.getPermutations().size(); i++) {
			
			presumptions[i] = (double) 2/r * (1- ((i-1) / (r-1)));
		}
		
		//R�ckgabe des Containers der W-Werte
		return presumptions;
	}
	
	/**
	 * Methode zur Bestimmung der Rangfolge der Permutaionen einer Generation
	 * Ausgangspunkt für angbasierte Selektion
	 * @param generation 	ursprüngliche Generation
	 * @return Generation	Generation mit entsprechender Rangfolge der Permutaionen
	 */
	public static Generation generationOrder(Generation generation) {
		
		//Container f�r die Wahrscheinlichkeitswerte der Permutationen
		Double[] presumptions = new Double[generation.getPermutations().size()];
		
		int i = 0;		
			 
		//Fitness der einzelnen Individuen bestimmen
		for (Permutation permutation : generation.getPermutations()) {
			
			permutation.calcFitness();
			presumptions[i] = permutation.getFitness();
			i++;
		}
		
		//Generation neu ordnen
		//Bubblesort
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
		
		//R�ckgabe der ge�nderten Generation
		return generation;
	}
	
	/**
	 * Rouletteselektion
	 * probalistisches Auswalverfahren
	 * Auswahl eines Elternteils aus der Menge an Permutaionen einer Generation
	 * @param generation	ursprüngliche Generation
	 * @param type			gewähltes probalistisches Selektionsverfahren
	 * @return Permutation	gewähltes Elternteil
	 */
	public static Permutation rouletteSelection(Generation generation, presumptionType type) {
		
		//Container für die Wahrscheinlichkeitswerte der Permutationen
		Double[] presumptions = new Double[generation.getPermutations().size()];
		
		//Warscheinlichkeitswert zur Bestimmung des entsprechenden Individuums
		double z =  Math.random();		
		double currentValue = 0;
		
		//Wahrscheinlichkeitswerte für Permutationen bestimmen
		//Auswahl zwischen den probalistischen Selektionsverfahren
		
		if(type == presumptionType.ranking) {
			//rangbasierte Selektion
			
			//1. Permutationen einer Generation in entsprechende RF bringen
			Generation newGeneration = generationOrder(generation);
			
			//2. Array mit Wahrscheinlichkeitswerten
			presumptions = rankingPropSelection(newGeneration);
			
			generation = newGeneration;
			
		} else {
			
			//fitnessprobalistische Selektion
			presumptions = fitnessPropSelection(generation);
		}		
		
		//eigentliche Rouletteselektion
		for(int i = 0; i < presumptions.length; i++) {
			
			//Auswahl der Permutation durch Zufall
			//Anordnung der Permutationen auf einem Rouletterad 
			//mit untersch. Wahrscheinlichkeiten
			//zufällige Zahl wird ermittelt (z)
			//Permutation, in dessem Bereich z liegt, wird gewählt
			
			currentValue += presumptions[i];
			
			if(currentValue > z)
				return generation.getPermutation(i);
		}
		
		return generation.getPermutation(presumptions.length - 1);
	}
	
	public static Permutation qSelection(Generation generation, int participantCount) {
		
		Generation participants = new Generation((participantCount < generation.getPermutationCount()) ? participantCount : generation.getPermutationCount());
		Vector<Integer> indices = new Vector<Integer>();
		int i = 0;
		while(i < participants.getPermutationCount()) {
			int j = (int) (Math.random() * generation.getPermutationCount());
			if(!indices.contains(j)) {
				participants.addPermutation(generation.getPermutation(j));
				i++;
			}
		}
		participants.calcFitness();
		return participants.getBestPermutation();
	}
	
	public static Vector<Permutation> multibleQSelection(Generation generation, int participantCount) {
		Vector<Integer> points = new Vector<Integer>();
		for(int n = 0; n < generation.getPermutationCount(); n++) {
			Generation participants = new Generation(((participantCount + 1) < generation.getPermutationCount()) ? (participantCount + 1) : generation.getPermutationCount());
			Vector<Integer> indices = new Vector<Integer>();

			participants.addPermutation(generation.getPermutation(n));
			indices.add(n);
			
			int i = 0;
			while(i < participants.getPermutationCount()) {
				int j = (int) (Math.random() * generation.getPermutationCount());
				if(!indices.contains(j)) {
					participants.addPermutation(generation.getPermutation(j));
					i++;
				}
			}
			participants.calcFitness();
			int winner = participants.getBestPermutationByIndex();
			if(winner != -1)
				points.set(winner, points.get(winner) + 1);
		}
		
		Vector<Permutation> winners = new Vector<Permutation>();
		int bestPoints = -1;
		for(int i = 0; i < points.size(); i++) {
			if(points.get(i) > bestPoints) {
				winners = new Vector<Permutation>();
				winners.add(generation.getPermutation(i));
				bestPoints = points.get(i);
			} else if(points.get(i) == bestPoints)
				winners.add(generation.getPermutation(i));
		}
		
		return winners;
	}

}
