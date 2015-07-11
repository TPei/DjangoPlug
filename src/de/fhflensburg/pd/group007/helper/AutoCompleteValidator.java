package de.fhflensburg.pd.group007.helper;

import java.util.List;

import org.eclipse.jface.dialogs.IInputValidator;

import de.fhflensburg.pd.group007.commands.AutoCompleter;

/**
 * This class validates a String. It makes sure that the String is between 5 and
 * 8 characters
 */
public class AutoCompleteValidator implements IInputValidator {
	/**
	 * Validates the String. Returns null for no error, or an error message
	 * 
	 * @param newText
	 *            the String to validate
	 * @return String
	 */
	public String isValid(String newText) {
		AutoCompleter ac = new AutoCompleter();
		List <String> proposals = ac.autoComplete(newText.split(" ")[0]);
		String prop = "";
		for(String proposal : proposals) {
			prop += proposal + "; ";
		}
		
		// special case for words with arguments
		// should accept input if first entered word equals first command word from word with arguments
		int size = proposals.size();
		String suggestions = null;
		
		// valid command in list of returned commands
		// e.g. sql when there's also sqlall, but also sqlall when there are no others
		if (proposals.contains(newText)) 
			suggestions = null;
		
		// one is returned, but doesn't outright match the input
		else if (size == 1)
			// that one has arguments
			if(proposals.get(0).split(" ").length > 1)
				// the one mathes, arguments are ignored
				if (proposals.get(0).split(" ")[0].equals(newText.split(" ")[0]))
					suggestions = null;
				// the one doesn't completely match, but is a substring obviously
				else 
					suggestions = prop;
			// the one doesn't have arguments
			else 
				// it matches, is valid (this can't happen because of the very first if right?)
				// TODO: cleanup
				if(proposals.get(0).equals(newText))
					suggestions = null;
				// it's a substring
				else if (proposals.get(0).startsWith(newText))
					suggestions = prop;
				// the first word matched, but additional commands where added
				// but aren't supported
				else
					suggestions = "no matching commands";
		// nothing was found
		else if (size == 0)
			suggestions = "no matching commands";
		// more than one possible command
		else {
			suggestions = prop;
		}
		
		return suggestions;
	}

}