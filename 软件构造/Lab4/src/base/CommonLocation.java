package base;

/**
 * This class {@link CommonLocation} is an implementation of {@link Location} with basic functions.
 *
 * @author cycleke
 * @implSpec The class must be immutable.
 */
public class CommonLocation implements Location {
  private final String name;
  private final boolean shareable;

  // Abstraction function:
  // AF(r) = {name, shareable}
  // Rep invariant:
  // name is not null
  // Safety from rep exposure:
  // The field are all private and final. There are only getters, no setters.

  /**
   * Constructor of {@link CommonLocation}.
   *
   * @param name      the name of the location, not null
   * @param shareable whether the location is shareable
   */
  public CommonLocation(String name, boolean shareable) {
    this.name = name;
    this.shareable = shareable;
    checkRep();
  }

  /**
   * Obtains an instance of {@link CommonLocation}.
   *
   * @param name      the name of the location, not null
   * @param shareable whether the location is shareable
   */
  public static CommonLocation of(String name, boolean shareable) {
    return new CommonLocation(name, shareable);
  }

  protected void checkRep() {
    assert name != null;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isShareable() {
    return shareable;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + name.hashCode();
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof Location)) {
      return false;
    }
    Location other = (Location) obj;
    return name.equals(other.getName());
  }

  @Override
  public String toString() {
    return name;
  }
}
