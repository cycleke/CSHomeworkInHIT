package base;

import org.apache.log4j.Logger;

/**
 * This class {@link AbstractCommonEntryState} implies the basic functions of {@link EntryState}.
 *
 * @author cycleke
 * @implNote This class always throw {@link IllegalStateTransitionException}, the sub class must override the legal
 * methods.
 */
public abstract class AbstractCommonEntryState implements EntryState {

  @Override
  public void allocated() throws IllegalStateTransitionException {
    String message = String.format("Can't move from %s to ALLOCATED STATE", toString());
    Logger.getRootLogger().error(message);
    throw new IllegalStateTransitionException(message);
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    String message = String.format("Can't move from %s to RUNNING STATE", toString());
    Logger.getRootLogger().error(message);
    throw new IllegalStateTransitionException(message);
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    String message = String.format("Can't move from %s to CANCELLED STATE", toString());
    Logger.getRootLogger().error(message);
    throw new IllegalStateTransitionException(message);
  }

  @Override
  public void end() throws IllegalStateTransitionException {
    String message = String.format("Can't move from %s to ENDED STATE", toString());
    Logger.getRootLogger().error(message);
    throw new IllegalStateTransitionException(message);
  }

}
