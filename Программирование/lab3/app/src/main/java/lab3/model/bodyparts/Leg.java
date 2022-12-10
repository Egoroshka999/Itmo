package lab3.model.bodyparts;

import lab3.model.abstracts.BodyPart;
import lab3.model.enums.Side;

public class Leg extends BodyPart {
	private Side side;

	public Leg(Side side) {
		super("Нога (" + side.toString() + ")");
		this.side = side;
	}
}