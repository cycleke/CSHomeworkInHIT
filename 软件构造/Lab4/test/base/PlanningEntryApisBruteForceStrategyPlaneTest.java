package base;

import flight.Plane;

/**
 * This class {@link PlanningEntryApisBruteForceStrategyPlaneTest} test {@link PlanningEntryApisBruteForceStrategy}
 *
 * @author cycleke
 */
public class PlanningEntryApisBruteForceStrategyPlaneTest extends PlanningEntryApisPlaneTest {

  @Override
  public PlanningEntryApis<Plane> singleton() {
    return new PlanningEntryApisBruteForceStrategy<>();
  }

}
