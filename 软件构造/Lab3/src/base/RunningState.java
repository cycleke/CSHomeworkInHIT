package base;

/**
 * This class {@link RunningState} an immutable class to represent the state of a plan running.
 *
 * @author cycleke
 */
public abstract class RunningState extends CommonEntryState {
  @Override
  public String toString() {
    return "RUNNING STATE";
  }
}
