package de.fhflensburg.pd.group007.handlers;

import java.util.ArrayList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;

import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.commands.ListAutoCompleter;
import de.fhflensburg.pd.group007.helper.AutoCompleteValidator;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class ManageHandler extends AbstractHandler implements DjangoHandler {
	ArrayList<String> autoCompleteList;
	/**
	 * The constructor.
	 */
	public ManageHandler() {
		this.autoCompleteList = new ArrayList<String>();
		autoCompleteList.add("check");
		autoCompleteList.add("compilemessages");
		autoCompleteList.add("createcachetable");
		autoCompleteList.add("dbshell");
		autoCompleteList.add("diffsettings");
		autoCompleteList.add("dumpdata");
		autoCompleteList.add("flush");
		autoCompleteList.add("help");
		autoCompleteList.add("inspectdb");
		autoCompleteList.add("loaddata");
		autoCompleteList.add("makemessages");
		autoCompleteList.add("makemigrations [<app label>]");
		autoCompleteList.add("migrate [<app label> [<migrationname>]]");
		autoCompleteList.add("runfcgi");
		autoCompleteList.add("runserver [port | address:port]");
		autoCompleteList.add("shell");
		autoCompleteList.add("showmigrations");
		autoCompleteList.add("sql");
		autoCompleteList.add("sqlall");
		autoCompleteList.add("sqlclear");
		autoCompleteList.add("sqlcustom");
		autoCompleteList.add("sqldropindexes");
		autoCompleteList.add("sqlflush");
		autoCompleteList.add("sqlindexes");
		autoCompleteList.add("sqlmigrate");
		autoCompleteList.add("sqlsequencereset");
		autoCompleteList.add("squashmigrations");
		autoCompleteList.add("startapp");
		autoCompleteList.add("startproject");
		autoCompleteList.add("syncdb");
		autoCompleteList.add("test");
		autoCompleteList.add("testserver");
		autoCompleteList.add("validate");
		autoCompleteList.add("startproject");
		autoCompleteList.add("startproject");
		
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
				new AutoCompleteValidator(new ListAutoCompleter(autoCompleteList)));
		if (dlg.open() == Window.OK) {
			// User clicked OK; update the label with the input
			// make manage command with given parameter
			
			ArrayList<String> commands = CommandBuilder.makeManageCommand(dlg.getValue());

			pipeToConsole(commands);

		}

		return null;
	}

	@Override
	public void pipeToConsole(ArrayList<String> commands) {
		ConsoleViewHandler.open(commands);
	}
}
