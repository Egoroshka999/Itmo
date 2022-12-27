package lab3.model.human;

import lab3.model.enums.Side;
import lab3.model.abstracts.BodyPart;
import lab3.model.human.Human;
import lab3.model.enums.HumanEffect;
import lab3.model.interfaces.Thinker;
import lab3.model.locations.Location;
import lab3.model.exceptions.OpenError;

public class Human extends Object implements Thinker {
	private String name;
	private BodyPart.Head head = new BodyPart.Head();
	private BodyPart.Torso torso = new BodyPart.Torso();
	private BodyPart.Arm armLeft = new BodyPart.Arm(Side.LEFT), armRight = new BodyPart.Arm(Side.RIGHT);
	private BodyPart.Leg legLeft = new BodyPart.Leg(Side.LEFT), legRight = new BodyPart.Leg(Side.RIGHT);

	public Human(String name, BodyPart.Head head, BodyPart.Torso torso, BodyPart.Arm armLeft, BodyPart.Arm armRight, BodyPart.Leg legLeft, BodyPart.Leg legRight) {
		this.name = name;
		this.head = head;
		this.torso = torso;
		this.armLeft = armLeft;
		this.armRight = armRight;
		this.legLeft = legLeft;
		this.legRight = legRight;
	}

	public Human(String name) {
		this(name, new BodyPart.Head(), new BodyPart.Torso(), new BodyPart.Arm(Side.LEFT), new BodyPart.Arm(Side.RIGHT), new BodyPart.Leg(Side.LEFT), new BodyPart.Leg(Side.RIGHT));
	}

	public String enter(Location location) throws OpenError{
		double chance = Math.random();
		if (chance > 0.5)
			return this.name + " входит в " + location.toString();
		else{
			System.out.println(this.name + " не смог повернуть ручку двери");
			throw new OpenError(this.name + " не смог повернуть ручку двери");
		}
			
	}

	public String secondenter(Location location){
		return this.name + " снова пробует повернуть ручки и входит в " + location.toString();
	}

	public String leave(Location location) {
		return this.name + " покидает " + location.toString();
	}

	public String admit(HumanEffect effect) {
		return this.name + " признается, что чувствует " + effect.toString();
	}

	public String confirm(HumanEffect effect) {
		return this.name + " подверждает, что тоже чувствует " + effect.toString();
	}

	public String remember(HumanEffect effect) {
		return this.name + " вспоминает, что чувствовал " + effect.toString();
	}

	public String feel(HumanEffect effect) {
		return this.name + " чувствует " + effect.toString();
	}

	public String lookLike(HumanEffect effect) {
		return this.name + " выглядит, будто чувствует " + effect.toString();
	}


	public BodyPart.Head getHead() {
		return head;
	}

	public BodyPart.Torso getTorso() {
		return torso;
	}

	public BodyPart.Arm getArm(Side side) {
		return side == Side.RIGHT ? armRight : armLeft;
	}

	public BodyPart.Leg getLeg(Side side) {
		return side == Side.RIGHT ? legRight : legLeft;
	}
	
	
	@Override
	public String toString() {
		return name + " {\n  " + head.toString() + "\n  " + torso.toString() + "\n  " + armLeft.toString() + "\n  " 
		+ armRight.toString() + "\n  " + legLeft.toString() + "\n  " + legRight.toString() + "\n}";
	}

	@Override
	public boolean equals(Object b) {
		if(!(b instanceof Human)) return false;
		Human a = (Human)b;
		return name.equals(a.name) && head.equals(a.head) && torso.equals(a.torso) && armLeft.equals(a.armLeft) && 
		armRight.equals(a.armRight) && legLeft.equals(a.legLeft) && legRight.equals(a.legRight);
	}

	@Override
	public int hashCode() {
		return name.hashCode() ^ head.hashCode() ^ torso.hashCode() ^ armLeft.hashCode() ^ armRight.hashCode() ^ legLeft.hashCode() ^ legRight.hashCode();
	}
}

