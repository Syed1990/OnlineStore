package automationFramework;

import automationFramework.ConfigFileReader;

public class ConfigManager {
		 
		private static ConfigManager fileReaderManager = new ConfigManager();
		private static ConfigFileReader configFileReader;
	 
		private ConfigManager() {
		}
	 
		 public static ConfigManager getInstance( ) {
		      return fileReaderManager;
		 }
	 
		 public ConfigFileReader getConfigReader() {
			 return (configFileReader == null) ? new ConfigFileReader() : configFileReader;
		 }
	
}
