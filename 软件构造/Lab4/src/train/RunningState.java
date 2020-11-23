package train;

import base.BlockableEntry;
import base.IllegalStateTransitionException;
import org.apache.log4j.Logger;

/**
 * This class {@link RunningState} an immutable class to represent the state of
 * a train plan running. 已从起始站发车
 *
 * @author cycleke
 */
public class RunningState extends base.AbstractRunningState implements BlockableEntry {
  private final TrainPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link RunningState}
   *
   * @param entry the train plan entry
   */
  public RunningState(TrainPlanningEntry entry) {
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
    if (entry.hasNextTimeslot()) {
      Logger.getRootLogger().error("Fail to end an unfinished entry.");
      throw new IllegalStateTransitionException("Haven't arrived final station.");
    }
    entry.moveToNextLocation();
    entry.setState(new EndedState(entry));
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    if (!entry.hasNextLocation()) {
      Logger.getRootLogger().error("Fail to block an entry having next station.");
      throw new IllegalStateTransitionException("The entry doesn't have next station");
    }
    entry.moveToNextLocation();
    entry.setState(new BlockedState(entry));
  }
}
