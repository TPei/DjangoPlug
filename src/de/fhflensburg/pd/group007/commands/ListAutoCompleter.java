package de.fhflensburg.pd.group007.commands;

import java.util.ArrayList;
import java.util.List;

public class ListAutoCompleter {
	private ArrayList<String> words;
	
	/**
	 * provide a list of words
	 * @param words
	 */
	public ListAutoCompleter(ArrayList<String> words) {
		this.words = words;
	}
	
	/**
	 * returns all words from list that start with the input string
	 * @param input to search for
	 * @return List of Strings matching the input string
	 */
	public List<String> autoCompleteFromList(String input) {
		List<String> entries = new ArrayList<String>();
		
		if(input == null)
			return entries;
		
		for(String word : words) {
			System.out.println(word);
			if(word.startsWith(input))
				entries.add(word);
		}
		
		
		return entries;
	}
}
