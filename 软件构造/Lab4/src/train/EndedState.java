package train;

/**
 * This class {@link EndedState} an immutable class to represent the state of a
 * train plan ended. 抵达终点站
 *
 * @author cycleke
 */
public class EndedState extends base.AbstractEndedState {
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
   * @param entry the train plan entry
   */
  public EndedState(TrainPlanningEntry entry) {
    this.entry = entry;
    checkRep();
  }

  protected void checkRep() {
    assert entry != null;
  }

  @Override
  public void end() {
    assert entry.getState() == this;
  }

}
