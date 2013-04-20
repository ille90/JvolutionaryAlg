package jea;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class XYSeriesChart extends ApplicationFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public XYSeriesChart(String title) {
		
        super(title);
    }
	
	public JPanel getPanel(XYSeries series) {
		
		 final XYSeriesCollection data = new XYSeriesCollection(series);
	        final JFreeChart chart = ChartFactory.createXYLineChart(
	            "Durchschnittliche Fitness einer Generation",
	            "Generation", 
	            "durchschnittliche Fitness", 
	            data,
	            PlotOrientation.VERTICAL,
	            true,
	            true,
	            false
	        );
	        
	        XYPlot plot = chart.getXYPlot();
	        NumberAxis axis = (NumberAxis) plot.getRangeAxis();
	        axis.setRange(30.0, 60.0);

	        final ChartPanel chartPanel = new ChartPanel(chart);
	        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		
		return chartPanel;		
	}

}
