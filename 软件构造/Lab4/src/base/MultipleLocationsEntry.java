package base;

import java.util.List;

/**
 * This class {@link MultipleLocationsEntry} is an interface for {@link PlanningEntry} with multiple locations.
 *
 * @author cycleke
 */
public interface MultipleLocationsEntry {

  /**
   * Return the locations from start to target.
   *
   * @return the locations of the entry
   */
  List<Location> getLocations();

  /**
   * Set the multiple locations.
   *
   * @param locations the locations of this entry, not null
   */
  void setLocations(List<Location> locations);
}
