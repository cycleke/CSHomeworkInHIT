package flight;

import java.io.File;

/**
 * This class {@link MismatchedDateException} is used to describe the dates of the flight plan mismatch.
 *
 * @author cycleke
 */
public class MismatchedDateException extends FileParseException {
  private static final long serialVersionUID = 7199423930400754384L;

  /**
   * Constructor of {@link MismatchedDateException}.
   *
   * @param file the file where failed
   * @param line the line where the plan start
   */
  public MismatchedDateException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "The dates mismatch.");
  }
}
