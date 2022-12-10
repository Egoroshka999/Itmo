package lab3.model.bodyparts;

import lab3.model.abstracts.BodyPart;
import lab3.model.enums.Side;

public class Head extends BodyPart {
	private Forehead forehead;
	private Eye eyeLeft, eyeRight;
	private Nose nose;
	private Cheeks cheeks;

	public Head(Forehead forehead, Eye eyeLeft, Eye eyeRight, Nose nose, Cheeks cheeks) {
		super("Голова");
		this.forehead = forehead;
		this.eyeLeft = eyeLeft;
		this.eyeRight = eyeRight;
		this.nose = nose;
		this.cheeks = cheeks;
	}

	public Head() {
		this(new Forehead(), new Eye(Side.LEFT), new Eye(Side.RIGHT), new Nose(), new Cheeks());
	}

	public Forehead getForehead() {
		return forehead;
	}
	public Eye getEye(Side side) {
		return side == Side.RIGHT ? eyeRight : eyeLeft;
	}
	public Nose getNose() {
		return nose;
	}
	public Cheeks getCheeks() {
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