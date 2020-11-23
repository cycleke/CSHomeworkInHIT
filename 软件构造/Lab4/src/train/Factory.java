package train;

import base.Location;
import base.Timeslot;

import java.util.List;

/**
 * This class {@link Factory} is a factory class for {@link TrainPlanningEntry}.
 *
 * @author cycleke
 */
public class Factory implements base.Factory<TrainCabin> {
  public static final Factory FACTORY = new Factory();

  /**
   * Get an instance of {@link TrainPlanningEntry}
   *
   * @param name      the name of the plan, not empty
   * @param timeslots the timeslots of the plan, not null
   * @param locations the locations of the plan, not null
   * @return a {@link TrainPlanningEntry} with given params
   */
  @Override
  public TrainPlanningEntry createPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    assert !timeslots.isEmpty() && timeslots.size() == locations.size() - 1;
    return TrainPlanningEntry.factoryMethod(name, timeslots, locations);
  }
}
