import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.fhflensburg.pd.group007.AutoCompleter;
import de.fhflensburg.pd.group007.CommandBuilder;


public class CommandBuilderTest {

	CommandBuilder cb;
	
	@Before
    public void setUp() {
		cb = new CommandBuilder();
    }
	
	@Test
	public void testMakeManageCommand() {
		String cmd = "makemigrations";
		assertEquals(cb.makeManageCommand(cmd), "python3 manage.py " + cmd);
	}
	
	@Test
	public void testMakeServerRunCommand() {
		assertEquals(cb.makeServerRunCommand(), "python3 manage.py runserver");
	}
	
	@Test
	public void testRunTestsCommand() {
		assertEquals(cb.runTestsCommand(), "python3 manage.py test");
		assertEquals(cb.runTestsCommand(""), "python3 manage.py test ");
		assertEquals(cb.runTestsCommand("car"), "python3 manage.py test car");
	}

}
