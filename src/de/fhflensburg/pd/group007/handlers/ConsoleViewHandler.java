package de.fhflensburg.pd.group007.handlers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.fhflensburg.pd.group007.Activator;
import de.fhflensburg.pd.group007.commands.ProcessExecutor;
import de.fhflensburg.pd.group007.views.ConsoleView;

public class ConsoleViewHandler {
	// manage list of active processes
	private static List<ProcessExecutor> activeProccesses = new ArrayList<ProcessExecutor>();
	private static ProcessExecutor process;
	private static String output = "";
	private static String givenCommand = "";
	private static ConsoleView consoleView;
	
	// timeout for console executions
	private static final int TIMEOUT = 500;
	// wait x times 
	private static final int COUNTER_LIMIT = 5;

	/**
	 * starts process
	 * opens console view
	 * pipes process output to console
	 * @param commands to execute in console
	 */
	public static void open(ArrayList<String> commands) {
		// open manage view
		
		// we'll handle errors here because we also want to
		// return the shell errors to the plugin console
		try {
			// get consoleview
			consoleView = (ConsoleView) PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.showView("de.fhflensburg.pd.group007.views.consoleview");

			// make a command string from command array
			// just for prettified output
			givenCommand = "";
			for (String command : commands) {
				givenCommand += command + " ";
			}
			
			// stop all other processes before starting a new process execution
			stopAllProcesses();

			// set info label
			consoleView.addText(givenCommand);
			consoleView.addText("Wird ausgeführt");
			
			// make a process with given command and start that process
			process = new ProcessExecutor(commands, consoleView);
			activeProccesses.add(process);
			process.start();
			
			// async execution to be able to have a timeout
			// because a timeout isn't allowed in the GUI thread
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					int counter = 0;
					
					// wait until process is finished or COUNTER_LIMIT * TIMEOUT => 2.5s
					while(process.getOutput() == null && counter < COUNTER_LIMIT) {
						// have the thread wait for a bit
						try {
							Thread.sleep(TIMEOUT);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						counter++;
					}
					
					// get the process output
					output = process.getOutput();
					
					// since the server keeps the process open we won't get any feedback
					// except errors, so it's safe to assume that the server was started 
					// if we didn't get any output in time
					if(output == null && givenCommand.contains("runserver")) {
						// get server port and address from settings
						String address = Activator.getDefault().getPreferenceStore().getString("serverAddress") + ":" + Activator.getDefault().getPreferenceStore().getInt("serverPort");
						output = "Server wurde gestartet unter " + address;
					}
					
					// pipe all info (command string and output string) to console view
					//consoleView.setLabel(givenCommand + "\n" + output);
					consoleView.addText(output);

					// shouldn't this stop the python server?
					stopAllProcesses();
				}
			});

		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** 
	 * stops all active processes
	 */
	public static void stopAllProcesses() {
		Iterator<ProcessExecutor> it = activeProccesses.iterator();

		while (it.hasNext()) {
			ProcessExecutor proc = it.next();

			proc.stopTheProcess();
			it.remove();
		}
	}
}
