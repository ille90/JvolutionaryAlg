package jea;

/**
 * Enumeration für die Wahl der Selektion
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
	 * gehört zur probalistischen Selektion (nach Zufall)
	 */
	rouletteSelection,
	/**
	 * q-fache Turnierselektion
	 * gehört zur probalistischen Selektion (nach Zufall)
	 */
	qSelection, 
	/**
	 * q-stufige zweifache Tuernierselektion
	 * gehört zur probalistischen Selektion (nach Zufall)
	 */
	multibleQSelection;
}
