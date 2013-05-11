package jea.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.data.general.SeriesDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class OverviewPanel extends JPanel {

	SeriesDataset bestInd;
	JFreeChart bestIndividChart;

	/**
	 * Create the panel.
	 */
	public OverviewPanel() {
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

		XYSeries series1 = new XYSeries("Punkte1");
		series1.add(0, 0);
		series1.add(1, 1);
		series1.add(2, 1);
		series1.add(3, 2);

		XYSeries series2 = new XYSeries("Punkte2");
		series2.add(1, 2);
		series2.add(2, 3);
		series2.add(3, 4);

		// Hinzufuegen von series1 und series2 zu der Datenmenge dataset
		XYSeriesCollection dataset2 = new XYSeriesCollection();
		dataset2.addSeries(series1);
		dataset2.addSeries(series2);
		XYDotRenderer dot = new XYDotRenderer();
		dot.setDotHeight(5);
		dot.setDotWidth(5);
		NumberAxis xax = new NumberAxis("x");
		NumberAxis yax = new NumberAxis("y");
		XYPlot plot = new XYPlot(dataset2, xax, yax, dot);
		bestIndividChart = new JFreeChart(plot);

		ChartPanel chartPanel = new ChartPanel(bestIndividChart);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 0;
		add(chartPanel, gbc_panel);
	}

}
