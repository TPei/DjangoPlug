package de.fhflensburg.pd.group007.commands;

import de.fhflensburg.pd.group007.helper.PropertyLoader;

public class CommandBuilder {
	private final String PYTHON_ENVIRONMENT = PropertyLoader.getProperty("python_environment");
	
	/**
	 * creates console command for executing a manage.py command
	 * @param cmd command to run from manage.py
	 * @return String command for console
	 * -> e.g. python3 manage.py makemigrations
	 */
	public String makeManageCommand(String cmd) {
		return PYTHON_ENVIRONMENT + " manage.py " + cmd;
	}
	
	/**
	 * creates a runserver command
	 * @return String command for console
	 * -> e.g. python3 manage.py runserver address:port
	 */
	public String makeServerRunCommand() {
		// TODO: get preference Data from plugin and append address:port to command
		return makeManageCommand("runserver");
	}
	
	/**
	 * creates a run test command
	 * @param whatToTest class or class.method to test
	 * @return String command for console
	 * -> e.g. python3 manage.py test car.getWindows
	 */
	public String runTestsCommand(String whatToTest) {
		return makeManageCommand("test") + " " + whatToTest;
	}
	
	/**
	 * creates a run test command
	 * @return String command for console
	 * -> e.g. python3 manage.py test
	 */
	public String runTestsCommand() {
		return makeManageCommand("test");
	}
}
