package lab3.model.enums;

public enum ClothesModifier {
	TORN("Изорван"),
	DIRTY("Покрыт грязью"),
	LOOSE("Едва держится");

	private String name;

	private ClothesModifier(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
