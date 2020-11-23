package base;

/**
 * This class {@link BlockedState} an immutable class to represent the state of a plan blocked.
 *
 * @author cycleke
 */
public abstract class BlockedState extends BlockableEntryState {
  @Override
  public String toString() {
    return "BLOCKED STATE";
  }
}
