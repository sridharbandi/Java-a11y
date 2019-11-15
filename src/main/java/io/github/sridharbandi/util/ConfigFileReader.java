package io.github.sridharbandi.util;

import java.io.IOException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigFileReader {

  private static Logger LOG = LoggerFactory.getLogger(ConfigFileReader.class);
  private static Properties properties = null;

  /**
   * Load properties from config.properties in resources dir
   */
  public ConfigFileReader(){
    try {
      properties = new Properties();
      //ensure properties is threadsafe
      synchronized (Properties.class) {
        properties.load(ConfigFileReader.class.getClassLoader().getResourceAsStream("config.properties"));
      }

    } catch (IOException e) {
      LOG.error(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
   * getProperty from config file
   * @param key to search for
   * @return value of key or empty string
   */
  public static String getProperty(String key) {
    return properties == null ? null : properties.getProperty(key, "");
  }
}
