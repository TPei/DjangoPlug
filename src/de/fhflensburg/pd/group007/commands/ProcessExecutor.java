package de.fhflensburg.pd.group007.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProcessExecutor extends Thread {
	private volatile int id;
	private String message;
	private volatile Process process = null;
	private ArrayList<String> commands;
	private final static String DJANGO_PATH = "C:\\Users\\Thomas Peikert\\djangoproject";
	String[] cmd = new String[4];

	public ProcessExecutor(int id, String message, ArrayList<String> commands) {
		this.id = id;
		this.message = message;
		this.commands = commands;
	}

	public int getProcessId() {
		return id;
	}

	public void stopTheProcess() {
		if (process != null) {
			process.destroy();
		}
	}
	
	public String getOutput() {
		return cmd[3];
	}

	public void run() {
		StringBuilder out = new StringBuilder();
		try {
			cmd[0] = "path to some app";
			cmd[1] = id + "";
			cmd[2] = message;

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
			
			cmd[3] = out.toString();

		} catch (Exception e) {
			cmd[3] += e.getMessage();
			stopTheProcess();
		}
	}
}