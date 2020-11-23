package flight;

import java.io.File;

/**
 * This class {@link FileParseException} is used to describe the exceptions thrown when parsing the file.
 *
 * @author cycleke
 */
public class FileParseException extends Exception {
  private static final long serialVersionUID = 7900029340035359753L;

  public FileParseException(String message) {
    super(message);
  }

  /**
   * Constructor of {@link FileParseException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public FileParseException(File file, int line) {
    super(String.format("Parse file %s failed on line %d.", file.getName(), line));
  }
}
