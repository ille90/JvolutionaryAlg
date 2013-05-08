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
import jea.func.NullstelleFunction;

public class NullstellenTest {
	public static void main(String[] args) {
		EvolutionSingleton es = EvolutionSingleton.getInstance();
		es.setCodingType(CodingType.binary);
		es.useGrayCode(false);
		es.setBinaryRecombType(BinaryRecombinationType.random);
		es.setRealRecombType(RealRecombinationType.random);
		es.setChainLength(20);
		es.setGeneCount(5);
		es.setLowestValue(-100);
		es.setHeighestValue(99);
		es.setParentSelType(ParentSelectionType.qSelection);
		es.setPresumptType(PresumptionType.fitness);
		es.setFitnessSelType(FitnessSelectionType.Lowest);
		es.setMaxThreads(2);

		DetermSelectionType type = DetermSelectionType.plusSelection;

		int permutationCount = 200;
		int maxGeneration = 40;
		int childrenCount = 500;
		
		NullstelleFunction nullstkFkt = new NullstelleFunction();
		es.setFitnessFunction(nullstkFkt);

		Population population = new Population(permutationCount, maxGeneration, childrenCount);
		population.init();
		nullstkFkt.printPermutationInfo(population.getBestPermutation());
		population.run(type);
		nullstkFkt.printPermutationInfo(population.getBestPermutation());
		
	}
}
