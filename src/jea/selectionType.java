package jea;

/**
 * Enumeration f�r die Wahl der Selektion
 * @author Franziska Staake
 *
 */
public enum selectionType {
	/**
	 * deterministische Selektion
	 * Individuen durch Fitnessfkt. bestimmt
	 */
	determSelection, 
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
