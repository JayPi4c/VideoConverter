package com.JayPi4c.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.JayPi4c.utils.Messages;

public class ConsoleView extends JPanel {

	private static final long serialVersionUID = -3616503454679608995L;

	JTextArea textArea;
	JScrollPane scrollPane;

	public ConsoleView(int width, int height) {
		textArea = new JTextArea(Messages.getString("Console.welcome"));
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setBackground(Color.black);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setPreferredSize(new Dimension(width, height));
		add(scrollPane);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void appendln(String line) {
		textArea.append(line);
		textArea.append("\n");
	}

	public void clear() {
		textArea.setText("");
	}
}
