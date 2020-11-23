package train;

import java.util.ArrayList;

import base.BlockableEntry;
import base.IllegalStateTransitionException;

/**
 * This class {@link AllocatedState} is an immutable class to represent the state of a train plan allocated. 分配了一组车厢
 *
 * @author cycleke
 */
public class AllocatedState extends base.AllocatedState implements BlockableEntry {
  public final TrainPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link AllocatedState}
   *
   * @param entry
   *          the train plan entry
   */
  public AllocatedState(TrainPlanningEntry entry) {
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
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    entry.setState(new RunningState(entry));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    entry.setResources(new ArrayList<>());
    entry.setState(new CancelledState(entry));
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to BLOCK STATE", toString()));
  }

}
