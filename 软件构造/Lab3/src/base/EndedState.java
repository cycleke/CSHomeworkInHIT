package base;

/**
 * This class {@link EndedState} an immutable class to represent the state of a plan ended.
 *
 * @author cycleke
 */
public abstract class EndedState extends CommonEntryState {
  @Override
  public String toString() {
    return "ENDED STATE";
  }
}
