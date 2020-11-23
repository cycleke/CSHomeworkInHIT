package train;

import base.IllegalStateTransitionException;
import org.apache.log4j.Logger;

/**
 * This class {@link BlockedState} an immutable class to represent the state of
 * a train plan blocked. 已抵达某个中间站，并停车
 *
 * @author cycleke
 */
public class BlockedState extends base.AbstractBlockedState {
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
   * @param entry the train plan entry
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
    assert entry.getState() == this;
    entry.setState(new CancelledState(entry));
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    if (!entry.hasNextLocation()) {
      Logger.getRootLogger().error("Fail to run an ended entry");
      throw new IllegalStateTransitionException("The entry doesn't have next station");
    }
    entry.moveToNextTimeslot();
    entry.setState(new RunningState(entry));
  }

  @Override
  public void block() {
    assert entry.getState() == this;
  }
}
