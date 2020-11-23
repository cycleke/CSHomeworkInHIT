package flight;

import base.Location;
import base.PairLocationsEntry;
import base.PlanningEntry;
import base.SingleResourceEntry;
import base.SingleTimeslotEntry;
import base.Timeslot;

/**
 * This class {@link FlightPlanningEntry} is an interface to represent a flight plan.
 *
 * @author cycleke
 */
public interface FlightPlanningEntry
  extends PlanningEntry<Plane>, PairLocationsEntry, SingleResourceEntry<Plane>, SingleTimeslotEntry {

  public static final boolean LOCATION_SHAREABLE = true;
  public static final boolean RESOURCE_SHAREABLE = false;

  /**
   * Get an instance of {@link FlightEntry}.
   *
   * @param name
   *          the name of the flight plan, not null
   * @param timeslot
   *          the time of the flight plan, not null
   * @param startLocation
   *          the start location of the plan, not null and is shareable
   * @param targetLocation
   *          the target location of the plan, not null and is shareable
   */
  public static FlightPlanningEntry factoryMethod(String name, Timeslot timeslot, Location startLocation,
    Location targetLocation) {
    return new FlightEntry(name, timeslot, startLocation, targetLocation);
  }
}
