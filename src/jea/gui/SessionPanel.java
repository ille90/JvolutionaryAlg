package jea.gui;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;

import jea.alg.Model;

public class SessionPanel extends JPanel {

	int populationen;
	JTabbedPane tabbedPane;
	
	/**
	 * Create the panel.
	 */
	public SessionPanel() {
		populationen = 0;
		setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);
		
		OverviewPanel overviewPanel = new OverviewPanel();
		tabbedPane.addTab("Überblick", null, overviewPanel, null);
	}
	
	public void addPropsPanel(Model model) {
		++populationen;
		
		PropsPanel propsPanel = new PropsPanel(model);
		tabbedPane.addTab("Population " + populationen, null, propsPanel, null);
	}

}
