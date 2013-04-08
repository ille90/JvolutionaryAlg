package reiseproblem;

import java.util.HashMap;

import jea.GenePoolSingleton;
import jea.Population;
import jea.determSelectionType;

public class RundreiseBeispiel {
	
	public static void main(String[] args) {
		determSelectionType type = determSelectionType.commaSelection;
		HashMap<Integer, int[]> costs = new HashMap<Integer, int[]>();
		costs.put(0, new int[] {0, 5, 8, 11, 4, 7});	
		costs.put(1, new int[] {5, 0, 10, 4, 9, 12});
		costs.put(2, new int[] {8, 10, 0, 6, 17, 8});
		costs.put(3, new int[] {11, 4, 6, 0, 6, 5});
		costs.put(4, new int[] {4, 9, 17, 6, 0, 11});
		costs.put(5, new int[] {7, 12, 8, 5, 11, 0});
		
		
		int permutationCount = 5;
		int maxGeneration = 15;
		int childrenCount = 15;
		float limit = 0.5f;
		
		Staedte staedte = new Staedte(costs);
		GenePoolSingleton.getInstance().setGenPool(staedte);
		Population population = new Population(permutationCount, maxGeneration, childrenCount, limit);
		population.init();
		staedte.printPermutationInfo(population.getBestPermutation());
		population.run(type);
		staedte.printPermutationInfo(population.getBestPermutation());
	}

}
