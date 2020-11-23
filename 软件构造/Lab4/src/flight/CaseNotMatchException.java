package flight;

import java.io.File;

/**
 * This class {@link CaseNotMatchException} is used to describe the case of a letter doesn't match.
 *
 * @author cycleke
 */
public class CaseNotMatchException extends FileParseException {

  public static final String UPPER_CASE = "uppercase";
  public static final String LOWER_CASE = "lowercase";
  public static final String CAPITALIZE = "capitalize";
  private static final long serialVersionUID = 9177179560551648754L;

  /**
   * Constructor of {@link CaseNotMatchException}.
   *
   * @param file         the file where failed
   * @param line         the line where failed
   * @param expectedCase the expected case
   */
  public CaseNotMatchException(File file, int line, String expectedCase) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expected case: " + expectedCase);
  }
}
