package de.fhflensburg.pd.group007.handlers;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;

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
		// TODO: get test preferences
		
		// make test run command
		ArrayList<String> commands = CommandBuilder.makeTestRunCommand();
		
		ConsoleViewHandler.open(commands);
		return null;
	}
}
