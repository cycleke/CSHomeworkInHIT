package course;

import java.util.List;

import base.Location;
import base.PlanningEntry;
import base.Timeslot;

/**
 * This class {@link Factory} is a factory class for {@link CoursePlanningEntry}.
 *
 * @author cycleke
 */
public class Factory implements base.Factory<Teacher> {

  public static final Factory FACTORY = new Factory();

  @Override
  public PlanningEntry<Teacher> creatPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    if (timeslots.size() != 1 || locations.size() != 1)
      throw new IllegalArgumentException();
    return CoursePlanningEntry.factoryMethod(name, timeslots.get(0), locations.get(0));
  }

}
