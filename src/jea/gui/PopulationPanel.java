package jea.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

import jea.alg.Model;
import jea.alg.Result;

@SuppressWarnings("serial")
public class PopulationPanel extends JPanel {

	JPanel rightPanel;
	PropsPanel propsPanel;
	
	JTextArea resultText;
	
	JTextArea infoText;
	
	JToggleButton tglbtnEinstellungen;
	JToggleButton tglbtnErgebniss;
	JToggleButton tglbtnInformation;
	
	GridBagConstraints gbc_panel;
	
	/**
	 * Create the panel.
	 */
	public PopulationPanel(int id, Model newModel, SessionPanel session) {
		rightPanel = new JPanel();
		propsPanel = new PropsPanel(id, newModel, session);
		infoText = new JTextArea();
		final JScrollPane infoScrollPane = new JScrollPane(infoText);
		infoText.setEditable(false);
		
		resultText = new JTextArea();
		final JScrollPane resultScrollPane = new JScrollPane(resultText);
		resultText.setEditable(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 200, 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0 };
		setLayout(gridBagLayout);

		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		add(scrollPane, gbc_scrollPane);
		
		JPanel leftPanel = new JPanel();
		scrollPane.setColumnHeaderView(leftPanel);
		leftPanel.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		cons.weightx = 1;
		cons.gridx = 0;
		
		tglbtnEinstellungen = new JToggleButton("Einstellungen");
		tglbtnEinstellungen.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanel.add(tglbtnEinstellungen, cons);
		tglbtnEinstellungen.setSelected(true);
		
		tglbtnErgebniss = new JToggleButton("Ergebniss");
		tglbtnErgebniss.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanel.add(tglbtnErgebniss, cons);
		
		tglbtnInformation = new JToggleButton("Informationen");
		tglbtnInformation.setAlignmentX(Component.CENTER_ALIGNMENT);
		leftPanel.add(tglbtnInformation, cons);
		
		gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(rightPanel, gbc_panel);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(propsPanel, BorderLayout.CENTER, 0);
		
		tglbtnEinstellungen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnEinstellungen.setSelected(true);
				tglbtnErgebniss.setSelected(false);
				tglbtnInformation.setSelected(false);
				rightPanel.remove(0);
				rightPanel.add(propsPanel, BorderLayout.CENTER, 0);
				rightPanel.repaint();
			}
		});
		
		tglbtnErgebniss.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tglbtnErgebniss.setSelected(true);
				tglbtnEinstellungen.setSelected(false);
				tglbtnInformation.setSelected(false);
				rightPanel.remove(0);
				rightPanel.add(resultScrollPane, BorderLayout.CENTER, 0);
				rightPanel.repaint();
			}
		});
		
		tglbtnInformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnInformation.setSelected(true);
				tglbtnEinstellungen.setSelected(false);
				tglbtnErgebniss.setSelected(false);
				rightPanel.remove(0);
				rightPanel.add(infoScrollPane, BorderLayout.CENTER, 0);
				rightPanel.repaint();
			}
		});
	}
	
	public void addInfo(Result result) {
		infoText.append("Generation " + result.generation + ":\n");
		infoText.append("Bestes Individuum:\n");
		infoText.append(result.bestPermutation);
		infoText.append("\nDurchschnitt: " + result.averageFitness + "\n\n");
		infoText.repaint();
	}
	
	public void setResult(Result result) {
		resultText.setText("Bestes Individuum nach " + result.generation + " Generationen:\n\n" + result.bestPermutation);
	}
}
