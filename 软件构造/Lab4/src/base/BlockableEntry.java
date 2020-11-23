package base;

/**
 * This class {@link BlockableEntry} is an interface for {@link PlanningEntry} that are blockable. The number of
 * {@link Timeslot} belong to the entry may greater than one.
 *
 * @author cycleke
 */
public interface BlockableEntry {
  /**
   * Block the entry. The entry must be running now.
   *
   * @throws IllegalStateTransitionException if the entry aren't running now
   */
  void block() throws IllegalStateTransitionException;
}
