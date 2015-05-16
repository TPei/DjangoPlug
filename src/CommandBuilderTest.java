import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.fhflensburg.pd.group007.commands.AutoCompleter;
import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.helper.PropertyLoader;


public class CommandBuilderTest {

	CommandBuilder cb;
	private final String PYTHON_ENVIRONMENT = PropertyLoader.getProperty("python_environment");
	
	@Before
    public void setUp() {
		cb = new CommandBuilder();
    }
	
	@Test
	public void testMakeManageCommand() {
		String cmd = "makemigrations";
		assertEquals(cb.makeManageCommand(cmd), PYTHON_ENVIRONMENT + " manage.py " + cmd);
	}
	
	@Test
	public void testMakeServerRunCommand() {
		assertEquals(cb.makeServerRunCommand(), PYTHON_ENVIRONMENT + " manage.py runserver");
	}
	
	@Test
	public void testRunTestsCommand() {
		assertEquals(cb.runTestsCommand(), PYTHON_ENVIRONMENT + " manage.py test");
		assertEquals(cb.runTestsCommand(""), PYTHON_ENVIRONMENT + " manage.py test ");
		assertEquals(cb.runTestsCommand("car"), PYTHON_ENVIRONMENT + " manage.py test car");
	}

}
