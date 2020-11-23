package course;

/**
 * This class {@link Teacher} is an immutable class to represent a teacher.
 *
 * @author cycleke
 */
public class Teacher {
  private final String idNumber, name, sex, title;

  // Abstraction function:
  // AF(p) = {id number, name, sex, title}
  // Rep invariant:
  // All fields are not null
  // Safety from rep exposure:
  // the field are all private and final. There are only getters, no setters.

  /**
   * Constructor of {@link Teacher}.
   *
   * @param idNumber the id number of the teacher, not null
   * @param name     the name of the teacher, not null
   * @param sex      the sex of the teacher, not null
   * @param title    the professional title of the teacher, not null
   */
  public Teacher(String idNumber, String name, String sex, String title) {
    this.idNumber = idNumber;
    this.name = name;
    this.sex = sex;
    this.title = title;
    checkRep();
  }

  protected void checkRep() {
    assert idNumber != null;
    assert name != null;
    assert sex != null;
    assert title != null;
  }

  public String getTitle() {
    return title;
  }

  public String getSex() {
    return sex;
  }

  public String getName() {
    return name;
  }

  public String getIdNumber() {
    return idNumber;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((idNumber == null) ? 0 : idNumber.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Teacher other = (Teacher) obj;
    return idNumber.equals(other.idNumber);
  }

  @Override
  public String toString() {
    return name + " [id number=" + idNumber + ", sex=" + sex + ", title=" + title + "]";
  }

}
