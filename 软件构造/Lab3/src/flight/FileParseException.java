package flight;

import java.io.File;

public class FileParseException extends Exception {
  private static final long serialVersionUID = 7900029340035359753L;

  public FileParseException(File file, int line) {
    super(String.format("Parse file %s failed on line %d.", file.getName(), line));
  }
}
