package de.fhflensburg.pd.group007.handlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;

import de.fhflensburg.pd.group007.commands.AutoCompleter;
import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.helper.InputValidator;
import de.fhflensburg.pd.group007.views.ConsoleView;

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
		// TODO: dialog to ask for input from user...
		// new TextInputWindow().run();
		InputDialog dlg = new InputDialog(
				Display.getCurrent().getActiveShell(), "",
				"Enter manage.py command", "command...",
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
