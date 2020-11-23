package base;

/**
 * This class {@link AbstractAllocatedState} is an immutable class to represent the state of a plan allocated.
 *
 * @author cycleke
 */
public abstract class AbstractAllocatedState extends AbstractCommonEntryState {
  @Override
  public String toString() {
    return "ALLOCATED STATE";
  }
}
