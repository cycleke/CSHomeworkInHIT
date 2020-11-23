package base;

import flight.Plane;

/**
 * This class {@link PlanningEntryApisWithMapStrategyPlaneTest} test {@link PlanningEntryApisBruteForceStrategy}
 *
 * @author cycleke
 */
public class PlanningEntryApisWithMapStrategyPlaneTest extends PlanningEntryApisPlaneTest {

  @Override
  public PlanningEntryApis<Plane> singleton() {
    return new PlanningEntryApisWithMapStrategy<>();
  }

}
