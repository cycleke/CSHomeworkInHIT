package base;

import flight.Plane;

/**
 * This class {@link PlanningEntryAPIsBruteForceStrategyPlaneTest} test {@link PlanningEntryAPIsBruteForceStrategy}
 *
 * @author cycleke
 */
public class PlanningEntryAPIsBruteForceStrategyPlaneTest extends PlanningEntryAPIsPlaneTest {

  @Override
  public PlanningEntryAPIs<Plane> singleton() {
    return new PlanningEntryAPIsBruteForceStrategy<>();
  }

}
