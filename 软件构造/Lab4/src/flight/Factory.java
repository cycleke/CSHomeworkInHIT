package flight;

import base.Location;
import base.Timeslot;

import java.util.List;

/**
 * This class {@link Factory} is a factory class for
 * {@link FlightPlanningEntry}.
 *
 * @author cycleke
 */
public class Factory implements base.Factory<Plane> {

  public static final Factory FACTORY = new Factory();

  /**
   * Get an instance of {@link FlightPlanningEntry}
   *
   * @param name      the name of the plan, not empty
   * @param timeslots the timeslots of the plan, size = 1
   * @param locations the locations of the plan, size = 2
   * @return a {@link FlightPlanningEntry} with given params
   */
  @Override
  public FlightPlanningEntry createPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    assert timeslots.size() == 1 && locations.size() == 2;
    return FlightPlanningEntry.factoryMethod(name, timeslots.get(0), locations.get(0), locations.get(1));
  }

}
