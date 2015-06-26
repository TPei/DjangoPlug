package de.fhflensburg.pd.group007.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShellCommand {

    public static void main(String[] args) {
    	CommandBuilder cb = new CommandBuilder();
		try {
			ShellCommand.execute(cb.makeServerRunCommand());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static void execute(ArrayList<String> commands) throws IOException, InterruptedException {
        //Run macro on target
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.directory(new File("/Users/thomas/Code/tv_tracker"));
        pb.redirectErrorStream(true);
        Process process = pb.start();

        //Read output
        StringBuilder out = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null, previous = null;
        while ((line = br.readLine()) != null)
            if (!line.equals(previous)) {
                previous = line;
                out.append(line).append('\n');
                System.out.println(line);
            }

	        //Check result
	        if (process.waitFor() == 0) {
	            System.out.println("Success!");
	            System.exit(0);
        }

        //Abnormal termination: Log command parameters and output and throw ExecutionException
        System.err.println(commands);
        System.err.println(out.toString());
        System.exit(1);
    }
}