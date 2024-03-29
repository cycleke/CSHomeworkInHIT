package base;

import java.util.List;

/**
 * This class {@link PlanningEntryApisBruteForceStrategy} implies a series of API for {@link PlanningEntry}.
 *
 * @param <R> the type of resources
 * @author cycleke
 */
public interface PlanningEntryApis<R> {
  /**
   * Detect whether there is a position exclusive conflict between a group of plan entries: if two plan entries occupy
   * unsharable positions at the same time point, then there is a position conflict.
   *
   * @param entries the list of entries, not null
   * @return true if there is a conflict
   */
  boolean checkLocationConflict(List<? extends PlanningEntry<R>> entries);

  /**
   * Check whether there is exclusive resource conflict between a group of plan entries.
   *
   * @param entries the list of entries, not null
   * @return true if there is a conflict
   */
  boolean checkResourceExclusiveConflict(List<? extends PlanningEntry<R>> entries);

  /**
   * Extract pre-planned entries for specific resources: For a certain resource r and a certain plan entry e using r,
   * find the pre-order f of e from a set of plan entries, f also uses the resource r, and the execution time of f is
   * Before e, and between e and f, there are no other plan entries that use resource r. If there is no such plan entry
   * f, then return null. If there are multiple such f, just return any one of them.
   *
   * @param r       the certain resource, not null
   * @param e       the planning entry, not null
   * @param entries the list of planning entry, not null
   * @return the previous entry, null if not exist
   */
  PlanningEntry<R> findPreEntryPerResource(R r, PlanningEntry<R> e, List<? extends PlanningEntry<R>> entries);
}
