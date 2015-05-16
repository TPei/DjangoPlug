package de.fhflensburg.pd.group007;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

public class AutoCompleter {
	private File file;
	
	/**
	 * per default use the commands.txt file
	 */
	public AutoCompleter() {
		this("commands.txt");
	}
	
	/**
	 * or provide specific file
	 * @param filename file to search in
	 */
	public AutoCompleter(String filename) {
		try {
			Bundle bundle = Platform.getBundle("de.fhflensburg.pd.group007");
			URL fileURL = bundle.getEntry("files/"+ filename);
			file = null;
			file = new File(FileLocator.resolve(fileURL).toURI());
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (NullPointerException exc) {
			// this is necessary when using this class outside the plugin context
			// for example when running tests
			file = new File("files/"+ filename);
		}
	}

	/**
	 * returns all lines from file that contain the input string
	 * @param input to search for
	 * @return List of Strings matching the input string
	 */
	public List<String> autoComplete(String input) {
		List<String> entries = new ArrayList<String>();

		try {
			// scan file for input
			final Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				
				String lineFromFile = scanner.nextLine();
				
				// if line contains input string, add line to array
				// or contains?
				if (lineFromFile.startsWith(input)) {
					entries.add(lineFromFile);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return entries;
	}
}
