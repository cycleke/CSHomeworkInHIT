package base;

/**
 * This class {@link CancelledState} an immutable class to represent the state of a plan cancelled.
 *
 * @author cycleke
 */
public abstract class CancelledState extends CommonEntryState {
  @Override
  public String toString() {
    return "CANCELLED STATE";
  }
}
