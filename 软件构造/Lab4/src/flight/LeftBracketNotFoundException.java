package flight;

import java.io.File;

/**
 * This class {@link LeftBracketNotFoundException} is used to describe the left bracket '{' not found.
 *
 * @author cyclekle
 */
public class LeftBracketNotFoundException extends FileParseException {
  private static final long serialVersionUID = 8532598582273776732L;

  /**
   * Constructor of {@link LeftBracketNotFoundException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public LeftBracketNotFoundException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expected a {");
  }
}
