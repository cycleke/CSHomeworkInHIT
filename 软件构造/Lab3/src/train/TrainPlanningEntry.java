package train;

import java.util.List;

import base.BlockableEntry;
import base.Location;
import base.MultipleLocationsEntry;
import base.MultipleSortedResourceEntry;
import base.MultipleTimeslotEntry;
import base.PlanningEntry;
import base.Timeslot;

/**
 * This class {@link TrainPlanningEntry}is an interface to represent a train plan.
 *
 * @author cycleke
 */
public interface TrainPlanningEntry extends PlanningEntry<TrainCabin>, MultipleLocationsEntry,
  MultipleSortedResourceEntry<TrainCabin>, MultipleTimeslotEntry, BlockableEntry {
  public static final boolean LOCATION_SHAREABLE = true;
  public static final boolean RESOURCE_SHAREABLE = false;

  /**
   * Get an instance of {@link TrainEntry}.
   *
   * @param name
   *          the name of the train plan, not null and empty
   * @param timeslots
   *          the timeslots of the train plan, not null, size > 0, timeslots must in increasing order
   * @param locations
   *          the locations of the train plan, not null, size = timeslots.size() + 1
   */
  public static TrainPlanningEntry factoryMethod(String name, List<Timeslot> timeslots, List<Location> locations) {
    return new TrainEntry(name, timeslots, locations);
  }

  boolean hasNextLocation();

  void moveToNextLocation();

  boolean hasNextTimeslot();

  void moveToNextTimeslot();
}
