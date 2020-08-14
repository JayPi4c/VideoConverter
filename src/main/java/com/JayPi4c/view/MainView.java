package com.JayPi4c.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MainView extends JFrame {

	private static final long serialVersionUID = 1L;
	JPanel contentPanel;

	public MainView() {
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

}
