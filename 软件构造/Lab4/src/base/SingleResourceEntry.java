package base;

/**
 * This class {@link SingleResourceEntry} is an interface for {@link PlanningEntry}. The number of the
 * resource is one.
 *
 * @param <R> the type of resource
 * @author cycleke
 */
public interface SingleResourceEntry<R> {

  /**
   * Return the single resource.
   *
   * @return the single resource
   */
  R getResource();

  /**
   * Set the resource of the entry.
   *
   * @param resource the resource to set
   */
  void setResource(R resource);
}
