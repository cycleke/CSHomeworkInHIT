package base;

/**
 * This class {@link AbstractBlockedState} an immutable class to represent the state of a plan blocked.
 *
 * @author cycleke
 */
public abstract class AbstractBlockedState extends AbstractBlockableEntryState {
  @Override
  public String toString() {
    return "BLOCKED STATE";
  }
}
