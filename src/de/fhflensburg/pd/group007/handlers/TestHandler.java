package de.fhflensburg.pd.group007.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import de.fhflensburg.pd.group007.Activator;
import de.fhflensburg.pd.group007.commands.CommandBuilder;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class TestHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public TestHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// get test preferences
		String testArgs = Activator.getDefault().getPreferenceStore().getString("testArgs");
		
		// make test run command
		ArrayList<String> commands = CommandBuilder.makeTestRunCommand(testArgs);
		
		ConsoleViewHandler.open(commands);
		return null;
	}
}
