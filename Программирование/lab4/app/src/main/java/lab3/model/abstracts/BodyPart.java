package lab3.model.abstracts;

import java.util.ArrayList;
import java.util.List;
import lab3.model.interfaces.Modifyable;
import lab3.model.enums.BodyPartModifier;
import lab3.model.enums.Side;
import lab3.model.exceptions.UnwearError;


public abstract class BodyPart implements Modifyable<BodyPartModifier> {
	private List<BodyPartModifier> modifiers = new ArrayList<BodyPartModifier>();
	private List<Clothes> clothes = new ArrayList<Clothes>(); 
	private String name;

	static public class Arm extends BodyPart {
		private Side side;
	
		public Arm(Side side) {
			super("Рука (" + side.toString() + ")");
			this.side = side;
		}
	}

	static public class Leg extends BodyPart {
		private Side side;
	
		public Leg(Side side) {
			super("Нога (" + side.toString() + ")");
			this.side = side;
		}
	}

	static public class Torso extends BodyPart {
		public Torso() {
			super("Торс");
		}
	}

	static public class Cheeks extends BodyPart {
		public Cheeks() {
			super("Щеки");
		}
	}

	static public class Eye extends BodyPart {
		private Side side;
	
		public Eye(Side side) {
			super("Глаз (" + side.toString() + ")");
			this.side = side;
		}
	}

	static public class Forehead extends BodyPart {
		public Forehead() {
			super("Лоб");
		}
	}

	static public class Nose extends BodyPart {
		public Nose() {
			super("Нос");
		}
	}	

	static public class Head extends BodyPart {
		private BodyPart.Forehead forehead;
		private BodyPart.Eye eyeLeft, eyeRight;
		private BodyPart.Nose nose;
		private BodyPart.Cheeks cheeks;
	
		public Head(BodyPart.Forehead forehead, BodyPart.Eye eyeLeft, BodyPart.Eye eyeRight, BodyPart.Nose nose, BodyPart.Cheeks cheeks) {
			super("Голова");
			this.forehead = forehead;
			this.eyeLeft = eyeLeft;
			this.eyeRight = eyeRight;
			this.nose = nose;
			this.cheeks = cheeks;
		}
	
		public Head() {
			this(new BodyPart.Forehead(), new BodyPart.Eye(Side.LEFT), new BodyPart.Eye(Side.RIGHT), new BodyPart.Nose(), new BodyPart.Cheeks());
		}
	
		public BodyPart.Forehead getForehead() {
			return forehead;
		}
		public BodyPart.Eye getEye(Side side) {
			return side == Side.RIGHT ? eyeRight : eyeLeft;
		}
		public BodyPart.Nose getNose() {
			return nose;
		}
		public BodyPart.Cheeks getCheeks() {
			return cheeks;
		}
	
		@Override
		public String toString() {
			return super.toString() + " {" + forehead.toString() + ", " + eyeLeft + ", " + eyeRight + ", " + nose + ", " + cheeks + "}";
		}
	
		@Override
		public boolean equals(Object b) {
			if(!(b instanceof Head)) return false;
			Head a = (Head)b;
			return super.equals(a) && forehead.equals(a) && eyeLeft.equals(a.eyeLeft) && eyeRight.equals(a.eyeRight) && nose.equals(a.nose) && cheeks.equals(a.cheeks);
		}
	
		@Override
		public int hashCode() {
			return super.hashCode() ^ forehead.hashCode() ^ eyeLeft.hashCode() ^ eyeRight.hashCode() ^ nose.hashCode() ^ cheeks.hashCode();
		}
	}

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

	public void removeClothes(Clothes clothes) throws RuntimeException{
		double chance = Math.random();
		if (chance > 0.5)
			this.clothes.remove(clothes);
		else{
			throw new UnwearError("Не удалось снять " + this.clothes);
		}
		
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
