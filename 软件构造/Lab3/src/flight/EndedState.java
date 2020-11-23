package flight;

import base.IllegalStateTransitionException;

/**
 * This class {@link EndedState} an immutable class to represent the state of a flight plan ended. 航班已降落目的地机场
 *
 * @author cycleke
 */
public class EndedState extends base.EndedState {
  private final FlightPlanningEntry entry;

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
   *          the flight plan entry
   */
  public EndedState(FlightPlanningEntry entry) {
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
}
