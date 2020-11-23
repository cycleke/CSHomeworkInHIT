package course;

import base.Location;
import base.PlanningEntry;
import base.SingleLocationEntry;
import base.SingleResourceEntry;
import base.SingleTimeslotEntry;
import base.Timeslot;

/**
 * This class {@link CoursePlanningEntry} is an interface to represent a course plan.
 *
 * @author cycleke
 */
public interface CoursePlanningEntry
  extends PlanningEntry<Teacher>, SingleResourceEntry<Teacher>, SingleLocationEntry, SingleTimeslotEntry {
  public static final boolean LOCATION_SHAREABLE = false;
  public static final boolean RESOURCE_SHAREABLE = false;

  /**
   * Get an instance of {@link CourseEntry}.
   *
   * @param name
   *          the name of the course plan, not null
   * @param timeslot
   *          the time of the course plan, not null
   * @param location
   *          the location of the plan, not null and is shareable
   */
  public static CoursePlanningEntry factoryMethod(String name, Timeslot timeslot, Location location) {
    return new CourseEntry(name, timeslot, location);
  }
}
