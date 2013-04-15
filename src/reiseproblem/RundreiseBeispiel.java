package reiseproblem;

import java.util.HashMap;

import jea.EvolutionSingleton;
import jea.FitnessSelectionType;
import jea.GenePool;
import jea.JythonFactory;
import jea.Population;
import jea.DetermSelectionType;
import jea.ParentSelection;
import jea.ParentSelectionType;
import jea.PresumptionType;

public class RundreiseBeispiel {
	
	public static void main(String[] args) {
		DetermSelectionType type = DetermSelectionType.plusSelection;
		HashMap<Integer, int[]> costs = new HashMap<Integer, int[]>();
		costs.put(0, new int[] {0, 5, 8, 11, 4, 7});	
		costs.put(1, new int[] {5, 0, 10, 4, 9, 12});
		costs.put(2, new int[] {8, 10, 0, 6, 17, 8});
		costs.put(3, new int[] {11, 4, 6, 0, 6, 5});
		costs.put(4, new int[] {4, 9, 17, 6, 0, 11});
		costs.put(5, new int[] {7, 12, 8, 5, 11, 0});
		
		
		int permutationCount = 15;
		int maxGeneration = 20;
		int childrenCount = 20;
		float limit = 0.8f;
		
		Staedte staedte = new Staedte(costs);
		GenePool genePool = JythonFactory.getJythonGenePool("/home/johannes/projects/JvolutionaryAlg/Staedte.py"); 
		EvolutionSingleton.getInstance().setGenPool(genePool);
		EvolutionSingleton.getInstance().setFitnessSelType(FitnessSelectionType.Lowest);
		EvolutionSingleton.getInstance().setParentSelType(ParentSelectionType.multibleQSelection);
		EvolutionSingleton.getInstance().setPresumptType(PresumptionType.ranking);
		EvolutionSingleton.getInstance().setMemberCount(5);
		
		Population population = new Population(permutationCount, maxGeneration, childrenCount, limit);
		population.init();
		staedte.printPermutationInfo(population.getBestPermutation());
		population.run(type);
		staedte.printPermutationInfo(population.getBestPermutation());
	}

}
