package base;

import flight.Plane;

/**
 * This class {@link PlanningEntryAPIsWithMapStrategyPlaneTest} test {@link PlanningEntryAPIsBruteForceStrategy}
 *
 * @author cycleke
 */
public class PlanningEntryAPIsWithMapStrategyPlaneTest extends PlanningEntryAPIsPlaneTest {

  @Override
  public PlanningEntryAPIs<Plane> singleton() {
    return new PlanningEntryAPIsWithMapStrategy<>();
  }

}
