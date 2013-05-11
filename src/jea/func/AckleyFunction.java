package jea.func;

import jea.alg.EvolutionSingleton;
import jea.alg.FitnessFunction;
import jea.alg.Permutation;

public class AckleyFunction implements FitnessFunction{

	public double lowestValue = -20;
	public double heighestValue = 30;
	EvolutionSingleton es;
	double basefitness;
	
	public AckleyFunction() {
		es = EvolutionSingleton.getInstance();
		basefitness = 20 + Math.exp(1);
	}

	@Override
	public Double getLowestValue() {
		return lowestValue;
	}

	@Override
	public Double getHeighestValue() {
		return heighestValue;
	}
	
	@Override
	public void calcFitness(Permutation permutation) {
		double fitness = basefitness;
		double sqrtsum = 0;
		double cossum = 0;
		for (int i = 0; i < es.getGeneCount(); i++) {
			sqrtsum += Math.pow(permutation.getGene(i).getValue(), 2);
			cossum += Math.cos(2 * Math.PI * permutation.getGene(i).getValue());
		}
		fitness -= 20 * Math.exp(-0.2 * Math.sqrt(sqrtsum / es.getGeneCount()));
		fitness -= Math.exp(cossum / es.getGeneCount());
		permutation.setFitness(fitness);
	}

	@Override
	public void printPermutationInfo(Permutation permutation) {
		String gene = "";
		for(int i = 0; i < es.getGeneCount(); i++) {
			gene += permutation.getGene(i).getValue() + ", ";
		}
		
		System.out.println("Gene: " + gene);
		calcFitness(permutation);
		System.out.println("Fitness: " + permutation.getFitness());
	}
	
	@Override
	public String getName() {
		return "Ackley";
	}
}
