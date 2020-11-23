package flight;

import base.*;

/**
 * This class {@link FlightPlanningEntry} is an interface to represent a flight plan.
 *
 * @author cycleke
 */
public interface FlightPlanningEntry
  extends PlanningEntry<Plane>, PairLocationsEntry, SingleResourceEntry<Plane>, SingleTimeslotEntry {

  boolean LOCATION_SHAREABLE = true;

  /**
   * Get an instance of {@link FlightPlanningEntry}.
   *
   * @param name           the name of the flight plan, not empty
   * @param timeslot       the time of the flight plan, not null
   * @param startLocation  the start location of the plan, not null and is shareable
   * @param targetLocation the target location of the plan, not null and is shareable
   * @return a {@link FlightPlanningEntry} with given params
   */
  static FlightPlanningEntry factoryMethod(String name, Timeslot timeslot, Location startLocation,
                                           Location targetLocation) {
    return new FlightEntry(name, timeslot, startLocation, targetLocation);
  }
}
