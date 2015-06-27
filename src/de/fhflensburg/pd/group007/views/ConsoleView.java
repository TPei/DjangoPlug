package de.fhflensburg.pd.group007.views;

import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

public class ConsoleView extends ViewPart {
	private Label label;
	private Text text;

	public ConsoleView() {
		super();
	}

	public void setFocus() {
		text.setFocus();
	}

	public void createPartControl(Composite parent) {
		label = new Label(parent, 0);
		label.setText("Console View");
	}
	
	public void setLabel(String text) {
		label.setText(text);
	}

}