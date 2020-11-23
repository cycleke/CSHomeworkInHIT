package flight;

/**
 * This class {@link CancelledState} is an immutable class to represent the state of a flight plan calcelled.
 * 航班未起飞之前可被取消，不再执飞
 *
 * @author cycleke
 */
public class CancelledState extends base.CancelledState {
  private final FlightPlanningEntry entry;

  // Abstraction function:
  // AF(s) = the state of entry
  // Rep invariant:
  // The entry not null.
  // Safety from rep exposure:
  // The field are all private and final.

  /**
   * The Constructor of {@link CancelledState}
   *
   * @param entry
   *          the flight plan entry
   */
  public CancelledState(FlightPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void cancel() {
    if (entry.getState() != this)
      throw new RuntimeException("The state of the entry is not this now.");
  }
}
