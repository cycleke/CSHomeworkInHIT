package course;

import base.*;

/**
 * This class {@link CoursePlanningEntry} is an interface to represent a course plan.
 *
 * @author cycleke
 */
public interface CoursePlanningEntry
  extends PlanningEntry<Teacher>, SingleResourceEntry<Teacher>, SingleLocationEntry, SingleTimeslotEntry {
  boolean LOCATION_SHAREABLE = false;

  /**
   * Get an instance of {@link CoursePlanningEntry}.
   *
   * @param name     the name of the course plan, not empty
   * @param timeslot the time of the course plan, not null
   * @param location the location of the plan, not null and is shareable
   * @return a {@link CoursePlanningEntry}
   */
  static CoursePlanningEntry factoryMethod(String name, Timeslot timeslot, Location location) {
    return new CourseEntry(name, timeslot, location);
  }
}
