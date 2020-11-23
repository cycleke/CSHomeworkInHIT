package base;

/**
 * This class {@link AbstractEndedState} an immutable class to represent the state of a plan ended.
 *
 * @author cycleke
 */
public abstract class AbstractEndedState extends AbstractCommonEntryState {
  @Override
  public String toString() {
    return "ENDED STATE";
  }
}
