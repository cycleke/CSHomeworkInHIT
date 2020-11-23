package flight;

import base.AbstractAllocatedState;
import base.IllegalStateTransitionException;

/**
 * This class {@link AllocatedState} is an immutable class to represent the state of a flight plan allocated.
 * 已分配具体飞机，但未从出发机场起飞
 *
 * @author cycleke
 */
public class AllocatedState extends AbstractAllocatedState {
  public final FlightPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link AllocatedState}
   *
   * @param entry the flight plan entry
   */
  public AllocatedState(FlightPlanningEntry entry) {
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
    entry.setResource(null);
    entry.setState(new CancelledState(entry));
  }

}
