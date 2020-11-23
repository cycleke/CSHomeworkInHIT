package course;

/**
 * This class {@link EndedState} an immutable class to represent the state of a
 * course plan ended. 下课
 *
 * @author cycleke
 */
public class EndedState extends base.AbstractEndedState {
  private final CoursePlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link EndedState}
   *
   * @param entry the course plan entry
   */
  public EndedState(CoursePlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void end() {
    assert entry.getState() == this;
  }
}
