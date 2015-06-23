import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fhflensburg.pd.group007.commands.AutoCompleter;
import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.helper.PropertyLoader;


public class CommandBuilderTest {

	static CommandBuilder cb;
	private final String PYTHON_ENVIRONMENT = PropertyLoader.getProperty("python_environment");
	
	@BeforeClass
    public static void setUp() {
		cb = new CommandBuilder();
    }
	
	@Test
	public void testMakeManageCommand() {
		String cmd = "makemigrations";
		assertEquals("should generate manage.py makemigrations command", cb.makeManageCommand(cmd), PYTHON_ENVIRONMENT + " manage.py " + cmd);
	}
	
	@Test
	public void testMakeServerRunCommand() {
		assertEquals("should generate manage.py runserver command", cb.makeServerRunCommand(), PYTHON_ENVIRONMENT + " manage.py runserver");
	}
	
	@Test
	public void testRunTestsCommand() {
		assertEquals("should generate manage.py general test command", cb.runTestsCommand(), PYTHON_ENVIRONMENT + " manage.py test");
		assertEquals("should generate manage.py general test command", cb.runTestsCommand(""), PYTHON_ENVIRONMENT + " manage.py test ");
		assertEquals("should generate manage.py class test command", cb.runTestsCommand("car"), PYTHON_ENVIRONMENT + " manage.py test car");
	}

}
