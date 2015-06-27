package de.fhflensburg.pd.group007.handlers;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import de.fhflensburg.pd.group007.views.ConsoleView;

public class ConsoleViewHandler {
	public static void open(String command, String feedback) {
		// open manage view
		ConsoleView cv;
		try {
			cv = (ConsoleView) PlatformUI
					.getWorkbench()
					.getActiveWorkbenchWindow()
					.getActivePage()
					.showView(
							"de.fhflensburg.pd.group007.views.consoleview");
			
			// make output for "console"
			// showing given command and returnes console feedback
			
			cv.setLabel(command + "\n" + feedback);
		} catch (PartInitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
