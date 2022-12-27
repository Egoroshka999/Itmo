package lab3.model.professions;

import lab3.model.enums.Side;
import lab3.model.abstracts.BodyPart;
import lab3.model.human.Human;

public class Ment extends Human {
	public Ment(String name, BodyPart.Head head, BodyPart.Torso torso, BodyPart.Arm armLeft, BodyPart.Arm armRight, BodyPart.Leg legLeft, BodyPart.Leg legRight) {
		super("Мент " + name, new BodyPart.Head(), new BodyPart.Torso(), new BodyPart.Arm(Side.LEFT), new BodyPart.Arm(Side.RIGHT), new BodyPart.Leg(Side.LEFT), new BodyPart.Leg(Side.RIGHT));
	}

	public Ment(String name) {
		this(name, new BodyPart.Head(), new BodyPart.Torso(), new BodyPart.Arm(Side.LEFT), new BodyPart.Arm(Side.RIGHT), new BodyPart.Leg(Side.LEFT), new BodyPart.Leg(Side.RIGHT));
	}
}