package train;

import base.IllegalStateTransitionException;
import org.apache.log4j.Logger;

/**
 * This class {@link WaitingState} an immutable class to represent the state of
 * a train plan waiting. 车次信息已确定，但未分配车厢
 *
 * @author cycleke
 */
public class WaitingState extends base.AbstractWaitingState {
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
   * @param entry the train plan entry
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
    assert entry.getState() == this;
    if (entry.getResources().isEmpty()) {
      Logger.getRootLogger().error("Fail to set a entry to ALLOCATED STATE without resource");
      throw new IllegalStateTransitionException("The entry is not allocated with resource!!!");
    }
    entry.setState(new AllocatedState(entry));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    entry.setState(new CancelledState(entry));
  }

}
