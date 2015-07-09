package de.fhflensburg.pd.group007.commands;

import java.util.ArrayList;

import de.fhflensburg.pd.group007.Activator;
import de.fhflensburg.pd.group007.helper.PropertyLoader;

public class CommandBuilder {
	private final static String MANAGE_COMMAND = "manage.py";
	
	/**
	 * creates console command for executing a manage.py command
	 * @param cmd command to run from manage.py
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py makemigrations
	 */
	public static ArrayList <String> makeManageCommand(String cmd) {
		ArrayList <String> commands = new ArrayList<String>();
		try {
			commands.add(Activator.getDefault().getPreferenceStore().getString("pythonDir"));
		}
		catch (NullPointerException exc) {}
				
		commands.add(MANAGE_COMMAND);
		
		if (!cmd.equals("")) {
			String[] input = cmd.split(" ");
			for (String in : input) {
				commands.add(in);
			}
		}			
		
		return commands;
	}
	
	/**
	 * creates a runserver command
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py runserver address:port
	 */
	public static ArrayList <String> makeServerRunCommand() {
		
		ArrayList<String> commands = makeManageCommand("runserver");
		// get preference Data from plugin and append address:port to command
		try {
			commands.add(Activator.getDefault().getPreferenceStore().getString("serverAddress") + ":" + Activator.getDefault().getPreferenceStore().getInt("serverPort"));
		}
		catch (NullPointerException exc) {}
		
		return commands;
	}
	
	/**
	 * creates a runserver command
	 * @param {String} address - to start server on
	 * @param {int} port - to start server on
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py runserver address:port
	 */
	public static ArrayList<String> makeServerRunCommand(String address, int port) {
		ArrayList <String> commands = makeManageCommand("runserver");

		// allowed ports for user usage are 1024 to 49151
		if(port >= 1024 && port <= 49151)
			commands.add(address + ":" + port);
		else
			commands.add(address + ":" + 8000);
		return commands;
	}
	
	/**
	 * creates a runserver command
	 * @param {String} addressPort - address:port to start server on
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py runserver address:port
	 */
	public static ArrayList<String> makeServerRunCommand(String addressPort) {
		ArrayList <String> commands = makeManageCommand("runserver");
		
		if (!addressPort.equals("")) {
			String[] addressAndPort = addressPort.split(":");
			
			// only add if there are at least two parts
			if(addressAndPort.length == 2) {
				try {
					int port = Integer.parseInt(addressAndPort[1]);
					// allowed ports for user usage are 1024 to 49151
					if(port >= 1024 && port <= 49151)
						commands.add(addressPort);
					else // invalid port, use port 8000
						commands.add(addressAndPort[0] + ":" + 8000);
				}
				catch (NumberFormatException exc) {}
			}
			
		}
			
		return commands;
	}
	
	/**
	 * creates a runserver command
	 * @param {int} port - to start server on
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py runserver address:port
	 */
	public static ArrayList<String> makeServerRunCommand(int port) {
		return makeServerRunCommand("0.0.0.0", port);
	}
	
	/**
	 * creates a run test command
	 * @param whatToTest class or class.method to test
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py test car.getWindows
	 */
	public static ArrayList <String> makeTestRunCommand(String whatToTest) {
		ArrayList <String> commands = makeManageCommand("test");
		
		if (!whatToTest.equals(""))
			commands.add(whatToTest);
		
		return commands;
	}
	
	/**
	 * creates a run test command
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py test
	 */
	public static ArrayList <String> makeTestRunCommand() {
		ArrayList<String> commands =  makeManageCommand("test");
		
		try {
			commands.add(Activator.getDefault().getPreferenceStore().getString("testArgs"));
		}
		catch (NullPointerException exc) {}
		
		
		return commands;
	}
}
