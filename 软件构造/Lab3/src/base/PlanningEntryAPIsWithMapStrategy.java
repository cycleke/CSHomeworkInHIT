package base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class {@link PlanningEntryAPIsWithMapStrategy} implies a series of API for {@link PlanningEntry}.
 *
 * @param <R>
 *          the type of resource
 * @author cycleke
 */
public class PlanningEntryAPIsWithMapStrategy<R> extends PlanningEntryAPIsBruteForceStrategy<R> {

  @Override
  @SuppressWarnings("unchecked")
  public boolean checkResourceExclusiveConflict(List<? extends PlanningEntry<R>> entries) {
    Map<R, List<PlanningEntry<R>>> resourceMap = new HashMap<>();
    for (PlanningEntry<R> entry : entries) {
      if (entry instanceof SingleResourceEntry) {
        SingleResourceEntry<R> singleResourceEntry = (SingleResourceEntry<R>)entry;
        R resource = singleResourceEntry.getResource();
        List<PlanningEntry<R>> list;
        if (resourceMap.containsKey(resource)) {
          list = resourceMap.get(resource);
        } else {
          list = new ArrayList<>();
          resourceMap.put(resource, list);
        }
        list.add(entry);
      } else if (entry instanceof MultipleSortedResourceEntry) {
        MultipleSortedResourceEntry<R> multipleSortedResourceEntry = (MultipleSortedResourceEntry<R>)entry;
        for (R resource : multipleSortedResourceEntry.getResources()) {
          List<PlanningEntry<R>> list;
          if (resourceMap.containsKey(resource)) {
            list = resourceMap.get(resource);
          } else {
            list = new ArrayList<>();
            resourceMap.put(resource, list);
          }
          list.add(entry);
        }
      }
    }
    for (List<PlanningEntry<R>> list : resourceMap.values()) {
      int n = list.size();
      for (int i = 0; i < n; ++i)
        for (int j = i + 1; j < n; ++j)
          if (list.get(i).conflictResourceWith(list.get(j)))
            return true;
    }
    return false;
  }

}
