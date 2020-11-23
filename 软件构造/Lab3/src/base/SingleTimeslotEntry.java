package base;

/**
 * This class {@link SingleTimeslotEntry} is an interface for {@link PlanningEntry} that has singal timeslot.
 *
 * @author cycleke
 */
public interface SingleTimeslotEntry {
  /**
   * Set the timeslot of the entry.
   *
   * @param timeslot
   *          the timeslot of this entry, not null
   */
  void setTimeslot(Timeslot timeslot);

  /**
   * Return the timeslot of the entry.
   *
   * @return the timeslot of this entry
   */
  Timeslot getTimeslot();
}
