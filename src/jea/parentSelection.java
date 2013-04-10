package jea;

import java.util.Vector;

public class parentSelection {
	
	public static Generation probSelection(Generation generation){
		
		return generation;
	}

	//rangabsierte, für das Minimierungsprinzip
	public static int rouletteSelection(double[] ps){
		
		double z =  Math.random();
		
		double currentValue = 0;
		
		for(int i = 0; i < ps.length; i++) {
			currentValue += ps[i];
			if(currentValue > z)
				return i;
		}
		
		return ps.length - 1;
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
