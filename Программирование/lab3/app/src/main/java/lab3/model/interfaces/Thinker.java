package lab3.model.interfaces;

import lab3.model.enums.HumanEffect;;

public interface Thinker {
	public String admit(HumanEffect effect);
	public String confirm(HumanEffect effect);
	public String remember(HumanEffect effect);
	public String feel(HumanEffect effect);
}