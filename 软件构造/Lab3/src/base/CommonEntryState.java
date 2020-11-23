package base;

/**
 * This class {@link CommonEntryState} implies the basic functions of {@link EntryState}.
 *
 * @author cycleke
 * @implNote This class always throw {@link IllegalStateTransitionException}, the sub class must override the legal
 *           methods.
 */
public abstract class CommonEntryState implements EntryState {

  @Override
  public void allocated() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to ALLOCATED STATE", toString()));
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to RUNNING STATE", toString()));
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to CANCELLED STATE", toString()));
  }

  @Override
  public void end() throws IllegalStateTransitionException {
    throw new IllegalStateTransitionException(String.format("Can't move from %s to ENDED STATE", toString()));
  }

}
