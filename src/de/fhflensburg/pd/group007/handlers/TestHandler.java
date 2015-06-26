package de.fhflensburg.pd.group007.handlers;

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
		CommandBuilder cb = new CommandBuilder();
		ArrayList<String> commands = cb.runTestsCommand();
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(
				window.getShell(),
				"Run Python Tests",
				"In the forseeable future, python tests would now be executed");
		return null;
	}
}
