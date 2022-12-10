package lab3.model.professions;

import lab3.model.bodyparts.Head;
import lab3.model.bodyparts.Torso;
import lab3.model.enums.Side;
import lab3.model.bodyparts.Arm;
import lab3.model.bodyparts.Leg;
import lab3.model.human.Human;

public class Ment extends Human {
	public Ment(String name, Head head, Torso torso, Arm armLeft, Arm armRight, Leg legLeft, Leg legRight) {
		super("Мент " + name, new Head(), new Torso(), new Arm(Side.LEFT), new Arm(Side.RIGHT), new Leg(Side.LEFT), new Leg(Side.RIGHT));
	}

	public Ment(String name) {
		this(name, new Head(), new Torso(), new Arm(Side.LEFT), new Arm(Side.RIGHT), new Leg(Side.LEFT), new Leg(Side.RIGHT));
	}
}