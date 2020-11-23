package base;

import java.util.List;

/**
 * This class {@link MultipleTimeslotEntry} is an interface for {@link PlanningEntry} that has multiple timeslots.
 *
 * @author cycleke
 */
public interface MultipleTimeslotEntry {
  /**
   * Set the timeslot of the entry.
   *
   * @param timeslots
   *          the timeslot of this entry, not null
   */
  void setTimeslots(List<Timeslot> timeslots);

  /**
   * Return the timeslots of the entry.
   *
   * @return the timeslots of this entry
   */
  List<Timeslot> getTimeslots();
}
