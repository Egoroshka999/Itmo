package lab3.model.abstracts;

import java.util.ArrayList;
import java.util.List;
import lab3.model.interfaces.Modifyable;
import lab3.model.enums.BodyPartModifier;


public abstract class BodyPart implements Modifyable<BodyPartModifier> {
	private List<BodyPartModifier> modifiers = new ArrayList<BodyPartModifier>();
	private List<Clothes> clothes = new ArrayList<Clothes>(); 
	private String name;

	public BodyPart(String name) {
		this.name = name;
	}

	public void addModifier(BodyPartModifier modifier) {
		modifiers.add(modifier);
	}

	public void removeModifier(BodyPartModifier modifier) {
		modifiers.remove(modifier);
	}

	public BodyPartModifier[] getModifiers() {
		return this.modifiers.toArray(new BodyPartModifier[0]);
	}

	public void addClothes(Clothes clothes) {
		this.clothes.add(clothes);
	}

	public void removeClothes(Clothes clothes) {
		this.clothes.remove(clothes);
	}

	public Clothes[] getClothes() {
		return clothes.toArray(new Clothes[0]);
	}

	@Override
	public String toString() {
		return name + " (" + modifiers.stream().map(item -> item.toString()).reduce("", (text, item) -> text += ", " + item).replaceFirst(", ", "") +")" + " (" + clothes.stream().map(item -> item.toString()).reduce("", (text, item) -> text += ", " + item).replaceFirst(", ", "") + ")";
	}

	@Override
	public boolean equals(Object b) {
		if(!(b instanceof BodyPart)) return false;
		BodyPart a = (BodyPart)b;
		return name.equals(a.name) && modifiers.equals(a.modifiers) && clothes.equals(a.clothes);
	}

	@Override
	public int hashCode() {
		return name.hashCode() ^ clothes.hashCode() ^ modifiers.hashCode();
	}
}
