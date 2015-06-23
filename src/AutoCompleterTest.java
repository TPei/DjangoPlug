import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fhflensburg.pd.group007.commands.AutoCompleter;


public class AutoCompleterTest {
	static AutoCompleter ac;
	
	@BeforeClass
    public static void setUp() {
		ac = new AutoCompleter("testCommands.txt");
    }
	
	@Test
	public void testAutoComplete() {
		List<String> expected = new ArrayList<String>();
		expected.add("makemessages");
		expected.add("makemigrations");
		assertEquals("should contain makemessages and makemigrations", ac.autoComplete("ma"), expected);
		assertEquals("should contain two elements", ac.autoComplete("ma").size(), 2);
		
		List<String> expected2 = new ArrayList<String>();
		expected2.add("makemessages");
		expected2.add("makemigrations");
		expected2.add("migrate");
		assertEquals("should contain makemessages, makemigrations and migrate", ac.autoComplete("m"), expected2);
		assertEquals("should contain three elements", ac.autoComplete("m").size(), 3);
		
		List<String> expected3 = new ArrayList<String>();
		expected3.add("changepassword");
		assertEquals("should contain changepassword", ac.autoComplete("cha"), expected3);
		assertEquals("should contain one element", ac.autoComplete("cha").size(), 1);
		
		List<String> expected4 = new ArrayList<String>();
		assertEquals("should not have found any commands", ac.autoComplete("THIS DOES NOT EXIST"), expected4);
		assertEquals("should not have found any commands", ac.autoComplete("THIS DOES NOT EXIST").size(), 0);
	}

}
