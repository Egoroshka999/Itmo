package lab3.model.abstracts;

import java.util.ArrayList;
import java.util.List;
import lab3.model.interfaces.Modifyable;
import lab3.model.enums.ClothesModifier;

public abstract class Clothes implements Modifyable<ClothesModifier> {
	private List<ClothesModifier> modifiers = new ArrayList<ClothesModifier>();
	private String name;

	public Clothes(String name) {
		this.name = name;
	}

	public void addModifier(ClothesModifier modifier) {
		modifiers.add(modifier);
	}

	public void removeModifier(ClothesModifier modifier) {
		modifiers.remove(modifier);
	}

	@Override
	public boolean equals(Object b) {
		if(!(b instanceof Clothes)) return false;
		Clothes a = (Clothes)b;
		return this.name.equals(a.name) && this.modifiers.equals(a.modifiers);
	}

	@Override
	public String toString() {
		return this.name + " (" + modifiers.stream().map(item -> item.toString()).reduce("", (text, item) -> text += ", " + item).replaceFirst(", ", "") + ")";
	}

	@Override
	public int hashCode() {
		return this.name.hashCode() ^ this.modifiers.hashCode();
	}
}
