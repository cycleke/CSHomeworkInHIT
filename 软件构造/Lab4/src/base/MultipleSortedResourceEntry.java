package base;

import java.util.List;

/**
 * This class {@link MultipleLocationsEntry} is an interface for {@link PlanningEntry}. The number of the resource may be
 * greater than one. The resources of the entry are arranged in order.
 *
 * @param <R> the type of resource
 * @author cycleke
 */
public interface MultipleSortedResourceEntry<R> {
  /**
   * Return resources of the entry.
   *
   * @return the resources of the entry
   */
  List<R> getResources();

  /**
   * Set the resource of the entry.
   *
   * @param resources the resource to set, not null
   */
  void setResources(List<R> resources);
}
