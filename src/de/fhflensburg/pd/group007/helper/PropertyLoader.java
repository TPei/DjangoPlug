package de.fhflensburg.pd.group007.helper;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
	/**
	 * gets a property by name from config.properties
	 * @param property
	 * @return {String | null} property value
	 */
	public static String getProperty(String property) {
		 
		Properties prop = new Properties();
		InputStream input = null;
		String value = "";
	 
		try {
	 
			input = new FileInputStream("files/config.properties");
	 
			// load a properties file
			prop.load(input);
	 
			// get the property value and print it out
			value = prop.getProperty(property);
			if (value == null) {
				value = "";
			}
			
		} catch (IOException ex) {
			ex.printStackTrace();
			return value;
		} finally {
			if (input != null) {
				try {
					input.close();	
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
