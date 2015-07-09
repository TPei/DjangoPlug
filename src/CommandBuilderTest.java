import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.fhflensburg.pd.group007.commands.AutoCompleter;
import de.fhflensburg.pd.group007.commands.CommandBuilder;
import de.fhflensburg.pd.group007.helper.PropertyLoader;


public class CommandBuilderTest {
	
	@Test
	public void testMakeManageCommand() {
		String cmd = "makemigrations";
		ArrayList <String> commands = new ArrayList<String>();
		commands.add("manage.py");
		commands.add(cmd);
		assertEquals("should generate manage.py makemigrations command", CommandBuilder.makeManageCommand(cmd), commands);
	}
	
	@Test
	public void testMakeServerRunCommand() {
		// without address and port
		ArrayList <String> commands = new ArrayList<String>();
		commands.add("manage.py");
		commands.add("runserver");
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand(), commands);
		
		// with address and port
		
		// gültige Äquivalenzklassen
		commands.add("0.0.0.0:1024");
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0", 1024), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0:1024"), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand(1024), commands);
		
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:49151");
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0", 49151), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0:49151"), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand(49151), commands);
		
		
		// ungültige Äqivalenzklassen
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:8000");
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0", 1023), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0:1023"), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand(1023), commands);
		
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:8000");
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0", 49152), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0:49152"), commands);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand(49152), commands);
		
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:8000");
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0:hallo"), commands);
		
		// besteht address:port nur aus einem argument, wird er verworfen
		commands.remove(commands.size()-1);
		assertEquals("should generate manage.py runserver command", CommandBuilder.makeServerRunCommand("0.0.0.0"), commands);
	}
	
	@Test
	public void testRunTestsCommand() {
		ArrayList <String> commands = new ArrayList<String>();
		commands.add("manage.py");
		commands.add("test");
		assertEquals("should generate manage.py general test command", CommandBuilder.makeTestRunCommand(), commands);
		assertEquals("should generate manage.py general test command", CommandBuilder.makeTestRunCommand(""), commands);
		
		commands.add("car");
		assertEquals("should generate manage.py class test command", CommandBuilder.makeTestRunCommand("car"), commands);
	}

}
