package train;

import base.IllegalStateTransitionException;

import java.util.ArrayList;

/**
 * This class {@link AllocatedState} is an immutable class to represent the state of a train plan allocated. 分配了一组车厢
 *
 * @author cycleke
 */
public class AllocatedState extends base.AbstractAllocatedState {
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
   * @param entry the train plan entry
   */
  public AllocatedState(TrainPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void allocated() {
    assert entry.getState() == this;
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    entry.setState(new RunningState(entry));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    assert entry.getState() == this;
    entry.setResources(new ArrayList<>());
    entry.setState(new CancelledState(entry));
  }

}
