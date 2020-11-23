package base;

/**
 * This class {@link AbstractBlockableEntryState} implies the basic functions of
 * {@link EntryState} and {@link BlockableEntry}.
 *
 * @author cycleke
 * @implNote This class always throw {@link IllegalStateTransitionException},
 * the sub class must override the legal methods.
 */
public abstract class AbstractBlockableEntryState extends AbstractCommonEntryState implements BlockableEntry {
}
