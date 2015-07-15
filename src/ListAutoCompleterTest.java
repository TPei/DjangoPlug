import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.fhflensburg.pd.group007.commands.ListAutoCompleter;


public class ListAutoCompleterTest {
	static ListAutoCompleter ac;
	
	@Test
	public void testAutoCompleteFromList() {
		ArrayList<String> words = new ArrayList<String>();
		words.add("changepassword");
		words.add("makemessages");
		words.add("makemigrations");
		words.add("migrate");
		
		ListAutoCompleter ac = new ListAutoCompleter(words);
		
		List<String> expected = new ArrayList<String>();
		expected.add("makemessages");
		expected.add("makemigrations");
		
		// return length 2
		// Äquivalenzklasse: Eintrag existiert
		assertEquals("should contain makemessages and makemigrations", expected, ac.autoCompleteFromList("ma"));
		assertEquals("should contain two elements", 2, ac.autoCompleteFromList("ma").size());
		
		List<String> expected2 = new ArrayList<String>();
		expected2.add("makemessages");
		expected2.add("makemigrations");
		expected2.add("migrate");
		
		// input length 1
		assertEquals("should contain makemessages, makemigrations and migrate", expected2, ac.autoCompleteFromList("m"));
		assertEquals("should contain three elements", 3, ac.autoCompleteFromList("m").size());
		
		// return length 1
		List<String> expected3 = new ArrayList<String>();
		expected3.add("changepassword");
		assertEquals("should contain changepassword", expected3, ac.autoCompleteFromList("cha"));
		assertEquals("should contain one element", 1, ac.autoCompleteFromList("cha").size());
		
		// return length 0
		// Äquivalenzklasse: Eintrag existiert nicht
		List<String> expected4 = new ArrayList<String>();
		assertEquals("should not have found any commands", expected4, ac.autoCompleteFromList("THIS DOES NOT EXIST"));
		assertEquals("should not have found any commands", 0, ac.autoCompleteFromList("THIS DOES NOT EXIST").size());
		
		// input length 0
		List<String> expected5 = new ArrayList<String>();
		expected5.add("changepassword");
		expected5.add("makemessages");
		expected5.add("makemigrations");
		expected5.add("migrate");
		assertEquals("should return every command", expected5, ac.autoCompleteFromList(""));
		assertEquals("should return every command", 4, ac.autoCompleteFromList("").size());
		
		// ungültige Äquivalenzklasse
		assertEquals("should return an empty list", new ArrayList<String>(), ac.autoCompleteFromList(null));
	}

}
