package com.JayPi4c.controller;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.JayPi4c.utils.ILanguageChangeListener;
import com.JayPi4c.utils.Messages;
import com.JayPi4c.view.ConsoleView;
import com.JayPi4c.view.MainView;
import com.JayPi4c.view.SettingsView;

public class MainController implements ILanguageChangeListener {
	MainView mainView;

	ConsoleView consoleView;
	SettingsView settingsView;

	private String OS;

	String destination = null, target = null, format = "mov";

	String ffmpegpath = "C:\\Users\\ebki3136\\Downloads\\ffmpeg-20200324-e5d25d1-win32-static\\ffmpeg-20200324-e5d25d1-win32-static\\bin";;

	public MainController() {
		OS = System.getProperty("os.name").toLowerCase();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				mainView = new MainView();

				JPanel contentPanel = new JPanel();

				contentPanel.setLayout(new BorderLayout());

				settingsView = new SettingsView(300, 480);
				consoleView = new ConsoleView(300, 480);
				addListeners();
				contentPanel.add(settingsView, BorderLayout.WEST);
				contentPanel.add(consoleView, BorderLayout.EAST);

				mainView.add(contentPanel);

				mainView.pack();
			}

			private void addListeners() {

				settingsView.getFFMPEGButton().addActionListener(e -> {
					JFileChooser filechooser = new JFileChooser();
					filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (filechooser.showOpenDialog(settingsView) == JFileChooser.APPROVE_OPTION) {
						File dir = filechooser.getSelectedFile();
						if (dir.exists() && dir.isDirectory())
							settingsView.getFFMPEGLabel().setText(ffmpegpath = dir.getAbsolutePath());
					}
				});

				settingsView.getDestinationButton().addActionListener(e -> {
					JFileChooser filechooser = new JFileChooser();
					filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (filechooser.showOpenDialog(settingsView) == JFileChooser.APPROVE_OPTION) {
						File dir = filechooser.getSelectedFile();
						if (dir.exists() && dir.isDirectory())
							settingsView.getDestinationLabel().setText(destination = dir.getAbsolutePath());

					}
				});

				settingsView.getTargetButton().addActionListener(e -> {
					JFileChooser filechooser = new JFileChooser();
					filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					if (filechooser.showOpenDialog(settingsView) == JFileChooser.APPROVE_OPTION) {
						File dir = filechooser.getSelectedFile();
						if (dir.exists() && dir.isDirectory())
							settingsView.getTargetLabel().setText(target = dir.getAbsolutePath());
					}
				});

				settingsView.getConvertButton().addActionListener(e -> {
					if (destination == null) {
						consoleView.appendln("No Destination set");
						// return;
					}
					if (target == null) {
						consoleView.appendln("No Target set");
						// return;
					}
					if (format == null) {
						consoleView.appendln("no Format set");
						// return;
					}
					/*
					 * String path; boolean environment; if (!(environment =
					 * isInEnvironmentVariable())) { if ((path = isInFolder()) == null) {
					 * consoleView.getTextArea().append(
					 * "FFMPEG ist nicht installiert oder konnte nicht gefunden werden,\n bitte überprüfen sie die Installation oder installieren sie FFMPEG\n"
					 * ); return; } }
					 */

					// https://mkyong.com/java/how-to-execute-shell-command-from-java/
					/*
					 * try { Process process = Runtime.getRuntime().exec("cmd /c dir C:");
					 * BufferedReader reader = new BufferedReader(new
					 * InputStreamReader(process.getInputStream())); String line; while ((line =
					 * reader.readLine()) != null) { consoleView.getTextArea().append(line + '\n');
					 * } int exitVal = process.waitFor(); if (exitVal == 0) {
					 * System.out.println("Success!");
					 * 
					 * } else { // abnormal... } } catch (IOException e1) { e1.printStackTrace(); }
					 * catch (InterruptedException e1) { // TODO Auto-generated catch block
					 * e1.printStackTrace(); }
					 */

					if (!isWindows()) {
						consoleView.appendln("Linux support is in progress");
						consoleView.appendln("Please use Windows instead!");
						return;
					}

					consoleView.clear();

					// https://www.internalpointers.com/post/convert-vob-files-mkv-ffmpeg

					String videoCodec = "-codec:v libx264 -crf 21";
					String audioCodec = "-codec:a libmp3lame -qscale:a 2";
					if (ffmpegpath == null) {
						// assume ffmpeg path is set:
						try {
							// Just one line and you are done !
							// We have given a command to start cmd
							// /K : Carries out command specified by string
							Runtime.getRuntime()
									.exec("cmd /c start cmd.exe /K \"type " + target + "\\*.VOB | ffmpeg -i - "
											+ videoCodec + " " + audioCodec + " " + destination + "\\result." + format
											+ "\"");

						} catch (Exception exc) {
							exc.printStackTrace();
						}

					} else {

						try {
							// Just one line and you are done !
							// We have given a command to start cmd
							// /K : Carries out command specified by string
							Runtime.getRuntime()
									.exec("cmd /c start cmd.exe /K \"type " + target + "\\*.VOB | " + ffmpegpath
											+ "\\ffmpeg -i - " + videoCodec + " " + audioCodec + " " + destination
											+ "\\result." + format + "\"");

						} catch (Exception exc) {
							exc.printStackTrace();
						}
					}

					/*
					 * new Thread(new Runnable() {
					 * 
					 * @Override public void run() { try {
					 * 
					 * Process process = Runtime.getRuntime().exec( "cat " + target +
					 * "/*.VOB | ffmpeg -i - " + destination + "/result." + format);
					 * 
					 * BufferedReader reader = new BufferedReader( new
					 * InputStreamReader(process.getInputStream())); String line; while ((line =
					 * reader.readLine()) != null) { System.out.println(line);
					 * consoleView.appendln(line); } } catch (IOException e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); } } }).start();
					 */
				});
			}
		});

		Messages.registerListener(this);
	}
	/*
	 * boolean isInEnvironmentVariable() { try { if (isWindows()) { Process process
	 * = Runtime.getRuntime().exec("ffmpeg -version"); if (process.waitFor() == 0)
	 * return true; } else if (isUnix()) { Process process =
	 * Runtime.getRuntime().exec("ffmpeg -version"); if (process.waitFor() == 0)
	 * return true; } } catch (IOException | InterruptedException e) {
	 * e.printStackTrace(); return false; } return false; // other OSs are not
	 * covered yet due to a lack of time }
	 * 
	 * String isInFolder() { if (isWindows()) {
	 * 
	 * } else if (isUnix()) { String path = "/usr/local/bin/ffmpeg"; File f = new
	 * File("/usr/local/bin/ffmpeg"); if (f.exists() && f.isDirectory()) return
	 * path;
	 * 
	 * try { Process process = Runtime.getRuntime().exec("which ffmpeg");
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(process.getInputStream())); while ((path =
	 * reader.readLine()) != null) { File file = new File(path); if (file.exists()
	 * && file.isDirectory()) return path; } } catch (IOException e) {
	 * e.printStackTrace(); } }
	 * 
	 * return null; }
	 */

	@Override
	public void onLanguageChanged() {

	}

	// https://mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/

	private boolean isWindows() {
		return (OS.indexOf("win") >= 0);
	}
	/*
	 * private boolean isUnix() { return (OS.indexOf("nix") >= 0 ||
	 * OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0); }
	 */
	/*
	 * private boolean isMac() {
	 * 
	 * return (OS.indexOf("mac") >= 0);
	 * 
	 * } private boolean isSolaris() {
	 * 
	 * return (OS.indexOf("sunos") >= 0);
	 * 
	 * }
	 */

}
