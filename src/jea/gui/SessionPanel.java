package jea.gui;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import jea.alg.EvolutionSingleton;
import jea.alg.Model;
import jea.alg.Population;

@SuppressWarnings("serial")
public class SessionPanel extends JPanel {

	int populationen;
	JTabbedPane tabbedPane;
	OverviewPanel overviewPanel;
	ArrayList<PopulationPanel> props;
	
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
		PopulationPanel populationPanel = new PopulationPanel(populationen, model, this);
		props.add(populationPanel);
		++populationen;
		tabbedPane.addTab("Population " + populationen, null, populationPanel, null);
		tabbedPane.setSelectedComponent(populationPanel);
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
			PopulationPanel pPanel = props.get(id);
			EvolutionSingleton.getInstance().setModel(pPanel.propsPanel.model);
			overviewPanel.addPopulation(id, "Population " + (id + 1));
			Population population = new Population();
			population.init();
			overviewPanel.addResult(id, population.getResult());
			pPanel.addInfo(population.getResult());
			while (population.nextGeneration()) {
				overviewPanel.addResult(id, population.getResult());
				pPanel.addInfo(population.getResult());
			}
			overviewPanel.addResult(id, population.getResult());
			pPanel.addInfo(population.getResult());
		}
	}
}
