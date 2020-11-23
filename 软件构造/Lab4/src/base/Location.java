package base;

/**
 * This class {@link Location} is an interface to describe the location. It contains fields like name.
 *
 * @author cycleke
 * @implSpec The class must be immutable.
 */
public interface Location {
  /**
   * Return the name of the location.
   *
   * @return the name of the location
   */
  String getName();

  /**
   * Return true if the location can be shared with multiple entry.
   *
   * @return true if the location can be shared with multiple entry
   */
  boolean isShareable();
}
