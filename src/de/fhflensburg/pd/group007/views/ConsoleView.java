package de.fhflensburg.pd.group007.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import org.eclipse.ui.part.ViewPart;

public class ConsoleView extends ViewPart {
	private Label label;
	private Text text;
	private Composite textParent;

	public ConsoleView() {
		super();
	}

	public void setFocus() {
		label.setFocus();
	}

	public void createPartControl(Composite parent) {
		this.textParent = parent;
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
		text = new Text(textParent, SWT.READ_ONLY | SWT.MULTI);
	}

}