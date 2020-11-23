package flight;

import java.io.File;

/**
 * This class {@link PlaneAttributeConflictException} is used to describe the exception that there are two planes with
 * the same name with different attribute.
 *
 * @author cycleke
 */
public class PlaneAttributeConflictException extends FileParseException {
  private static final long serialVersionUID = 8555297858929828033L;


  public PlaneAttributeConflictException(File file, int planeLine, int anotherPlaneLine) {
    super("Parse file " + file.getName() + " failed. " + "The plane on line " + planeLine + " and " + anotherPlaneLine
      + " share the same name, but the attributes differ.");
  }
}
