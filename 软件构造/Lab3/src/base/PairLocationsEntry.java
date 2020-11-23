package base;

import java.util.List;

/**
 * This class {@link PairLocationsEntry} is an interface for {@link PlanningEntry} with two locations.
 *
 * @author cycleke
 */
public interface PairLocationsEntry {

  /**
   * Return the start location of the entry.
   *
   * @return the start location of the entry
   */
  Location getStartLocation();

  /**
   * Return the target location of the entry.
   *
   * @return the target location of the entry.
   */
  Location getTargetLocation();

  /**
   * Return the locations from start to target.
   *
   * @return the locations of the entry
   */
  List<Location> getLocations();

  /**
   * Change the start location of the entry.
   *
   * @param startLocation
   *          the start location of the entry, not null
   */
  void setStartLocation(Location startLocation);

  /**
   * Change the target location of the entry.
   *
   * @param targetLocation
   *          the target location of the entry, not null
   */
  void setTargetLocation(Location targetLocation);

  /**
   * Change the locations from start to target.
   *
   * @param locations
   *          the locations of the entry, the size must be 2
   */
  void setLocations(List<Location> locations);
}
