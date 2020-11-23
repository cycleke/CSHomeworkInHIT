package flight;

import java.util.List;

import base.Location;
import base.PlanningEntry;
import base.Timeslot;

/**
 * This class {@link Factory} is a factory class for {@link FlightPlanningEntry}.
 *
 * @author cycleke
 */
public class Factory implements base.Factory<Plane> {

  public static final Factory FACTORY = new Factory();

  @Override
  public PlanningEntry<Plane> creatPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    if (timeslots.size() != 1 || locations.size() != 2)
      throw new IllegalArgumentException();
    return FlightPlanningEntry.factoryMethod(name, timeslots.get(0), locations.get(0), locations.get(1));
  }

}
