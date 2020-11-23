package base;

import java.util.List;

/**
 * This class {@link Factory} is an factory interface.
 *
 * @param <R> the type of resource
 * @author cycleke
 */
public interface Factory<R> {
  /**
   * Get an instance of {@link PlanningEntry}.
   *
   * @param name      the name of the plan, not empty
   * @param timeslots the timeslots of the plan, not null
   * @param locations the locations of the plan, not null
   * @return a {@link PlanningEntry} with given params
   */
  PlanningEntry<R> createPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations);
}
