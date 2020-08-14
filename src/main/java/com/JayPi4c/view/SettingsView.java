package com.JayPi4c.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.JayPi4c.utils.Messages;

public class SettingsView extends JPanel {

	private static final long serialVersionUID = -3204918135300995614L;
	JButton convert;
	JButton chooseTarget;
	JButton chooseDestination;
	JLabel targetFolder;
	JLabel destinationFolder;
	JLabel FFMPEGPath;
	JButton chooseFFMPEG;

	TitledBorder destinationBorder, targetBorder, ffmpegBorder;

	public SettingsView(int width, int height) {
		setPreferredSize(new Dimension(width, height));

		JPanel ffmpegPanel = new JPanel();
		ffmpegBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
				Messages.getString("Settings.path"));
		ffmpegPanel.setBorder(ffmpegBorder);
		ffmpegPanel.setLayout(new BorderLayout());
		chooseFFMPEG = new JButton(Messages.getString("Settings.path"));
		FFMPEGPath = new JLabel("...");
		FFMPEGPath.setHorizontalAlignment(JLabel.CENTER);
		FFMPEGPath.setVerticalAlignment(JLabel.CENTER);
		ffmpegPanel.add(chooseFFMPEG, BorderLayout.WEST);
		ffmpegPanel.add(FFMPEGPath, BorderLayout.CENTER);

		JPanel targetPanel = new JPanel();
		targetBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
				Messages.getString("Settings.target"));
		targetPanel.setBorder(targetBorder);
		targetPanel.setLayout(new BorderLayout());
		chooseTarget = new JButton(Messages.getString("Settings.target"));
		targetFolder = new JLabel("...");
		targetFolder.setHorizontalAlignment(JLabel.CENTER);
		targetFolder.setVerticalAlignment(JLabel.CENTER);
		targetPanel.add(chooseTarget, BorderLayout.WEST);
		targetPanel.add(targetFolder, BorderLayout.CENTER);

		JPanel destinationPanel = new JPanel();
		destinationBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.DARK_GRAY),
				Messages.getString("Settings.destination"));
		destinationPanel.setBorder(destinationBorder);
		destinationPanel.setLayout(new BorderLayout());
		chooseDestination = new JButton(Messages.getString("Settings.destination"));
		destinationFolder = new JLabel("...");
		destinationFolder.setHorizontalAlignment(JLabel.CENTER);
		destinationFolder.setVerticalAlignment(JLabel.CENTER);
		destinationPanel.add(chooseDestination, BorderLayout.WEST);
		destinationPanel.add(destinationFolder, BorderLayout.CENTER);

		convert = new JButton(Messages.getString("Settings.convert"));

		RelativeLayout rl = new RelativeLayout(RelativeLayout.Y_AXIS);
		rl.setFill(true);
		setLayout(rl);
		add(ffmpegPanel);
		add(targetPanel);
		add(destinationPanel);
		add(convert);
	}

	public JButton getConvertButton() {
		return convert;
	}

	public JButton getDestinationButton() {
		return chooseDestination;
	}

	public JButton getTargetButton() {
		return chooseTarget;
	}

	public JLabel getDestinationLabel() {
		return destinationFolder;
	}

	public JLabel getTargetLabel() {
		return targetFolder;
	}

	public JLabel getFFMPEGLabel() {
		return FFMPEGPath;
	}

	public JButton getFFMPEGButton() {
		return chooseFFMPEG;
	}

}
