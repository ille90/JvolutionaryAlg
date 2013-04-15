package jea;

/**
 * Enumeration für die Wahl des Deterministischen Selektionstyps
 * @author Franziska Staake
 *
 */
public enum DetermSelectionType {
	/**
	 * Kommaselektion (nur aus Kindern gewählt)
	 */
	commaSelection,
	/**
	 * Plusselektion (aus Eltern und Kindern gewählt)
	 */
	plusSelection;
}
