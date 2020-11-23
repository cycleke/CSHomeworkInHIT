package train;

import base.*;

import java.util.List;

/**
 * This class {@link TrainPlanningEntry}is an interface to represent a train plan.
 *
 * @author cycleke
 */
public interface TrainPlanningEntry extends PlanningEntry<TrainCabin>, MultipleLocationsEntry,
  MultipleSortedResourceEntry<TrainCabin>, MultipleTimeslotEntry, BlockableEntry {
  boolean LOCATION_SHAREABLE = true;

  /**
   * Get an instance of {@link TrainPlanningEntry}.
   *
   * @param name      the name of the train plan, not empty
   * @param timeslots the timeslots of the train plan, not null, size > 0, timeslots must in increasing order
   * @param locations the locations of the train plan, not null, size = timeslots.size() + 1
   * @return a {@link TrainPlanningEntry} with given params
   */
  static TrainPlanningEntry factoryMethod(String name, List<Timeslot> timeslots, List<Location> locations) {
    return new TrainEntry(name, timeslots, locations);
  }

  /**
   * Check whether current plan has next location to arrive.
   *
   * @return true if there is a location to arrive
   */
  boolean hasNextLocation();

  /**
   * Move to the next location.
   */
  void moveToNextLocation();

  /**
   * Check whether current plan has next timeslot
   *
   * @return true if there is a timeslot to run
   */
  boolean hasNextTimeslot();

  /**
   * Run the next timeslot
   */
  void moveToNextTimeslot();
}
