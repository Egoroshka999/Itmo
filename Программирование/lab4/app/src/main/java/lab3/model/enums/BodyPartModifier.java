package lab3.model.enums;

public enum BodyPartModifier {
	NUMB("Отнялся"),
	BANDAGED("Перевязан"),
	NOT_FELT("Не чувствуется");

	private String name;
	
	private BodyPartModifier(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}