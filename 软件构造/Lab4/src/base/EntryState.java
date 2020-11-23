package base;

/**
 * This class {@link EntryState} is an immutable interface to represent the state of a {@link PlanningEntry}.
 *
 * @author cycleke
 * @implSpec the class must be immutable.
 */
public interface EntryState {

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
}
