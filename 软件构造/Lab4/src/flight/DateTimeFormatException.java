package flight;

import java.io.File;

/**
 * This class {@link DateTimeFormatException} is used to describe the data time format mismatches yyyy-MM-dd HH:ss.
 *
 * @author cycleke
 */
public class DateTimeFormatException extends FileParseException {
  private static final long serialVersionUID = -7976051530546288293L;

  /**
   * Constructor of {@link DateTimeFormatException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public DateTimeFormatException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expected date time format: yyyy-MM-dd HH:mm");
  }

}
