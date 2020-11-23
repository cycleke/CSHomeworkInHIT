package train;

import base.*;
import org.apache.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class {@link TrainEntry} implies basic functions of
 * {@link TrainPlanningEntry}.
 *
 * @author cycleke
 */
public class TrainEntry extends AbstractCommonPlanningEntry<TrainCabin> implements TrainPlanningEntry {
  public static final boolean LOCATION_SHAREABLE = true;

  private final int segments;
  private final MultipleSortedResourceEntry<TrainCabin> multipleSortedResourceEntryImpl;
  private final MultipleTimeslotEntry multipleTimeslotEntryImpl;
  private int timeslotIndex, locationIndex;
  private MultipleLocationsEntry multipleLocationsEntryImpl;

  // Abstraction function:
  // AF(e) = {timeslots, locations, train cabin}
  // Rep invariant:
  // All field is not null, the locations are shareable. The size of timeslots =
  // the size of locations - 1, locations and train cabins are distinct.
  // Safety from rep exposure:
  // the field are all private.

  /**
   * Constructor of {@link TrainEntry}.
   *
   * @param name      the name of the train plan, not empty
   * @param timeslots the timeslots of the train plan, not null, size > 0,
   *                  timeslots must in increasing order
   * @param locations the locations of the train plan, not null, size =
   *                  timeslots.size() + 1
   */
  public TrainEntry(String name, List<Timeslot> timeslots, List<Location> locations) {
    this.name = name;
    state = new WaitingState(this);

    timeslotIndex = 0;
    locationIndex = 0;
    segments = timeslots.size();
    multipleLocationsEntryImpl = new MultipleLocationsEntryImpl();
    multipleLocationsEntryImpl.setLocations(locations);
    multipleLocationsEntryImpl = new MultipleLocationsEntryUnmodifiableDecorator(multipleLocationsEntryImpl);
    multipleTimeslotEntryImpl = new MultipleTimeslotEntryImpl();
    multipleTimeslotEntryImpl.setTimeslots(timeslots);
    multipleSortedResourceEntryImpl = new MultipleSortedResourceEntryImpl<>();
    checkRep();
  }

  @Override
  protected void checkRep() {
    super.checkRep();
    assert !name.isEmpty();
    assert multipleLocationsEntryImpl != null;
    assert multipleSortedResourceEntryImpl != null;
    assert multipleTimeslotEntryImpl != null;
    List<Timeslot> timeslots = multipleTimeslotEntryImpl.getTimeslots();
    assert !timeslots.isEmpty();
    assert segments == timeslots.size();

    List<Location> locations = multipleLocationsEntryImpl.getLocations();
    assert segments == locations.size() - 1;
    assert segments == locations.stream().distinct().count() - 1;
    assert multipleLocationsEntryImpl.getLocations().stream().allMatch(Location::isShareable);
    assert multipleLocationsEntryImpl.getLocations().stream().distinct().count() == segments + 1;

    List<TrainCabin> trainCabins = multipleSortedResourceEntryImpl.getResources();
    assert trainCabins.stream().map(TrainCabin::getId).distinct().count() == trainCabins.size();
  }

  @Override
  public Timeslot getCurrentTimeslot() {
    return multipleTimeslotEntryImpl.getTimeslots().get(timeslotIndex);
  }

  @Override
  public Location getCurrentLocation() {
    return multipleLocationsEntryImpl.getLocations().get(locationIndex);
  }

  @Override
  public boolean conflictResourceWith(PlanningEntry<?> entry) {
    if (!(entry instanceof TrainPlanningEntry)) {
      return false;
    }
    if (multipleSortedResourceEntryImpl.getResources().isEmpty()) {
      return false;
    }

    TrainPlanningEntry anotherEntry = (TrainPlanningEntry) entry;
    List<Timeslot> timeslots = getTimeslots(), anotherTimeslots = anotherEntry.getTimeslots();
    List<TrainCabin> trainCabins = getResources(), anotherTrainCabins = anotherEntry.getResources();
    boolean found = false;
    Set<TrainCabin> set = new HashSet<>(trainCabins);
    for (TrainCabin trainCabin : anotherTrainCabins) {
      if (set.contains(trainCabin)) {
        found = true;
        break;
      }
    }
    if (found) {
      for (Timeslot timeslot : timeslots) {
        for (Timeslot timeslot2 : anotherTimeslots) {
          if (timeslot.intersect(timeslot2)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  @Override
  public boolean conflictLocationWith(PlanningEntry<?> entry) {
    return false;
  }

  @Override
  public List<Location> getLocations() {
    return multipleLocationsEntryImpl.getLocations();
  }

  @Override
  public void setLocations(List<Location> locations) {
    multipleLocationsEntryImpl.setLocations(locations);
    checkRep();
  }

  @Override
  public List<TrainCabin> getResources() {
    return multipleSortedResourceEntryImpl.getResources();
  }

  @Override
  public void setResources(List<TrainCabin> resources) {
    multipleSortedResourceEntryImpl.setResources(resources);
    checkRep();
  }

  @Override
  public List<Timeslot> getTimeslots() {
    return multipleTimeslotEntryImpl.getTimeslots();
  }

  @Override
  public void setTimeslots(List<Timeslot> timeslots) {
    multipleTimeslotEntryImpl.setTimeslots(timeslots);
    checkRep();
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    if (!(state instanceof BlockableEntry)) {
      String message = String.format("Can't move from %s to ALLOCATED STATE", getState().toString());
      Logger.getRootLogger().error(message);
      throw new IllegalStateTransitionException(message);
    }
    ((BlockableEntry) state).block();
  }

  @Override
  public boolean hasNextLocation() {
    return locationIndex + 1 <= segments;
  }

  @Override
  public void moveToNextLocation() {
    ++locationIndex;
  }

  @Override
  public boolean hasNextTimeslot() {
    return timeslotIndex + 1 < segments;
  }

  @Override
  public void moveToNextTimeslot() {
    ++timeslotIndex;
  }

  @Override
  public String toString() {
    List<Location> locations = getLocations();
    return getName() + " " + getCurrentTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - "
      + getCurrentTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " "
      + String.format("%s - %s", locations.get(0).getName(), locations.get(locations.size() - 1).getName());
  }
}
