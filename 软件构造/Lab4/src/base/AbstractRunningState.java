package base;

/**
 * This class {@link AbstractRunningState} an immutable class to represent the state of a plan running.
 *
 * @author cycleke
 */
public abstract class AbstractRunningState extends AbstractCommonEntryState {
  @Override
  public String toString() {
    return "RUNNING STATE";
  }
}
