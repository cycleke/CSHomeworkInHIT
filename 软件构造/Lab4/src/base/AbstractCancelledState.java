package base;

/**
 * This class {@link AbstractCancelledState} an immutable class to represent the state of a plan cancelled.
 *
 * @author cycleke
 */
public abstract class AbstractCancelledState extends AbstractCommonEntryState {
  @Override
  public String toString() {
    return "CANCELLED STATE";
  }
}
