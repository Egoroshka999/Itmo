package lab3.model.enums;

public enum Side {
	RIGHT("Правый"), LEFT("Левый");

	private String name;
	
	private Side(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}