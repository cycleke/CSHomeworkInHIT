package train;

import base.BlockableEntry;
import base.IllegalStateTransitionException;

/**
 * This class {@link EndedState} an immutable class to represent the state of a train plan ended. 抵达终点站
 *
 * @author cycleke
 */
public class EndedState extends base.EndedState implements BlockableEntry {
  private final TrainPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link EndedState}
   *
   * @param entry
   *          the train plan entry
   */
  public EndedState(TrainPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void end() throws IllegalStateTransitionException {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
  }

  @Override
  public void block() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to BLOCK STATE", toString()));
  }
}
