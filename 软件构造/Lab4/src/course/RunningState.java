package course;

import base.IllegalStateTransitionException;

/**
 * This class {@link RunningState} an immutable class to represent the state of
 * a course plan running. 上课
 *
 * @author cycleke
 */
public class RunningState extends base.AbstractRunningState {
  private final CoursePlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link RunningState}
   *
   * @param entry the course plan entry
   */
  public RunningState(CoursePlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void run() {
    assert entry.getState() == this;
  }

  @Override
  public void end() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    entry.setState(new EndedState(entry));
  }
}
