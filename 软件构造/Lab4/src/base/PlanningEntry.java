package base;

/**
 * This class {@link PlanningEntry} is an interface to represent a single plan.
 *
 * @param <R> the resource of the plan
 * @author cycleke
 */
public interface PlanningEntry<R> {

  /**
   * Allocate the resource of the plan. The resource of the plan must be allocated.
   *
   * @throws IllegalStateTransitionException if the plan can't be allocated
   */
  void allocated() throws IllegalStateTransitionException;

  /**
   * Run the plan. The plan must be ready to start, like in state ALLOCATED.
   *
   * @throws IllegalStateTransitionException if the plan is not ready to start
   */
  void run() throws IllegalStateTransitionException;

  /**
   * Cancel the plan. The plan must be able to cancel, like in state RUNNING.
   *
   * @throws IllegalStateTransitionException if the plan is unable to cancel
   */
  void cancel() throws IllegalStateTransitionException;

  /**
   * End the plan. The plan must be running before.
   *
   * @throws IllegalStateTransitionException if the plan is not in state RUNNING before
   */
  void end() throws IllegalStateTransitionException;

  /**
   * Return the name of the plan.
   *
   * @return the name of the plan
   */
  String getName();

  /**
   * Return the state of the plan.
   *
   * @return the state of the plan
   */
  EntryState getState();

  /**
   * Change the state of the plan.
   *
   * @param state the next state
   * @throws IllegalStateTransitionException if the parameter state is unreachable from current state
   */
  void setState(EntryState state) throws IllegalStateTransitionException;

  /**
   * Return current timeslot of the plan.
   *
   * @return the current timeslot of the plan
   */
  Timeslot getCurrentTimeslot();

  /**
   * Return current location of the plan.
   *
   * @return the current location of the plan
   */
  Location getCurrentLocation();

  /**
   * Check whether there is exclusive resource conflict with another entry.
   *
   * @param entry the entry to check
   * @return true if a conflict exists
   */
  boolean conflictResourceWith(PlanningEntry<?> entry);

  /**
   * Check whether there is exclusive location conflict with another entry.
   *
   * @param entry the entry to check
   * @return true if a conflict exists
   */
  boolean conflictLocationWith(PlanningEntry<?> entry);
}
