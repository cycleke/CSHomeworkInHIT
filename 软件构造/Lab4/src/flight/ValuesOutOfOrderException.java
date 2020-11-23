package flight;

import java.io.File;

/**
 * This class {@link ValuesOutOfOrderException} is used to describe the multiple values are out of order.
 *
 * @author cycleke
 */
public class ValuesOutOfOrderException extends FileParseException {

  private static final long serialVersionUID = 414294985594308210L;

  /**
   * Constructor of {@link ValuesOutOfOrderException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public ValuesOutOfOrderException(File file, int line) {
    super(file, line);
  }
}
