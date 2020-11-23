package course;

import base.IllegalStateTransitionException;

/**
 * This class {@link WaitingState} an immutable class to represent the state of a course plan waiting. 新建立了课程安排，并确定了时间地点
 *
 * @author cycleke
 */
public class WaitingState extends base.WaitingState {
  private final CoursePlanningEntry entry;

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
   *          the course plan entry
   */
  public WaitingState(CoursePlanningEntry entry) {
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
