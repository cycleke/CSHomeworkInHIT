package course;

import base.IllegalStateTransitionException;

/**
 * This class {@link AllocatedState} is an immutable class to represent the state of a course plan allocated. 为某课程安排了教师
 *
 * @author cycleke
 */
public class AllocatedState extends base.AbstractAllocatedState {
  public final CoursePlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link AllocatedState}
   *
   * @param entry the course plan entry
   */
  public AllocatedState(CoursePlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void allocated() {
    assert entry.getState() == this;
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    entry.setState(new RunningState(entry));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    entry.setResource(null);
    entry.setState(new CancelledState(entry));
  }

}
