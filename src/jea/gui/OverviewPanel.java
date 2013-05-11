package jea.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import jea.alg.Result;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.general.SeriesDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OverviewPanel extends JPanel {

	SeriesDataset bestInd;
	JFreeChart bestIndividChart;
	ArrayList<XYSeries> bestFitness;
	XYSeriesCollection bestFitnessColl;

	/**
	 * Create the panel.
	 */
	public OverviewPanel() {
		bestFitness = new ArrayList<>();
		bestFitnessColl = new XYSeriesCollection();
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 150, 0 };
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

		JToggleButton btnbestInd = new JToggleButton("Beste Individuen");
		btnbestInd.setSelected(true);
		scrollPane.setColumnHeaderView(btnbestInd);

		XYLineAndShapeRenderer dot = new XYLineAndShapeRenderer();
		NumberAxis xax = new NumberAxis("Generation");
		NumberAxis yax = new NumberAxis("Fitness");
		XYPlot plot = new XYPlot(bestFitnessColl, xax, yax, dot);
		bestIndividChart = new JFreeChart(plot);

		ChartPanel chartPanel = new ChartPanel(bestIndividChart);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(chartPanel, gbc_panel);
	}
	
	public void addPopulation(int id, String name) {
		XYSeries newSeries = new XYSeries(name);
		bestFitness.add(id, newSeries);
		bestFitnessColl.addSeries(newSeries);
	}
	
	public void addResult(int id, Result result) {
		bestFitness.get(id).add(result.generation, result.bestFitness);
	}
}
