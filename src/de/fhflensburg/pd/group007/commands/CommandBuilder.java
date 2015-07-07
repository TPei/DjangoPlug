package de.fhflensburg.pd.group007.commands;

import java.util.ArrayList;

import de.fhflensburg.pd.group007.helper.PropertyLoader;

public class CommandBuilder {
	private final static String PYTHON_ENVIRONMENT = "C:\\python34\\python"; //PropertyLoader.getProperty("windows_path");
	private final static String MANAGE_COMMAND = "manage.py";
	
	/**
	 * creates console command for executing a manage.py command
	 * @param cmd command to run from manage.py
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py makemigrations
	 */
	public static ArrayList <String> makeManageCommand(String cmd) {
		ArrayList <String> commands = new ArrayList<String>();
		commands.add(PYTHON_ENVIRONMENT);
		commands.add(MANAGE_COMMAND);
		
		if (cmd != "") {
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
		// TODO: get preference Data from plugin and append address:port to command
		return makeManageCommand("runserver");
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
		commands.add(address + ":" + port);
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
		commands.add(addressPort);
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
		
		if (whatToTest != "")
			commands.add(whatToTest);
		
		return commands;
	}
	
	/**
	 * creates a run test command
	 * @return {ArrayList<String>} command for console
	 * -> e.g. python3 manage.py test
	 */
	public static ArrayList <String> makeTestRunCommand() {
		return makeManageCommand("test");
	}
}
