package lab3.model.bodyparts;

import lab3.model.abstracts.BodyPart;
import lab3.model.enums.Side;

public class Eye extends BodyPart {
	private Side side;

	public Eye(Side side) {
		super("Глаз (" + side.toString() + ")");
		this.side = side;
	}
}
