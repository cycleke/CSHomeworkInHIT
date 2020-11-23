package train;

import base.BlockableEntry;
import base.IllegalStateTransitionException;

/**
 * This class {@link CancelledState} is an immutable class to represent the state of a train plan calcelled.
 * 高铁在起始站未发车之前可被取消，或者在某个中间站停车期间被取消
 *
 * @author cycleke
 */
public class CancelledState extends base.CancelledState implements BlockableEntry {
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
   * @param entry
   *          the train plan entry
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
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to BLOCK STATE", toString()));
  }
}
