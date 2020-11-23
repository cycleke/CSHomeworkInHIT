package P2;

/**
 * Person
 */
public class Person {
	private String name;

	/**
	 * Person Constructor
	 *
	 * @param name the person's name
	 */
	public Person(String name) {
		this.name = name;
	}

	/**
	 * Get the name of the person.
	 *
	 * @return the name of the person
	 */
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object anObject) {
		if (anObject instanceof Person) {
			return name.equals(((Person)anObject).getName());
		}
		return false;
	}
}
