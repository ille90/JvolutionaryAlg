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

	/**
	 * fitnessprobalistische Selektion Methode für Bestimmung von
	 * Wahrscheinlichkeitswerte für die Permutationen Ausgangspunkt für
	 * probalistische Auswahlverfahren
	 * 
	 * @param generation
	 *            ursprüngliche Generation
	 * @return Double[] Wahrscheinlichkeitswerte der Permutationen
	 */
	private static double[] fitnessPropSelection(Generation generation) {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		double[] presumptions = new double[generation.getPermutations().size()];

		// Fitness der ursprünglichen Generation
		double generationFitness = generation.calcFitness();
		int i = 0;

		// Bestimmung des Wahrscheinlichkeitswerts einer jeden Permutation
		for (Permutation permutation : generation.getPermutations()) {

			// Fitness der Permutation bestimmen
			permutation.calcFitness();

			// W-Wert bestimmen
			// Verhältnis der Fitness des Individuums zur Fitness aller
			presumptions[i] = permutation.getFitness() / generationFitness;

			i++;
		}

		// Rückgabe des Containers der W-Werte
		return presumptions;
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
	private static double[] rankingPropSelection(Generation generation) {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		double[] presumptions = new double[generation.getPermutations().size()];

		// Anzahl der Individuen der Generation
		int r = generation.getPermutations().size();

		// Wahrscheinlichkeitswerte bestimmen
		for (int i = 0; i < generation.getPermutations().size(); i++) {

			presumptions[i] = (double) 2 / r * (1 - ((i - 1) / (r - 1)));
		}

		// Rückgabe des Containers der W-Werte
		return presumptions;
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
	public static Generation generationOrder(Generation generation) {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		double[] presumptions = new double[generation.getPermutations().size()];

		int i = 0;

		// Fitness der einzelnen Individuen bestimmen
		for (Permutation permutation : generation.getPermutations()) {

			permutation.calcFitness();
			presumptions[i] = permutation.getFitness();
			i++;
		}

		// Generation neu ordnen
		// Bubblesort
		for (int y = 0; y < presumptions.length - 1; y++) {

			for (int x = 0; x < presumptions.length - 1; x++) {

				if ((EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Highest && presumptions[x] > presumptions[x + 1]) ||
						(EvolutionSingleton.getInstance().getFitnessSelType() == FitnessSelectionType.Lowest && presumptions[x] < presumptions[x + 1])) {

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

		// Rückgabe der geänderten Generation
		return generation;
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
	public static Permutation rouletteSelection(Generation generation,
			PresumptionType type) {

		// Container für die Wahrscheinlichkeitswerte der Permutationen
		double[] presumptions = new double[generation.getPermutations().size()];

		// Warscheinlichkeitswert zur Bestimmung des entsprechenden Individuums
		double z = new Random().nextDouble();
		double currentValue = 0;

		// Auswahl zwischen den probalistischen Selektionsverfahren

		if (type == PresumptionType.ranking) {
			// rangbasierte Selektion

			// 1. Permutationen einer Generation in entsprechende RF bringen
			Generation newGeneration = generationOrder(generation);

			// 2. Array mit Wahrscheinlichkeitswerten
			presumptions = rankingPropSelection(newGeneration);

			generation = newGeneration;

		} else {

			// fitnessprobalistische Selektion
			presumptions = fitnessPropSelection(generation);
		}

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

	public static Permutation qSelection(Generation generation,
			int participantCount) {

		Generation participants = new Generation(
				(participantCount < generation.getMaxPermutationCount()) ? participantCount
						: generation.getMaxPermutationCount());
		Vector<Integer> indices = new Vector<Integer>();
		int i = 0;
		while (i < participants.getMaxPermutationCount()) {
			int j = (int) (new Random().nextDouble() * generation.getMaxPermutationCount());
			if (!indices.contains(j)) {
				participants.addPermutation(generation.getPermutation(j));
				i++;
			}
		}
		participants.calcFitness();
		return participants.getBestPermutation();
	}

	public static Permutation multibleQSelection(Generation generation,
			int participantCount) {
		int[] points = new int[generation.getMaxPermutationCount()];
		for (int n = 0; n < generation.getMaxPermutationCount(); n++) {
			Generation participants = new Generation(
					((participantCount + 1) < generation.getMaxPermutationCount()) ? (participantCount + 1)
							: generation.getMaxPermutationCount());
			Vector<Integer> indices = new Vector<Integer>();

			participants.addPermutation(generation.getPermutation(n));
			indices.add(n);

			int i = 0;
			while (i < participants.getMaxPermutationCount()) {
				int j = (int) (new Random().nextDouble() * generation.getMaxPermutationCount());
				if (!indices.contains(j)) {
					participants.addPermutation(generation.getPermutation(j));
					indices.add(j);
					i++;
				}
			}
			participants.calcFitness();
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

	public static Permutation useParentSelection(Generation generation) {

		switch (EvolutionSingleton.getInstance().getParentSelType()) {
		case rouletteSelection:
			return rouletteSelection(generation, EvolutionSingleton
					.getInstance().getPresumptType());
		case qSelection:
			return qSelection(generation, EvolutionSingleton.getInstance()
					.getMemberCount());
		case multibleQSelection:
			return multibleQSelection(generation, EvolutionSingleton
					.getInstance().getMemberCount());
		default:
			return null;
		}
	}

}
