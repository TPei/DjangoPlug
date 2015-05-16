import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.fhflensburg.pd.group007.AutoCompleter;


public class AutoCompleterTest {
	AutoCompleter ac;
	
	@Before
    public void setUp() {
		ac = new AutoCompleter("testCommands.txt");
    }
	
	@Test
	public void testAutoComplete() {
		fail("Not yet implemented");
	
		List<String> expected = new ArrayList<String>();
		expected.add("makemessages");
		expected.add("makemigrations");
		assertEquals(ac.autoComplete("ma"), expected);
		assertEquals(ac.autoComplete("ma").size(), 2);
		
		List<String> expected2 = new ArrayList<String>();
		expected.add("makemessages");
		expected.add("makemigrations");
		expected.add("migrate");
		assertEquals(ac.autoComplete("m"), expected2);
		assertEquals(ac.autoComplete("m").size(), 3);
		
		List<String> expected3 = new ArrayList<String>();
		expected.add("changepassword");
		assertEquals(ac.autoComplete("cha"), expected3);
		assertEquals(ac.autoComplete("cha").size(), 1);
		
		List<String> expected4 = new ArrayList<String>();
		assertEquals(ac.autoComplete("THIS DOES NOT EXIST"), expected4);
		assertEquals(ac.autoComplete("THIS DOES NOT EXIST").size(), 0);
	}

}
