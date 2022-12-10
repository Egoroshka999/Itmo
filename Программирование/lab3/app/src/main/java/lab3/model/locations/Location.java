package lab3.model.locations;

public class Location {
	String name;

	public Location(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object b) {
		if(!(b instanceof Location)) return false;
		Location a = (Location)b;
		return name.equals(a.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
