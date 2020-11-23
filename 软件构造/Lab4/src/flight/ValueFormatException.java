package flight;

import java.io.File;

/**
 * This class {@link ValueFormatException} is used to describe the format of the value is illegal.
 *
 * @author cycleke
 */
public class ValueFormatException extends FileParseException {

  private static final long serialVersionUID = 7399153711096392420L;

  /**
   * Constructor of {@link ValueFormatException}.
   *
   * @param file           the file where failed
   * @param line           the line where failed
   * @param expectedFormat the expected format of value
   */
  public ValueFormatException(File file, int line, String expectedFormat) {
    super(String.format("Parse file %s failed on line %d. Expected format: %s", file.getName(), line, expectedFormat));
  }
}
