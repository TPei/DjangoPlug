package de.fhflensburg.pd.group007.handlers;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;

import de.fhflensburg.pd.group007.Activator;
import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.helper.InputValidator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ServerHandler extends AbstractHandler {
	/**
	 * The constructor.
	 */
	public ServerHandler() {
	}

	/**
	 * the command has been executed, so extract extract the needed information
	 * from the application context.
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// get server preferences (address, port...)
		String addressPort = Activator.getDefault().getPreferenceStore().getString("serverAddress") + ":" + Activator.getDefault().getPreferenceStore().getInt("serverPort");
		
		// make server run command
		ArrayList<String> commands = CommandBuilder.makeServerRunCommand(addressPort);

		ConsoleViewHandler.open(commands);
		return null;
	}
}
