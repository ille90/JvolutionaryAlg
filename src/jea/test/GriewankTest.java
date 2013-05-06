package jea.test;

import jea.alg.EvolutionSingleton;
import jea.alg.Population;
import jea.alg.coding.CodingType;
import jea.alg.coding.binary.BinaryRecombinationType;
import jea.alg.coding.real.RealRecombinationType;
import jea.alg.selection.DetermSelectionType;
import jea.alg.selection.FitnessSelectionType;
import jea.alg.selection.ParentSelectionType;
import jea.alg.selection.PresumptionType;
import jea.func.GriewankFunction;

public class GriewankTest {
	public static void main(String[] args) {
		EvolutionSingleton es = EvolutionSingleton.getInstance();
		es.setCodingType(CodingType.real);
		es.useGrayCode(false);
		es.setBinaryRecombType(BinaryRecombinationType.onepoint);
		es.setRealRecombType(RealRecombinationType.arithmetic);
		es.setChainLength(10);
		es.setGeneCount(5);
		es.setLowestValue(-512);
		es.setHeighestValue(511);
		es.setParentSelType(ParentSelectionType.qSelection);
		es.setPresumptType(PresumptionType.ranking);
		es.setFitnessSelType(FitnessSelectionType.Lowest);
		es.setMaxThreads(4);

		DetermSelectionType type = DetermSelectionType.plusSelection;

		int permutationCount = 200;
		int maxGeneration = 20;
		int childrenCount = 500;
		float limit = 0.8f;
		
		GriewankFunction griewankFkt = new GriewankFunction();
		es.setFitnessFunction(griewankFkt);

		Population population = new Population(permutationCount, maxGeneration, childrenCount, limit);
		population.init();
		griewankFkt.printPermutationInfo(population.getBestPermutation());
		population.run(type);
		griewankFkt.printPermutationInfo(population.getBestPermutation());
		
		/*Permutation father = Permutation.getRandomPermutation();
		father.calcFitness();
		System.out.println(father);
		Permutation mother = Permutation.getRandomPermutation();
		mother.calcFitness();
		System.out.println(mother);
		Permutation[] children = es.getCoding().recombination(father, mother);
		for (Permutation child : children) {
			child.calcFitness();
			System.out.println(child);
		}*/
		
		/*Permutation perm = Permutation.getRandomPermutation();
		perm.calcFitness();
		System.out.println(perm);
		es.getCoding().mutation(perm);
		perm.calcFitness();
		System.out.println(perm);*/
	}
}
