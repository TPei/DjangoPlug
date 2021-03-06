package de.fhflensburg.pd.group007.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

public class ConsoleView extends ViewPart {
	private Text text;

	public ConsoleView() {
		super();
	}

	public void setFocus() {
		text.setFocus();
	}

	public void createPartControl(Composite parent) {
		text = new Text(parent, SWT.READ_ONLY | SWT.MULTI);
	}
	
	/**
	 * Adds new line to console
	 * @param {String} text
	 */
	public void addText(String text) {
		this.text.append("\n");
		this.text.append(text);
	}
	
	/**
	 * resets text
	 */
	public void resetText() {
		text.setText("");
	}

}