package io.github.sridharbandi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileReaderManager {

  private static Logger LOG = LoggerFactory.getLogger(FileReaderManager.class);
  private static FileReaderManager fileReaderManager = null;
  private static ConfigFileReader reader = null;

  private FileReaderManager() {
    try {
      reader = new ConfigFileReader();
    } catch (Exception e) {
      LOG.error(e.getMessage());
      e.printStackTrace();
    }

  }

  /**
   * FileReaderManager
   * @return instance of fileReaderManager
   */
  public static FileReaderManager getInstance( ) {
    return (fileReaderManager == null)? new FileReaderManager() : fileReaderManager;
  }

  /**
   * ConfigFileReader
   * @return instance of ConfigFileReader
   */
  public ConfigFileReader getConfigReader() {
    return (reader == null) ? new ConfigFileReader() : reader;
  }
}
