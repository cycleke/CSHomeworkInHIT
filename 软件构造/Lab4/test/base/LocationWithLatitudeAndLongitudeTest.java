package base;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This class {@link LocationWithLatitudeAndLongitudeTest} tests {@link LocationWithLatitudeAndLongitude}.
 *
 * @author cycleke
 */
public class LocationWithLatitudeAndLongitudeTest extends CommonLocationTest {

  @Override
  public Location constructor(String name, boolean shareable) {
    return new LocationWithLatitudeAndLongitude(name, shareable);
  }

  public LocationWithLatitudeAndLongitude constructor(String name, boolean shareable, double latitude,
                                                      double longitude) {
    return new LocationWithLatitudeAndLongitude(name, shareable, latitude, longitude);
  }

  @Override
  public Location ofMethod(String name, boolean shareable) {
    return LocationWithLatitudeAndLongitude.of(name, shareable);
  }

  public LocationWithLatitudeAndLongitude ofMethod(String name, boolean shareable, double latitude, double longitude) {
    return LocationWithLatitudeAndLongitude.of(name, shareable, latitude, longitude);
  }

  /**
   * Tests {@link LocationWithLatitudeAndLongitude#LocationWithLatitudeAndLongitude(String, boolean, double, double)}
   *
   * <p>
   * Testing strategy
   * </p>
   * <p>
   * Partition the inputs as follows:
   * <q>the length of name: 0, 1, > 1<\q>
   * <q>the value of shareable: true, false<\q>
   * <q>the value of latitude: -90, (-90, -1), -1, 0, 1, (1, 90), 90<\q>
   * <q>the value of longitude: -180, (-180, -1), -1, 0, 1, (1, 180), 180<\q>
   */
  @Test
  public void testConstructor() {
    super.testConstructor();
    String[] names = new String[]{"", "a", "New York"};
    boolean[] shareables = new boolean[]{true, false};
    double[] latitudes = new double[]{-90, -45.23, -1, 0, 1, 78.90, 80, 90};
    double[] longitudes = new double[]{-180, -3.2222, -1, 0, 1, 22, 34.0, 180};
    for (String name : names)
      for (boolean shareable : shareables)
        for (double latitude : latitudes)
          for (double longitude : longitudes)
            assertNotNull(constructor(name, shareable, latitude, longitude));
  }

  /**
   * Tests {@link LocationWithLatitudeAndLongitude#of(String, boolean, double, double)}
   *
   * <p>
   * Testing strategy
   * </p>
   * <p>
   * Partition the inputs as follows:
   * <q>the length of name: 0, 1, > 1<\q>
   * <q>the value of shareable: true, false<\q>
   * <q>the value of latitude: -90, (-90, -1), -1, 0, 1, (1, 90), 90<\q>
   * <q>the value of longitude: -180, (-180, -1), -1, 0, 1, (1, 180), 180<\q>
   */
  @Test
  public void testOf() {
    super.testOf();
    String[] names = new String[]{"", "a", "New York"};
    boolean[] shareables = new boolean[]{true, false};
    double[] latitudes = new double[]{-90, -45.23, -1, 0, 1, 78.90, 80, 90};
    double[] longitudes = new double[]{-180, -3.2222, -1, 0, 1, 22, 34.0, 180};
    for (String name : names)
      for (boolean shareable : shareables)
        for (double latitude : latitudes)
          for (double longitude : longitudes)
            assertNotNull(ofMethod(name, shareable, latitude, longitude));
  }

  /**
   * Tests {@link LocationWithLatitudeAndLongitude#getLatitude()} and
   * {@link LocationWithLatitudeAndLongitude#getLongitude()}
   */
  @Test
  public void testGetters() {
    String[] names = new String[]{"", "a", "New York"};
    boolean[] shareables = new boolean[]{true, false};
    double[] latitudes = new double[]{-90, -45.23, -1, 0, 1, 78.90, 80, 90};
    double[] longitudes = new double[]{-180, -3.2222, -1, 0, 1, 22, 34.0, 180};
    LocationWithLatitudeAndLongitude location;
    for (String name : names)
      for (boolean shareable : shareables) {
        location = (LocationWithLatitudeAndLongitude) ofMethod(name, shareable);
        assertTrue(Double.isNaN(location.getLatitude()));
        assertTrue(Double.isNaN(location.getLongitude()));
        for (double latitude : latitudes)
          for (double longitude : longitudes) {
            location = ofMethod(name, shareable, latitude, longitude);
            assertEquals(latitude, location.getLatitude(), 0);
            assertEquals(longitude, location.getLongitude(), 0);
          }
      }
  }

  /**
   * Tests {@link LocationWithLatitudeAndLongitude#equals(Object)} and
   * {@link LocationWithLatitudeAndLongitude#hashCode()}
   */
  @Test
  public void testEqualsAndHashCode() {
    super.testEqualsAndHashCode();
    LocationWithLatitudeAndLongitude location1 = ofMethod("Harbin", false, 10, -1);
    LocationWithLatitudeAndLongitude location2 = ofMethod("Harbin", true, 20, 111);
    CommonLocation location3 = CommonLocation.of("Harbin", false);
    assertEquals(location1, location1);
    assertEquals(location1, location2);
    assertEquals(location1, location3);

    assertEquals(location1.hashCode(), location2.hashCode());
    assertEquals(location1.hashCode(), location3.hashCode());
  }

  /**
   * Tests {@link LocationWithLatitudeAndLongitude#toString()}
   */
  @Test
  public void testToString() {
    super.testToString();
    assertEquals("", ofMethod("", false).toString());
    assertEquals("a", ofMethod("a", true).toString());
    assertEquals("Harbin", ofMethod("Harbin", true).toString());
    assertEquals("Harbin(45.801389°N, 126.529167°E)", ofMethod("Harbin", true, 45.801389, 126.529167).toString());

    assertEquals("ABC(0.000000°, 0.000000°)", ofMethod("ABC", true, 0, 0).toString());
    assertEquals("ABC(0.000000°, 1.000000°W)", ofMethod("ABC", false, 0, -1).toString());
    assertEquals("ABC(1.000000°N, 12.010000°E)", ofMethod("ABC", false, 1, 12.01).toString());
    assertEquals("ABC(1.000000°S, 180.000000°W)", ofMethod("ABC", false, -1, -180).toString());
  }
}
