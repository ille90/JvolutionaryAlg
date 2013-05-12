package jea.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import jea.alg.Result;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.SeriesDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

@SuppressWarnings("serial")
public class OverviewPanel extends JPanel {

	SeriesDataset bestInd;
	JFreeChart bestIndividChart;
	ArrayList<XYSeries> bestFitness;
	XYSeriesCollection bestFitnessColl;
	
	JFreeChart worstIndividChart;
	ArrayList<XYSeries> worstFitness;
	XYSeriesCollection worstFitnessColl;
	
	JPanel rightPanel;

	/**
	 * Create the panel.
	 */
	public OverviewPanel() {
		rightPanel = new JPanel();
		bestFitness = new ArrayList<>();
		bestFitnessColl = new XYSeriesCollection();
		
		worstFitness = new ArrayList<>();
		worstFitnessColl = new XYSeriesCollection();
		
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
		
		final JToggleButton btnbestInd = new JToggleButton("Beste Individuen");
		btnbestInd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnbestInd.setSelected(true);
		leftPanel.add(btnbestInd, cons);

		final JToggleButton btnWorstInd = new JToggleButton("Schlechteste Individuen");
		btnWorstInd.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnWorstInd.setSelected(false);
		leftPanel.add(btnWorstInd, cons);

		XYLineAndShapeRenderer dot = new XYLineAndShapeRenderer();
		NumberAxis xax = new NumberAxis("Generation");
		NumberAxis yax = new NumberAxis("Fitness");
		
		XYPlot bestFitnessPlot = new XYPlot(bestFitnessColl, xax, yax, dot);
		bestIndividChart = new JFreeChart(bestFitnessPlot);
		
		XYPlot worstFitnessPlot = new XYPlot(worstFitnessColl, xax, yax, dot);
		worstIndividChart = new JFreeChart(worstFitnessPlot);

		final ChartPanel chartPanel = new ChartPanel(bestIndividChart);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		//add(chartPanel, gbc_panel);
		add(rightPanel, gbc_panel);
		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(chartPanel, BorderLayout.CENTER, 0);
		
		btnbestInd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnWorstInd.setSelected(false);
				btnbestInd.setSelected(true);
				chartPanel.setChart(bestIndividChart);
			}
		});
		
		btnWorstInd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnbestInd.setSelected(false);
				btnWorstInd.setSelected(true);
				chartPanel.setChart(worstIndividChart);
			}
		});
	}
	
	public void addPopulation(int id, String name) {
		XYSeries bestIndSeries = new XYSeries(name);
		bestFitness.add(id, bestIndSeries);
		bestFitnessColl.addSeries(bestIndSeries);

		XYSeries worstIndSeries = new XYSeries(name);
		worstFitness.add(id, worstIndSeries);
		worstFitnessColl.addSeries(worstIndSeries);
	}
	
	public void addResult(int id, Result result) {
		bestFitness.get(id).add(result.generation, result.bestFitness);
		worstFitness.get(id).add(result.generation, result.worstFitness);
	}
}
