package lab3.model.enums;

public enum HumanEffect {
	NAUSEA("Тошнота"), FEAR("Страх"), LIGHTNESS("Легкость"), SHABBY("Потрепанность");

	private String name;
	
	private HumanEffect(String name) {
		this.name = name;
	}

	public String toString() {
		return this.name;
	}
}
