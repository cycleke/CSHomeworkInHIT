package base;

/**
 * This class {@link WaitingState} an immutable class to represent the state of a plan waiting.
 *
 * @author cycleke
 */
public abstract class WaitingState extends CommonEntryState {
  @Override
  public String toString() {
    return "WAITING STATE";
  }
}
