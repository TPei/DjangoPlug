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
		assertEquals("should generate manage.py makemigrations command", commands, CommandBuilder.makeManageCommand(cmd));
	}
	
	@Test
	public void testMakeServerRunCommand() {
		// without address and port
		ArrayList <String> commands = new ArrayList<String>();
		commands.add("manage.py");
		commands.add("runserver");
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand());
		
		// with address and port
		
		// gültige Äquivalenzklassen
		commands.add("0.0.0.0:1024");
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0", 1024));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0:1024"));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand(1024));
		
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:49151");
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0", 49151));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0:49151"));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand(49151));
		
		
		// ungültige Äqivalenzklassen
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:8000");
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0", 1023));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0:1023"));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand(1023));
		
		commands.remove(commands.size()-1);
		commands.add("0.0.0.0:8000");
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0", 49152));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0:49152"));
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand(49152));
		
		commands.remove(commands.size()-1);
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0:hallo"));
		
		// besteht address:port nur aus einem argument, wird er verworfen
		assertEquals("should generate manage.py runserver command", commands, CommandBuilder.makeServerRunCommand("0.0.0.0"));
		
		// null cases
		assertEquals("", commands, CommandBuilder.makeServerRunCommand(null));
		assertEquals("", commands, CommandBuilder.makeServerRunCommand(null, 1025));
	}
	
	@Test
	public void testRunTestsCommand() {
		ArrayList <String> commands = new ArrayList<String>();
		commands.add("manage.py");
		commands.add("test");
		assertEquals("should generate manage.py general test command", commands, CommandBuilder.makeTestRunCommand());
		assertEquals("should generate manage.py general test command", commands, CommandBuilder.makeTestRunCommand(""));
		
		commands.add("car");
		assertEquals("should generate manage.py class test command", commands, CommandBuilder.makeTestRunCommand("car"));
	}

}
