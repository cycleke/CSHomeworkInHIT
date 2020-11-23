package base;

/**
 * This class {@link BlockableEntryState} implies the basic functions of {@link EntryState} and {@link BlockableEntry}.
 *
 * @author cycleke
 * @implNote This class always throw {@link IllegalStateTransitionException}, the sub class must override the legal
 *           methods.
 */
public abstract class BlockableEntryState extends CommonEntryState implements BlockableEntry {

  @Override
  public void block() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to BLOCKED STATE", toString()));
  }

}
