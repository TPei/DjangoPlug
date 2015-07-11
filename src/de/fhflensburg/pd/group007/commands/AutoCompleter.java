package de.fhflensburg.pd.group007.commands;

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
			//URL fileURL = new URL("platform:/plugin/de.fhflensburg.pd.group007/files/"+filename);
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
	 * or provide file right away
	 * @param file with lines for autocompletion
	 */
	public AutoCompleter(File file) {
		this.file = file;
	}

	/**
	 * returns all lines from file that start with the input string
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
				
				// if line starts with input string, add line to array
				if (lineFromFile.startsWith(input)) {
					entries.add(lineFromFile);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e){
			e.printStackTrace();
		}

		return entries;
	}
}
