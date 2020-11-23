package train;

/**
 * This class {@link CancelledState} is an immutable class to represent the
 * state of a train plan cancelled. 高铁在起始站未发车之前可被取消，或者在某个中间站停车期间被取消
 *
 * @author cycleke
 */
public class CancelledState extends base.AbstractCancelledState {
  private final TrainPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link CancelledState}
   *
   * @param entry the train plan entry
   */
  public CancelledState(TrainPlanningEntry entry) {
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
