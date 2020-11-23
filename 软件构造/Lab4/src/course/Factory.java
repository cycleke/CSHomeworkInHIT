package course;

import base.Location;
import base.Timeslot;

import java.util.List;

/**
 * This class {@link Factory} is a factory class for
 * {@link CoursePlanningEntry}.
 *
 * @author cycleke
 */
public class Factory implements base.Factory<Teacher> {

  public static final Factory FACTORY = new Factory();

  /**
   * Get an instance of {@link CoursePlanningEntry}
   *
   * @param name      the name of the plan, not empty
   * @param timeslots the timeslots of the plan, size = 1
   * @param locations the locations of the plan, size = 1
   * @return a {@link CoursePlanningEntry} with given params
   */
  @Override
  public CoursePlanningEntry createPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    assert !name.isEmpty() && timeslots.size() == 1 && locations.size() == 1;
    return CoursePlanningEntry.factoryMethod(name, timeslots.get(0), locations.get(0));
  }

}
