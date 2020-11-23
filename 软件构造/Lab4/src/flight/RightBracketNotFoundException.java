package flight;

import java.io.File;

/**
 * This class {@link RightBracketNotFoundException} is used to describe the right bracket '}' not found.
 *
 * @author cyclekle
 */
public class RightBracketNotFoundException extends FileParseException {
  private static final long serialVersionUID = 7430475242854075218L;

  /**
   * Constructor of {@link RightBracketNotFoundException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   */
  public RightBracketNotFoundException(File file, int line) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expected a }");
  }
}
