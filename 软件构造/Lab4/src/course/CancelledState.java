package course;

/**
 * This class {@link CancelledState} is an immutable class to represent the
 * state of a course plan cancelled. 该次课程启动之前可被取消
 *
 * @author cycleke
 */
public class CancelledState extends base.AbstractCancelledState {
  private final CoursePlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link CancelledState}
   *
   * @param entry the course plan entry
   */
  public CancelledState(CoursePlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void cancel() {
    assert entry.getState() == this;
  }
}
