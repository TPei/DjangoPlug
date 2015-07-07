package de.fhflensburg.pd.group007.helper;

import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;

import de.fhflensburg.pd.group007.commands.AutoCompleter;

/**
 * This class validates a String. It makes sure that the String is between 5 and
 * 8 characters
 */
public class InputValidator implements IInputValidator {
	/**
	 * Validates the String. Returns null for no error, or an error message
	 * 
	 * @param newText
	 *            the String to validate
	 * @return String
	 */
	public String isValid(String newText) {
		
		AutoCompleter ac = new AutoCompleter();
		List <String> proposals = ac.autoComplete(newText);
		String prop = "";
		for(String proposal : proposals) {
			prop += proposal + "; ";
		}

		if (proposals.size() == 1 && proposals.get(0).equals(newText) || newText.equals("migrate")) {
			// valid input
			return null;
		}
		else {
			// more than one possible command, or not command not valid
			return prop;
		}
	}

}