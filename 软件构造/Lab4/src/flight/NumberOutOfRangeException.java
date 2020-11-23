package flight;

import java.io.File;

/**
 * This class {@link NumberOutOfRangeException} is used to describe the number
 * is out of range.
 *
 * @author cycleke
 */
public class NumberOutOfRangeException extends FileParseException {
  private static final long serialVersionUID = 7194819509108049969L;

  /**
   * Constructor of {@link NumberOutOfRangeException}.
   *
   * @param file       the file where failed
   * @param line       the line where failed
   * @param lowerBound the lower bound of the expected range
   * @param upperBound the upper bound of the expected range
   */
  public NumberOutOfRangeException(File file, int line, Number lowerBound, Number upperBound) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "The number must "
      + (lowerBound != null ? ">= " + lowerBound + " " : "") + (upperBound != null ? "<= " + upperBound + " " : "")
      + ".");
  }
}
