import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import de.fhflensburg.pd.group007.commands.ListAutoCompleter;
import de.fhflensburg.pd.group007.helper.AutoCompleteValidator;


public class AutoCompleteValidatorTest {
static AutoCompleteValidator iv;
	
	@BeforeClass
    public static void setUp() {
		ArrayList<String> autoCompleteList = new ArrayList<String>();
		autoCompleteList.add("check");
		autoCompleteList.add("compilemessages");
		autoCompleteList.add("createcachetable");
		autoCompleteList.add("dbshell");
		autoCompleteList.add("diffsettings");
		autoCompleteList.add("dumpdata");
		autoCompleteList.add("flush");
		autoCompleteList.add("help");
		autoCompleteList.add("inspectdb");
		autoCompleteList.add("loaddata");
		autoCompleteList.add("makemessages");
		autoCompleteList.add("makemigrations [<app label>]");
		autoCompleteList.add("migrate [<app label> [<migrationname>]]");
		autoCompleteList.add("runfcgi");
		autoCompleteList.add("runserver [port | address:port]");
		autoCompleteList.add("shell");
		autoCompleteList.add("showmigrations");
		autoCompleteList.add("sql");
		autoCompleteList.add("sqlall");
		autoCompleteList.add("sqlclear");
		autoCompleteList.add("sqlcustom");
		autoCompleteList.add("sqldropindexes");
		autoCompleteList.add("sqlflush");
		autoCompleteList.add("sqlindexes");
		autoCompleteList.add("sqlmigrate");
		autoCompleteList.add("sqlsequencereset");
		autoCompleteList.add("squashmigrations");
		autoCompleteList.add("startapp");
		autoCompleteList.add("startproject");
		autoCompleteList.add("syncdb");
		autoCompleteList.add("test");
		autoCompleteList.add("testserver");
		autoCompleteList.add("validate");
		autoCompleteList.add("startproject");
		autoCompleteList.add("startproject");
		iv = new AutoCompleteValidator(new ListAutoCompleter(autoCompleteList));
    }
	
	@Test
	public void testValidation() {
		// there is a total of 8 return value set positions
		
		//assertEquals("description", expected, actual);
		
		// case 1
		assertNull("input is equal to exactly one command, therefore null is returned", iv.isValid("makemessages"));
		
		// case 2
		assertNull("input string has additional arguments, command supports it, null is returned", iv.isValid("makemigrations additionalArg"));
		
		// case 3
		assertEquals("input is part of input with additionalArgs", "makemigrations [<app label>]; ", iv.isValid("makemigratio"));
		
		// case 4 - seems to be unreachable code. no test possible.
		
		// case 5
		assertTrue("input matches one command, therefore a string containing that command should be returned", iv.isValid("makemessage").equals("makemessages; "));
		
		// case 6
		assertEquals("input string has additional arguments, but command doesn't support that", "no matching commands", iv.isValid("makemessages additionalArg"));
		
		// case 7
		assertEquals("input doesn't exist in commands, should return: no matching commands", "no matching commands", iv.isValid("this doesn't exist"));
		
		// case 8
		assertTrue("input matches multiple commands, therefore a string containing these commands should be returned", iv.isValid("make").contains("makemessages") && iv.isValid("make").contains("makemigrations"));
		
	}
}
