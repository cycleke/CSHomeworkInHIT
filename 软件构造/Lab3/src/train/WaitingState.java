package train;

import base.BlockableEntry;
import base.IllegalStateTransitionException;

/**
 * This class {@link WaitingState} an immutable class to represent the state of a train plan waiting. 车次信息已确定，但未分配车厢
 *
 * @author cycleke
 */
public class WaitingState extends base.WaitingState implements BlockableEntry {
  private final TrainPlanningEntry entry;

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
   *          the train plan entry
   */
  public WaitingState(TrainPlanningEntry entry) {
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
    if (entry.getResources().isEmpty())
      throw new IllegalStateTransitionException("The entry is not allocated with resource!!!");
    entry.setState(new AllocatedState(entry));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    entry.setState(new CancelledState(entry));
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to BLOCK STATE", toString()));
  }
}
