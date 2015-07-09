package de.fhflensburg.pd.group007.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.helper.InputValidator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ManageHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ManageHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// new TextInputWindow().run();
		InputDialog dlg = new InputDialog(
				Display.getCurrent().getActiveShell(), "",
				"Enter manage.py command", "migrate",
				new InputValidator());
		if (dlg.open() == Window.OK) {
			// User clicked OK; update the label with the input
			// make manage command with given parameter
			
			ArrayList<String> commands = CommandBuilder.makeManageCommand(dlg.getValue());

			ConsoleViewHandler.open(commands);

		}

		return null;
	}
}
