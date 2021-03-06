package jea.alg.selection;

/**
 * Enumeration für die Wahl des Typs für die Bestimmung der Wahrscheinlichkeitswerte
 * für die probalistische Selektion
 * @author Franziska Staake
 *
 */
public enum PresumptionType {
	/**
	 * Fitnessprobalistische Selektion
	 * für Maximierungsprobleme
	 */
	fitness, 
	/**
	 * Rangbasierte Selektion
	 * für Minimierungsprobleme
	 */
	ranking;
}
