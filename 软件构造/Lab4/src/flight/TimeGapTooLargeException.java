package flight;

import java.io.File;

/**
 * This class {@link TimeGapTooLargeException} is used to describe the time gap if longer than one day.
 *
 * @author cycleke
 */
public class TimeGapTooLargeException extends FileParseException {
  private static final long serialVersionUID = -3949378081645555507L;

  /**
   * Constructor of {@link TimeGapTooLargeException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public TimeGapTooLargeException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". "
      + "The time gap between departure time and arrival time is longer than one day.");
  }
}
