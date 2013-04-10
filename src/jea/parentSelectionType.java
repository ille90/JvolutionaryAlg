package jea;

/**
 * Enumeration f�r die Wahl der Selektion
 * @author Franziska Staake
 *
 */
public enum parentSelectionType { 
	/**
	 * Rouletteselektion
	 * geh�rt zur probalistischen Selektion (nach Zufall)
	 */
	rouletteSelection,
	/**
	 * q-fache Turnierselektion
	 * geh�rt zur probalistischen Selektion (nach Zufall)
	 */
	qSelection, 
	/**
	 * q-stufige zweifache Tuernierselektion
	 * geh�rt zur probalistischen Selektion (nach Zufall)
	 */
	multibleQSelection;
}
