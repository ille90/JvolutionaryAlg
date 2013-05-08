package jea.test;

import jea.alg.EvolutionSingleton;
import jea.alg.Model;
import jea.alg.Population;
import jea.alg.coding.CodingType;
import jea.alg.coding.binary.BinaryRecombinationType;
import jea.alg.selection.DetermSelectionType;
import jea.alg.selection.FitnessSelectionType;
import jea.alg.selection.ParentSelectionType;
import jea.alg.selection.PresumptionType;
import jea.func.NullstelleFunction;

public class NullstellenTest {
	public static void main(String[] args) {
		EvolutionSingleton es = EvolutionSingleton.getInstance();
		es.setMaxThreads(2);

		NullstelleFunction nullstFkt = new NullstelleFunction();
		Model nullstModel = new Model(nullstFkt);
		nullstModel.setCodingType(CodingType.binary);
		nullstModel.useGrayCode = false;
		nullstModel.binaryRecombType = BinaryRecombinationType.random;
		nullstModel.setChainLength(10);
		nullstModel.geneCount = 20;
		nullstModel.parentSelType = ParentSelectionType.qSelection;
		nullstModel.presumptType = PresumptionType.ranking;
		nullstModel.fitnessSelType = FitnessSelectionType.Lowest;
		nullstModel.permutationCount = 200;
		nullstModel.maxGeneration = 20;
		nullstModel.childrenCount = 500;
		nullstModel.determSelType = DetermSelectionType.plusSelection;
		nullstModel.setLowestValue(-100);
		nullstModel.setHeighestValue(99);
		
		es.setModel(nullstModel);

		Population population = new Population();
		population.init();
		nullstFkt.printPermutationInfo(population.getBestPermutation());
		population.run();
		nullstFkt.printPermutationInfo(population.getBestPermutation());
		
	}
}
