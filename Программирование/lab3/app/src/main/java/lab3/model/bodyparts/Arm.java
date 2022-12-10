package lab3.model.bodyparts;

import lab3.model.abstracts.BodyPart;
import lab3.model.enums.Side;

public class Arm extends BodyPart {
	private Side side;

	public Arm(Side side) {
		super("Рука (" + side.toString() + ")");
		this.side = side;
	}
}