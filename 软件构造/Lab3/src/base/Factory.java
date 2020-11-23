package base;

import java.util.List;

/**
 * This class {@link Factory} is an factory interface.
 *
 * @param <R>
 *          the type of resource
 * @author cycleke
 */
public interface Factory<R> {
  PlanningEntry<R> creatPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations);
}
