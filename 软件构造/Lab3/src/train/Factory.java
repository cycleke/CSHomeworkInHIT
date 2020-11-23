package train;

import java.util.List;

import base.Location;
import base.PlanningEntry;
import base.Timeslot;

/**
 * This class {@link Factory} is a factory class for {@link TrainPlanningEntry}.
 *
 * @author cycleke
 */
public class Factory implements base.Factory<TrainCabin> {
  public static final Factory FACTORY = new Factory();

  @Override
  public PlanningEntry<TrainCabin> creatPlanningEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    if (timeslots.size() != 1 || locations.size() != 2)
      throw new IllegalArgumentException();
    return TrainPlanningEntry.factoryMethod(name, timeslots, locations);
  }
}
