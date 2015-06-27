package de.fhflensburg.pd.group007.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShellCommand {
	
	// TODO get django path from user settings
	private final static String DJANGO_PATH = "/Users/thomas/Code/tv_tracker";

    public static void main(String[] args) {
		ShellCommand.execute(CommandBuilder.makeServerRunCommand());
    }
    
    public static String execute(ArrayList<String> commands) {
    	StringBuilder out = new StringBuilder();
    	
    	
    	// we'll handle errors here because we also want to 
    	// return the shell errors to the plugin console
    	try {
		    //Run macro on target
		    ProcessBuilder pb = new ProcessBuilder(commands);
		    pb.directory(new File(DJANGO_PATH));
		    pb.redirectErrorStream(true);
		    Process process = pb.start();
		
		    //Read output
		    BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
		    String line = null, previous = null;
		    while ((line = br.readLine()) != null)
		        if (!line.equals(previous)) {
		            previous = line;
		            out.append(line).append('\n');
		            //System.out.println(line);
		        }
		
		        //Check result
		        if (process.waitFor() == 0) {
		            //System.out.println("Success!");
		            //System.exit(0);
		    }
		
		    //Abnormal termination: Log command parameters and output and throw ExecutionException
		    //System.err.println(commands);
		    //System.err.println(out.toString());
		    //System.exit(1);
    	}
    	catch (IOException exc) {
    		out.append("IO Exception");
    	} 
    	catch (InterruptedException exc) {
    		out.append("Interrupted Exception");
    	}
        
        return out.toString();
    }
}