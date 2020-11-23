package base;

/**
 * This class {@link CommonPlanningEntry} implies some basic functions of {@link PlanningEntry}
 *
 * @param <R>
 *          the resource of the plan
 * @author cycleke
 * @implNote Initialize LOCATION_SHAREABLE, RESOURCE_SHAREABLE and name.
 */
public abstract class CommonPlanningEntry<R> implements PlanningEntry<R> {

  protected EntryState state;
  protected String name;

  protected void checkRep() {
    assert state != null;
    assert name != null;
  }

  @Override
  public void allocated() throws IllegalStateTransitionException {
    state.allocated();
  }

  @Override
  public void run() throws IllegalStateTransitionException {
    state.run();
  }

  @Override
  public void cancel() throws IllegalStateTransitionException {
    state.cancel();
  }

  @Override
  public void end() throws IllegalStateTransitionException {
    state.end();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public EntryState getState() {
    return state;
  }

  @Override
  public void setState(EntryState state) throws IllegalStateTransitionException {
    this.state = state;
    checkRep();
  }

}
