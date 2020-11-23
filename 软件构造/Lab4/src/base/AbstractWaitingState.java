package base;

/**
 * This class {@link AbstractWaitingState} an immutable class to represent the state of a plan waiting.
 *
 * @author cycleke
 */
public abstract class AbstractWaitingState extends AbstractCommonEntryState {
  @Override
  public String toString() {
    return "WAITING STATE";
  }
}
