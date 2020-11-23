package train;

import base.BlockableEntry;
import base.IllegalStateTransitionException;

/**
 * This class {@link RunningState} an immutable class to represent the state of a train plan running. 已从起始站发车
 *
 * @author cycleke
 */
public class RunningState extends base.RunningState implements BlockableEntry {
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
   * @param entry
   *          the train plan entry
   */
  public RunningState(TrainPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
  }

  @Override
  public void end() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    if (entry.hasNextTimeslot())
      throw new IllegalStateTransitionException("Haven't arrived final station.");
    entry.moveToNextLocation();
    entry.setState(new EndedState(entry));
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
    if (!entry.hasNextLocation())
      throw new IllegalStateTransitionException("The entry doesn't have next station");
    entry.moveToNextLocation();
    entry.setState(new BlockedState(entry));
  }
}
