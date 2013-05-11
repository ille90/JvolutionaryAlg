package jea.gui;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jea.alg.Model;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

public class PropsPanel extends JPanel {

	Model model;
	private JTextField funktionTextField;
	/**
	 * Create the panel.
	 */
	public PropsPanel(Model newModel) {
		this.model = newModel;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 28, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblFunktion = new JLabel("Funktion:");
		GridBagConstraints gbc_lblFunktion = new GridBagConstraints();
		gbc_lblFunktion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFunktion.anchor = GridBagConstraints.EAST;
		gbc_lblFunktion.gridx = 0;
		gbc_lblFunktion.gridy = 0;
		add(lblFunktion, gbc_lblFunktion);
		
		funktionTextField = new JTextField();
		funktionTextField.setText(model.getFitnessFunction().getName());
		funktionTextField.setEditable(false);
		GridBagConstraints gbc_funktionTextField = new GridBagConstraints();
		gbc_funktionTextField.gridwidth = 4;
		gbc_funktionTextField.insets = new Insets(0, 0, 5, 5);
		gbc_funktionTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_funktionTextField.gridx = 2;
		gbc_funktionTextField.gridy = 0;
		add(funktionTextField, gbc_funktionTextField);
		funktionTextField.setColumns(10);
		
		JLabel lblGenerationen = new JLabel("Generationen:");
		GridBagConstraints gbc_lblGenerationen = new GridBagConstraints();
		gbc_lblGenerationen.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenerationen.gridx = 0;
		gbc_lblGenerationen.gridy = 1;
		add(lblGenerationen, gbc_lblGenerationen);
		
		JSpinner generationSpinner = new JSpinner();
		generationSpinner.setModel(new SpinnerNumberModel(model.maxGeneration, 5, 1000, 5));
		GridBagConstraints gbc_generationSpinner = new GridBagConstraints();
		gbc_generationSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_generationSpinner.gridx = 1;
		gbc_generationSpinner.gridy = 1;
		add(generationSpinner, gbc_generationSpinner);
		
		JLabel lblIndividuen = new JLabel("Individuen:");
		GridBagConstraints gbc_lblIndividuen = new GridBagConstraints();
		gbc_lblIndividuen.insets = new Insets(0, 0, 5, 5);
		gbc_lblIndividuen.gridx = 2;
		gbc_lblIndividuen.gridy = 1;
		add(lblIndividuen, gbc_lblIndividuen);
		
		JSpinner individuenSpinner = new JSpinner();
		individuenSpinner.setModel(new SpinnerNumberModel(model.permutationCount, 5, 1000, 5));
		GridBagConstraints gbc_individuenSpinner = new GridBagConstraints();
		gbc_individuenSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_individuenSpinner.gridx = 3;
		gbc_individuenSpinner.gridy = 1;
		add(individuenSpinner, gbc_individuenSpinner);
		
		JLabel lblKinder = new JLabel("Kinder:");
		GridBagConstraints gbc_lblKinder = new GridBagConstraints();
		gbc_lblKinder.insets = new Insets(0, 0, 5, 5);
		gbc_lblKinder.gridx = 4;
		gbc_lblKinder.gridy = 1;
		add(lblKinder, gbc_lblKinder);
		
		JSpinner kinderSpinner = new JSpinner();
		kinderSpinner.setModel(new SpinnerNumberModel(model.childrenCount, 5, 1000, 5));
		GridBagConstraints gbc_kinderSpinner = new GridBagConstraints();
		gbc_kinderSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_kinderSpinner.gridx = 5;
		gbc_kinderSpinner.gridy = 1;
		add(kinderSpinner, gbc_kinderSpinner);
		
		JLabel lblGene = new JLabel("Gene:");
		GridBagConstraints gbc_lblGene = new GridBagConstraints();
		gbc_lblGene.insets = new Insets(0, 0, 5, 5);
		gbc_lblGene.gridx = 0;
		gbc_lblGene.gridy = 2;
		add(lblGene, gbc_lblGene);
		
		JSpinner geneSpinner = new JSpinner();
		geneSpinner.setModel(new SpinnerNumberModel(model.geneCount, 5, 1000, 1));
		GridBagConstraints gbc_geneSpinner = new GridBagConstraints();
		gbc_geneSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_geneSpinner.gridx = 1;
		gbc_geneSpinner.gridy = 2;
		add(geneSpinner, gbc_geneSpinner);
		
		JLabel lblMinWert = new JLabel("min. Wert:");
		GridBagConstraints gbc_lblMinWert = new GridBagConstraints();
		gbc_lblMinWert.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinWert.gridx = 2;
		gbc_lblMinWert.gridy = 2;
		add(lblMinWert, gbc_lblMinWert);
		
		JSpinner minWertSpinner = new JSpinner();
		minWertSpinner.setModel(new SpinnerNumberModel(model.getLowestValue(), -1024.0d, 1023.0d, 1.0d));
		if(model.getFitnessFunction().getLowestValue() != null)
			minWertSpinner.setEnabled(false);
		GridBagConstraints gbc_minWertSpinner = new GridBagConstraints();
		gbc_minWertSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_minWertSpinner.gridx = 3;
		gbc_minWertSpinner.gridy = 2;
		add(minWertSpinner, gbc_minWertSpinner);
		
		JLabel lblMaxWert = new JLabel("max. Wert:");
		GridBagConstraints gbc_lblMaxWert = new GridBagConstraints();
		gbc_lblMaxWert.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaxWert.gridx = 4;
		gbc_lblMaxWert.gridy = 2;
		add(lblMaxWert, gbc_lblMaxWert);
		
		JSpinner maxWertSpinner = new JSpinner();
		maxWertSpinner.setModel(new SpinnerNumberModel(model.getHeighestValue(), -1024.0d, 1023.0d, 1.0d));
		if(model.getFitnessFunction().getHeighestValue() != null)
			maxWertSpinner.setEnabled(false);
		GridBagConstraints gbc_maxWertSpinner = new GridBagConstraints();
		gbc_maxWertSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_maxWertSpinner.gridx = 5;
		gbc_maxWertSpinner.gridy = 2;
		add(maxWertSpinner, gbc_maxWertSpinner);
		
		JLabel lblKodierung = new JLabel("Kodierung:");
		GridBagConstraints gbc_lblKodierung = new GridBagConstraints();
		gbc_lblKodierung.anchor = GridBagConstraints.EAST;
		gbc_lblKodierung.insets = new Insets(0, 0, 5, 5);
		gbc_lblKodierung.gridx = 0;
		gbc_lblKodierung.gridy = 3;
		add(lblKodierung, gbc_lblKodierung);
		
		JComboBox kodierungComboBox = new JComboBox();
		GridBagConstraints gbc_kodierungComboBox = new GridBagConstraints();
		gbc_kodierungComboBox.gridwidth = 2;
		gbc_kodierungComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_kodierungComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_kodierungComboBox.gridx = 1;
		gbc_kodierungComboBox.gridy = 3;
		add(kodierungComboBox, gbc_kodierungComboBox);
		
		JCheckBox chckbxGraykodierung = new JCheckBox("Graykodierung");
		GridBagConstraints gbc_chckbxGraykodierung = new GridBagConstraints();
		gbc_chckbxGraykodierung.gridwidth = 2;
		gbc_chckbxGraykodierung.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxGraykodierung.gridx = 4;
		gbc_chckbxGraykodierung.gridy = 3;
		add(chckbxGraykodierung, gbc_chckbxGraykodierung);
		
		JLabel lblRekombination = new JLabel("Rekombination:");
		GridBagConstraints gbc_lblRekombination = new GridBagConstraints();
		gbc_lblRekombination.insets = new Insets(0, 0, 5, 5);
		gbc_lblRekombination.gridx = 0;
		gbc_lblRekombination.gridy = 4;
		add(lblRekombination, gbc_lblRekombination);
		
		JComboBox rekombinationComboBox = new JComboBox();
		GridBagConstraints gbc_rekombinationComboBox = new GridBagConstraints();
		gbc_rekombinationComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_rekombinationComboBox.gridwidth = 2;
		gbc_rekombinationComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_rekombinationComboBox.gridx = 1;
		gbc_rekombinationComboBox.gridy = 4;
		add(rekombinationComboBox, gbc_rekombinationComboBox);
		
		JLabel lblBitanzahl = new JLabel("Bitanzahl:");
		GridBagConstraints gbc_lblBitanzahl = new GridBagConstraints();
		gbc_lblBitanzahl.insets = new Insets(0, 0, 5, 5);
		gbc_lblBitanzahl.gridx = 4;
		gbc_lblBitanzahl.gridy = 4;
		add(lblBitanzahl, gbc_lblBitanzahl);
		
		JSpinner bitanzahlSpinner = new JSpinner();
		bitanzahlSpinner.setModel(new SpinnerNumberModel(model.getChainLength(), 4, 64, 1));
		GridBagConstraints gbc_bitanzahlSpinner = new GridBagConstraints();
		gbc_bitanzahlSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_bitanzahlSpinner.gridx = 5;
		gbc_bitanzahlSpinner.gridy = 4;
		add(bitanzahlSpinner, gbc_bitanzahlSpinner);
		
		JLabel lblElternselektion = new JLabel("Elternselektion:");
		GridBagConstraints gbc_lblElternselektion = new GridBagConstraints();
		gbc_lblElternselektion.gridwidth = 2;
		gbc_lblElternselektion.insets = new Insets(0, 0, 5, 5);
		gbc_lblElternselektion.gridx = 0;
		gbc_lblElternselektion.gridy = 5;
		add(lblElternselektion, gbc_lblElternselektion);
		
		JComboBox elternSelComboBox = new JComboBox();
		GridBagConstraints gbc_elternSelComboBox = new GridBagConstraints();
		gbc_elternSelComboBox.gridwidth = 3;
		gbc_elternSelComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_elternSelComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_elternSelComboBox.gridx = 2;
		gbc_elternSelComboBox.gridy = 5;
		add(elternSelComboBox, gbc_elternSelComboBox);
		
		JLabel lblVerteilung = new JLabel("Verteilung:");
		GridBagConstraints gbc_lblVerteilung = new GridBagConstraints();
		gbc_lblVerteilung.anchor = GridBagConstraints.EAST;
		gbc_lblVerteilung.insets = new Insets(0, 0, 5, 5);
		gbc_lblVerteilung.gridx = 0;
		gbc_lblVerteilung.gridy = 6;
		add(lblVerteilung, gbc_lblVerteilung);
		
		JComboBox verteilungComboBox = new JComboBox();
		GridBagConstraints gbc_verteilungComboBox = new GridBagConstraints();
		gbc_verteilungComboBox.gridwidth = 2;
		gbc_verteilungComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_verteilungComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_verteilungComboBox.gridx = 1;
		gbc_verteilungComboBox.gridy = 6;
		add(verteilungComboBox, gbc_verteilungComboBox);
		
		JLabel lblMitglieder = new JLabel("Mitglieder:");
		GridBagConstraints gbc_lblMitglieder = new GridBagConstraints();
		gbc_lblMitglieder.insets = new Insets(0, 0, 5, 5);
		gbc_lblMitglieder.gridx = 4;
		gbc_lblMitglieder.gridy = 6;
		add(lblMitglieder, gbc_lblMitglieder);
		
		JSpinner mitgliederSpinner = new JSpinner();
		mitgliederSpinner.setModel(new SpinnerNumberModel(model.memberCount, 4, 20, 1));
		GridBagConstraints gbc_mitgliederSpinner = new GridBagConstraints();
		gbc_mitgliederSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_mitgliederSpinner.gridx = 5;
		gbc_mitgliederSpinner.gridy = 6;
		add(mitgliederSpinner, gbc_mitgliederSpinner);
		
		JLabel lblDetermSelektion = new JLabel("determ. Selektion:");
		GridBagConstraints gbc_lblDetermSelektion = new GridBagConstraints();
		gbc_lblDetermSelektion.gridwidth = 2;
		gbc_lblDetermSelektion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDetermSelektion.gridx = 0;
		gbc_lblDetermSelektion.gridy = 7;
		add(lblDetermSelektion, gbc_lblDetermSelektion);
		
		JComboBox determSelComboBox = new JComboBox();
		GridBagConstraints gbc_determSelComboBox = new GridBagConstraints();
		gbc_determSelComboBox.gridwidth = 2;
		gbc_determSelComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_determSelComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_determSelComboBox.gridx = 2;
		gbc_determSelComboBox.gridy = 7;
		add(determSelComboBox, gbc_determSelComboBox);
		
		JLabel lblFitness = new JLabel("Fitness:");
		GridBagConstraints gbc_lblFitness = new GridBagConstraints();
		gbc_lblFitness.gridwidth = 2;
		gbc_lblFitness.insets = new Insets(0, 0, 5, 5);
		gbc_lblFitness.gridx = 0;
		gbc_lblFitness.gridy = 8;
		add(lblFitness, gbc_lblFitness);
		
		JComboBox fitnessComboBox = new JComboBox();
		GridBagConstraints gbc_fitnessComboBox = new GridBagConstraints();
		gbc_fitnessComboBox.gridwidth = 2;
		gbc_fitnessComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_fitnessComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_fitnessComboBox.gridx = 2;
		gbc_fitnessComboBox.gridy = 8;
		add(fitnessComboBox, gbc_fitnessComboBox);
		
		JButton btnStarten = new JButton("Starten!");
		btnStarten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_btnStarten = new GridBagConstraints();
		gbc_btnStarten.gridwidth = 2;
		gbc_btnStarten.gridx = 4;
		gbc_btnStarten.gridy = 10;
		add(btnStarten, gbc_btnStarten);

	}

}
