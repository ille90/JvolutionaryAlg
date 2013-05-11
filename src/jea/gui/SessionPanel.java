package jea.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import java.util.ArrayList;

import jea.alg.EvolutionSingleton;
import jea.alg.Model;
import jea.alg.Population;

public class SessionPanel extends JPanel {

	int populationen;
	JTabbedPane tabbedPane;
	OverviewPanel overviewPanel;
	ArrayList<PropsPanel> props;
	
	Thread workingThread;
	
	/**
	 * Create the panel.
	 */
	public SessionPanel() {
		populationen = 0;
		props = new ArrayList<>();
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		overviewPanel = new OverviewPanel();
		tabbedPane.addTab("Überblick", null, overviewPanel, null);
	}
	
	public void addPropsPanel(Model model) {
		PropsPanel propsPanel = new PropsPanel(populationen, model, this);
		props.add(propsPanel);
		++populationen;
		tabbedPane.addTab("Population " + populationen, null, propsPanel, null);
		tabbedPane.setSelectedComponent(propsPanel);
	}
	
	public boolean startPopulation(int id) {
		if(workingThread != null && workingThread.isAlive()) {
			return false;
		}
		tabbedPane.setSelectedComponent(overviewPanel);
		
		workingThread = new Thread(new PopulationWorker(id));
		workingThread.start();
		return true;
	}
	
	public class PopulationWorker implements Runnable {
		
		int id;
		
		public PopulationWorker(int id) {
			this.id = id;
		}
		
		@Override
		public void run() {
			EvolutionSingleton.getInstance().setModel(props.get(id).model);
			overviewPanel.addPopulation(id, "Population " + (id + 1));
			Population population = new Population();
			population.init();
			overviewPanel.addResult(id, population.getResult());
			while (population.nextGeneration()) {
				overviewPanel.addResult(id, population.getResult());
			}
			overviewPanel.addResult(id, population.getResult());
		}
	}
}
