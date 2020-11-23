package train;

import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import base.BlockableEntry;
import base.CommonPlanningEntry;
import base.IllegalStateTransitionException;
import base.Location;
import base.MultipleLocationsEntry;
import base.MultipleLocationsEntryImpl;
import base.MultipleLocationsEntryUnmodifiableDecorator;
import base.MultipleSortedResourceEntry;
import base.MultipleSortedResourceEntryImpl;
import base.MultipleTimeslotEntry;
import base.MultipleTimeslotEntryImpl;
import base.PlanningEntry;
import base.Timeslot;

/**
 * This class {@link TrainEntry} implies basic functions of {@link TrainPlanningEntry}.
 *
 * @author cycleke
 */
public class TrainEntry extends CommonPlanningEntry<TrainCabin> implements TrainPlanningEntry {
  public static final boolean LOCATION_SHAREABLE = true;
  public static final boolean RESOURCE_SHAREABLE = false;

  private final int segments;
  private int timeslotIndex, locationIndex;
  private MultipleLocationsEntry multipleLocationsEntryImpl;
  private MultipleSortedResourceEntry<TrainCabin> multipleSortedResourceEntryImpl;
  private MultipleTimeslotEntry multipleTimeslotEntryImpl;

  // Abstraction function:
  // AF(e) = {timeslots, locations, train carbin}
  // Rep invariant:
  // All field is not null, the locations are shareable. The size of timeslots = the size of locations - 1
  // Safety from rep exposure:
  // the field are all private.

  /**
   * Constructor of {@link TrainEntry}.
   *
   * @param name
   *          the name of the train plan, not null and empty
   * @param timeslots
   *          the timeslots of the train plan, not null, size > 0, timeslots must in increasing order
   * @param locations
   *          the locations of the train plan, not null, size = timeslots.size() + 1
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
    multipleSortedResourceEntryImpl = new MultipleSortedResourceEntryImpl<TrainCabin>();
    checkRep();
  }

  @Override
  protected void checkRep() {
    super.checkRep();
    assert !name.isEmpty();
    assert multipleLocationsEntryImpl != null;
    assert multipleSortedResourceEntryImpl != null;
    assert multipleTimeslotEntryImpl != null;
    assert !multipleTimeslotEntryImpl.getTimeslots().isEmpty();
    assert multipleTimeslotEntryImpl.getTimeslots().size() == multipleLocationsEntryImpl.getLocations().size() - 1;
    for (Location location : multipleLocationsEntryImpl.getLocations())
      assert location.isShareable();
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
    if (!(entry instanceof TrainPlanningEntry))
      return false;
    if (multipleSortedResourceEntryImpl.getResources().isEmpty())
      return false;

    TrainPlanningEntry anotherEntry = (TrainPlanningEntry)entry;
    List<Timeslot> timeslots = getTimeslots(), anotherTimeslots = anotherEntry.getTimeslots();
    List<TrainCabin> trainCabins = getResources(), anotherTrainCabins = anotherEntry.getResources();
    boolean found = false;
    Set<TrainCabin> set = new HashSet<>();
    for (TrainCabin trainCabin : trainCabins) {
      set.add(trainCabin);
    }
    for (TrainCabin trainCabin : anotherTrainCabins) {
      if (set.contains(trainCabin)) {
        found = true;
        break;
      }
    }
    if (found) {
      for (Timeslot timeslot : timeslots)
        for (Timeslot timeslot2 : anotherTimeslots)
          if (timeslot.intersect(timeslot2))
            return true;
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
  public void setTimeslots(List<Timeslot> timeslots) {
    multipleTimeslotEntryImpl.setTimeslots(timeslots);
    checkRep();
  }

  @Override
  public List<Timeslot> getTimeslots() {
    return multipleTimeslotEntryImpl.getTimeslots();
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    if (!(state instanceof BlockableEntry))
      throw new IllegalStateTransitionException();
    ((BlockableEntry)state).block();
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
