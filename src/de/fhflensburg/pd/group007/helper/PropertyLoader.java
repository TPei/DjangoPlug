package de.fhflensburg.pd.group007.helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	public static String getProperty(String property) {
		 
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("files/config.properties");
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			return prop.getProperty(property);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return "";
	 
	  }
}