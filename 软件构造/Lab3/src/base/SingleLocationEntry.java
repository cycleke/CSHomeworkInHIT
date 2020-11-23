package base;

/**
 * This class {@link SingleLocationEntry} is an interface for {@link PlanningEntry} with single location.
 *
 * @author cycleke
 */
public interface SingleLocationEntry {

  /**
   * Return the location of the entry.
   *
   * @return the location of the entry
   */
  Location getLocation();

  /**
   * Set the location.
   *
   * @param location
   *          the location of the entry, not null
   */
  void setLocation(Location location);
}
