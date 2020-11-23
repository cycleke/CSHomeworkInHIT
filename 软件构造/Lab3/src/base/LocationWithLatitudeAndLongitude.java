package base;

/**
 * This class {@link LocationWithLatitudeAndLongitude} is an implementation of {@link Location} with latitude and
 * longitude.
 *
 * @author cycleke
 * @implSpec The class must be immutable.
 */
public class LocationWithLatitudeAndLongitude extends CommonLocation {
  private final double latitude;
  private final double longitude;

  // Abstraction function:
  // AF(r) = {name, shareable, latitude, longitude}
  // Rep invariant:
  // name is not null, and -90 <= latitude <= 90, -180 <= longitude <= 180, or they are both NaN
  // Safety from rep exposure:
  // The field are all private and final. There are only getters, no setters.

  /**
   * Constructor of {@link LocationWithLatitudeAndLongitude}, if the latitude and longitude are unknown.
   *
   * @param name
   *          the name of the location, not null
   * @param shareable
   *          whether the location is shareable
   */
  public LocationWithLatitudeAndLongitude(String name, boolean shareable) {
    super(name, shareable);
    latitude = longitude = Double.NaN;
    checkRep();
  }

  /**
   * Obtains an instance of {@link LocationWithLatitudeAndLongitude}, if the latitude and longitude are unknown.
   *
   * @param name
   *          the name of the location, not null
   * @param shareable
   *          whether the location is shareable
   */
  public static LocationWithLatitudeAndLongitude of(String name, boolean shareable) {
    return new LocationWithLatitudeAndLongitude(name, shareable);
  }

  /**
   * Constructor of {@link LocationWithLatitudeAndLongitude}, if the latitude and longitude are known.
   *
   * @param name
   *          the name of the location, not null
   * @param shareable
   *          whether the location is shareable
   * @param latitude
   *          the latitude of the location, -90 <= latitude <= 90
   * @param longitude
   *          the longitude of the location, -180 <= longitude <= 180
   */
  public LocationWithLatitudeAndLongitude(String name, boolean shareable, double latitude, double longitude) {
    super(name, shareable);
    this.latitude = latitude;
    this.longitude = longitude;
    checkRep();
  }

  /**
   * Obtains an instance of {@link LocationWithLatitudeAndLongitude}, if the latitude and longitude are known.
   *
   * @param name
   *          the name of the location, not null
   * @param shareable
   *          whether the location is shareable
   * @param latitude
   *          the latitude of the location, -90 <= latitude <= 90
   * @param longitude
   *          the longitude of the location, -180 <= longitude <= 180
   */
  public static LocationWithLatitudeAndLongitude of(String name, boolean shareable, double latitude, double longitude) {
    return new LocationWithLatitudeAndLongitude(name, shareable, latitude, longitude);
  }

  protected void checkRep() {
    super.checkRep();
    if (Double.isNaN(latitude)) {
      assert Double.isNaN(longitude);
    } else {
      assert latitude >= -90 && latitude <= 90;
      assert longitude >= -180 && longitude <= 180;
    }
  }

  /**
   * Return the latitude of the location.
   *
   * @return the latitude of the location
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * Return the longitude of the location.
   *
   * @return the longitude of the location
   */
  public double getLongitude() {
    return longitude;
  }

  @Override
  public String toString() {
    if (Double.isNaN(latitude)) {
      return getName();
    } else {
      String northOrSouch, eastOrWest;
      double latitude = Math.abs(this.latitude), longitude = Math.abs(this.longitude);
      if (latitude == 0) {
        northOrSouch = "°";
      } else {
        northOrSouch = this.latitude > 0 ? "°N" : "°S";
      }
      if (longitude == 0) {
        eastOrWest = "°";
      } else {
        eastOrWest = this.longitude > 0 ? "°E" : "°W";
      }
      return getName() + String.format("(%f%s, %f%s)", latitude, northOrSouch, longitude, eastOrWest);
    }
  }
}
