package jea.alg.selection;

import java.util.Random;
import java.util.Vector;

import jea.alg.EvolutionSingleton;
import jea.alg.Generation;
import jea.alg.Permutation;

/**
 * Klasse, welche Methoden für Elternselektion enthält
 * 
 * @author Franziska Staake, Tim Illner
 * 
 */
public class ParentSelection {

	double[] presumptions;
	Generation generation;

	public ParentSelection() {
		// TODO Auto-generated constructor stub
	}

	public void load(Generation generation) {
		generationOrder(generation);
		this.generation = generation;
		if (EvolutionSingleton.getInstance().getParentSelType() == ParentSelectionType.rouletteSelection) {
			if (EvolutionSingleton.getInstance().getPresumptType() == PresumptionType.ranking) {
				// rangbasierte Selektion
				rankingPropSelection();
			} else {
				// fitnessprobalistische Selektion
				fitnessPropSelection();
			}
		}
	}

	/**
	 * fitnessprobalistische Selektion Methode für Bestimmung von
	 * Wahrscheinlichkeitswerte für die Permutationen Ausgangspunkt für
	 * probalistische Auswahlverfahren
	 * 
	 * @param generation
	 *            ursprüngliche Generation
	 * @return Double[] Wahrscheinlichkeitswerte der Permutationen
	 */
	private void fitnessPropSelection() {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		presumptions = new double[generation.getPermutations().size()];

		// Fitness der ursprünglichen Generation
		double generationFitness = generation.calcFitness();
		int i = 0;

		// Bestimmung des Wahrscheinlichkeitswerts einer jeden Permutation
		for (Permutation permutation : generation.getPermutations()) {

			// W-Wert bestimmen
			// Verhältnis der Fitness des Individuums zur Fitness aller
			presumptions[i] = permutation.getFitness() / generationFitness;

			i++;
		}
	}

	/**
	 * rangbasierte Selektion Methode für Bestimmung von
	 * Wahrscheinlichkeitswerte für die Permutationen Ausgangspunkt für
	 * probalistische Auswahlverfahren
	 * 
	 * @param generation
	 *            ursprüngliche Generation
	 * @return Double[] Wahrscheinlichkeitswerte der Permutationen
	 */
	private void rankingPropSelection() {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		presumptions = new double[generation.getPermutations().size()];

		// Anzahl der Individuen der Generation
		int r = generation.getPermutations().size();

		// Wahrscheinlichkeitswerte bestimmen
		for (int i = 0; i < generation.getPermutations().size(); i++) {

			presumptions[i] = (double) 2 / r * (1 - ((i - 1) / (r - 1)));
		}
	}

	/**
	 * Methode zur Bestimmung der Rangfolge der Permutaionen einer Generation
	 * Ausgangspunkt für angbasierte Selektion
	 * 
	 * @param generation
	 *            ursprüngliche Generation
	 * @return Generation Generation mit entsprechender Rangfolge der
	 *         Permutaionen
	 */
	private void generationOrder(Generation generation) {

		if (EvolutionSingleton.getInstance().getParentSelType() != ParentSelectionType.rouletteSelection
				|| EvolutionSingleton.getInstance().getPresumptType() != PresumptionType.ranking)
			return;

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		double[] presumptions = new double[generation.getPermutations().size()];

		int i = 0;

		// Fitness der einzelnen Individuen bestimmen
		for (Permutation permutation : generation.getPermutations()) {

			presumptions[i] = permutation.getFitness();
			i++;
		}

		// Generation neu ordnen
		// Bubblesort
		for (int y = 0; y < presumptions.length - 1; y++) {

			for (int x = 0; x < presumptions.length - 1; x++) {

				if ((EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest && presumptions[x] > presumptions[x + 1])
						|| (EvolutionSingleton.getInstance()
								.getFitnessSelType() == FitnessSelectionType.Lowest && presumptions[x] < presumptions[x + 1])) {

					double t = presumptions[x];
					Permutation u = generation.getPermutation(x);

					presumptions[x] = presumptions[x + 1];
					generation.setPermutation(generation.getPermutation(x + 1),
							x);

					presumptions[x + 1] = t;
					generation.setPermutation(u, x + 1);
				}
			}
		}
	}

	/**
	 * Rouletteselektion probalistisches Auswalverfahren Auswahl eines
	 * Elternteils aus der Menge an Permutaionen einer Generation
	 * 
	 * @param generation
	 *            ursprüngliche Generation
	 * @param type
	 *            gewähltes probalistisches Selektionsverfahren
	 * @return Permutation gewähltes Elternteil
	 */
	private Permutation rouletteSelection() {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		double[] presumptions = new double[generation.getPermutations().size()];

		// Warscheinlichkeitswert zur Bestimmung des entsprechenden Individuums
		double z = new Random().nextDouble();
		double currentValue = 0;

		// eigentliche Rouletteselektion
		for (int i = 0; i < presumptions.length; i++) {

			// Auswahl der Permutation durch Zufall
			// Anordnung der Permutationen auf einem Rouletterad
			// mit untersch. Wahrscheinlichkeiten
			// zufällige Zahl wird ermittelt (z)
			// Permutation, in dessem Bereich z liegt, wird gewählt

			currentValue += presumptions[i];

			if (currentValue > z)
				return generation.getPermutation(i);
		}

		return generation.getPermutation(presumptions.length - 1);
	}

	private Permutation qSelection(int participantCount) {

		Generation participants = new Generation(
				(participantCount < generation.getMaxPermutationCount()) ? participantCount
						: generation.getMaxPermutationCount());
		Vector<Integer> indices = new Vector<Integer>();
		int i = 0;
		while (i < participants.getMaxPermutationCount()) {
			int j = (int) (new Random().nextDouble() * generation
					.getMaxPermutationCount());
			if (!indices.contains(j)) {
				participants.addPermutation(generation.getPermutation(j));
				i++;
			}
		}
		return participants.getBestPermutation();
	}

	private Permutation multibleQSelection(int participantCount) {
		int[] points = new int[generation.getMaxPermutationCount()];
		for (int n = 0; n < generation.getMaxPermutationCount(); n++) {
			Generation participants = new Generation(
					((participantCount + 1) < generation
							.getMaxPermutationCount()) ? (participantCount + 1)
							: generation.getMaxPermutationCount());
			Vector<Integer> indices = new Vector<Integer>();

			participants.addPermutation(generation.getPermutation(n));
			indices.add(n);

			int i = 0;
			while (i < participants.getMaxPermutationCount()) {
				int j = (int) (new Random().nextDouble() * generation
						.getMaxPermutationCount());
				if (!indices.contains(j)) {
					participants.addPermutation(generation.getPermutation(j));
					indices.add(j);
					i++;
				}
			}
			int winner = indices.get(participants.getBestPermutationByIndex());
			if (winner != -1) {
				points[winner]++;
			}
		}

		Vector<Permutation> winners = new Vector<Permutation>();
		int bestPoints = -1;
		for (int i = 0; i < points.length; i++) {
			if (points[i] > bestPoints) {
				winners = new Vector<Permutation>();
				winners.add(generation.getPermutation(i));
				bestPoints = points[i];
			} else if (points[i] == bestPoints)
				winners.add(generation.getPermutation(i));
		}

		Generation winnerGen = new Generation(winners.size());
		for (Permutation permutation : winners) {
			winnerGen.addPermutation(permutation);
		}

		return winnerGen.getRandomPermutation();
	}

	public Permutation useParentSelection() {

		switch (EvolutionSingleton.getInstance().getParentSelType()) {
		case rouletteSelection:
			return rouletteSelection();
		case qSelection:
			return qSelection(EvolutionSingleton.getInstance().getMemberCount());
		case multibleQSelection:
			return multibleQSelection(EvolutionSingleton.getInstance()
					.getMemberCount());
		default:
			return null;
		}
	}

}
