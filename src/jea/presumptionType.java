package jea;

/**
 * Enumeration f�r die Wahl des Typs f�r die Bestimmung der Wahrscheinlichkeitswerte
 * f�r die probalistische Selektion
 * @author Franziska Staake
 *
 */
public enum presumptionType {
	/**
	 * Fitnessprobalistische Selektion
	 * f�r Maximierungsprobleme
	 */
	fitness, 
	/**
	 * Rangbasierte Selektion
	 * f�r Minimierungsprobleme
	 */
	ranking;
}
