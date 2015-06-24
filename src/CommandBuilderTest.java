import static org.junit.Assert.*;

import java.util.ArrayList;

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
		ArrayList <String> commands = new ArrayList<String>();
		commands.add(PYTHON_ENVIRONMENT);
		commands.add("manage.py");
		commands.add(cmd);
		assertEquals("should generate manage.py makemigrations command", cb.makeManageCommand(cmd), commands);
	}
	
	@Test
	public void testMakeServerRunCommand() {
		ArrayList <String> commands = new ArrayList<String>();
		commands.add(PYTHON_ENVIRONMENT);
		commands.add("manage.py");
		commands.add("runserver");
		assertEquals("should generate manage.py runserver command", cb.makeServerRunCommand(), commands);
	}
	
	@Test
	public void testRunTestsCommand() {
		ArrayList <String> commands = new ArrayList<String>();
		commands.add(PYTHON_ENVIRONMENT);
		commands.add("manage.py");
		commands.add("test");
		assertEquals("should generate manage.py general test command", cb.runTestsCommand(), commands);
		assertEquals("should generate manage.py general test command", cb.runTestsCommand(""), commands);
		
		commands.add("car");
		assertEquals("should generate manage.py class test command", cb.runTestsCommand("car"), commands);
	}

}
