package jea.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import jea.alg.Model;
import jea.func.AckleyFunction;
import jea.func.GriewankFunction;
import jea.func.NullstelleFunction;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private int activeSession;
	private ArrayList<SessionPanel> sessions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		activeSession = -1;
		sessions = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnSession = new JMenu("Session");
		menuBar.add(mnSession);
		
		JMenuItem mntmNeuSession = new JMenuItem("Neu");
		mnSession.add(mntmNeuSession);
		
		JMenu mnPopulation = new JMenu("Population");
		menuBar.add(mnPopulation);
		
		final JMenuItem mntmNeuPopulation = new JMenuItem("Neu");
		mntmNeuPopulation.setEnabled(false);
		mnPopulation.add(mntmNeuPopulation);
		
		JSeparator separator = new JSeparator();
		mnPopulation.add(separator);
		
		final JRadioButtonMenuItem rdbtnmntmAckley = new JRadioButtonMenuItem("Ackley");
		mnPopulation.add(rdbtnmntmAckley);
		rdbtnmntmAckley.setSelected(true);
		
		final JRadioButtonMenuItem rdbtnmntmGriewank = new JRadioButtonMenuItem("Griewank");
		mnPopulation.add(rdbtnmntmGriewank);
		
		final JRadioButtonMenuItem rdbtnmntmNullstellen = new JRadioButtonMenuItem("Nullstellen");
		mnPopulation.add(rdbtnmntmNullstellen);
		
		ButtonGroup funktionSel = new ButtonGroup();
		funktionSel.add(rdbtnmntmAckley);
		funktionSel.add(rdbtnmntmGriewank);
		funktionSel.add(rdbtnmntmNullstellen);
		
		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(Box.createHorizontalGlue());
		
		final JComboBox<String> sessionComboBox = new JComboBox<>();
		menuBar.add(sessionComboBox);
		sessionComboBox.setEnabled(false);
		
		sessionComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				contentPane.remove(sessions.get(activeSession));
				activeSession = sessionComboBox.getSelectedIndex();
				contentPane.add(sessions.get(activeSession), BorderLayout.CENTER);
				contentPane.repaint();
			}
		});
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		mntmNeuSession.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(activeSession != -1)
					contentPane.remove(sessions.get(activeSession));
				sessions.add(new SessionPanel());
				activeSession = sessions.size() -1;
				contentPane.add(sessions.get(activeSession), BorderLayout.CENTER);
				sessionComboBox.addItem("Session " + (activeSession + 1));
				sessionComboBox.setSelectedIndex(activeSession);
				mntmNeuPopulation.setEnabled(true);
				sessionComboBox.setEnabled(true);
			}
		});
		
		mntmNeuPopulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Model model;
				if(rdbtnmntmAckley.isSelected())
					model = new Model(new AckleyFunction());
				else if(rdbtnmntmGriewank.isSelected())
					model = new Model(new GriewankFunction());
				else
					model = new Model(new NullstelleFunction());
				sessions.get(activeSession).addPropsPanel(model);
			}
		});
	}

}
