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
import jea.func.GriewankFunction;

public class GriewankTest {
	public static void main(String[] args) {
		EvolutionSingleton es = EvolutionSingleton.getInstance();
		es.setMaxThreads(2);

		GriewankFunction griewankFkt = new GriewankFunction();
		Model griewankModel = new Model(griewankFkt);
		griewankModel.setCodingType(CodingType.binary);
		griewankModel.useGrayCode = false;
		griewankModel.binaryRecombType = BinaryRecombinationType.random;
		griewankModel.setChainLength(10);
		griewankModel.geneCount = 10;
		griewankModel.parentSelType = ParentSelectionType.qSelection;
		griewankModel.presumptType = PresumptionType.ranking;
		griewankModel.fitnessSelType = FitnessSelectionType.Lowest;
		griewankModel.permutationCount = 200;
		griewankModel.maxGeneration = 20;
		griewankModel.childrenCount = 500;
		griewankModel.determSelType = DetermSelectionType.plusSelection;
		
		es.setModel(griewankModel);

		Population population = new Population();
		population.init();
		griewankFkt.printPermutationInfo(population.getBestPermutation());
		population.run();
		griewankFkt.printPermutationInfo(population.getBestPermutation());
	}
}
