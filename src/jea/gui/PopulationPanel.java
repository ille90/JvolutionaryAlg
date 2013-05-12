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
	JPanel infoPanel;
	JTextArea infoText;
	
	JToggleButton tglbtnEinstellungen;
	JToggleButton tglbtnInformation;
	
	GridBagConstraints gbc_panel;
	
	/**
	 * Create the panel.
	 */
	public PopulationPanel(int id, Model newModel, SessionPanel session) {
		rightPanel = new JPanel();
		propsPanel = new PropsPanel(id, newModel, session);
		infoPanel = new JPanel();
		infoText = new JTextArea();
		infoPanel.add(infoText);
		final JScrollPane infoScrollPane = new JScrollPane(infoPanel);
		infoText.setEditable(false);
		//infoScrollPane.add(infoText);
		
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
				tglbtnInformation.setSelected(false);
				rightPanel.remove(0);
				rightPanel.add(propsPanel, BorderLayout.CENTER, 0);
				rightPanel.repaint();
			}
		});
		
		tglbtnInformation.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tglbtnInformation.setSelected(true);
				tglbtnEinstellungen.setSelected(false);
				rightPanel.remove(0);
				rightPanel.add(infoScrollPane, BorderLayout.CENTER, 0);
				rightPanel.repaint();
				infoScrollPane.repaint();
				infoText.repaint();
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

}
