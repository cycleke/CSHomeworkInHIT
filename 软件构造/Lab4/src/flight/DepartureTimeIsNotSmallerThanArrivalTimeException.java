package flight;

import java.io.File;

/**
 * This class {@link DepartureTimeIsNotSmallerThanArrivalTimeException} is used to describe the exception that the
 * departure time of the plane is not smaller than the arrival time.
 *
 * @author cycleke
 */
public class DepartureTimeIsNotSmallerThanArrivalTimeException extends FileParseException {
  private static final long serialVersionUID = 1606886524685990319L;

  /**
   * Constructor of {@link DepartureTimeIsNotSmallerThanArrivalTimeException}
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public DepartureTimeIsNotSmallerThanArrivalTimeException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". "
      + "The departure time of the plane is not smaller than the arrival time.");
  }
}
