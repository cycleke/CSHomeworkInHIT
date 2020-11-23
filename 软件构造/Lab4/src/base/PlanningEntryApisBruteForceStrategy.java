package base;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * This class {@link PlanningEntryApisBruteForceStrategy} implies a series of API for {@link PlanningEntry}.
 *
 * @param <R> the type of resource
 * @author cycleke
 */
public class PlanningEntryApisBruteForceStrategy<R> implements PlanningEntryApis<R> {

  @Override
  public boolean checkLocationConflict(List<? extends PlanningEntry<R>> entries) {
    assert entries != null;
    for (ListIterator<? extends PlanningEntry<R>> iterator1 = entries.listIterator(); iterator1.hasNext(); ) {
      PlanningEntry<?> entry1 = iterator1.next();
      for (ListIterator<? extends PlanningEntry<R>> iterator2 = entries.listIterator(iterator1.nextIndex());
           iterator2.hasNext(); ) {
        PlanningEntry<R> entry2 = iterator2.next();
        if (entry1.conflictLocationWith(entry2)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean checkResourceExclusiveConflict(List<? extends PlanningEntry<R>> entries) {
    assert entries != null;
    for (ListIterator<? extends PlanningEntry<R>> iterator1 = entries.listIterator(); iterator1.hasNext(); ) {
      PlanningEntry<R> entry1 = iterator1.next();
      for (ListIterator<? extends PlanningEntry<R>> iterator2 = entries.listIterator(iterator1.nextIndex());
           iterator2.hasNext(); ) {
        PlanningEntry<R> entry2 = iterator2.next();
        if (entry1.conflictResourceWith(entry2)) {
          return true;
        }
      }
    }
    return false;
  }

  @Override
  @SuppressWarnings("unchecked")
  public PlanningEntry<R> findPreEntryPerResource(R r, PlanningEntry<R> e, List<? extends PlanningEntry<R>> entries) {
    if (r == null || e == null || entries == null) {
      return null;
    }
    PlanningEntry<R> resultEntry = null;
    LocalDateTime resultStartTime = null;

    List<PlanningEntry<R>> entriesContainsR = new ArrayList<>();
    for (PlanningEntry<R> entry : entries) {
      if (entry instanceof SingleResourceEntry) {
        SingleResourceEntry<R> singleResourceEntry = (SingleResourceEntry<R>) entry;
        if (r.equals(singleResourceEntry.getResource())) {
          entriesContainsR.add(entry);
        }
      } else if (entry instanceof MultipleSortedResourceEntry) {
        MultipleSortedResourceEntry<R> multipleSortedResourceEntry = (MultipleSortedResourceEntry<R>) e;
        if (multipleSortedResourceEntry.getResources().contains(r)) {
          entriesContainsR.add(entry);
        }
      }
    }

    for (PlanningEntry<R> entry : entriesContainsR) {
      if (e instanceof SingleTimeslotEntry) {
        if (!(entry instanceof SingleTimeslotEntry)) {
          continue;
        }
        SingleTimeslotEntry singleTimeslotEntry = (SingleTimeslotEntry) e;
        SingleTimeslotEntry anotherSingleTimeslotEntry = (SingleTimeslotEntry) entry;
        if (anotherSingleTimeslotEntry.getTimeslot().getEndTime()
          .compareTo(singleTimeslotEntry.getTimeslot().getStartTime()) <= 0) {
          if (resultEntry != null) {
            if (anotherSingleTimeslotEntry.getTimeslot().getStartTime()
              .compareTo(anotherSingleTimeslotEntry.getTimeslot().getStartTime()) <= 0) {
              resultEntry = entry;
            }
          } else {
            resultEntry = entry;
          }
        }
      } else if (e instanceof MultipleTimeslotEntry) {
        if (!(entry instanceof MultipleTimeslotEntry)) {
          continue;
        }
        MultipleTimeslotEntry multipleTimeslotEntry = (MultipleTimeslotEntry) e;

        List<Timeslot> timeslots = multipleTimeslotEntry.getTimeslots();
        LocalDateTime startTime = timeslots.get(0).getStartTime();

        MultipleTimeslotEntry anotherMultipleTimeslotEntry = (MultipleTimeslotEntry) entry;
        List<Timeslot> anotherTimeslots = anotherMultipleTimeslotEntry.getTimeslots();
        LocalDateTime anotherStartTime = anotherTimeslots.get(0).getStartTime();
        LocalDateTime anotherEndTime = anotherTimeslots.get(anotherTimeslots.size() - 1).getEndTime();
        if (anotherEndTime.compareTo(startTime) > 0) {
          continue;
        }
        if (resultEntry == null || anotherEndTime.compareTo(resultStartTime) > 0) {
          resultEntry = entry;
          resultStartTime = anotherStartTime;
        }
      }
    }
    return resultEntry;
  }

}
