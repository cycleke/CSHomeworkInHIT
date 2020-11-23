package flight;

import java.io.File;

/**
 * This class {@link ValueNotFoundException} is used to describe the value is missing.
 *
 * @author cycleke
 */
public class ValueNotFoundException extends FileParseException {

  private static final long serialVersionUID = -8092368009521609418L;

  /**
   * Constructor of {@link ValueNotFoundException}.
   *
   * @param file the file where failed
   * @param line the line where failed
   * @param key  the key of which the value is missing
   */
  public ValueNotFoundException(File file, int line, String key) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "The value of " + key + " not found.");
  }
}
