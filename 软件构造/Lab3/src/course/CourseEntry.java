package course;

import java.time.format.DateTimeFormatter;

import base.CommonPlanningEntry;
import base.Location;
import base.PlanningEntry;
import base.SingleLocationEntry;
import base.SingleLocationEntryImpl;
import base.SingleResourceEntry;
import base.SingleResourceEntryImpl;
import base.SingleTimeslotEntry;
import base.SingleTimeslotEntryImpl;
import base.Timeslot;

/**
 * This class {@link CourseEntry} implies the basic functions of {@link CoursePlanningEntry}.
 *
 * @author cycleke
 */
public class CourseEntry extends CommonPlanningEntry<Teacher> implements CoursePlanningEntry {
  public static final boolean LOCATION_SHAREABLE = false;
  public static final boolean RESOURCE_SHAREABLE = false;

  private SingleLocationEntry singleLocationEntryImpl;
  private SingleResourceEntry<Teacher> singleResourceEntryImpl;
  private SingleTimeslotEntry singleTimeslotEntryImpl;

  // Abstraction function:
  // AF(e) = {timeslot, location, teacher}
  // Rep invariant:
  // All field is not null, the locations aren't shareable.
  // Safety from rep exposure:
  // the field are all private.

  /**
   * Constructor of {@link CourseEntry}.
   *
   * @param name
   *          the name of the course plan, not null
   * @param timeslot
   *          the time of the course plan, not null
   * @param location
   *          the location of the plan, not null and is shareable
   */
  public CourseEntry(String name, Timeslot timeslot, Location location) {
    this.name = name;
    state = new WaitingState(this);

    singleLocationEntryImpl = new SingleLocationEntryImpl(location);
    singleTimeslotEntryImpl = new SingleTimeslotEntryImpl(timeslot);
    singleResourceEntryImpl = new SingleResourceEntryImpl<>();
    checkRep();
  }

  @Override
  protected void checkRep() {
    super.checkRep();
    assert singleLocationEntryImpl != null;
    assert singleResourceEntryImpl != null;
    assert singleTimeslotEntryImpl != null;
    assert !singleLocationEntryImpl.getLocation().isShareable();
  }

  @Override
  public Teacher getResource() {
    return singleResourceEntryImpl.getResource();
  }

  @Override
  public void setResource(Teacher resource) {
    singleResourceEntryImpl.setResource(resource);
    checkRep();
  }

  @Override
  public Location getLocation() {
    return singleLocationEntryImpl.getLocation();
  }

  @Override
  public void setLocation(Location location) {
    singleLocationEntryImpl.setLocation(location);
    checkRep();
  }

  @Override
  public void setTimeslot(Timeslot timeslot) {
    singleTimeslotEntryImpl.setTimeslot(timeslot);
    checkRep();
  }

  @Override
  public Timeslot getTimeslot() {
    return singleTimeslotEntryImpl.getTimeslot();
  }

  @Override
  public Timeslot getCurrentTimeslot() {
    return singleTimeslotEntryImpl.getTimeslot();
  }

  @Override
  public Location getCurrentLocation() {
    return singleLocationEntryImpl.getLocation();
  }

  @Override
  public boolean conflictLocationWith(PlanningEntry<?> entry) {
    if (!(entry instanceof CoursePlanningEntry))
      return false;
    CoursePlanningEntry anotherEntry = (CoursePlanningEntry)entry;
    Timeslot anotherTimeslot = anotherEntry.getTimeslot();
    if (!getTimeslot().intersect(anotherTimeslot))
      return false;
    return getLocation().equals(anotherEntry.getLocation());
  }

  @Override
  public boolean conflictResourceWith(PlanningEntry<?> entry) {
    if (!(entry instanceof CoursePlanningEntry))
      return false;
    if (singleResourceEntryImpl.getResource() == null)
      return false;
    CoursePlanningEntry anotherEntry = (CoursePlanningEntry)entry;
    Timeslot anotherTimeslot = anotherEntry.getTimeslot();
    if (!getTimeslot().intersect(anotherTimeslot))
      return false;
    return getResource().equals(anotherEntry.getResource());
  }

  @Override
  public String toString() {
    return getName() + " " + getTimeslot().getStartTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " - "
      + getTimeslot().getEndTime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " " + getLocation().getName();
  }

}
