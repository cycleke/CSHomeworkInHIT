package flight;

import java.io.File;

/**
 * This class {@link WrongNumberOfValuesException} is used to describe the values are too many.
 *
 * @author cycleke
 */
public class WrongNumberOfValuesException extends FileParseException {
  private static final long serialVersionUID = -4250814557306211589L;

  public WrongNumberOfValuesException(File file, int line, int expectedNumber, int actualNumber) {
    super("Parse file " + file.getName() + " failed on line " + line + ". " + "Expect " + expectedNumber
      + " values, but got " + actualNumber + " values.");
  }
}
