package flight;

import java.io.File;

/**
 * This class {@link MismatchedKeyException} is used to describe the exception when the key mismatched.
 *
 * @author cycleke
 */
public class MismatchedKeyException extends FileParseException {

  private static final long serialVersionUID = 5454864838101114543L;

  /**
   * Constructor of {@link MismatchedKeyException}.
   *
   * @param file     the file where failed
   * @param line     the line where failed
   * @param expected the expected key
   */
  public MismatchedKeyException(File file, int line, String expected) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expected Key: " + expected);
  }
}
