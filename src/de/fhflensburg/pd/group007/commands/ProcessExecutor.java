package de.fhflensburg.pd.group007.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import de.fhflensburg.pd.group007.Activator;
import de.fhflensburg.pd.group007.views.ConsoleView;

public class ProcessExecutor extends Thread {
	private volatile Process process = null;
	private ArrayList<String> commands;
	private final static String DJANGO_PATH = Activator.getDefault().getPreferenceStore().getString("djangoDir");
	private String output = null;

	public ProcessExecutor(ArrayList<String> commands) {
		this.commands = commands; 
	}

	public void stopTheProcess() {
		if (process != null) {
			process.destroy();
		}
	}
	
	public String getOutput() {
		return this.output;
	}

	public void run() {
		StringBuilder out = new StringBuilder();
		try {

			// Run macro on target
			ProcessBuilder pb = new ProcessBuilder(commands);
			pb.directory(new File(DJANGO_PATH));
			pb.redirectErrorStream(true);
			process = pb.start();

			// Read output
			BufferedReader br = new BufferedReader(
					new InputStreamReader(process.getInputStream()));
			String line = null, previous = null;
			while ((line = br.readLine()) != null) {
				if (!line.equals(previous)) {
					previous = line;
					out.append(line).append('\n');
				}
			}
			
			this.output = out.toString();

		} catch (Exception e) {
			if(output == null)
				output = e.getMessage();
			else
				this.output += e.getMessage();
			
			stopTheProcess();
		}
	}
}