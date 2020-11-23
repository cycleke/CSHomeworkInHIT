package flight;

import java.io.File;

/**
 * This class {@link DateFormatException} is used to describe the data format mismatches yyyy-MM-dd.
 *
 * @author cycleke
 */
public class DateFormatException extends FileParseException {
  private static final long serialVersionUID = -4581816709097516167L;

  /**
   * Constructor of {@link DateFormatException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public DateFormatException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expected date format: yyyy-MM-dd");
  }
}
