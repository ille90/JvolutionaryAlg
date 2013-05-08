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
import jea.func.AckleyFunction;

public class AckleyTest {

	public static void main(String[] args) {
		EvolutionSingleton es = EvolutionSingleton.getInstance();
		es.setMaxThreads(2);
		
		AckleyFunction ackleykFkt = new AckleyFunction();
		Model ackleyModel = new Model(ackleykFkt);
		ackleyModel.setCodingType(CodingType.binary);
		ackleyModel.useGrayCode = false;
		ackleyModel.binaryRecombType = BinaryRecombinationType.random;
		ackleyModel.setChainLength(10);
		ackleyModel.geneCount = 20;
		ackleyModel.parentSelType = ParentSelectionType.qSelection;
		ackleyModel.presumptType = PresumptionType.ranking;
		ackleyModel.fitnessSelType = FitnessSelectionType.Lowest;
		ackleyModel.permutationCount = 200;
		ackleyModel.maxGeneration = 20;
		ackleyModel.childrenCount = 500;
		ackleyModel.determSelType = DetermSelectionType.plusSelection;
		
		es.setModel(ackleyModel);

		Population population = new Population();
		population.init();
		ackleykFkt.printPermutationInfo(population.getBestPermutation());
		population.run();
		ackleykFkt.printPermutationInfo(population.getBestPermutation());
		
	}
}
