import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.fhflensburg.pd.group007.helper.AutoCompleteValidator;


public class AutoCompleteValidatorTest {
static AutoCompleteValidator iv;
	
	@BeforeClass
    public static void setUp() {
		iv = new AutoCompleteValidator();
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
