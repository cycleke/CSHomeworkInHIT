package train;

import org.junit.Test;

import base.BlockableEntry;
import base.IllegalStateTransitionException;

/**
 * This class {@link BlockedState} an immutable class to represent the state of a train plan blocked. 已抵达某个中间站，并停车
 *
 * @author cycleke
 */
public class BlockedState extends base.BlockedState {
  private final TrainPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link BlockedState}
   *
   * @param entry
   *          the train plan entry
   */
  public BlockedState(TrainPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    entry.setState(new CancelledState(entry));
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    if (!entry.hasNextLocation())
      throw new IllegalStateTransitionException("The entry doesn't have next station");
    entry.moveToNextTimeslot();
    entry.setState(new RunningState(entry));
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
  }
}
