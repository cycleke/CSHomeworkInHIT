package base;

/**
 * This class {@link SingleResourceEntryImpl} implies the basic functions of {@link PlanningEntry}.
 *
 * @param <R>
 *          the type of resource
 * @author cycleke
 */
public class SingleResourceEntryImpl<R> implements SingleResourceEntry<R> {
  private R resource;

  // Abstraction function:
  // AF(i) = resource
  // Rep invariant:
  // true
  // Safety from rep exposure:
  // the field are all private.

  public SingleResourceEntryImpl() {
    resource = null;
  }

  protected void checkRep() {}

  @Override
  public R getResource() {
    return resource;
  }

  @Override
  public void setResource(R resource) {
    this.resource = resource;
    checkRep();
  }

}
