package lab3.model.human;

import lab3.model.bodyparts.Head;
import lab3.model.bodyparts.Torso;
import lab3.model.enums.Side;
import lab3.model.bodyparts.Arm;
import lab3.model.bodyparts.Leg;
import lab3.model.human.Human;
import lab3.model.enums.HumanEffect;
import lab3.model.interfaces.Thinker;
import lab3.model.locations.Location;

public class Human extends Object implements Thinker {
	private String name;
	private Head head = new Head();
	private Torso torso = new Torso();
	private Arm armLeft = new Arm(Side.LEFT), armRight = new Arm(Side.RIGHT);
	private Leg legLeft = new Leg(Side.LEFT), legRight = new Leg(Side.RIGHT);

	public Human(String name, Head head, Torso torso, Arm armLeft, Arm armRight, Leg legLeft, Leg legRight) {
		this.name = name;
		this.head = head;
		this.torso = torso;
		this.armLeft = armLeft;
		this.armRight = armRight;
		this.legLeft = legLeft;
		this.legRight = legRight;
	}

	public Human(String name) {
		this(name, new Head(), new Torso(), new Arm(Side.LEFT), new Arm(Side.RIGHT), new Leg(Side.LEFT), new Leg(Side.RIGHT));
	}

	public String enter(Location location) {
		return this.name + " входит в " + location.toString();
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


	public Head getHead() {
		return head;
	}

	public Torso getTorso() {
		return torso;
	}

	public Arm getArm(Side side) {
		return side == Side.RIGHT ? armRight : armLeft;
	}

	public Leg getLeg(Side side) {
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

