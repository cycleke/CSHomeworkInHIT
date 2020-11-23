package flight;

import base.IllegalStateTransitionException;

/**
 * This class {@link WaitingState} an immutable class to represent the state of a flight plan waiting. 航班其他信息均已确定，但未分配飞机
 *
 * @author cycleke
 */
public class WaitingState extends base.WaitingState {
  private final FlightPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link WaitingState}
   *
   * @param entry
   *          the flight plan entry
   */
  public WaitingState(FlightPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void allocated() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    if (entry.getResource() == null)
      throw new IllegalStateTransitionException("The entry is not allocated with resource!!!");
    entry.setState(new AllocatedState(entry));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    entry.setState(new CancelledState(entry));
  }

}
