package flight;

import java.io.File;

/**
 * This class {@link SameElementException} is used to describe the exception that there are two planning entry with same
 * date and number.
 *
 * @author cycleke
 */
public class SameElementException extends FileParseException {
  private static final long serialVersionUID = 4704110699193673632L;

  /**
   * Constructor of {@link SameElementException}.
   *
   * @param file               the file where failed
   * @param elementLine        the line where the first element start
   * @param anotherElementLine the line where the second element start
   */
  public SameElementException(File file, int elementLine, int anotherElementLine) {
    super("Parse file " + file.getName() + " failed. " + "The element of " + elementLine + " and " + anotherElementLine
      + " are the same.");
  }
}
